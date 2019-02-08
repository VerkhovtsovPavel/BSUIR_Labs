(ns chat.model.users
  (:use [chat.model.ws-actions])
  (:require [chat.data.persistance :as domain]))

(def authUsers (atom {}))

(defmethod perform-ws-action "login" [message channel]
  (let [login (message "userName")
        password (message "password")]
    (if (domain/isUserExist login password)
      (do
        (swap! authUsers (fn [current_state] (assoc current_state channel login)))
        (str "Success"))
      (str "Failed! Incorrect user name or password"))))


(defmethod perform-ws-action "registration" [message channel]
  (let [login (message "userName")
        password (message "password")]
    (if (domain/isUserExist login)
      (str "Failed. User name duplication")
      (do
        (domain/addUser login password)
        (domain/subscribe "global" login)
        (swap! authUsers (fn [current_state] (assoc current_state channel login)))
        (str "Success")))))
