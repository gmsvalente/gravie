(ns gravie.frontend.components.result-view
  (:require [reagent-mui.styles :refer [styled]]
            [reagent-mui.material.grid :refer [grid]]
            [reagent-mui.material.typography :refer [typography]]
            [reagent-mui.material.paper :refer [paper]]
            [gravie.frontend.components.game-view :refer [game-view]]
            [gravie.frontend.utils :refer [<sub]]
            [gravie.frontend.subs :as subs]))

(defn result-view-style
  "Result view style map"
  [theme]
  {".results" {:position "absolute"
               :margin-top "150px"
               :padding "17px"}
   ".not-found" {:padding "20px"}})

(defn game-result-view []
  (let [games (<sub [::subs/results])]
    [grid {:class "results"
           :container true}
     (for [game games]
       ^{:key (:id game)}
       [grid {:item true
              :xl 2 :lg 3 :md 4 :sm 6 :xs 12}
        [game-view {:game game}]])]))

(defn notfound-result-view []
  [:div {:class "results"}
   [paper {:class "not-found"}
    [typography  "No games found! Please try again!"]]])

(defn result-view*
  "Reagent result-view"
  [{:keys [class-name]}]
  (let [view (<sub [::subs/result-view])]
    [:div {:class class-name}
     (when view
       [view])]))


(def result-view
  "Styled result-view"
  (styled  result-view* result-view-style))
