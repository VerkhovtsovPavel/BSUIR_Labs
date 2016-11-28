(ns chat.data.searchDSL
  (:refer-clojure :exclude [find sort])
  (:require [monger.core :as mcore]
            [monger.collection :as mcoll]
            [monger.operators :as ops]
            [chat.util.dateUtils :as dateUtils]
            [chat.data.persistance :as domain]))

(def ^:dynamic *room* "")
(def ^:dynamic *room_list* "")

(defmacro defilter [name body]
  (let [paramName (last (clojure.string/split (str name) #"-"))
        param (symbol paramName)]
    `(defn ~name [~param] ~@body)))

(defmacro defextractor [name source]
  (let [conn# (mcore/connect {:host "localhost" :post 27017})
        db# (mcore/get-db conn# "chactics")]
  `(defn ~name [& filters#]
     (let [final_filter# (merge @filters#)]
       (println final_filter#)
        (reduce merge (map (fn
                             [current_room#]
                             (map #(assoc % :room current_room#) (mcoll/find-maps ~db# current_room# final_filter#))) ~source))))))

(defilter with-text ({:text {ops/$regex (str ".*" text ".*")}}))

(defilter for-period ((let [lowDate (dateUtils/minusDays period)] {:time {ops/$gte lowDate}})))

(defilter with-sender ({:author sender}))


;(defextractor current 'room)

;(defextractor all (distinct (chat.data.persistance/getUserRooms user)))
;(defextractor current room)

(defmacro all [& filters]
  `(let [conn# (mcore/connect {:host "localhost" :post 27017})
         db# (mcore/get-db conn# "chactics")
         final_filter# (merge ~@filters)]
     (reduce merge (map (fn
                          [current_room#]
                          (map #(assoc % :room current_room#) (mcoll/find-maps db# current_room# final_filter#))) *room_list*))))


(defmacro current [& filters]
  (let [conn (mcore/connect {:host "localhost" :post 27017})
         db (mcore/get-db conn "chactics")
         final_filter (apply merge filters)]
     (mcoll/find-maps db *room* final_filter)))


(defn performQuery [query room room_list]
  (binding [*ns* (:ns (meta #'performQuery))
            *room* room
            *room_list* room_list]
    ;(try
    (load-string query)))
;  (catch Exception e# (str "Invalid search query")))))


(comment
  (defn performQuery [query message user]
    (binding [*ns* (:ns (meta #'performQuery))]
      (try
      (load-string query)))
   (catch Exception e# (str "Invalid search query"))))



