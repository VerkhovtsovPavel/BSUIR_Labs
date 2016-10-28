(ns chat.model.rooms)

(defmethod perform-ws-action "roomEnter" [message channel]
  (let [room (message "room")
        room_bindpoint (@bindpoints room)]
        (if room_bindpoint
          (add-subscriper room_bindpoint channel)
          (do
            (create_room_bindpoint room)
            (add-subscriper (@bindpoints room) channel))
         ))
  (str "Ok")
)

(defmethod perform-ws-action "roomLeave" [message channel] 
  (let [room (message "room")
        room_bindpoint (@bindpoints room)]
          (remove-watch room_bindpoint channel)))

(defmethod perform-ws-action "roomList" [message channel])