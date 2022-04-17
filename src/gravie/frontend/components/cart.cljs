(ns gravie.frontend.components.cart
  (:require [reagent-mui.styles :refer [styled]]
            [reagent-mui.material.fab :refer [fab]]
            [reagent-mui.icons.shopping-cart :refer [shopping-cart]]))

(defn cart-style [theme]
  {".cart-box" {:margin-right "15px"
                :margin-top "-9px"
                :float "right"}
   ".cart-button" {:z-index 2000}})

(defn cart* [{:keys [class-name]}]
  [:div {:class class-name}
   [:div {:class "cart-box"}
    [fab {:class "cart-button"
          :color "secondary"}
     [shopping-cart]]]])

(def cart (styled cart* cart-style))
