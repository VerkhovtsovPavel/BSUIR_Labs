(ns cljs.chat.client.uiHandlers
  (:require [cljs.chat.client.html-utils :as hutil]
            [cljs.chat.client.core :refer [send]]))

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
    (send {:method "message", :text (.-value text), :room room})))

(defn saveStyle []
  (let [bgrColor (hutil/getById "bgrColor")
        bgrImage (hutil/getById "bgrImage")
        msgFont (hutil/getById "msgFont")]))                ;TODO Create css text and send

(defn createRoom []
  (let [roomNameField (hutil/getById "roomName")
        roomName (.-value roomNameField)
        roomParticipantsField (hutil/getById "roomPart")
        roomPart (.-value roomParticipantsField)]
    (send {:method "newRoom", :roomName roomName, :part (clojure.string/split roomPart ";")})))