(ns cljs.chat.client.uiHandlers
  (:require [cljs.chat.client.utils.htmlUtils :as hutil]
            [cljs.chat.client.utils.cssUtils :as cutil]
            [cljs.chat.client.model.state :as state])
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

(defn showSubscription []
  ;TODO Show/hide
  ;TODO Get and show room list
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
    (send {:method "nextPage", :page (+ 1 @state/page), :room room}))
  (swap! state/page (fn [current_state] (+ current_state 1))))

(defn saveStyle []
  (let [bgrColor (hutil/getValueById "bgrColor")
        bgrImage (hutil/getValueById "bgrImage")
        msgFont (hutil/getValueById "msgFont")
        ;TODO Add font size
        ;TODO Add font color
        ;TODO Add control elements color
        room (state/currentRoom)
        roomStyle (cutil/build-css bgrColor bgrImage msgFont)]
    (send {:method "saveStyle", :roomStyle roomStyle, :room room})))

(defn createRoom []
  (let [roomName (hutil/getValueById "roomName")
        roomPart (hutil/getValueById "roomPart")]
    (if (or (= roomName "") (= roomPart ""))
      (js/alert "Please feel all fields")
      (send {:method "newRoom", :roomName roomName, :part (clojure.string/split roomPart ";")}))))

(defn searchQuery []
  (let [query (hutil/getValueById "searchQuery")
        room (state/currentRoom)]
    (send {:method "search", :query query, :room room})))

(defn subscribe []) ;TODO Implement
(defn unsubscribe []) ;TODO Implement