(ns cljs.chat.client.loginRegistaration
  (:require [cljs.chat.client.utils.htmlUtils :as hutil])
  (:use [cljs.chat.client.websocket]
        [cljs.chat.client.extraFeatures]
        [cljs.chat.client.model.state]))

(defn successLogin []
  (let [dialog (hutil/getById "dialog")
        loginReg (hutil/getById "loginReg")
        left-sidebar (hutil/getById "left-sidebar")
        right-sidebar (hutil/getById "right-sidebar")
        room (currentRoom)]
    (hutil/setVisibility loginReg "hidden")
    (hutil/setVisibility dialog "visible")
    (hutil/setVisibility left-sidebar "visible")
    (hutil/setVisibility right-sidebar "visible")

    (send {:method "roomList"})
    (send {:method "roomEnter" :room room})
    (if-let [style (getStyle)]
      (addStyle style)
      (send {:method "roomStyle" :room room}))))
