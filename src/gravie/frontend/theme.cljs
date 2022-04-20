(ns gravie.frontend.theme
  (:require [reagent-mui.styles :refer [theme-provider create-theme]]
            [reagent-mui.material.css-baseline :refer [css-baseline]]
            [gravie.frontend.utils :refer [<sub]]
            [gravie.frontend.subs :as subs]))


(def system-theme-mode
  "Get system color scheme"
  (let [mode (.. js/window
                 (matchMedia "(prefers-color-scheme: dark)")
                 -matches)]
    (condp = mode
      true "dark"
      false "light"
      :else "light")))


(def custom-theme
  "Custom mui theme"
  {:palette {:primary {:main "#176fd9"}
             :secondary {:main "#ec2024"}
             :mode system-theme-mode}})

(defn with-theme
  "Mui theme wraper"
  [& children]
  (let [theme (<sub [::subs/theme])]
    (fn []
      [theme-provider (create-theme (clj->js theme))
       [css-baseline]
       (into [:<>] children)])))
