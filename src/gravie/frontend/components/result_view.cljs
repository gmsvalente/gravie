(ns gravie.frontend.components.result-view
  (:require [reagent-mui.styles :refer [styled]]
            [reagent-mui.material.grid :refer [grid]]
            [gravie.frontend.components.game-view :refer [game-view]]
            [re-frame.core :as rf]
            [gravie.frontend.subs :as subs]))

(defn result-view-style [theme]
  {".results" {:padding "17px"}})

(defn result-view* [{:keys [class-name]}]
  (let [games(rf/subscribe [::subs/results])]
    (fn []
      [:div {:class class-name}
       [grid {:class "results"
              :container true}
        (for [game @games]
          ^{:key (:id game)}
          [grid {:item true
                 :md 4 :sm 6 :xs 12}
           [game-view {:game game}]])]])))


(def result-view (styled  result-view* result-view-style))
