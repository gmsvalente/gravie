(ns gravie.frontend.events.cart
  (:require [re-frame.core :as rf]))

;;; add game to cart
(rf/reg-event-db
 ::add-to-cart
 (fn [db [_ game]]
   (update db :cart conj game )))

;;; delete item in cart
(rf/reg-event-db
 ::delete-item
 (fn [db [_ item-uuid]]
   (update db :cart #(remove (fn [u] (= (:uuid u) item-uuid)) %))))

;;; clear cart
(rf/reg-event-db
 ::clear-cart
 (fn [db _]
   (assoc db :cart [])))
