(ns gravie.frontend.components.header
  (:require [reagent-mui.styles :refer [styled]]
            [reagent-mui.material.app-bar :refer [app-bar]]
            [reagent-mui.material.toolbar :refer [toolbar]]
            [reagent-mui.material.icon-button :refer [icon-button]]
            [reagent-mui.material.typography :refer [typography]]
            [gravie.frontend.utils :refer [<sub >evt]]
            [gravie.frontend.subs :as subs]
            [gravie.frontend.events.theme :as theme]
            [gravie.frontend.events.db :as db]))

(def giantbomb-logo-src
  "Source link to giantbomb logo"
  "https://camo.githubusercontent.com/b7216da3d2ecd6101cc024529ef2b5619ba3480a8a7913855817965741a6480b/68747470733a2f2f75706c6f61642e77696b696d656469612e6f72672f77696b6970656469612f656e2f342f34622f4769616e745f426f6d625f6c6f676f2e706e67")

(def gravie-logo-src
  "Source link to gravie logo"
  "https://www.gravie.com/wp-content/uploads/2020/08/Blue-Gravie-Logo_400x160.png")

(defn giantbomb-title
  "Giantbomb title fallback"
  []
  [:div {:class "gb-box-title"}
   [typography {:class "giantbomb-title"
                :style {:color "red"}}
    "GIANT"]
   [typography {:class "giantbomb-title"}
    "BOMB"]])

(defn gravie-title
  "Gravie title fallback"
  []
  [:div 
   [typography {:class "gravie-title"} "Gravie"]] )


(defn giantbomb-logo
  "GiantBomb img logo"
  []
  [:img {:class "giantbomb-logo"
         :src giantbomb-logo-src
         :on-error #(>evt [::db/set-logo :giantbomb (giantbomb-title)])}])

(defn gravie-logo
  "Gravie img logo"
  []
  [:img {:class "gravie-logo"
         :src gravie-logo-src
         :on-error #(>evt [::db/set-logo :gravie (gravie-title)])}])

;;; send logos to rf/app-db
(>evt [::db/set-logo :giantbomb (giantbomb-logo)])
(>evt [::db/set-logo :gravie (gravie-logo)])


(defn header-styles
  "Styles map"
  [{:keys [theme]}]
  {".bar" {:position "relative"
           :width "100%"
           :z-index 1000}
   ".header" {:display "flex"
              :justify-content "space-between"
              :align-items "center"
              :height "78px"}
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

(defn header*
  "Reagent app header"
  [{:keys [class-name]}]
  [:div {:class class-name }
   [app-bar {:class "bar"}
    [toolbar {:class "header"}
     [:div {:class "logo-box"}
      (<sub [::subs/gravie-logo])
      (<sub [::subs/gb-logo])]
     [:div 
      [icon-button {:on-click #(>evt [::theme/change-theme-mode])}
       [(<sub [::subs/mode-icon])]]]]]])

(def header
  "Styled header"
  (styled header* header-styles))
