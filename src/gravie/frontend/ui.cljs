(ns gravie.frontend.ui
  (:require [reagent.core :as r]
            [gravie.frontend.theme :refer [with-theme]]))

(defn app-ui []
  (with-theme
    [:div
     [:h1 "Gravie GiantBomb"]]))

