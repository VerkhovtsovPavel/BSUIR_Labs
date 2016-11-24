(ns cljs.chat.client.model.state
  (:require [alandipert.storage-atom :refer [local-storage]]))

(def state (local-storage (atom {}) :state))
(reset! alandipert.storage-atom/storage-delay 100)

(def page (atom 1))

(defn getStyle
  (let [room (:room @state)
        styles (:styles @state)]
  (room styles)))

(defn currentRoom (:room @state "global")) ;TODO Save value if to exist
(defn getCachedMessages (:messages @state))

(defn cacheMessage [message])
(defn cacheStyle [style]
  (let [room (:room @state)]
   ; (swap! state assoc :styles )
  ))
(defn changeRoom [newRoom])

