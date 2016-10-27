(ns chat.handler
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [org.httpkit.server :as kit]
            [ring.util.response :as resp]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [chat.model.websocket :as ws]))

(defroutes app-routes
  (GET "/" [] (resp/resource-response "index.html" {:root "public"}))
  (GET "/ws" [] ws/handler)
 ; (GET "/room/:id" [id] )
 ; (PUT "/room/style/" [] )
  (route/not-found "Not Found"))

(defn -main [& args]
  (kit/run-server (handler/site #'app-routes) {:port 8080})
  (println "Server started"))
