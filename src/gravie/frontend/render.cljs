(ns gravie.frontend.render
  (:require [re-frame.core :as rf ]
            [reagent.dom :as rdom]
            [gravie.frontend.ui :refer [app-ui]]))



(defn ^:dev/after-load render []
  (rdom/render app-ui (.. js/document (getElementById "root"))))

(defn init []
  (println "Init")
  (render))
