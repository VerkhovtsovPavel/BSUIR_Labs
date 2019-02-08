(ns chat.model.search
  (:use
    [chat.model.users]
    [chat.model.ws-actions])
  (:require [chat.data.searchDSL :as sDSL]))

(defmethod perform-ws-action "search" [message channel]
  (let [user (@authUsers channel)
        query (message "query")]
    (sDSL/performQuery query user message)))