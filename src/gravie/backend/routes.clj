(ns gravie.backend.routes
  (:require [reitit.ring :as ring ]
            [reitit.ring.middleware.parameters :refer [parameters-middleware]]
            [reitit.ring.middleware.muuntaja :as muuntaja]
            [muuntaja.core :as m]
            [gravie.backend.handlers :as h]))

(def routes
  (ring/ring-handler
   (ring/router
    [["/" {:name ::root
           :handler h/land-page}]
     ["/search/" {:name ::search
                  :get {:handler h/search}
                  :middleware [parameters-middleware]}]
     ["/checkout" {:name ::checkout
                   :get {:handler h/checkout}}]]
    {:data {:muuntaja m/instance
            :middleware [muuntaja/format-middleware]}})
   (ring/routes
    (ring/create-file-handler {:path "/"})
    (ring/create-default-handler
     {:not-found (fn [_]
                   {:status 404
                    :body "PAGE NOT FOUND"})}))))
