(ns gravie.backend.handlers
  (:require [hiccup.page :as hp]
            [gravie.backend.http :refer [search-gb]]))

(defn land-page [_]
  {:status 200
   :body (hp/html5 {}
          [:head
           [:meta {:name "viewport"
                   :content "width=device-width, initial-scale=1.0"}]
           [:meta {:charset "utf-8"}]]
          [:body
           [:div#root]
           [:script {:src "/js/main.js"}]])})

(defn search [{:keys [query-params] :as req}]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (search-gb query-params)})

(defn checkout [req]
    {:status 200
     :body "checkout"})
