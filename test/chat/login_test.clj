(ns chat.login-test
  (:require [clojure.test :refer :all]
            [chat.model.dispatcher :refer :all]))

(deftest test-app
  (testing "Login without registration"
      (is (= (:result (getResponse {"method" "login","userName" "Test","password" "123456"} 1))))))

(deftest test-app
  (testing "Registration"
    (is (= (:result (getResponse {"method" "registration","userName" "Test","password" "123456"} 1))))))

(deftest test-app
  (testing "Login"
    (is (= (:result (getResponse {"method" "login","userName" "Test","password" "123456"} 1))))))