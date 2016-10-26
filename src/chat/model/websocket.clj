(ns chat.model.websocket
  (:use org.httpkit.server))


(def bindpoint (atom nil))

(defn handler [request]
  (with-channel request channel
    (on-close channel (fn [status]
                        (remove-watch bindpoint channel)))
    (on-receive channel (fn [message]
                          (send! channel (response message channel))))

    (add-watch bindpoint channel
               (fn [_channel _atom _old_json json]
                 (send! channel json)))))


(defn- response
  [message channel]
  (let[json json/parse-string message]
    (json/generate-string
      {:method (json "method")
      :result (perform-ws-action json channel)}))
  )


(defmulti perform-ws-action (fn [message] (:method message)))

(defmethod perform-ws-action :connect [message channel] (str "Young " (:name person)))
(defmethod perform-ws-action :disconnect [message channel] (:name person))
(defmethod perform-ws-action :new_message [message channel] (str "Old " (:name person)))


;reset! bindpoint message
