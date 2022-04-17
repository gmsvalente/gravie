(ns gravie.frontend.theme
  (:require [reagent-mui.styles :refer [theme-provider create-theme]]
            [reagent-mui.material.css-baseline :refer [css-baseline]]
            [reagent-mui.colors :as colors]))


(def system-theme-mode
  (let [mode (.. js/window
                 (matchMedia "(prefers-color-scheme: dark)")
                 -matches)]
    (if mode "dark" "light")))



(def custom-theme
  {:palette {:primary colors/blue
             :secondary colors/red
             :mode system-theme-mode}})

(defn with-theme [& children]
  [theme-provider (create-theme custom-theme)
   [css-baseline]
   (into [:<>] children)])

