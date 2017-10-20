(ns map-server.core
    (:require [cljs.nodejs :as node]
              [taoensso.timbre :as log]))

; Equivalent of node's require statements
(def express (node/require "express"))

(defn server-started-callback
    "Callback triggered when ExpressJS server has started"
    []
    (log/info "App started at http://localhost:3000"))

(defn obj->clj
    "Convert a Javascript object into a Clojure map"
    [req]
    (into {} (for [k (.keys js/Object req)]
                  [(keyword k) (aget req k)])))

(defn handler-simple
    "Function to handle a route"
    [req res]
    (log/info "Request:" (.-method req) (.-url req))
    (log/debug "Request:" (obj->clj req))
    (-> res
        (.status 200)
        (.json (clj->js {:message "Hello Clojurescript"}))))


(defn canonicalise-fn
    "Create a function, depending on input type"
    [item]
    (cond (fn? item) item
          (string? item) (fn [_ _] {:message item})
          :else (fn [_ _] item)))


(defn handler
    "Function to handle a generic route"
    [handler-fn]
    (let [canonical-fn (canonicalise-fn handler-fn)]
        (fn [req res]
            (log/info "Request:" (.-method req) (.-url req))
            (log/debug "Request:" (obj->clj req))
            (-> res
                (.status 200)
                (.json (clj->js (canonical-fn req res)))))))


(defn -main
    "Our main handler"
    [& args]
    (doto (new express)
;          (.get "/" handler-simple)
          (.get "/" (handler "Hello ClojureScript"))
          (.get "/xyzzy" (handler {:result "Nothing happens"}))
          (.get "/echo" (handler (fn [req _] {:method (.-method req) :url (.-url req)})))
          (.listen 3000 server-started-callback)))

; Specify the function to call when using node to run our app
(set! *main-cli-fn* -main)
