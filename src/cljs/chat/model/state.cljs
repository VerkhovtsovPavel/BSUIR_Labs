(ns cljs.chat.client.model.state
  (:require [alandipert.storage-atom :refer [local-storage]]))

(def state (local-storage (atom {}) :state))
(reset! alandipert.storage-atom/storage-delay 100)

(def page (atom 1))

(defn currentRoom (:room @state "global"))

(defn getStyle
  (let [room (currentRoom)
        styles (:styles @state)]
    (styles room)))

(defn getCachedMessages (:messages @state))

(defn cacheMessage [message])

(defn cacheStyle [style]
  (swap! state update-in [:styles (currentRoom)] style))

(defn changeRoom [newRoom]
  (swap! state update :room newRoom))

