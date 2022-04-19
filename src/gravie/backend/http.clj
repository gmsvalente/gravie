(ns gravie.backend.http
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]))

(def api "5af4db5be8e3d4d626e3640e67a79d12dd5e6482")

(def gb-uri "https://www.giantbomb.com/api/search/")

(defn create-uri [query resources]
  (str gb-uri "?api_key=" api "&format=json&query=" query "&resources=" resources))


(defn get-search [uri]
  (try
    (client/get uri {:headers {:user-agent "ClojureGiantBombs"}
                     :as :json})
    (catch Exception e
      {:body (json/read-json (:body (ex-data e)))})))

(defn search-gb [{:strs [query resources] :as qp}]
  (let [uri (create-uri query resources)
        response (get-search uri)]
    (:body response)))
