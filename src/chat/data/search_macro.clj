(ns chat.data.search-macro
  (:refer-clojure :exclude [find sort])
  (:require [monger.core :as mcore]
            [monger.collection :as mcoll]
            [monger.operators :as ops]
            [monger.query :refer [paginate with-collection find fields sort]]))

(def ^:dynamic *room* "")
(def ^:dynamic *room_list* "")

(defn- minusDays [date days]
  (let [cal (java.util.Calendar/getInstance)
        calMinusDays (.add cal java.util.Calendar/DATE (- 0 days))]
    (.getTime calMinusDays)))

(defn with-text [text]
  {:text {ops/$regex (str ".*" text ".*")}})

(defn for-period [days]
  (let [currentDate (java.util.Date.)
        lowDate (minusDays currentDate days)]
    {:time {ops/$gte lowDate}}))

(defn with-sender [user]
  {:author user})

(defmacro all [& filters]
  `(let [conn# (mcore/connect {:host "localhost" :post 27017})
         db# (mcore/get-db conn# "chactics")
         final_filter# (merge ~@filters)]
     (reduce merge (map (fn
                          [current_room]
                          (map #(assoc % :room current_room) (mcoll/find-maps db# current_room final_filter#))) *room_list*))))


(defmacro current [& filters]
  `(let [conn# (mcore/connect {:host "localhost" :post 27017})
         db# (mcore/get-db conn# "chactics")
         final_filter# (merge ~@filters)]
     (mcoll/find-maps db# *room* final_filter#)))


(defmacro performQuery [query room room_list]
  `(binding [*room* ~room
             *room_list* ~room_list]
     ;(try
       (load-string ~query)))
     ;  (catch Exception e# (str "Invalid search query")))))



