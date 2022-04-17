(ns gravie.frontend.events.db
  (:require [re-frame.core :as rf]
            [gravie.frontend.theme :refer [custom-theme]]))

(def init-db {:theme custom-theme
              :user {}
              :cart {}
              :games {}})

(rf/reg-event-db
 ::init-db
 (fn [_]
   {:app init-db}))

