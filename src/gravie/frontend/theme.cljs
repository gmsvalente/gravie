(ns gravie.frontend.theme
  (:require [reagent-mui.styles :refer [theme-provider create-theme]]
            [reagent-mui.material.css-baseline :refer [css-baseline]]
            [reagent-mui.colors :as colors]
            [re-frame.core :as rf]
            [gravie.frontend.subs :as subs]))


(def system-theme-mode
  (let [mode (.. js/window
                 (matchMedia "(prefers-color-scheme: dark)")
                 -matches)]
    (if mode "dark" "light")))



(def custom-theme
  {:palette {:primary {:main "#176fd9"}
             :secondary {:main "#ec2024"}
             :mode system-theme-mode}})



(defn with-theme [& children]
  (let [theme @(rf/subscribe [::subs/theme])]
    [theme-provider (create-theme (clj->js theme))
     [css-baseline]
     (into [:<>] children)]))

