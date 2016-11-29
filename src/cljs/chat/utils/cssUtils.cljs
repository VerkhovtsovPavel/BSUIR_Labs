(ns cljs.chat.client.utils.cssUtils)

(defn build-css
  [bgrColor bgrImageUrl msgFont msgFontSize msgFontColor controlsColor]
  (str "#output { background-color: " bgrColor "; background-image: url(" bgrImageUrl ");} p { font-family : " msgFont "; color:" msgFontColor "; font-size: " msgFontSize "pt;  }"))
;TODO Add control elements color
;TODO Font css (p) can be applyed for ALL!!!! text on page