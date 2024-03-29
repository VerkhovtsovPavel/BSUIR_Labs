(ns chat.model.rooms
  (:use [chat.model.websocket]
        [chat.model.users]
        [chat.model.ws-actions]
        [org.httpkit.server])
  (:require [chat.data.persistance :as domain]))

(def bindpoints (atom {"global" (atom nil)}))

(defn create_room_bindpoint [room]
  (swap! bindpoints (fn [current_state] (assoc current_state room (atom nil)))))

(defn- add-subscriper [bindpoint channel]
  (add-watch bindpoint channel
             (fn [_channel _atom _old_val val]
               (send! channel val))))

(defmethod perform-ws-action "roomEnter" [message channel]
  (let [room (message "room")
        room_bindpoint (@bindpoints room)
        user (@authUsers channel)
        room_list (domain/getAccessibleRoomsByUser user)]
    (if (some #{room} room_list)
      (do
        (if room_bindpoint
          (add-subscriper room_bindpoint channel)
          (do
            (create_room_bindpoint room)
            (add-subscriper (@bindpoints room) channel))
          )
        (str "Ok")
        )
      (str "Illegal access"))))

(defmethod perform-ws-action "unsubscribe" [message channel]
  (let [room (message "room")
        user (@authUsers channel)]
    (domain/unsubscribe room user)
  (str room)))

(defmethod perform-ws-action "subscribe" [message channel]
  (let [room (message "room")
        user (@authUsers channel)]
    (domain/subscribe room user)
    (sendMapToChannel channel {:method "newRoom" :result room}))
  (str "Ok"))

(defmethod perform-ws-action "newRoom" [message channel]
  (let [author (@authUsers channel)
        participants (message "part")
        roomName (message "roomName")]
    (domain/addNewRoom roomName (conj participants author))
    (doseq [[userChannel authUser] @authUsers]
      ((fn [user]
         (if (some #{user} participants)
           (sendMapToChannel userChannel {:method "newRoom" :result roomName}))
         ) authUser)
      )
    (str roomName)))

(defmethod perform-ws-action "roomLeave" [message channel]
  (let [room (message "room")
        room_bindpoint (@bindpoints room)]
    (remove-watch room_bindpoint channel))
  (str "Ok"))

(defmethod perform-ws-action "roomList" [message channel]
  (domain/getAccessibleRoomsByUser (@authUsers channel)))

(defmethod perform-ws-action "roomListToSubscibe" [message channel]
  (map #(:roomName %) (domain/getRoomToSubscribe (@authUsers channel))))