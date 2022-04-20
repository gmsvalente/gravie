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
            [gravie.frontend.events.checkout :as checkout]
            [gravie.frontend.events.cart :as cart]))

(def checkout-styles
  {})

(defn item-content [{:keys [name price image uuid]}]
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
   [icon-button {:on-click #(rf/dispatch [::cart/delete-item uuid])} [delete-forever]]])

(defn checkout-dialog* [{:keys [class-name]}]
  (let [open? (rf/subscribe [::subs/checkout-open?])
        on-close-fn #(rf/dispatch [::checkout/close])
        items (rf/subscribe [::subs/cart-items])
        total (fn [items]
                (->> items
                     (map :price)
                     (map #(js/parseFloat %))
                     (reduce +)))]
    (fn []
      [:div {:class class-name}
       [dialog {:open @open?
                :on-close on-close-fn
                :scroll :paper}
        [dialog-title 
         [typography (str "You selected " (count @items) " items")]
         [typography (str "Total Cost: $" (.toFixed (total @items) 2))]]
        [dialog-content {:dividers true}
         (if (empty? @items)
           [typography "NO ITEMS TO RENT"]
           (for [item @items]
             ^{:key (:uuid item)}
             [item-content item]))]]])))

(def checkout-dialog (styled checkout-dialog* checkout-styles))
