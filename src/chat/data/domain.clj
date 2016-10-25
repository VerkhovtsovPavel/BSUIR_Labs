(ns chatics.data
  (:require [monger.core :as mcore]
            [monger.collection :as mcoll]
            [monger.operators :refer :all]))

(def conn (mcore/connect {:host "localhost" :post 27017}))
(def db (mcore/get-db conn "chactics"))

(mcoll/insert db "chats" {:roomName "Secret Chat" :patr ["Pavel" "Axelandra"]})
(mcoll/find-maps db "chats")

(defn addUser
  [name password]
    (mcoll/insert db "users" {:name name :password password :rooms []})
)

(defn isUserExist
  [name password]
    (mcoll/find db "users" {:name name :password password})
  [name]
    (mcoll/find db "users" {:name name})
)

(defn addUserToRoom
  [user room]
    (mc/update db "users" {:name user} {$push {:rooms room}})
)

(defn getAccessibleRoomsByUser
  [user]
   (mc/find-map db "chats" {$or [{:patr []}
                                 {:patr {$in [user]}}]})
)

(defn addMessage
  [chat message]
    (mcoll/insert db chat message)
)

(defn getMessagesByRoom
  [room page]
  (mcoll/find db room)(paginate :page page :per-page 20)
)



;"Add and extend protocol"
