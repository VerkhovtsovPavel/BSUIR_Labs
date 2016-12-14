(ns cljs.chat.client.rooms
  (:require [cljs.chat.client.utils.htmlUtils :as hutil]
            [cljs.chat.client.model.state :as state]
            [cljs.chat.client.websocket :as ws]))

(defn addRoom [list roomId]
  (let [listItem (.createElement js/document "li")]
    (aset listItem "id" roomId)
    (set! (.-onclick listItem)
          (fn [e]
            (let [newRoom (.-id (.-target e))
                  output (hutil/getById "output")
                  currentRoomTitle (hutil/getById "CurrentRoomName")]
              (ws/send {:method "roomLeave" :room (state/currentRoom)})
              (ws/send {:method "roomEnter" :room newRoom})
              (state/changeRoom newRoom)
              (aset currentRoomTitle "innerHTML" newRoom)
              (hutil/removeChilds output))))

    (.appendChild listItem (.createTextNode js/document roomId))
    (.appendChild list listItem)))

(defn removeRoom [list roomId]
  (let [room (hutil/getById roomId)]
    (.removeChild list room)))


(defn builtRoomList [room_list]
  (let [list (hutil/getById "rlist")]
    (hutil/removeChilds list)
    (doseq [l_room room_list] (addRoom list l_room))))
