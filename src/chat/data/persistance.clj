(ns chat.data.persistance
  (:refer-clojure :exclude [find sort])
  (:require [monger.core :as mcore]
            [monger.collection :as mcoll]
            [monger.operators :as ops]
            [monger.query :refer [paginate with-collection find fields sort]]))

(def conn (mcore/connect {:host "localhost" :post 27017}))
(def db (mcore/get-db conn "chactics"))

;(mcoll/insert db "chats" {:roomName "global" :patr []})
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
    (mcoll/update db "users" {:name user} {ops/$addToSet {:rooms room}})
)

(defn getAccessibleRoomsByUser
  [user]
    (mcoll/find-maps db "chats" {ops/$or [{:part []}
                                 {:part {ops/$in [user]}}]})
)

(defn addMessage
  [chat message]
    (mcoll/insert db chat message)
)

(defn getMessagesByRoom
  [room page]
  (monger.conversion/from-db-object
    (with-collection db room
      (find {})
      (sort {:time -1})
      (paginate :page page :per-page 20)) true)
)

(defn addNewRoom
    [roomName participants]
  (mcoll/insert db "chats" {:roomName roomName :part participants}))

(defn saveCustomStyle
  [room user styleDesc]
  (mcoll/insert db "styles" {:room room :user user :style styleDesc}))

(defn getStyle
  [room user]
  (mcoll/find-maps db "styles" {:room room :user user}))

(defn getUserRooms [user]
  (first (map #(:rooms %)(mcoll/find-maps db "users" {:name user}))))


;"Add and extend protocol"
