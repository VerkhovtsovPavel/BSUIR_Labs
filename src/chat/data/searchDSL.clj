(ns chat.data.searchDSL
  (:refer-clojure :exclude [find sort])
  (:require [monger.core :as mcore]
            [monger.collection :as mcoll]
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
        (reduce merge (map (fn
                             [current_room#]
                             (seq (map #(assoc % :room current_room#) (mcoll/find-maps domain/db current_room# final_filter#)))) ~source)))))

(defilter with-text ({:text {ops/$regex (str ".*" text ".*")}}))
(defilter for-period ((let [lowDate (dateUtils/minusDays period)] {:time {ops/$gte lowDate}})))
(defilter with-sender ({:author sender}))

(defextractor current [(*message* "room")])

;(defextractor all (distinct (chat.data.persistance/getUserRooms *user*)))
;(defextractor current room)

;(defmacro all [& filters]
;  `(let [conn# (mcore/connect {:host "localhost" :post 27017})
;         db# (mcore/get-db conn# "chactics")
;         final_filter# (merge ~@filters)]
;     (reduce merge (map (fn
;                          [current_room#]
;                          (map #(assoc % :room current_room#) (mcoll/find-maps db# current_room# final_filter#))) *room_list*))))
;

(clojure.core/defn
  all
  [& filters__5553__auto__]
  (clojure.core/let
    [final_filter__5554__auto__ (clojure.core/apply clojure.core/merge filters__5553__auto__)]
    (clojure.core/reduce
      clojure.core/merge
      (clojure.core/map
        (clojure.core/fn
          [current_room__5555__auto__]
          (seq(clojure.core/map
            (fn* [p1__5552__5556__auto__] (clojure.core/assoc p1__5552__5556__auto__ :room current_room__5555__auto__))
              (monger.collection/find-maps chat.data.persistance/db current_room__5555__auto__ final_filter__5554__auto__))))
        (distinct (chat.data.persistance/getUserRooms *user*))))))

;(defn current [& filters]
;  (let [final_filter (apply merge filters)]
;     (mcoll/find-maps domain/db *room* final_filter)))



(defn performQuery [query user message]
  (binding [*ns* (:ns (meta #'performQuery))
            *user* user
            *message* message]
    ;(try
    (map #(dissoc % :_id) (load-string query))))
;  (catch Exception e# (str "Invalid search query")))))


(comment
  (defn performQuery [query message user]
    (binding [*ns* (:ns (meta #'performQuery))]
      (try
      (load-string query)))
   (catch Exception e# (str "Invalid search query"))))



