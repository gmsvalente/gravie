(ns gravie.backend.handlers
  (:require [hiccup.page :as hp]
            [gravie.backend.http :refer [search-gb checkout-cart]]))

(defn land-page
  "Base land-page html handler"
  [_]
  {:status 200
   :body (hp/html5 {}
          [:head
           [:title "Gravie GiantBomb"]
           [:link {:rel "preconnect" :href "https://fonts.googleapis.com"}]
           [:link {:rel "preconnect" :href "https://fonts.gstatic.com" :crossorigin true}]
           [:link {:href "https://fonts.googleapis.com/css2?family=Luckiest+Guy&family=Rajdhani&display=swap" :rel "stylesheet"}]
           [:meta {:name "viewport"
                   :content "width=device-width, initial-scale=1.0"}]
           [:meta {:charset "utf-8"}]]
          [:body
           [:div#root "You need javascript to run this app!"]
           [:script {:src "/js/main.js"}]])})

(defn search
  "Search handler"
  [{:keys [query-params]}]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (search-gb query-params)})

(defn checkout
  "Checkout handler"
  [{:keys [body-params]}]
  {:status 200
   :body (checkout-cart body-params)})
