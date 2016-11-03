(ns chat.data.domain
  (:require [monger.core :as mcore]
            [monger.collection :as mcoll]
            [monger.operators :refer :all]
            [monger.query :refer :all]))

(def conn (mcore/connect {:host "localhost" :post 27017}))
(def db (mcore/get-db conn "chactics"))

;(mcoll/insert db "chats" {:roomName "Secret Chat" :patr ["Pavel" "Axelandra"]})
;(mcoll/find-maps db "chats")

(defn addUser
  [name password]
    (mcoll/insert db "users" {:name name :password password :rooms []})
)

(defn isUserExist
  [name password]
    (mcoll/find db "users" {:name name :password password})
  [name]
    (mcoll/find db "users" {:name name})
) ; Use some check to return bool or empty collection

(defn addUserToRoom
  [user room]
    (mcoll/update db "users" {:name user} {$push {:rooms room}})
)

(defn getAccessibleRoomsByUser
  [user]
    (mcoll/find-maps db "chats" {$or [{:patr []}
                                 {:patr {$in [user]}}]})
)

(defn addMessage
  [chat message]
    (mcoll/insert db chat message)
)

(defn getMessagesByRoom
  [room page]
  (with-collection db room
    (mcoll/find-maps)
    (paginate :page page :per-page 20))
)



;"Add and extend protocol"
