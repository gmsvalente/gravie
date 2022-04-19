(ns gravie.frontend.components.result-view
  (:require [reagent-mui.styles :refer [styled]]
            [reagent-mui.material.grid :refer [grid]]
            [reagent-mui.material.paper :refer [paper]]
            [gravie.frontend.components.game-view :refer [game-view]]
            [re-frame.core :as rf]
            [gravie.frontend.subs :as subs]))

(defn result-view-style [theme]
  {".results" {:margin "17px"
               :border "2px solid red"}})

(defn result-view* [{:keys [class-name]}]
  (let [results (rf/subscribe [::subs/results])]
    (fn []
      [:div {:class class-name}
       [grid {:class "results"
              :container true}
        (for [game @results]
          ^{:key (:id game)}
          [grid {:item true
                 :xl 2 :lg 3 :md 4 :sm 6 :xs 12}
           [game-view {:game game}]])]])))


(def result-view (styled  result-view* result-view-style))
