(ns chat.model.websocket
  (:require [cheshire.core :as jsonprs])
  (:use [chat.util.logger]
        [org.httpkit.server]))

(defn sendMapToChannel [channel message]
  (log "Send to" channel message)
  (send! channel (jsonprs/generate-string message)))
