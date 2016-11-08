(ns chat.model.websocket
  (:use [org.httpkit.server])
  (:require [cheshire.core :as jsonprs]
            [chat.data.domain :as domain]))


(def bindpoints (atom {"global" (atom nil)}))
(def authUsers (atom {}))

(defn- extractServerWSPort [channel]
  (.substring (str channel) (+ (.lastIndexOf (str channel) ":") 1)))

(defn- create_room_bindpoint [room]
  (swap! bindpoints (fn [current_state] (assoc current_state room (atom nil)))))

(defn- add-subscriper [bindpoint channel]
  (add-watch bindpoint channel
               (fn [_channel _atom _old_json json]
                 (send! channel json))))

(defn- sendMapToChannel [channel message]
  (send! channel (jsonprs/generate-string message)))

(defn- beatify-message
  ([message datetime author]
  (let [ctime (.format (java.text.SimpleDateFormat. "HH/mm/ss") datetime)]
          (update message "text" (fn [old] (str author ": " old " ~ " ctime )))))
  ([message]
   (let [datetime (:time message)
        author    (:author message)
        text      (:text message)
        ctime     (.format (java.text.SimpleDateFormat. "HH/mm/ss") datetime)]
   (str author ": " text " ~ " ctime))))

(defmulti perform-ws-action (fn [message channel] (message "method")))

(defmethod perform-ws-action "message" [message channel] 
  (let [room (message "room")
        bus  (@bindpoints room)
        datetime  (java.util.Date.)
        author (@authUsers channel)
        text (message "text")
        jsmessage (jsonprs/generate-string (beatify-message message datetime author))]
       (println (str "Publinsh to " room " message " jsmessage))
       (domain/addMessage room {:author author :time datetime :text text})
       (reset! bus jsmessage))
  (message "text"))

(defmethod perform-ws-action "roomEnter" [message channel]
  (let [room (message "room")
        room_bindpoint (@bindpoints room)
        user (@authUsers channel)]
        (if room_bindpoint
          (add-subscriper room_bindpoint channel)
          (do
            (create_room_bindpoint room)
            (add-subscriper (@bindpoints room) channel))
        )
        (domain/addUserToRoom user room)
        (sendMapToChannel channel {:method "customStyle" :result (domain/getStyle room user)})
        (map beatify-message (domain/getMessagesByRoom room 1))))

(defmethod perform-ws-action "newRoom" [message chanel]
  (let [author (@authUsers chanel)
        participants (message "part")
        roomName (message "roomName")]
    (domain/addNewRoom roomName (conj participants author))
    (str roomName)))

(defmethod perform-ws-action "saveStyle" [message channel]
  (let [user (@authUsers channel)
        style (message "roomStyle")
        room (message "room")]
    (domain/saveCustomStyle room user style))
  (str "Ok"))


(defmethod perform-ws-action "nextPage" [message channel]
  (let [page (message "page")
        room (message "room")]
    (map beatify-message (domain/getMessagesByRoom room page))))

(defmethod perform-ws-action "roomLeave" [message channel]
  (let [room (message "room")
        room_bindpoint (@bindpoints room)]
          (remove-watch room_bindpoint channel))
  (str "Ok"))

(defmethod perform-ws-action "login" [message channel] 
  (let [login (message "userName")
        password (message "password")]
          (if (domain/isUserExist login password)
            (do
              (swap! authUsers (fn[current_state] (assoc current_state channel login)))
              (str "Success"))
            (str "Failed! Incorrect user name or password"))))


(defmethod perform-ws-action "registration" [message channel] 
  (let [login (message "userName")
        password (message "password")]
          (if (domain/isUserExist login)
            (str "Failed. User name duplication") 
            (do
              (domain/addUser login password)
              (swap! authUsers (fn[current_state] (assoc current_state channel login)))
              (str "Success")))))
                       
(defmethod perform-ws-action "roomList" [message channel]
  (map #(:roomName %) (domain/getAccessibleRoomsByUser (@authUsers channel))))

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
