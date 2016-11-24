(ns chat.model.search
  (:use
    [chat.model.users]
    [chat.model.ws-actions])
  (:require [chat.data.searchDSL :as sDSL]
            [chat.data.persistance :as domain]))

(defmethod perform-ws-action "search" [message channel]
  (let [room (message "room")
        user (@authUsers channel)
        room_list (distinct (domain/getUserRooms user))
        query (message "query")]
    (sDSL/performQuery query room room_list)))