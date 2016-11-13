(ns cljs.chat.client.core
  (:require [clojure.string :refer [split]]
            [cljs.chat.client.html-utils :as hutil]))

(def currentRoomTitle (hutil/getById "CurrentRoomName"))
(def webSocket (let [currentURL (.-URL js/document)]
                 (js/WebSocket. (str "ws://" (.substring currentURL (.indexOf currentURL "//")) "ws")))) ;
(def room "global")
(def fonts ["Times New Roman", "Tahoma", "Arial"])          ; TODO Increase count of fonts. Ma45ybe move html

(defn send [clj-map]
  (.send webSocket (.stringify js/JSON (clj->js clj-map))))

(defn display [message]
  (let [p (.createElement js/document "p")
        new_text (.createTextNode js/document message)
        output (hutil/getById "output")]
    (.appendChild p new_text)
    (if (.-firstChild output)
      (.insertBefore output p (.-firstChild output))
      (.appendChild output p))))

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
  (send {:method "roomEnter" :room room}))


(defn addStyle [styleSheet]
  (let [style (.createElement js/document "style")]
    (aset style "type" "text/css")
    (aset style "innerHTML" styleSheet)
    (.appendChild (first (.getElementsByTagName js/document "head")) style))) ;TODO Implement some way to remove :)

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

              "roomStyle"
              (addStyle (msg "result"))

              "roomEnter"
              (let [messages (msg "result")]
                (if (= message "Illigal access")
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