(ns cljs.chat.client.model
  (:require [alandipert.storage-atom :refer [local-storage]]))
;TODO Import lib

(def state (local-storage (atom {}) :state))
(reset! alandipert.storage-atom/storage-delay 100)

(defn getStyle
  (let [room (:room @state)
        styles (:styles @state)]
  (room styles)))

(defn currentRoom (:room @state))
(defn getCachedMessages (:messages @state))

(defn cacheMessage [message])
(defn cacheStyle [style])
(defn changeRoom [newRoom])



