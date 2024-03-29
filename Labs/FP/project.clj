(defproject chat "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.293"]
                 [compojure "1.5.1"]
                 [http-kit "2.2.0"]
                 [ring/ring-defaults "0.2.1"]
                 [cheshire "5.6.3"]
                 [com.novemberain/monger "3.1.0"]
                 [alandipert/storage-atom "1.2.4"]]
  :plugins [[lein-ring "0.9.7"]
            [lein-cljsbuild "1.1.4"]]

  :cljsbuild {
              :builds [{:source-paths ["src/cljs"]
                        :compiler {:output-to "resources/public/core.js"
                                   :output-dir "resources/public/out"}}]}

  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}}
  :main chat.handler)
