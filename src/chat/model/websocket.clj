(ns chat.model.websocket)

(defn- add-subscriper [bindpoint channel]
  (add-watch bindpoint channel
             (fn [_channel _atom _old_json json]
               (send! channel json))))

(defn- sendMapToChannel [channel message]
  (log "Send to" channel message)
  (send! channel (jsonprs/generate-string message)))
