(ns gravie.frontend.events
  (:require [re-frame.core :as rf]
            [gravie.frontend.events.db :as db]))

(defn initialize-db []
  (rf/dispatch-sync [::db/init-db]))

(defn set-logo [in logo]
  (rf/dispatch [::db/set-logo in logo]))
