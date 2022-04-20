(ns gravie.frontend.components.checkout
  (:require [reagent-mui.styles :refer [styled]]
            [reagent-mui.material.dialog :refer [dialog]]
            [reagent-mui.material.dialog-title :refer [dialog-title]]
            [reagent-mui.material.dialog-content :refer [dialog-content]]
            [reagent-mui.icons.delete-forever :refer [delete-forever]]
            [reagent-mui.material.icon-button :refer [icon-button]]
            [reagent-mui.material.paper :refer [paper]]
            [reagent-mui.material.typography :refer [typography]]
            [re-frame.core :as rf]
            [gravie.frontend.subs :as subs]
            [gravie.frontend.events.checkout :as checkout]))

(def checkout-styles
  {})

(defn item-content [{:keys [name price image id]}]
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
   [icon-button [delete-forever]]])

(defn checkout-dialog* [{:keys [class-name]}]
  (let [open? (rf/subscribe [::subs/checkout-open?])
        on-close-fn #(rf/dispatch [::checkout/close])
        items (rf/subscribe [::subs/cart-items])]
    (fn []
      [:div {:class class-name}
       [dialog {:open @open?
                :on-close on-close-fn
                :scroll :paper}
        [dialog-title 
         [typography "Your rent"]]
        [dialog-content {:dividers true}
         (for [item @items]
           [item-content item])]]])))

(def checkout-dialog (styled checkout-dialog* checkout-styles))
