(ns chat.util.logger
  (:import (java.io FileWriter))
  (:use [chat.model.users]))

(def logAgent (agent (FileWriter. "chat.log" true)))

(defn- extractServerWSPort [channel]
  (.substring (str channel) (+ (.lastIndexOf (str channel) ":") 1)))

(defn- logMessage [out msg]
  (.write out (str msg "\n"))
  (.flush out)
  (println msg)
  out)

(defn log
  ([prefix message]
   (let [datetime (java.util.Date.)
         msg (str "[" datetime "] " prefix " with message " message)]
     (clojure.core/send-off logAgent logMessage msg)))
  ([prefix channel message]
   (let [port (extractServerWSPort channel)
         user (@authUsers channel "Unautorized")
         datetime (java.util.Date.)
         msg (str "[" datetime "] " prefix " " port "(" user ") with " message)]
     (clojure.core/send-off logAgent logMessage msg)))
)
