(ns chatics.data
  (:require [monger.core :as mcore]
            [monger.collection :as mcoll]))

(def conn (mcore/connect {:host "localhost" :post 27017}))
(def db (mcore/get-db conn "chactics"))

(mcoll/insert db "chats" {:roomName "Secret Chat" :patr ["Pavel" "Axelandra"]})
(mcoll/find-maps db "chats")

(defn addUser [name password]
  mcoll/insert db "users" {:name name :password password}
)

(defn isUserExist
  [name password]
    mcoll/find db "users" {:name name :password password}
  [name]
    mcoll/find db "users" {:name name})

(defn addUserToRoom[user room]
  )


(defn addMessage [chat message]
  mcoll/insert db chat message)



