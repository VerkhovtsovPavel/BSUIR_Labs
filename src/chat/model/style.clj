(ns chat.model.style
  (:require [chat.data.persistance :as domain])
  (:use [chat.model.ws-actions]
        [chat.model.users]
        [chat.model.websocket]))

(defmethod perform-ws-action "roomStyle" [message channel]
  (let [room (message "room")
        user (@authUsers channel)]
    (sendMapToChannel channel {:method "roomStyle" :result (:style (first (domain/getStyle room user)) "")})))

(defmethod perform-ws-action "saveStyle" [message channel]
  (let [user (@authUsers channel)
        style (message "roomStyle")
        room (message "room")]
    (domain/saveCustomStyle room user style))
  (str "Ok"))
