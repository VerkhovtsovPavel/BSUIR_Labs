(ns chat.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [chat.handler :refer :all]))

(deftest test-app
  (testing "main route"
    (let [response (app-routes (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (:body response))))

  (testing "not-found route"
    (let [response (app-routes (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))