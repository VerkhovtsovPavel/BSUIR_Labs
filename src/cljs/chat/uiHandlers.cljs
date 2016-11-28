(ns cljs.chat.client.uiHandlers
  (:require [cljs.chat.client.utils.htmlUtils :as hutil])
  (:use [cljs.chat.client.websocket]))

(defn showAddRoom []
  (let [addRoomDiv (hutil/getById "addRoom")
        searchDiv (hutil/getById "search")
        setStyleDiv (hutil/getById "setStyle")]
    (hutil/setVisibility {searchDiv "hidden" setStyleDiv "hidden" addRoomDiv "visible"})))

(defn showSearch []
  (let [addRoomDiv (hutil/getById "addRoom")
        searchDiv (hutil/getById "search")
        setStyleDiv (hutil/getById "setStyle")]
    (hutil/setVisibility {searchDiv "visible" setStyleDiv "hidden" addRoomDiv "hidden"})))

(defn showCustomizeRoom []
  (let [addRoomDiv (hutil/getById "addRoom")
        searchDiv (hutil/getById "search")
        setStyleDiv (hutil/getById "setStyle")]
    (hutil/setVisibility {searchDiv "hidden" setStyleDiv "visible" addRoomDiv "hidden"})))


(defn registerOrLogin []
  (let [userName (hutil/getById "userName")
        password (hutil/getById "password")
        userChoice (first (filter (fn [i] (.-checked i)) (.getElementsByName js/document "logRegRadio")))]
    (if (= (.-value userChoice) "reg")
      (send {:method "registration" :userName (.-value userName) :password (.-value password)})
      (send {:method "login" :userName (.-value userName) :password (.-value password)}))))

(defn sendNewMessage []
  (let [text (hutil/getById "text")
        room (.-innerHTML (hutil/getById "CurrentRoomName"))]
    (if (= text "")
      (js/alert "Please feel all fields")
      (send {:method "message", :text (.-value text), :room room}))))

(defn nextPage []
  (let [room (.-innerHTML (hutil/getById "CurrentRoomName"))]
    (send {:method "nextPage", :page (+ 1 @page), :room room}))
  (swap! page (fn [current_state] (+ current_state 1))))

(defn saveStyle []
  (let [bgrColor (hutil/getById "bgrColor")
        bgrImage (hutil/getById "bgrImage")
        msgFont (hutil/getById "msgFont")
        room (.-innerHTML (hutil/getById "CurrentRoomName"))
        roomStyle (str "#output { background-color: " (.-value bgrColor) "; background-image: url(" (.-value bgrImage) ");} p { font-family : " (hutil/getSelectedValue msgFont) ";}")]
    (send {:method "saveStyle", :roomStyle roomStyle, :room room})))

(defn createRoom []
  (let [roomNameField (hutil/getById "roomName")
        roomName (.-value roomNameField)
        roomParticipantsField (hutil/getById "roomPart")
        roomPart (.-value roomParticipantsField)]
    (if (or (= roomName "") (= roomPart ""))
      (js/alert "Please feel all fields")
      (send {:method "newRoom", :roomName roomName, :part (clojure.string/split roomPart ";")}))))

(defn searchQuery []
  (let [queryFileld (hutil/getById "searchQuery")
        query (.-value queryFileld)
        room (.-innerHTML (hutil/getById "CurrentRoomName"))]
    (send {:method "search", :query query, :room room})))