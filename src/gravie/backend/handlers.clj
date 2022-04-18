(ns gravie.backend.handlers
  (:require [hiccup.page :as hp]
            [gravie.backend.http :refer [search-api]]))

(defn land-page [_]
  {:status 200
   :body (hp/html5
          [:div#root]
          [:script {:src "/js/main.js"}])})

(defn search [{:keys [query-params] :as req}]
  (let [{:strs [query resources]} query-params]
    {:status 200
     :body (search-api query resources)}))

(defn checkout [req]
    {:status 200
   :body "checkout"})
