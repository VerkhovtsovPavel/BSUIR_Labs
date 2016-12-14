(ns chat.util.dateUtils)

(defn minusDays [days]
  (let [cal (java.util.Calendar/getInstance)]
    (.add cal java.util.Calendar/DATE (- 0 days))
    (.getTime cal)))
