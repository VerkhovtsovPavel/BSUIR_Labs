(ns chat.data.persistance
  (:refer-clojure :exclude [find sort])
  (:require [monger.core :as mcore]
            [monger.collection :as mcoll]
            [monger.operators :as ops]
            [monger.query :refer [paginate with-collection find fields sort limit skip]]))

(def conn (mcore/connect {:host "localhost" :post 27017}))
(def ^:dynamic db (mcore/get-db conn "chactics"))

(defn addUser
  [name password]
    (mcoll/insert db "users" {:_id name :password password}))

(defn isUserExist
   ([name]
    (seq(mcoll/find db "users" {:_id name})))
  ([name password]
   (seq(mcoll/find db "users" {:_id name :password password}))))

(defn getAccessibleRoomsByUser
  [user]
   (map #(:roomName %) (mcoll/find-maps db "chats" {:part {ops/$in [user]}})))

(defn getRoomToSubscribe [user]
  (mcoll/find-maps db "chats" {:isOpened true :part {ops/$nin [user]}}))

(defn addMessage
  [chat message]
    (mcoll/insert db chat message)
)

(defn getMessagesByRoom
  [room page newMessages]
  (monger.conversion/from-db-object
    (with-collection db room
      (find {})
      (sort {:time -1})
      (skip (+ (* page 20) newMessages))
      (limit 20)) true)
)

(defn addNewRoom
    [roomName participants]
  (mcoll/insert db "chats" {:roomName roomName :part participants}))

(defn saveCustomStyle
  [room user styleDesc]
  (mcoll/remove db "styles" {:room room :user user})
  (mcoll/insert db "styles" {:room room :user user :style styleDesc}))

(defn getStyle
  [room user]
  (mcoll/find-maps db "styles" {:room room :user user}))

(defn subscribe [room user]
  (mcoll/update db "chats" {:roomName room} {ops/$addToSet {:part user}}))

(defn unsubscribe [room user]
  (mcoll/update db "chats" {:roomName room} {ops/$pull {:part user}}))
