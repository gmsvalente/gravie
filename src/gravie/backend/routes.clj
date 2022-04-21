(ns gravie.backend.routes
  (:require [reitit.ring :as ring ]
            [reitit.ring.middleware.parameters :refer [parameters-middleware ]]
            [reitit.ring.middleware.muuntaja :as muu]
            [reitit.http.interceptors.muuntaja :as muuntaja]
            [muuntaja.core :as m]
            [gravie.backend.handlers :as h]))

(def routes
  (ring/ring-handler
   (ring/router
    [["/" {:name ::root
           :handler h/land-page}]
     ["/search" {:name ::search
                 :get {:handler h/search}}]
     ["/checkout" {:name ::checkout
                   :post {:handler h/checkout}}]]
    {:data {:muuntaja m/instance
            :middleware [parameters-middleware
                         muu/format-negotiate-middleware
                         muu/format-response-middleware
                         muu/format-request-middleware]}})
   (ring/routes
    (ring/create-file-handler {:path "/"})
    (ring/create-default-handler
     {:not-found (fn [_]
                   {:status 404
                    :body "PAGE NOT FOUND"})}))))
