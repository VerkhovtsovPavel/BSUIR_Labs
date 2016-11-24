(ns cljs.chat.client.websocket)

(def webSocket (let [currentURL (.-URL js/document)]
                 (js/WebSocket. (str "ws://" (.substring currentURL (.indexOf currentURL "//")) "ws"))))
(defn send [clj-map]
  (.send webSocket (.stringify js/JSON (clj->js clj-map))))

(defn parse [json]
  (js->clj (.parse js/JSON json)))
