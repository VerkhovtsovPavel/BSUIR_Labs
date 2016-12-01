(ns cljs.chat.client.model.state
  (:require [alandipert.storage-atom :refer [local-storage]]))

(def state (local-storage (atom {:room "global" :styles {}}) :state))
(reset! alandipert.storage-atom/storage-delay 100)

(def page (atom 1))

(defn currentRoom [] (@state :room))

(defn getStyle []
  (let [room (currentRoom)
        styles (:styles @state)]
    (styles room)))

(defn cacheStyle [style]
  (swap! state update-in [:styles (currentRoom)] (fn[old_value] style)))

(defn changeRoom [newRoom]
  (swap! state update :room (fn [old_value] newRoom)))

