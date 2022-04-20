(ns gravie.backend.http
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]))

(def api "5af4db5be8e3d4d626e3640e67a79d12dd5e6482")

(def gb-uri "https://www.giantbomb.com/api/search/")

(defn create-uri [query resources]
  (str gb-uri "?api_key=" api "&format=json&query=" query "&resources=" resources))


(defn try-search [query resources]
  (try
    (client/get gb-uri {:query-params {:api_key api
                                       :format "json"
                                       :query query
                                       :field_list "name,id,image,platforms,description,deck"
                                       :resources resources}
                        :headers {:user-agent "ClojureGiantBombs"}
                        :as :json})
    (catch Exception e
      (-> (ex-data e)
          (update :body json/read-json)))))

(defn search-gb [{:strs [query resources]}]
  (let [response (try-search query resources)]
    (:body response)))
