(ns gravie.frontend.components.checkout
  (:require [reagent-mui.styles :refer [styled]]
            [reagent-mui.material.dialog :refer [dialog]]
            [reagent-mui.material.dialog-title :refer [dialog-title]]
            [reagent-mui.material.dialog-content :refer [dialog-content]]
            [reagent-mui.material.dialog-actions :refer [dialog-actions]]
            [reagent-mui.icons.delete-forever :refer [delete-forever]]
            [reagent-mui.material.button :refer [button]]
            [reagent-mui.material.icon-button :refer [icon-button]]
            [reagent-mui.material.paper :refer [paper]]
            [reagent-mui.material.typography :refer [typography]]
            [gravie.frontend.utils :refer [<sub >evt]]
            [gravie.frontend.subs :as subs]
            [gravie.frontend.events.checkout :as checkout]
            [gravie.frontend.events.cart :as cart]))

;;; does not work possible because dialog mui element
(def checkout-styles
  "Checkout styles map"
  {})

(defn item-content
  "Items dialog contentent"
  [{:keys [name price image uuid]}]
  [:div {:style {:display "flex"
                 :justify-content "space-between"
                 :width "100%"}}
   [paper {:style {:display "flex"
                   :align-items "center"
                   :padding "5px"
                   :margin "5px"
                   :width "100%"}}
    [:img {:src image}]
    [:div {:style {:display "flex"
                   :justify-content "space-between"
                   :width "100%"
                   :margin-left "5px"}}
     [typography name]
     [typography {:style {:margin-left "97px"}}
      (str "$ " price)]]]
   [icon-button {:on-click #(>evt [::cart/delete-item uuid])} [delete-forever]]])

(defn checkout-dialog*
  "Reagent checkout"
  [{:keys [class-name]}]
  (let [open? (<sub [::subs/checkout-open?])
        on-close-fn #(>evt [::checkout/close])
        items (<sub [::subs/cart-items])
        items-count (<sub [::subs/cart-count])
        total (<sub [::subs/cart-total-price])]
    [:div {:class class-name}
     [dialog {:open open?
              :on-close on-close-fn
              :scroll :paper}
      [dialog-title 
       [typography (str "You have " items-count " items in cart")]
       [typography (str "Total Cost: $" total)]]
      [dialog-content {:dividers true}
       (if (empty? items)
         [typography "NO ITEMS TO RENT"]
         (for [item items]
           ^{:key (:uuid item)}
           [item-content item]))]
      [dialog-actions
       [button {:on-click on-close-fn
                :variant "outlined"
                :color "warning"} "Rent more!"]
       [button {:variant "contained"
                :color "success"} "Checkout"]]]]))

(def checkout-dialog
  "Styled checkout"
  (styled checkout-dialog* checkout-styles))
