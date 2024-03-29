(ns chat.model.dispatcher
  (:use [org.httpkit.server]
        [chat.model.ws-actions]
        [chat.model.rooms]
        [chat.util.logger]
        [chat.model.websocket]
        [chat.model.messages]
        [chat.model.users]
        [chat.model.search]
        [chat.model.style])
  (:require [cheshire.core :as jsonprs]))

(defn getResponse
  [message channel]
  (log "Request from" channel message)
  (let [json (jsonprs/parse-string message)]
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
                                (sendMapToChannel channel responce))))))
