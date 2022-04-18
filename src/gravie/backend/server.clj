(ns gravie.backend.server
  (:require [ring.adapter.jetty :as jetty]
            [gravie.backend.routes :refer [routes]]))

(defonce server (atom nil))

(defn -main []
  (reset! server (jetty/run-jetty #'routes
                                  {:port 8080
                                   :join? false})))



