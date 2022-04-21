(ns gravie.backend.routes
  (:require [reitit.ring :as ring ]
            [reitit.ring.middleware.parameters :refer [parameters-middleware ]]
            [reitit.ring.middleware.muuntaja :as muuntaja]
            [muuntaja.core :as m]
            [gravie.backend.handlers :as h]))

(def routes
  "Reitit routes"
  (ring/ring-handler
   (ring/router
    [["/" {:name ::root
           :handler h/land-page}]
     ["/search" {:name ::search
                 :responses {401 {:body {:error string?}}}
                 :get {:handler h/search}}]
     ["/checkout" {:name ::checkout
                   :post {:handler h/checkout}}]]
    {:data {:muuntaja m/instance
            :middleware [parameters-middleware
                         muuntaja/format-negotiate-middleware
                         muuntaja/format-response-middleware
                         muuntaja/format-request-middleware]}})
   (ring/routes
    (ring/create-file-handler {:path "/"})
    (ring/create-default-handler
     {:not-found (fn [_]
                   {:status 404
                    :body "PAGE NOT FOUND"})}))))
