(ns chat.model.model
  (:use [org.httpkit.server]
        [chat.model.ws-actions]
        [chat.model.logger]
        [chat.model.rooms]
        [chat.model.websocket]
        [chat.model.users])
  (:require [cheshire.core :as jsonprs]
            [chat.data.persistance :as domain]
            [chat.data.searchDSL :as sDSL]))

(defn getResponse
  [message channel]
  (log "Request from" channel message)
  (let [json (jsonprs/parse-string message)]                ;TODO Maybe keywordise
    {:method (json "method")
     :result (perform-ws-action json channel)}))

(defn handler [request]
  (with-channel request channel
                (on-close channel
                          (fn [status]
                            (doseq [[k v] @bindpoints] (remove-watch v channel))))
                (on-receive channel
                            (fn [message]
                              (let [responce (getResponse message channel)]
                                ;(log "Responce for" channel responce)
                                (sendMapToChannel channel responce))))))
