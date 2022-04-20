(ns gravie.frontend.render
  (:require [reagent.dom :as rdom]
            [gravie.frontend.utils :refer [>sync]]
            [gravie.frontend.ui :refer [app-ui]]
            [gravie.frontend.events.db :as db]))

(defn ^:dev/after-load render
  "Render the app"
  []
  (rdom/render app-ui (.. js/document (getElementById "root"))))

(defn init
  "Initialize re-frame db and render"
  []
  (>sync [::db/init-db])
  (render))
