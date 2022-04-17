(ns gravie.backend.server
  (:require [ring.adapter.jetty :as jetty]
            [hiccup.page :as hp]))

(defonce server (atom nil))

(defn land-page [_]
  {:status 200
   :body (hp/html5
          [:h1 "GiantBomb"]
          [:div#root]
          [:script {:src "/public/js/main.js"}])})

(defn -main []
  (reset! server (jetty/run-jetty #'land-page {:port 3000
                                             :join? false})))



