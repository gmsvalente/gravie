(ns gravie.frontend.components.game-view
  (:require [reagent-mui.styles :refer [styled]]
            [reagent-mui.material.paper :refer [paper]]
            [reagent-mui.material.card :refer [card]]
            [reagent-mui.material.card-header :refer [card-header]]
            [reagent-mui.material.card-media :refer [card-media]]
            [reagent-mui.material.card-content :refer [card-content]]
            [reagent-mui.material.typography :refer [typography]]
            [reagent-mui.material.fab :refer [fab]]
            [reagent-mui.icons.add-shopping-cart :refer [add-shopping-cart]]
            [gravie.frontend.utils :refer [>evt]]
            [gravie.frontend.events.cart :as cart]))

(defn show-platforms
  "Show platforms helper fn"
  [platforms]
  [:div {:class "platforms"}
   (for [{:keys [name id]} platforms]
     ^{:key id} [typography name])])

(defn game-view-styles
  "Game-view styles map"
  [{:keys [theme]}]
  {".card" {:margin "10px"
            :background (-> theme :palette :primary :dark)
            :height "646px"}
   ".card-header" {:background (-> theme :palette :primary :dark)
                   :height "10%"
                   :color "black"}
   ".card-content" {:background (-> theme :palette :primary :light)
                    :overflow "auto"
                    :height "28%"
                    :color "black"}
   ".info" {:display "flex"
            :height "30%"
            :align-items "flex-start"
            :color "black"
            :background-color "silver"}
   ".platforms-box" {:margin "3px"
                     :width "60%"
                     :height "200px"
                     :overflow "auto"}
   ".rent-box" {:display "flex"
                :width "40%"
                :flex-direction "column"
                :justify-content "center"
                :align-items "center"}})

(defn game-view*
  "Reagent game-view"
  [{:keys [class-name game]}]
  (let [{:keys [name platforms image deck]} game
        price (.toFixed (rand 100) 2)
        to-cart (-> game
                    (select-keys [:name :id])
                    (merge {:price price
                            :image (:tiny-url image)}))]
    (fn []
      [:div {:class class-name}
       [card {:class "card"}
        [card-header {:title name
                      :class "card-header"}]
        [card-media {:component "img"
                     :image (:medium-url image)
                     :height "40%"
                     :class ".card-image"}]
        [card-content {:class "card-content"} deck]
        [paper {:class "info"}
         [card-content {:class "platforms-box"}
          [typography "Platforms:"]
          [show-platforms platforms]]
         [card-content {:class "rent-box"}
          [typography "Price:"]
          [typography (str "$" price)]
          [fab {:on-click #(>evt [::cart/add-to-cart (assoc to-cart :uuid (random-uuid)) ])}
           [add-shopping-cart]]]]]])))


(def game-view
  "Styled game-view"
  (styled game-view* game-view-styles))
