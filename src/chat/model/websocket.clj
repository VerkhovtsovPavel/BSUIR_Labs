(ns chat.model.websocket
  (:use org.httpkit.server)
  (:require [cheshire.core :as jsonprs]))


(def bindpoints (atom {"global" (atom nil)}))

(defn- extractServerWSPort [channel]
  (.substring (str channel) (+ (.lastIndexOf (str channel) ":") 1)))

(defn- create_room_bindpoint [room]
  (swap! bindpoints (fn [current_state] (assoc current_state {room (atom nil)}))))

(defn- add-subscriper [bindpoint channel]
  (add-watch bindpoint channel
               (fn [_channel _atom _old_json json]
                 (send! channel json))))

(defn- beatify-message [message channel]
  (let [datetime (java.util.Date.)
        ctime (.format (java.text.SimpleDateFormat. "HH/mm/ss") datetime)]
          (update message "text" (fn [old](str (extractServerWSPort channel) ": " (message "text") " /\\ " ctime )))))

(defmulti perform-ws-action (fn [message channel] (message "method")))

(defmethod perform-ws-action "connect"
 [message channel]
  (let [room (message "room")
        room_bindpoint (@bindpoints room)]
        (if room_bindpoint
          (add-subscriper room_bindpoint channel)
          (do
            (create_room_bindpoint room)
            (add-subscriper (@bindpoints room) channel))
         )
  )
  (str "Ok")
)

(defmethod perform-ws-action "disconnect" [message channel] 
  (let [room (message "room")
        room_bindpoint (@bindpoints room)]
          (remove-watch room_bindpoint channel)))

(defmethod perform-ws-action "message" [message channel] 
  (let [room (message "room")
        bus  (@bindpoints room)
       jsmessage (jsonprs/generate-string (beatify-message message channel))]
       (println (str "Pushinsh to " room " message " jsmessage)) 
       (reset! bus jsmessage))
  (message "text"))

(defn getResponse
  [message channel]
  (println (str "Request from " (extractServerWSPort channel) " with " message))
  (let[json (jsonprs/parse-string message)]
    (jsonprs/generate-string
      {:method (json "method")
       :result (perform-ws-action json channel)}))
)

(defn handler [request]
  (with-channel request channel
    (on-close channel (fn [status]
                        (doseq [[k v] @bindpoints] (remove-watch v channel))))
    (on-receive channel (fn [message]
                          (let [responce (getResponse message channel)]
                              (println (str "Responce for " (extractServerWSPort channel) " with " responce))
                              (send! channel responce))))))

