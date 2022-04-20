(ns gravie.frontend.components.game-view
  (:require [reagent-mui.styles :refer [styled]]
            [reagent-mui.material.paper :refer [paper]]
            [reagent-mui.material.card :refer [card]]
            [reagent-mui.material.card-header :refer [card-header]]
            [reagent-mui.material.card-media :refer [card-media]]
            [reagent-mui.material.card-content :refer [card-content]]
            [reagent-mui.material.grid :refer [grid]]
            [reagent-mui.material.box :refer [box]]
            [reagent-mui.material.typography :refer [typography]]
            [reagent-mui.material.fab :refer [fab]]
            [reagent-mui.icons.add-shopping-cart :refer [add-shopping-cart]]
            [re-frame.core :as rf]
            [gravie.frontend.events.cart :as cart]))

(defn show-platforms [platforms]
  [:div {:class "platforms"}
   (for [{:keys [name id]} platforms]
     ^{:key id} [typography name])])

(def game-view-styles 
  {".info" {:display "flex"
            :margin "7px"
            :min-height "200px"
            :justify-content "space-around"
            :align-items "center"
            :background-color "gray"}
   ".rent-box" {:display "flex"
                :flex-direction "column"
                :min-height "150px"
                :max-height "200px"
                :justify-content "space-around"
                :align-items "center"}
   ".card" {:margin "10px"
            :min-height "350px"}
   ".platforms" {:margin-top "7px"
                 :max-height "200px"
                 :overflow "auto"}
   ".image" {:height "50%"}})

(defn game-view* [{:keys [class-name game]}]
  (let [{:keys [name platforms image deck]} game
        price (.toFixed (rand 100) 2)
        to-cart (-> game
                    (select-keys [:name :id])
                    (merge {:price price
                            :image (:tiny-url image)}))]
    (fn []
      [:div {:class class-name}
       [card {:class "card"}
        [card-header {:title name}]
        [:div {:class "image"}
         [card-media {:component "img"
                      :image (:medium-url image)
                      :height "100%"}]]
        [card-content deck]
        [paper {:class "info"}
         [card-content
          [typography "Platforms:"]
          [show-platforms platforms]]
         [card-content {:class "rent-box"}
          [typography "Price:"]
          [typography (str "$" price)]
          [fab {:on-click #(rf/dispatch [::cart/add-to-cart to-cart])}
           [add-shopping-cart]]]]]])))


(def game-view (styled game-view* game-view-styles))
