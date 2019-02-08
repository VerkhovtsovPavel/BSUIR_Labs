(ns cljs.chat.client.loginRegistaration
  (:require [cljs.chat.client.utils.htmlUtils :as hutil]
            [cljs.chat.client.model.state :as state])
  (:use [cljs.chat.client.websocket :only [send]]
        [cljs.chat.client.extraFeatures :only [addStyle]]))

(defn successLogin []
  (let [dialog (hutil/getById "dialog")
        loginReg (hutil/getById "loginReg")
        left-sidebar (hutil/getById "left-sidebar")
        right-sidebar (hutil/getById "right-sidebar")
        currentRoomTitle (hutil/getById "CurrentRoomName")
        room (state/currentRoom)]
    (hutil/setVisibility loginReg "hidden")
    (hutil/setVisibility dialog "visible")
    (hutil/setVisibility left-sidebar "visible")
    (hutil/setVisibility right-sidebar "visible")

    (send {:method "roomList"})
    (send {:method "roomEnter" :room room})

    (aset currentRoomTitle "innerHTML" room)

    (if-let [style (state/getStyle)]
      (addStyle style)
      (send {:method "roomStyle" :room room}))))
