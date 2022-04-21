(ns gravie.frontend.events.result-view
  (:require [re-frame.core :as rf]))

(rf/reg-event-db
 ::add-view
 (fn [db [_ view]]
   (assoc-in db [:ui :result-view] view)))
