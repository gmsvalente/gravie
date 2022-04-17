(ns gravie.frontend.ui
  (:require [reagent.core :as r]
            [gravie.frontend.theme :refer [with-theme]]
            [gravie.frontend.components.header :refer [header]]
))

(defn app-ui []
  (with-theme
    [header]))
