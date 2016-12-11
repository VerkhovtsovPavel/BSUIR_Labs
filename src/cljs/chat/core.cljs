(ns cljs.chat.client.core
  (:require [cljs.chat.client.utils.htmlUtils :as hutil]
            [cljs.chat.client.model.state :as state]
            [cljs.chat.client.websocket :as ws])
  (:use [cljs.chat.client.messages :only [display]]
        [cljs.chat.client.rooms :only [addRoom removeRoom builtRoomList]]
        [cljs.chat.client.loginRegistaration :only [successLogin]]
        [cljs.chat.client.extraFeatures :only [addStyle]]))

(set! (.-onopen ws/webSocket)
      (fn []
        (display "Connection opened...")
        (aset (hutil/getById "CurrentRoomName") "innerHTML" (state/currentRoom))))

(set! (.-onclose ws/webSocket)
      (fn [] (display "Connection closed...")))

(set! (.-onmessage ws/webSocket)
      (fn [message]
        (let [msg (ws/parse (aget message "data"))]
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
                (state/cacheStyle style))

              "search"
              (let [results (msg "result")]
                (js/alert results))

              "roomListToSubscibe"
              (let [result (msg "result")]
                (hutil/fillSelect result))

              "roomEnter"
              (let [messages (msg "result")]
                (if (= messages "Illegal access")
                  (js/alert messages)
                  (do
                    (ws/send {:method "nextPage", :page 0, :room (state/currentRoom)})
                    (ws/send {:method "roomStyle" :room (state/currentRoom)})
                  )))

              "newRoom"
              (let [list (hutil/getById "rlist")
                    roomId (msg "result")]
                (addRoom list roomId))

              "unsubscribe"
              (let [list (hutil/getById "rlist")
                    roomId (msg "result")]
                (removeRoom list roomId))

              "saveStyle"
              (let [style (msg "result")]
                (addStyle style)
                (state/cacheStyle style))

              (msg "result"))
            )
          (let [text (hutil/getById "text")]
            (aset text "value" ""))
          )))