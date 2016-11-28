(ns cljs.chat.client.rooms
  (:require [cljs.chat.client.utils.htmlUtils :as hutil]
            [cljs.chat.client.model.state :as state])
  (:use [cljs.chat.client.websocket]))

(defn addRoom [list roomId]
  (let [listItem (.createElement js/document "li")]
    (aset listItem "id" roomId)
    (set! (.-onclick listItem)
          (fn [e]
            (let [newRoom (.-id (.-target e))
                  output (hutil/getById "output")
                  currentRoomTitle (hutil/getById "CurrentRoomName")]
              (send {:method "roomLeave" :room (state/currentRoom)})
              (send {:method "roomEnter" :room newRoom})
              (state/changeRoom newRoom)
              (aset currentRoomTitle "innerHTML" newRoom)
              (hutil/removeChilds output))))

    (.appendChild listItem (.createTextNode js/document roomId))
    (.appendChild list listItem)))


(defn builtRoomList [room_list]
  (let [list (hutil/getById "rlist")]
    (hutil/removeChilds list)
    (doseq [l_room room_list] (addRoom list l_room))))
