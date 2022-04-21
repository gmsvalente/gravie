(ns gravie.backend.http
  (:require [clj-http.client :as client]
            [environ.core :refer [env]]))


(def gb-api
  "Get giantbomb api from environment"
  (:giantbomb-api env))

(def gb-uri
  "Giantbomb uri"
  "https://www.giantbomb.com/api/search")

(defn try-search
  "Try to GET from Giantbomb server"
  [query resources]
  (try
    (client/get gb-uri {:query-params {:api_key gb-api
                                       :format "json"
                                       :query query
                                       :field_list "name,id,image,platforms,deck"
                                       :resources resources}
                        :headers {:user-agent "ClojureGiantBombs"}
                        :accept :json})
    (catch Exception e
      (println "Exception: " (.getMessage e)))))

(defn search-gb
  "Helper function to return body response"
  [{:strs [query resources]}]
  (let [response (try-search query resources)]
    (:body response)))

(defn checkout-cart
  "Pring a checkout request"
  [{:keys [data] :as body}]
  (println "Your request to rent is completed, but wait! we have no db yet!\n" data)
  body)
