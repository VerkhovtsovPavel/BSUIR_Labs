(ns chat.model.messages
  (:require [cheshire.core :as jsonprs]
            [chat.data.persistance :as domain])
  (:use [chat.model.rooms]
        [chat.model.users]
        [chat.util.logger]
        [chat.model.ws-actions]))

(defn- beatify-message
  ([message datetime author]
   (let [ctime (.format (java.text.SimpleDateFormat. "HH/mm/ss") datetime)]
     (update message "text" (fn [old] (str author ": " old " ~ " ctime)))))
  ([message]
   (let [datetime (:time message)
         author (:author message)
         text (:text message)
         ctime (.format (java.text.SimpleDateFormat. "HH/mm/ss") datetime)]
     (str author ": " text " ~ " ctime))))

(defmethod perform-ws-action "message" [message channel]
  (let [room (message "room")
        bus (@bindpoints room)
        datetime (java.util.Date.)
        author (@authUsers channel)
        text (message "text")
        jsmessage (jsonprs/generate-string (beatify-message message datetime author))]
    (log (str "Publinsh to " room) jsmessage)
    (domain/addMessage room {:author author :time datetime :text text})
    (reset! bus jsmessage))
  (message "text"))

(defmethod perform-ws-action "nextPage" [message channel]
  (let [page (message "page")
        room (message "room")]
    (map beatify-message (domain/getMessagesByRoom room page))))
