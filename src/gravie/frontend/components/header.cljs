(ns gravie.frontend.components.header
  (:require [re-frame.core :as rf]
            [reagent-mui.styles :refer [styled]]
            [reagent-mui.material.app-bar :refer [app-bar]]
            [reagent-mui.material.toolbar :refer [toolbar]]
            [reagent-mui.material.icon-button :refer [icon-button]]
            [reagent-mui.material.typography :refer [typography]]
            [reagent-mui.icons.dark-mode :refer [dark-mode]]
            [gravie.frontend.utils :refer [<sub >evt]]
            [gravie.frontend.subs :as subs]
            [gravie.frontend.events.theme :as theme]
            [gravie.frontend.events.db :as db]))

(def giantbomb-logo-src "https://camo.githubusercontent.com/b7216da3d2ecd6101cc024529ef2b5619ba3480a8a7913855817965741a6480b/68747470733a2f2f75706c6f61642e77696b696d656469612e6f72672f77696b6970656469612f656e2f342f34622f4769616e745f426f6d625f6c6f676f2e706e67")

(def gravie-logo-src "https://www.gravie.com/wp-content/uploads/2020/08/Blue-Gravie-Logo_400x160.png")

(defn giantbomb-title []
  [:div {:class "gb-box-title"}
   [typography {:class "giantbomb-title"
                :style {:color "red"}}
    "GIANT"]
   [typography {:class "giantbomb-title"}
    "BOMB"]])

(defn gravie-title []
  [:div 
   [typography {:class "gravie-title"} "Gravie"]] )


(defn giantbomb-logo []
  [:img {:class "giantbomb-logo"
         :src giantbomb-logo-src
         :on-error #(>evt [::db/set-logo :giantbomb (giantbomb-title)])}])

(defn gravie-logo []
      [:img {:class "gravie-logo"
             :src gravie-logo-src
             :on-error #(>evt [::db/set-logo  :gravie (gravie-title)])}])

(>evt [::db/set-logo :giantbomb (giantbomb-logo)])
(>evt [::db/set-logo :gravie (gravie-logo)])


(defn header-styles [theme]
  {".bar" {:position "relative"
           :z-indez 1000}
   ".header" {:display "flex"
              :justify-content "space-between"
              :align-items "center"
              :height "100px"}
   ".logo-box" {:display "flex"
                :width "340px"
                :justify-content "space-around"
                :align-items "center"}
   ".gravie-logo" {:width "127px"
                   :height "36px"}
   ".gravie-title" {:font-family "Rajdhani"
                    :font-size 40
                    :color "#176fd9"}
   ".gb-box-title" {:display "flex"
                    :justify-content "center"
                    :align-items "center"}
   ".giantbomb-logo" {:width "100px"
                      :heidht "24px"}
   ".giantbomb-title" {:font-family "Luckiest Guy"
                       :font-size 30}})

(defn header* [{:keys [class-name]}]
  [:div {:class class-name }
   [app-bar {:class "bar"}
    [toolbar {:class "header"}
     [:div {:class "logo-box"}
      @(rf/subscribe [::subs/gravie-logo])
      @(rf/subscribe [::subs/gb-logo])]
     [:div 
      [icon-button {:on-click #(rf/dispatch [::theme/change-theme-mode])} [@(rf/subscribe [::subs/mode-icon])]]]]]])

(def header (styled header* header-styles))
