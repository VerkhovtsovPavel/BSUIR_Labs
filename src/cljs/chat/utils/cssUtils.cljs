(ns cljs.chat.client.utils.cssUtils)

(defn build-css
  [bgrColor bgrImageUrl msgFont]
  (str "#output { background-color: " bgrColor "; background-image: url(" bgrImageUrl ");} p { font-family : " msgFont ";}"))