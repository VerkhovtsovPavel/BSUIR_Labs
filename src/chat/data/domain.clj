(ns chat.data.domain
  (:refer-clojure :exclude [find])
  (:require [monger.core :as mcore]
            [monger.collection :as mcoll]
            [monger.operators :as ops]
            [monger.query :refer [paginate with-collection find]]))

(def conn (mcore/connect {:host "localhost" :post 27017}))
(def db (mcore/get-db conn "chactics"))

;(mcoll/insert db "chats" {:roomName "Secret Chat" :patr ["Pavel" "Axelandra"]})
;(mcoll/find-maps db "chats")

(defn addUser
  [name password]
    (mcoll/insert db "users" {:name name :password password :rooms []})
)

(defn isUserExist
   ([name]
    (seq(mcoll/find db "users" {:name name})))
  ([name password]
   (seq(mcoll/find db "users" {:name name :password password}))))

(defn addUserToRoom
  [user room]
    (mcoll/update db "users" {:name user} {ops/$push {:rooms room}})
)

(defn getAccessibleRoomsByUser
  [user]
    (mcoll/find-maps db "chats" {ops/$or [{:part []}
                                 {:part {ops/$in [user]}}]})
)

(defn addMessage
  [chat message]
    (mcoll/insert db chat message)
  ;TODO Save in DB text, time and authors
)

(defn getMessagesByRoom
  [room page]
  (monger.conversion/from-db-object
  (with-collection db room
    (find {})
    (paginate :page page :per-page 20)) true)
  ;TODO Add back order
)

(defn addNewRoom
    [roomName participants]
  (mcoll/insert db "chats" {:name roomName :part participants}))

(defn saveCustomStyle
  [room user styleDesc]
  (mcoll/insert db "styles" {:room room :user user :style styleDesc}))

(defn getStyle
  [room user]
  (mcoll/find-maps db "styles" {:room room :user user}))


;"Add and extend protocol"
