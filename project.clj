(defproject metosin/compojure-api-example "0.0.1"
  :description "Compojure-api-examples"
  :url "https://github.com/metosin/compojure-api"
  :license {:name "Eclipse Public License - v 1.0"
            :url "http://www.eclipse.org/legal/epl-v10.html"
            :distribution :repo
            :comments "same as Clojure"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [metosin/compojure-api "0.0.1-SNAPSHOT"]
                 [cheshire "5.3.0"]
                 [prismatic/schema "0.1.10"]
                 [compojure "1.1.6"]]
  :plugins [[lein-ring "0.8.7"]]
  :ring {:handler compojure.api.example.handler/app}
  :profiles {:dev {:plugins [[lein-clojars "0.9.1"]]
                   :dependencies [[ring-mock "0.1.5"]
                                  [midje "1.6.0"]
                                  [clj-http "0.7.8" :exclusions [commons-codec]]]}})
