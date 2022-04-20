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
            [gravie.frontend.utils :refer [>evt <sub]]
            [gravie.frontend.events.cart :as cart]
            [gravie.frontend.subs :as subs]))

(defn show-platforms
  "Show platforms helper fn"
  [platforms]
  [:div {:class "platforms"}
   (for [{:keys [name id]} platforms]
     ^{:key id} [typography name])])

(defn game-view-styles
  "Game-view styles map"
  [{:keys [theme]}]
  {".info" {:display "flex"
            :margin "7px"
            :min-height "200px"
            :justify-content "space-around"
            :align-items "center"
            :color "black"
            :background-color "silver"}
   ".rent-box" {:display "flex"
                :flex-direction "column"
                :min-height "150px"
                :max-height "200px"
                :justify-content "space-around"
                :align-items "center"}
   ".card" {:margin "10px"
            :background (-> theme :palette :primary :dark)
            :min-height "350px"}
   ".platforms" {:margin-top "7px"
                 :max-height "200px"
                 :overflow "auto"}
   ".card-header" {:background (-> theme :palette :primary :dark)
                   :color "black"}
   ".card-content" {:background (-> theme :palette :primary :light)
                    :color "black"}})

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
                     :image (:medium-url image)}]
        [card-content {:class "card-content"} deck]
        [paper {:class "info"}
         [card-content
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
