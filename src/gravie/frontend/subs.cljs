(ns gravie.frontend.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
 ::theme
 (fn [db]
   (:theme db)))

(rf/reg-sub
 ::gb-logo
 (fn [db]
   (get-in db [:ui :logo :giantbomb])))

(rf/reg-sub
 ::gravie-logo
 (fn [db]
   (get-in db [:ui :logo :gravie])))



