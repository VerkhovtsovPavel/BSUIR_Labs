(ns cljs.chat.client.messages
  (:require [cljs.chat.client.utils.htmlUtils :as hutil]))

(defn display
  ([message back_order]
   (let [p (.createElement js/document "p")
         new_text (.createTextNode js/document message)
         output (hutil/getById "output")]
     (.appendChild p new_text)
     (if (and (.-firstChild output) back_order)
       (.insertBefore output p (.-firstChild output)))
     (.appendChild output p)))
  ([message]
   (display message true)))