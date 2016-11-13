(ns cljs.chat.client.html-utils)

(defn setVisibility
  ([element new-value]
   (set! (.-visibility (.-style element)) new-value))
  ([elements]
   (doseq [[elenent new-value] elements] (setVisibility elenent new-value))))

(defn removeChilds [element]
  (while (.-firstChild element)
    (.removeChild element (.-firstChild element))))

(defn getById
  [id]
  (.getElementById js/document id))

(extend-type js/NodeList
  ISeqable
  (-seq [array] (array-seq array 0)))

(extend-type js/HTMLCollection
  ISeqable
  (-seq [array] (array-seq array 0)))
