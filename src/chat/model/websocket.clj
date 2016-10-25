(ns chat.model.websocket
  (:use org.httpkit.server))


(def bindpoint (atom nil))

(defn handler [request]
  (with-channel request channel
    (on-close channel (fn [status]
                        (remove-watch bindpoint channel)))
    (on-receive channel (fn [message]
                          (reset! bindpoint message)))
    (add-watch bindpoint channel
               (fn [_channel _atom _old_json json]
                 (send! channel json)))))




