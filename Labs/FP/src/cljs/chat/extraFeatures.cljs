(ns cljs.chat.client.extraFeatures)


(defn addStyle [styleSheet]
  (let [style (first (.getElementsByTagName js/document "style"))]
    (aset style "type" "text/css")
    (aset style "innerHTML" styleSheet)))

