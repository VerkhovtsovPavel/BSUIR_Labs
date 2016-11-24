(ns chat.model.ws-actions)

(defmulti perform-ws-action (fn [message channel] (message "method")))