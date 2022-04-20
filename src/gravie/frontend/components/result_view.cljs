(ns gravie.frontend.components.result-view
  (:require [reagent-mui.styles :refer [styled]]
            [reagent-mui.material.grid :refer [grid]]
            [gravie.frontend.components.game-view :refer [game-view]]
            [gravie.frontend.utils :refer [<sub]]
            [gravie.frontend.subs :as subs]))

(defn result-view-style
  "Result view style map"
  [theme]
  {".results" {:position "absolute"
               :margin-top "250px"
               :padding "17px"}})

(defn result-view*
  "Reagent result-view"
  [{:keys [class-name]}]
  [:div {:class class-name}
   [grid {:class "results"
          :container true}
    (for [game (<sub [::subs/results])]
      ^{:key (:id game)}
      [grid {:item true
             :md 4 :sm 6 :xs 12}
       [game-view {:game game}]])]])


(def result-view
  "Styled result-view"
  (styled  result-view* result-view-style))
