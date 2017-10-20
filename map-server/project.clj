(defproject map-server "0.1.0-SNAPSHOT"
  :description "ExpressJS in ClojureScript proof of concept"
  :url "http://example.com/tbc"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [; Core Clojure
                 [org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.946"]

                 ; Logging
                 [com.taoensso/timbre "4.10.0"]]

  :plugins [[lein-cljsbuild "1.1.7"]]

  :cljsbuild {
    :builds [{:source-paths ["src/cljs"]
              :compiler {:output-to "resources/public/core.js"
                         :optimizations :advanced
                         :externs ["node_modules/body-parser/index.js"
                                   "node_modules/express/lib/middleware/init.js"
                                   "node_modules/express/lib/middleware/query.js"
                                   "node_modules/express/lib/router/index.js"
                                   "node_modules/express/lib/router/layer.js"
                                   "node_modules/express/lib/router/route.js"
                                   "node_modules/express/lib/application.js"
                                   "node_modules/express/lib/express.js"
                                   "node_modules/express/lib/request.js"
                                   "node_modules/express/lib/response.js"
                                   "node_modules/express/lib/utils.js"
                                   "node_modules/express/lib/view.js"
                                   "externs/externs.js"]
                         :target :nodejs
                         :main "map-server.core"}}]})
