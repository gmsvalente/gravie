(ns gravie.frontend.events.cart
  (:require [re-frame.core :as rf]))

(rf/reg-event-db
 ::add-to-cart
 (fn [db [_ game]]
   (update db :cart conj game )))
