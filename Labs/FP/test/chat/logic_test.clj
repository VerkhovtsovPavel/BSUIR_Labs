(ns chat.logic-test
  (:require [clojure.test :refer :all]
            [chat.model.dispatcher :refer :all]
            [chat.data.persistance :as pers]
            [monger.core :as mcore]))


(defn use-db [f]
  (binding [pers/db (mcore/get-db pers/conn "chat-test")]
    (f)
    (mcore/drop-db pers/conn "chat-test")))

(use-fixtures :once use-db)

(deftest logic-test
  (testing "Login without registration"
    (is (= (:result (getResponse "{\"method\":\"login\",\"userName\":\"Pavel\",\"password\":\"1\"}" 1)) "Failed! Incorrect user name or password")))

  (testing "Registration"
    (is (= (:result (getResponse "{\"method\":\"registration\",\"userName\":\"Pavel\",\"password\":\"1\"}" 1)) "Success")))

  (testing "Login"
    (is (= (:result (getResponse "{\"method\":\"login\",\"userName\":\"Pavel\",\"password\":\"1\"}" 1)) "Success")))


  (testing "Room list"
    (is (= (:result (getResponse "{\"method\":\"roomList\"}" 1)) ())))

  (let [roomName "TestRoom"]

    (testing "Add room"
      (is (= (:result (getResponse (str "{\"method\":\"newRoom\",\"roomName\": \"" roomName "\" ,\"part\":[]}") 1)) roomName)))
    (testing "Room list"
      (is (= (:result (getResponse "{\"method\":\"roomList\"}" 1)) (list roomName))))

    (testing "Enter to invalid room"
      (is (= (:result (getResponse "{\"method\":\"roomEnter\",\"room\":\"Secret Room\"}" 1)) "Illegal access")))

    (testing "Enter to valid room"
      (is (= (:result (getResponse (str "{\"method\":\"roomEnter\",\"room\":\"" roomName "\"}") 1)) "Ok")))

    (testing "Leave room"
      (is (= (:result (getResponse (str "{\"method\" : \"roomLeave\", \"room\" : \"" roomName "\"}") 1)) "Ok"))))

  (testing "Send message"
    (is (= (:result (getResponse "{\"method\":\"message\",\"text\":\"4\",\"room\":\"TestRoom\"}" 1)) "4")))

  (testing "Search message (empty)"
    (is (= (:result (getResponse "{\"method\":\"search\",\"query\":\"(current (with-text \\\"5\\\"))\",\"room\":\"TestRoom\"}" 1)) ())))

  (testing "Search message"
    (is (= (:text (first (:result (getResponse "{\"method\":\"search\",\"query\":\"(current (with-text \\\"4\\\"))\",\"room\":\"TestRoom\"}" 1)))) "4"))))


















