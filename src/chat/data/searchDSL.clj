(ns chat.data.searchDSL
  (:require [monger.collection :as mcoll]
            [monger.operators :as ops]
            [chat.util.dateUtils :as dateUtils]
            [chat.data.persistance :as domain]))

(def ^:dynamic *user* "")
(def ^:dynamic *message* {})

(defmacro defilter [name body]
  (let [paramName (last (clojure.string/split (str name) #"-"))
        param (symbol paramName)]
    `(defn ~name [~param] ~@body)))

(defmacro defextractor [name source]
  `(defn ~name [& filters#]
     (let [final_filter# (apply merge filters#)]
       (flatten (map (fn
                       [current_room#]
                       (map #(assoc % :room current_room#) (mcoll/find-maps domain/db current_room# final_filter#))) ~source)))))

(defilter with-text ({:text {ops/$regex (str ".*" text ".*")}}))
(defilter for-period ((let [lowDate (dateUtils/minusDays period)] {:time {ops/$gte lowDate}})))
(defilter with-sender ({:author sender}))

(defextractor current [(*message* "room")])
(defextractor all (distinct (chat.data.persistance/getUserRooms *user*)))


(defn performQuery [query user message]
  (binding [*ns* (:ns (meta #'performQuery))
            *user* user
            *message* message]
    (try
      (map #(dissoc % :_id) (load-string query))
      (catch Exception e# (str "Invalid search query")))))



