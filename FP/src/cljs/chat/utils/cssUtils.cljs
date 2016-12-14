(ns cljs.chat.client.utils.cssUtils)

(defn build-css
  [bgrColor bgrImageUrl msgFont msgFontSize msgFontColor controlsColor]
  (str "#output { background-color: " bgrColor "; background-image: url(" bgrImageUrl ");} #output p { font-family : " msgFont "; color: " msgFontColor "; font-size: " msgFontSize "pt;  } button { background-color: " controlsColor "}"))