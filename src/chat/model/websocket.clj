(ns chat.model.websocket
  (:use org.httpkit.server)
  (:require [cheshire.core :as jsonprs]))

(defn- create_room_bindpoint [room]
  (dosync
    alter bindpoint assoc {room (atom nil)}))

(def bindpoint ref{"global" (atom nil)})

(defmulti perform-ws-action (fn [message channel] (message "method")))

(defmethod perform-ws-action "connect"
 [message channel]
  (let [room (message "room")
        room_bindpoint (bindpoint room)]
        (if(!room_bindpoint)
          (do
            (create_room_bindpoint room)
            (add-subscriper (bindpoint room) channel))
         (add-subscriper room_bindpoint channel))

  )
)

(defmethod perform-ws-action "disconnect" [message channel] (str "Leave " (:room message)))
(defmethod perform-ws-action "message" [message channel] (reset! bindpoint (jsonprs/generate-string message))(message "text"))

(defn- injectServerWSPort [channel]
  (.substring (str channel) (+ (.lastIndexOf (str channel) ":") 1))
)

(defn getResponse
  [message channel]
  (println (str "Request from " (injectServerWSPort channel) " with " message))
  (let[json (jsonprs/parse-string message)]
    (jsonprs/generate-string
      {:method (json "method")
       :result (perform-ws-action json channel)}))
)

(defn handler [request]
  (with-channel request channel
    (on-close channel (fn [status]
                        (remove-watch bindpoint channel)))
    (on-receive channel (fn [message]
                          (let [responce (getResponse message channel)]
                              (println (str "Responce for " (injectServerWSPort channel) " with " responce))    
                              (send! channel responce))))

    (add-watch bindpoint channel
               (fn [_channel _atom _old_json json]
                 (send! channel json)))))