(ns gravie.frontend.subs
  (:require [re-frame.core :as rf]
            [reagent-mui.icons.dark-mode :refer [dark-mode]]
            [reagent-mui.icons.light-mode :refer [light-mode]]))

;;; check if checkout modal element open?
(rf/reg-sub
 ::checkout-open?
 (fn [db]
   (-> db :ui :checkout :open?)))

;;; return the cart
(rf/reg-sub
 ::cart-items
 (fn [db]
   (:cart db)))

;;; return cart total price
(rf/reg-sub
 ::cart-total-price
 :<- [::cart-items]
 (fn [items]
   (let [total (->> items
                    (map :price)
                    (map #(js/parseFloat %))
                    (reduce +))]
     (.toFixed total 2))))

;;; return cart items count
(rf/reg-sub
 ::cart-count
 :<- [::cart-items]
 (fn [items]
   (count items)))

;;; return the search results
(rf/reg-sub
 ::results
 (fn [db]
   (-> db :search-response :results)))

;;; return mui theme
(rf/reg-sub
 ::theme
 (fn [db]
   (:theme db)))

;;; return mui theme mode
(rf/reg-sub
 ::theme-mode
 :<- [::theme]
 (fn [theme]
   (get-in theme [:palette :mode])))

;;; infer the mode icon from theme mode
(rf/reg-sub
 ::mode-icon
 :<- [::theme-mode]
 (fn [mode]
   (condp = mode
     "light" dark-mode
     "dark" light-mode
     :else dark-mode)))

;;; get giantbomb logo or fallback
(rf/reg-sub
 ::gb-logo
 (fn [db]
   (get-in db [:ui :logo :giantbomb])))

;;; get gravie logo or fallback
(rf/reg-sub
 ::gravie-logo
 (fn [db]
   (get-in db [:ui :logo :gravie])))


