(ns gravie.frontend.render
  (:require [re-frame.core :as rf ]
            [reagent.dom :as rdom]))

(defn app []
  [:div
   [:h1 "Gravie GiantBomb"]])

(defn ^:dev/after-load render []
  (rdom/render app (.. js/document (getElementById "root"))))

(defn init []
  (println "Init")
  (render))
