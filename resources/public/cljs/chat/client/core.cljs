(ns cljs.chat.client.core)

(extend-type js/NodeList
  ISeqable
  (-seq [array] (array-seq array 0)))

(def text (.getElementById js/document "text"))
(def output (.getElementById js/document "output"))
(def dialog (.getElementById js/document "dialog"))
(def loginReg (.getElementById js/document "loginReg"))
(def currentRoomTitle (.getElementById js/document "CurrentRoomName"))
(def addRoomDiv (.getElementById js/document "addRoom"))
(def searchDiv (.getElementById js/document "search"))
(def setStyleDiv (.getElementById js/document "setStyle"))                                                ;
(def currentURL (.-URL js/document))                                                                      ; Move by max to let blocks
(def webSocket (js/WebSocket. (str "ws://" (.substring currentURL (.indexOf currentURL "//")) "ws")))     ;

(def room "global")
(def fonts ["Times New Roman", "Tahoma", "Arial"])   ; TODO Increase count of fonts

(defn send [clj-map]
  (.send webSocket (.stringify js/JSON (clj->js clj-map))))

(defn display [message]
  (let [p (.createElement js/document "p")
        new_text (.createTextNode js/document message)]
    (.appendChild p new_text)
    (if (.-firstChild output)
      (.insertBefore output p (.-firstChild output))
      (.appendChild output p))))


(defn addStyle [styleSheet]
  (let [style (.createElement js/document "style")]
    (set! (.-type style) "text/css")
    (set! (.-innerHTML style) styleSheet)
    (.appendChild (get (.getElementsByTagName js/document "head") 0) style)))

(defn builtRoomList [room_list]
  (let [list (.getElementById js/document "rlist")]

    (while (.-firstChild list)
      (.removeChild list (.-firstChild list)))        ; TODO Move to separate method

    (doseq [l_room room_list] (fn []
                                (let [listItem (.createElement js/document "li")]
                                  (set! (.-id listItem) l_room)
                                  (set! (.-onclick listItem) (fn [e]
                                                               (let [newRoom (.-id (.-target e))]
                                                                 (send {:method "roomLeave" :room room})
                                                                 (send {:method "roomEnter" :room newRoom})
                                                                 (set! room newRoom)
                                                                 (set! (.-innerHTML currentRoomTitle) room)
                                                                 (while (.-firstChild output)
                                                                   (.removeChild output (.-firstChild output))))))


                                  (.appendChild listItem (.createTextNode js/document l_room))
                                  (.appendChild list listItem))))
    ))

(set! (.-onopen webSocket) (fn []
                             (display "Connection opened...")
                             (set!(.-innerHTML currentRoomTitle) room)))

(set! (.-onclose webSocket) (fn [] (display "Connection closed...")))

(set! (.-onmessage webSocket) (fn [message]
                               (let [msg (js->clj (.parse js/JSON (.-data message)))]
                                 (if (= (msg "result") js/undefined)
                                   (display (msg "text"))

                                   (case (msg "methods")
                                     "roomList" (builtRoomList (msg "result"))

                                     "login"
                                     (if (= (msg "result") "Success")
                                       (do
                                         (set! (.-visibility (.-style loginReg)) "hidden") ; TODO Create separate method
                                         (set! (.-visibility (.-style dialog)) "visible")
                                         (set! (.-visibility (.-style (get (.getElementsByClassName js/document "left-sidebar") 0))) "visible")
                                         (set! (.-visibility (.-style (get (.getElementsByClassName js/document "right-sidebar") 0))) "visible")

                                         (send {:method "roomList"})
                                         (send {:method "roomEnter" :room room})
                                         )
                                       (js/alert (msg "result"))) ;TODO Remove duplication

                                     "registration"
                                     (if (= (msg "result") "Success")
                                       (do
                                         (set! (.-visibility (.-style loginReg)) "hidden") ; TODO Create separate method
                                         (set! (.-visibility (.-style dialog)) "visible")
                                         (set! (.-visibility (.-style (get (.getElementsByClassName js/document "left-sidebar") 0))) "visible")
                                         (set! (.-visibility (.-style (get (.getElementsByClassName js/document "right-sidebar") 0))) "visible")

                                         (send {:method "roomList"})
                                         (send {:method "roomEnter" :room room})
                                         )
                                       (js/alert (msg "result")))

                                     "roomStyle" (addStyle (msg "result"))

                                     "roomEnter"
                                     (let [messages (msg "result")]
                                       (doseq [m messages] (display m)))

                                     "newRoom"
                                     (let [list (.getElementById js/document "rlist")
                                           listItem (.createElement js/document "li")]
                                       (set! (.-id listItem) (msg "result"))
                                       (set! (.-onclick listItem) (fn [e]
                                                                    (let [newRoom (.-id (.-target e))]
                                                                      (send {:method "roomLeave" :room room})
                                                                      (send {:method "roomEnter" :room newRoom})
                                                                      (set! room newRoom)
                                                                      (set! (.-innerHTML currentRoomTitle) room)
                                                                      (while (.-firstChild output)
                                                                        (.removeChild output (.-firstChild output))))))


                                       (.appendChild listItem (.createTextNode js/document (msg "result")))
                                       (.appendChild list listItem))))
                                 (set! (.-value text) "")
                                 )))

(defn sendNewMessage [] (send {:method "message", :text (.-value text), :room room}))



(defn createRoom []
  (let [roomNameField (.getElementById js/document "roomName")
        roomName (.-value roomNameField)
        roomParticipantsField (.getElementById js/document "roomPart")
        roomPart (.-value roomParticipantsField)]
    (send {:method "newRoom", :roomName roomName, :part (clojure.string/split roomPart ";")})))

(defn saveStyle []
  (let [bgrColor (.getElementById js/document "bgrColor")
        bgrImage (.getElementById js/document "bgrImage")
        msgFont (.getElementById js/document "msgFont")]))


; handlers
(defn showAddRoom []
  (set! (.-visibility (.-style searchDiv)) "hidden")
  (set! (.-visibility (.-style setStyleDiv)) "hidden")
  (set! (.-visibility (.-style addRoomDiv)) "visible"))

(defn showSearch []
  (set! (.-visibility (.-style searchDiv)) "visible")
  (set! (.-visibility (.-style setStyleDiv)) "hidden")
  (set! (.-visibility (.-style addRoomDiv)) "hidden"))

(defn showCustomizeRoom []
  (set! (.-visibility (.-style searchDiv)) "hidden")
  (set! (.-visibility (.-style setStyleDiv)) "visible")
  (set! (.-visibility (.-style addRoomDiv)) "hidden"))


(defn registerOrLogin []
  (let [userName (.getElementById js/document "userName")
        password (.getElementById js/document "password")
        userChoice (first (filter (fn [i] (.-checked i)) (.getElementsByName js/document "logRegRadio")))]
    (if (= (.-value userChoice) "reg")
      (send {:method "registration" :userName (.-value userName) :password (.-value password)})
      (send {:method "login" :userName (.-value userName) :password (.-value password)}))))

