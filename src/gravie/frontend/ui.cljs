(ns gravie.frontend.ui
  (:require [gravie.frontend.theme :refer [with-theme]]
            [gravie.frontend.components.header :refer [header]]
            [gravie.frontend.components.cart :refer [cart]]
            [gravie.frontend.components.search-bar :refer [search-bar]]
            [gravie.frontend.components.result-view :refer [result-view]]))

(defn app-ui []
  [with-theme
   [header]
   [cart]
   [search-bar]
   [result-view]])
