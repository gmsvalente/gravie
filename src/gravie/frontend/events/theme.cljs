(ns gravie.frontend.events.theme
  (:require [re-frame.core :as rf]
            [gravie.frontend.theme :as theme]))

(defn change-mode [mode]
  (condp = mode
    "light" "dark"
    "dark" "light"
    :else theme/system-theme-mode))

(rf/reg-event-db
 ::change-theme-mode
 (fn [db _]
   (update-in db [:theme :palette :mode] change-mode)))
