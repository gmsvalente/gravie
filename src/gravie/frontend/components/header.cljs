(ns gravie.frontend.components.header
  (:require [reagent-mui.styles :refer [styled]]
            [reagent-mui.material.app-bar :refer [app-bar]]
            [reagent-mui.material.toolbar :refer [toolbar]]
            [reagent-mui.material.icon-button :refer [icon-button]]
            [reagent-mui.material.fab :refer [fab]]
            [reagent-mui.icons.dark-mode :refer [dark-mode]]
            [reagent-mui.icons.light-mode :refer [light-mode]]
            [reagent-mui.icons.shopping-cart :refer [shopping-cart]]))

(def giantbomb-logo-src "https://camo.githubusercontent.com/b7216da3d2ecd6101cc024529ef2b5619ba3480a8a7913855817965741a6480b/68747470733a2f2f75706c6f61642e77696b696d656469612e6f72672f77696b6970656469612f656e2f342f34622f4769616e745f426f6d625f6c6f676f2e706e67")

(def gravie-logo-src "https://www.gravie.com/wp-content/uploads/2020/08/Blue-Gravie-Logo_400x160.png")

(defn header-styles [theme]
  {".bar" {:position "relative"
           :z-indez 1000}
   ".header" {:display "flex"
              :justify-content "space-between"
              :align-items "center"}
   ".images-list" {:display "flex"
                   :margin "10px"
                   :width "240px"
                   :justify-content "space-between"
                   :align-items "center"}
   })


(defn header* [{:keys [class-name]}]
  [:div {:class class-name }
   [app-bar {:class "bar"}
    [toolbar {:class "header"}
     [:div {:class "images-list"}
      [:img {:src gravie-logo-src
             :width "127px"
             :height "36px"}]
      [:img {:width "100px"
             :heidht "24px"
             :src giantbomb-logo-src}]]

                                        ;typography {:class "header-title"} "Gravie GiantBomb"
       
     [:div 
      [icon-button {:color "primary"} [dark-mode]]
      [fab {:color "secondary"}
       [shopping-cart]]]]]])

(def header (styled header* header-styles))
