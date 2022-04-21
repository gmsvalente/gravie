(ns gravie.frontend.components.header-box
  (:require [reagent-mui.styles :refer [styled]]
            [reagent-mui.material.box :refer [box]]
            [reagent-mui.colors :as colors]))

(defn header-box-styles
  "Styles map"
  [{:keys [theme]}]
  {".box"  {:position "fixed"
            :width "100%"
            :height "170px"
            :z-index 500
            :background-color (-> theme :palette :primary :dark)}
})

(defn header-box* [{:keys [class-name children]}]
  [:div {:class class-name}
   [box {:class "box"}
    (reduce conj [:<>] children)]])



(def header-box (styled header-box* header-box-styles))
