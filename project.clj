(defproject metosin/compojure-api "2.0.0-alpha30"
  :description "Compojure Api"
  :url "https://github.com/metosin/compojure-api"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"
            :distribution :repo
            :comments "same as Clojure"}
  :dependencies [; Fix conflicts
                 [clj-time "0.15.1"]
                 [riddley "0.2.0"]

                 [potemkin "0.4.5"]
                 [prismatic/schema "1.1.10"]
                 [prismatic/plumbing "0.5.5"]
                 [ikitommi/linked "1.3.1-alpha1"] ;; waiting for the original
                 [metosin/muuntaja "0.6.4"]
                 [com.fasterxml.jackson.datatype/jackson-datatype-joda "2.9.8"]
                 [ring/ring-core "1.7.1"]
                 [compojure "1.6.1" ]
                 [metosin/spec-tools "0.9.1"]
                 [metosin/ring-http-response "0.9.1"]
                 [metosin/ring-swagger-ui "2.2.10"]
                 [metosin/ring-swagger "0.26.2"]]
  :pedantic? :abort
  :profiles {:uberjar {:aot :all
                       :ring {:handler examples.thingie/app}
                       :source-paths ["examples/thingie/src"]
                       :dependencies [[org.clojure/clojure "1.10.0"]
                                      [http-kit "2.3.0"]
                                      [reloaded.repl "0.2.4"]
                                      [com.stuartsierra/component "0.4.0"]]}
             :dev {:plugins [[lein-clojars "0.9.1"]
                             [lein-midje "3.2.1"]
                             [lein-ring "0.12.5"]
                             [funcool/codeina "0.5.0"]]
                   :dependencies [[org.clojure/clojure "1.10.0"]
                                  [org.clojure/core.async "0.4.490"]
                                  [javax.servlet/javax.servlet-api "4.0.1"]
                                  [peridot "0.5.1"]
                                  [midje "1.9.6"]
                                  [com.rpl/specter "1.1.2"]
                                  [com.stuartsierra/component "0.4.0"]
                                  [expound "0.7.2"]
                                  [metosin/jsonista "0.2.2"]
                                  [reloaded.repl "0.2.4"]
                                  [metosin/muuntaja-msgpack "0.6.4"]
                                  [metosin/muuntaja-yaml "0.6.4"]
                                  [org.immutant/immutant "2.1.10"]
                                  [http-kit "2.3.0"]
                                  [criterium "0.4.4"]]
                   :test-paths ["test19"]
                   :ring {:handler examples.thingie/app
                          :reload-paths ["src" "examples/thingie/src"]}
                   :source-paths ["examples/thingie/src" "examples/thingie/dev-src"]
                   :main examples.server}
             :perf {:jvm-opts ^:replace ["-server"
                                         "-Xmx4096m"
                                         "-Dclojure.compiler.direct-linking=true"]}
             :logging {:dependencies [[org.clojure/tools.logging "0.4.1"]
                                      [org.slf4j/jcl-over-slf4j "1.7.26"]
                                      [org.slf4j/jul-to-slf4j "1.7.26"]
                                      [org.slf4j/log4j-over-slf4j "1.7.26"]
                                      [ch.qos.logback/logback-classic "1.2.3" ]]}
             :async {:jvm-opts ["-Dcompojure-api.test.async=true"]
                     :dependencies [[manifold "0.1.8" :exclusions [org.clojure/tools.logging]]]}}
  :eastwood {:namespaces [:source-paths]
             :add-linters [:unused-namespaces]}
  :codeina {:sources ["src"]
            :target "gh-pages/doc"
            :src-uri "http://github.com/metosin/compojure-api/blob/master/"
            :src-uri-prefix "#L"}
  :deploy-repositories [["releases" :clojars]]
  :aliases {"all" ["with-profile" "dev:dev,async"]
            "start-thingie" ["run"]
            "aot-uberjar" ["with-profile" "uberjar" "do" "clean," "ring" "uberjar"]
            "test-ancient" ["midje"]
            "perf" ["with-profile" "default,dev,perf"]
            "deploy!" ^{:doc "Recompile sources, then deploy if tests succeed."}
["do" ["clean"] ["midje"] ["deploy" "clojars"]]})
