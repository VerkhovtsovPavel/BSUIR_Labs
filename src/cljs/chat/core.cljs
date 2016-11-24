(ns cljs.chat.client.core
  (:require [clojure.string :refer [split]]
            [cljs.chat.client.html-utils :as hutil])
  (:use [cljs.chat.client.model]))

(def currentRoomTitle (hutil/getById "CurrentRoomName"))
(def webSocket (let [currentURL (.-URL js/document)]
                 (js/WebSocket. (str "ws://" (.substring currentURL (.indexOf currentURL "//")) "ws")))) ;
(def room (currentRoom) "global")                           ;TODO Remove

(defn send [clj-map]
  (.send webSocket (.stringify js/JSON (clj->js clj-map))))

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


(defn- addRoom [list roomId]
  (let [listItem (.createElement js/document "li")]
    (aset listItem "id" roomId)
    (set! (.-onclick listItem)
          (fn [e]
            (let [newRoom (.-id (.-target e))
                  output (hutil/getById "output")]
              (send {:method "roomLeave" :room room})
              (send {:method "roomEnter" :room newRoom})
              (set! room newRoom)
              (aset currentRoomTitle "innerHTML" room)
              (hutil/removeChilds output))))

    (.appendChild listItem (.createTextNode js/document roomId))
    (.appendChild list listItem)))


(defn addStyle [styleSheet]
  (let [style (first (.getElementsByTagName js/document "style"))]
    (aset style "type" "text/css")
    (aset style "innerHTML" styleSheet)))

(defn successLogin []
  (let [dialog (hutil/getById "dialog")
        loginReg (hutil/getById "loginReg")
        left-sidebar (hutil/getById "left-sidebar")
        right-sidebar (hutil/getById "right-sidebar")]
    (hutil/setVisibility loginReg "hidden")
    (hutil/setVisibility dialog "visible")
    (hutil/setVisibility left-sidebar "visible")
    (hutil/setVisibility right-sidebar "visible"))

  (send {:method "roomList"})
  (send {:method "roomEnter" :room room})
  (if-let [style (getStyle)]
    (addStyle style)
    (send {:method "roomStyle" :room room})))


(defn builtRoomList [room_list]
  (let [list (hutil/getById "rlist")]
    (hutil/removeChilds list)
    (doseq [l_room room_list] (addRoom list l_room))))

(set! (.-onopen webSocket)
      (fn []
        (display "Connection opened...")
        (aset currentRoomTitle "innerHTML" room)))

(set! (.-onclose webSocket)
      (fn [] (display "Connection closed...")))

(set! (.-onmessage webSocket)
      (fn [message]
        (let [msg (js->clj (.parse js/JSON (aget message "data")))]
          (if (= (msg "result") js/undefined)
            (display (msg "text"))

            (case (msg "method")
              "roomList"
              (builtRoomList (msg "result"))

              "login"
              (if (= (msg "result") "Success")
                (successLogin)
                (js/alert (msg "result")))

              "registration"
              (if (= (msg "result") "Success")
                (successLogin)
                (js/alert (msg "result")))

              "nextPage"
              (let [messages (msg "result")]
                (doseq [m messages] (display m false)))

              "roomStyle"
              (let [style (msg "result")]
                (addStyle style)
                (cacheStyle style))

              "search"
              (let [results (msg "result")]
                (js/alert results))

              "roomEnter"
              (let [messages (msg "result")]
                (if (= messages "Illegal access")
                  (js/alert (msg "result"))
                  (doseq [m messages] (display m))))

              "newRoom"
              (let [list (hutil/getById "rlist")
                    roomId (msg "result")]
                (addRoom list roomId))

              (msg "result"))
            )
          (let [text (hutil/getById "text")]
            (aset text "value" ""))
          )))