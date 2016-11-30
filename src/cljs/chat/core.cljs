(ns cljs.chat.client.core
  (:require [cljs.chat.client.utils.htmlUtils :as hutil])
  (:use [cljs.chat.client.websocket]
        [cljs.chat.client.messages]
        [cljs.chat.client.rooms]
        [cljs.chat.client.loginRegistaration]
        [cljs.chat.client.extraFeatures]
        [cljs.chat.client.model.state]))

(set! (.-onopen webSocket)
      (fn []
        (display "Connection opened...")
        (aset (hutil/getById "CurrentRoomName") "innerHTML" (currentRoom))))

(set! (.-onclose webSocket)
      (fn [] (display "Connection closed...")))

(set! (.-onmessage webSocket)
      (fn [message]
        (let [msg (parse (aget message "data"))]
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

              "roomListToSubscibe"
              (let [result (msg "result")]
                (hutil/fillSelect result))

              ;TODO Subs and Unsubs (Add, remove item)

              "roomEnter"
              (let [messages (msg "result")]
                (if (= messages "Illegal access")
                  (js/alert (msg "result"))
                  (do
                    (send {:method "nextPage", :page 0, :room (currentRoom)})
                    (swap! page 1)
                  )))

              "newRoom"
              (let [list (hutil/getById "rlist")
                    roomId (msg "result")]
                (addRoom list roomId))

              (msg "result"))
            )
          (let [text (hutil/getById "text")]
            (aset text "value" ""))
          )))