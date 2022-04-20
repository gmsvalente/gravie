(ns gravie.frontend.components.cart
  (:require [reagent-mui.styles :refer [styled]]
            [reagent-mui.material.fab :refer [fab]]
            [reagent-mui.material.badge :refer [badge]]
            [reagent-mui.icons.shopping-cart :refer [shopping-cart]]
            [re-frame.core :as rf]
            [gravie.frontend.subs :as subs]))

(defn cart-style [theme]
  {".cart-box" {:margin-right "15px"
                :margin-top "-9px"
                :float "right"}
   ".cart-button" {:z-index 2000}})

(defn cart* [{:keys [class-name]}]
  [:div {:class class-name}
   [:div {:class "cart-box"}
    [badge {:anchor-origin {:vertical "bottom"
                            :horizontal "right"}
            :color "success"
            :badge-content @(rf/subscribe [::subs/cart-count])}
     [fab {:class "cart-button"
           :color "secondary"}
      [shopping-cart]]]]])

(def cart (styled cart* cart-style))
