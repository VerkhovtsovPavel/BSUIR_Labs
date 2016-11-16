(ns chat.data.search-macro
  (:refer-clojure :exclude [find sort])
  (:require [monger.core :as mcore]
            [monger.collection :as mcoll]
            [monger.operators :as ops]
            [monger.query :refer [paginate with-collection find fields sort]]))

(defn- minusDays[date days]
  (let[cal (.setTime (.getInstance java.util.Calendar) date)
       calMinusDays (.add cal java.util.Calendar/DATE (- 0 days))]
    (.getTime calMinusDays)))

(defn with-text[text]
  {:text text})

(defn for-period[days]
  (let [currentDate (java.util.Date.)
        lowDate (minusDays currentDate days)]

  {:time {ops/$gte lowDate}}))

(defn with-sender [user]
  {:author user})

(defmacro all[filters]
  `(let[conn# (mcore/connect {:host "localhost" :post 27017})
       db# (mcore/get-db conn# "chactics")
       final_filter# (reduce assoc ~filters {})]
    (reduce assoc (map #(mcoll/find-maps db# % final_filter#) room_list)) {}))


(defmacro current[filters]
  (let [conn (mcore/connect {:host "localhost" :post 27017})
        db (mcore/get-db conn "chactics")]
  `(mcoll/find-maps db room (reduce assoc ~filters {}))))


(defmacro performQuery [query room room_list]
  `~(read-string ~query))




