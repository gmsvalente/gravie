(ns gravie.frontend.render
  (:require [reagent.dom :as rdom]
            [gravie.frontend.ui :refer [app-ui]]
            [gravie.frontend.events :refer [initialize-db]]))

(defn ^:dev/after-load render []
  (rdom/render app-ui (.. js/document (getElementById "root"))))

(defn init []
  (initialize-db)
  (render))
