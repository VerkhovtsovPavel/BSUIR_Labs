(ns cljs.chat.client.utils.htmlUtils)

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

(defn getValueById
  [id]
  (.-value (getById id)))

(defn getSelectedValue
  [select]
  (let [options (.-options select)
        index (.-selectedIndex select)]
    (.-value (nth (array-seq options 0) index))))


(defn fillSelect [values]
  (let [sel (getById "roomToSubscribe")]
    (doseq values #(let [opt (.createElement js/document "option")]
                    (aset opt "innerHTML" %)
                    (aset opt "value" %)
                    (.appendChild sel opt)))))

(extend-type js/NodeList
  ISeqable
  (-seq [array] (array-seq array 0)))

(extend-type js/HTMLCollection
  ISeqable
  (-seq [array] (array-seq array 0)))

