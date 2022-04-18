(ns gravie.frontend.ui
  (:require [reagent.core :as r]
            [gravie.frontend.theme :refer [with-theme]]
            [gravie.frontend.components.header :refer [header]]
            [gravie.frontend.components.cart :refer [cart]]
            [gravie.frontend.components.search-bar :refer [search-bar]]
))

(defn app-ui []
  (with-theme
    [header]
    [cart]
    [:div {:style {:margin "10 auto"}}
     [search-bar]]))
