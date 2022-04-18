(ns gravie.backend.http
  (:require [clj-http.client :as client]))

(def api "5af4db5be8e3d4d626e3640e67a79d12dd5e6482")

(def uri "https://www.giantbomb.com/api/search/")

(defn search-api [query resources]
  (let [uri (str uri "?api_key=" api "&format=json&query=" query "&resources=" resources)]
    (println ">>> " uri)
    (-> uri
        (client/get {:headers {:user-agent "ClojureGiantBombs"}
                     :as :json})
     :body)))

