(ns gravie.frontend.components.cart
  (:require [reagent-mui.styles :refer [styled]]
            [reagent-mui.material.fab :refer [fab]]
            [reagent-mui.material.badge :refer [badge]]
            [reagent-mui.icons.shopping-cart :refer [shopping-cart]]
            [gravie.frontend.utils :refer [<sub >evt]]
            [gravie.frontend.subs :as subs]
            [gravie.frontend.events.checkout :as checkout]))

(defn cart-style
  "Cart style map"
  [theme]
  {".cart-box" {:margin-right "15px"
                :margin-top "-9px"
                :float "right"}
   ".cart-button" {:z-index 2000}})

(defn cart*
  "Reagent cart button"
  [{:keys [class-name]}]
  [:div {:class class-name}
   [:div {:class "cart-box"}
    [badge {:anchor-origin {:vertical "bottom"
                            :horizontal "right"}
            :color "success"
            :badge-content (<sub [::subs/cart-count])}
     [fab {:class "cart-button"
           :color "secondary"
           :on-click #(>evt [::checkout/open])}
      [shopping-cart]]]]])

(def cart
  "Styled cart"
  (styled cart* cart-style))
