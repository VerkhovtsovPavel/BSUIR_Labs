(ns chat.data.domain
  (:refer-clojure :exclude [find])
  (:require [monger.core :as mcore]
            [monger.collection :as mcoll]
            [monger.operators :refer :all]
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
    (mcoll/find db "users" {:name name}))
  ([name password]
    (mcoll/find db "users" {:name name :password password}))) ; Use some check to return bool or empty collection

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
  (monger.conversion/from-db-object
  (with-collection db room
    (find {})
    (paginate :page page :per-page 20)) true)
)



;"Add and extend protocol"
