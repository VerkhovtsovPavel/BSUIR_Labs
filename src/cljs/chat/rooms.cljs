(ns cljs.chat.client.rooms
  (:require [cljs.chat.client.utils.htmlUtils :as hutil])
  (:use [cljs.chat.client.websocket]))

(def room (currentRoom) "global")                           ;TODO Remove

(defn addRoom [list roomId]
  (let [listItem (.createElement js/document "li")]
    (aset listItem "id" roomId)
    (set! (.-onclick listItem)
          (fn [e]
            (let [newRoom (.-id (.-target e))
                  output (hutil/getById "output")
                  currentRoomTitle (hutil/getById "CurrentRoomName")]
              (send {:method "roomLeave" :room room})
              (send {:method "roomEnter" :room newRoom})
              (set! room newRoom)
              (aset currentRoomTitle "innerHTML" room)
              (hutil/removeChilds output))))

    (.appendChild listItem (.createTextNode js/document roomId))
    (.appendChild list listItem)))


(defn builtRoomList [room_list]
  (let [list (hutil/getById "rlist")]
    (hutil/removeChilds list)
    (doseq [l_room room_list] (addRoom list l_room))))
