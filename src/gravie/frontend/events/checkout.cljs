(ns gravie.frontend.events.checkout
  (:require [re-frame.core :as rf]))

(rf/reg-event-db
 ::close
 (fn [db _]
   (assoc-in db [:ui :checkout :open?] false)))

(rf/reg-event-db
 ::open
 (fn [db _]
   (assoc-in db [:ui :checkout :open?] true)))
