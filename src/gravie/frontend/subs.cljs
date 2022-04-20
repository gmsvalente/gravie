(ns gravie.frontend.subs
  (:require [re-frame.core :as rf]
            [reagent-mui.icons.dark-mode :refer [dark-mode]]
            [reagent-mui.icons.light-mode :refer [light-mode]]))

(rf/reg-sub
 ::results
 (fn [db]
   (-> db :search-response :results)))

(rf/reg-sub
 ::theme
 (fn [db]
   (:theme db)))

(rf/reg-sub
 ::theme-mode
 :<- [::theme]
 (fn [theme]
   (get-in theme [:palette :mode])))

(rf/reg-sub
 ::mode-icon
 :<- [::theme-mode]
 (fn [mode]
   (condp = mode
     "light" dark-mode
     "dark" light-mode
     :else dark-mode)))


(rf/reg-sub
 ::gb-logo
 (fn [db]
   (get-in db [:ui :logo :giantbomb])))

(rf/reg-sub
 ::gravie-logo
 (fn [db]
   (get-in db [:ui :logo :gravie])))


