(ns cljs.chat.client.uiHandlers
  (:require [cljs.chat.client.utils.htmlUtils :as hutil]
            [cljs.chat.client.utils.cssUtils :as cutil]
            [cljs.chat.client.model.state :as state]
            [cljs.chat.client.core])
  (:use [cljs.chat.client.websocket :only [send]]))

(defn showAddRoom []
  (let [addRoomDiv (hutil/getById "addRoom")
        searchDiv (hutil/getById "search")
        subscription (hutil/getById "subscription")
        setStyleDiv (hutil/getById "setStyle")]
    (hutil/setVisibility {searchDiv "hidden" setStyleDiv "hidden" addRoomDiv "visible" subscription "hidden"})))

(defn showSearch []
  (let [addRoomDiv (hutil/getById "addRoom")
        searchDiv (hutil/getById "search")
        subscription (hutil/getById "subscription")
        setStyleDiv (hutil/getById "setStyle")]
    (hutil/setVisibility {searchDiv "visible" setStyleDiv "hidden" addRoomDiv "hidden" subscription "hidden"})))

(defn showCustomizeRoom []
  (let [addRoomDiv (hutil/getById "addRoom")
        searchDiv (hutil/getById "search")
        subscription (hutil/getById "subscription")
        setStyleDiv (hutil/getById "setStyle")]
    (hutil/setVisibility {searchDiv "hidden" setStyleDiv "visible" addRoomDiv "hidden" subscription "hidden"})))

(defn showSubscription []
  (let [addRoomDiv (hutil/getById "addRoom")
        searchDiv (hutil/getById "search")
        subscription (hutil/getById "subscription")
        setStyleDiv (hutil/getById "setStyle")]
    (hutil/setVisibility {searchDiv "hidden" setStyleDiv "hidden" addRoomDiv "hidden" subscription "visible"}))
  (send {:method "roomListToSubscibe"})
  )

(defn registerOrLogin []
  (let [userName (hutil/getValueById "userName")
        password (hutil/getValueById "password")
        userChoice (first (filter (fn [i] (.-checked i)) (.getElementsByName js/document "logRegRadio")))]
    (if (= (.-value userChoice) "reg")
      (send {:method "registration" :userName userName :password password})
      (send {:method "login" :userName userName :password password}))))

(defn sendNewMessage []
  (let [text (hutil/getValueById "text")
        room (state/currentRoom)]
    (if (= text "")
      (js/alert "Message is empty")
      (send {:method "message", :text text, :room room}))))

(defn nextPage []
  (let [room (state/currentRoom)]
    (send {:method "nextPage", :page @state/page, :messages @state/newMessages :room room}))
  (swap! state/page (fn [current_state] (+ current_state 1))))

(defn saveStyle []
  (let [bgrColor (hutil/getSelectedValue (hutil/getById "bgrColor"))
        bgrImage (hutil/getValueById "bgrImage")
        msgFont (hutil/getSelectedValue (hutil/getById "msgFont"))
        msgFontSize (hutil/getValueById "msgFontSize")
        msgFontColor (hutil/getSelectedValue (hutil/getById "msgFontColor"))
        controlsColor (hutil/getSelectedValue (hutil/getById "controlsColor"))
        room (state/currentRoom)
        roomStyle (cutil/build-css bgrColor bgrImage msgFont msgFontSize msgFontColor controlsColor)]
    (send {:method "saveStyle", :roomStyle roomStyle, :room room})))

(defn createRoom []
  (let [roomName (hutil/getValueById "roomName")
        roomPart (hutil/getValueById "roomPart")
        type (.-checked (hutil/getById "roomType"))]
    (if (or (= roomName "") (= roomPart ""))
      (js/alert "Please feel all fields")
      (send {:method "newRoom", :roomName roomName, :part (clojure.string/split roomPart ";") :isOpened type}))))

(defn searchQuery []
  (let [query (hutil/getValueById "searchQuery")
        room (state/currentRoom)]
    (send {:method "search", :query query, :room room})))

(defn subscribe []
  (let [room (hutil/getSelectedValue (hutil/getById "roomToSubscribe"))]
     (send {:method "subscribe" :room room})))

(defn unsubscribe []
  (let [room (state/currentRoom)]
    (send {:method "unsubscribe", :room room})))