(ns gravie.frontend.events.cart
  (:require [re-frame.core :as rf]))

(rf/reg-event-db
 ::add-to-cart
 (fn [db [_ game]]
   (update db :cart conj game )))


(rf/reg-event-db
 ::delete-item
 (fn [db [_ item-uuid]]
   (update db :cart #(remove (fn [u] (= (:uuid u) item-uuid)) %))))
