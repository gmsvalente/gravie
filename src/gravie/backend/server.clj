(ns gravie.backend.server
  (:require [ring.adapter.jetty :as jetty]
            [gravie.backend.routes :refer [routes]]
            [environ.core :refer [env]]))

(defonce server (atom nil))

(def port (or (:port env) "8080"))

(defn -main []
  (reset! server (jetty/run-jetty #'routes
                                  {:port (Integer/parseInt port)
                                   :join? false})))



