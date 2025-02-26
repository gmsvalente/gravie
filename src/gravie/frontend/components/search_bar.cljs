(ns gravie.frontend.components.search-bar
  (:require [reagent-mui.styles :refer [styled]]
            [reagent-mui.material.paper :refer [paper]]
            [reagent-mui.material.input-base :refer [input-base]]
            [reagent-mui.material.text-field :refer [text-field]]
            [reagent-mui.material.menu-item :refer [menu-item]]
            [reagent-mui.material.icon-button :refer [icon-button]]
            [reagent-mui.icons.search :refer [search]]
            [reagent.core :as r]
            [gravie.frontend.utils :refer [get-key tgt-val <sub >evt]]
            [gravie.frontend.events.http :as http]))

(def search-options
  "Options vector"
  [{:name "game"
    :key 0}])

(def search-bar-style
  "Search-bar style map"
  {".options" {:width "15ch"
               :margin "10px"}
   ".input-box" {:margin "8px 20px auto"
                 :width "80%"
                 :display "flex"
                 :align-items "center"}
   ".input" {:width "70%"
             :height "100%"
             :margin "15px"}})

(defn check-inputs [input option]
  (if (and (seq input) (seq option))
    (>evt [::http/request-get input option])
    (.. js/window (alert "Need category and search string"))))

(defn search-bar*
  "Reagent search-bar"
  [{:keys [class-name]}]
  (let [option (r/atom "")
        search-string (r/atom "")]
    (fn []
      [:div {:class class-name}
       [paper {:class "input-box"}
        [text-field {:class "options"
                     :select true
                     :label "Category"
                     :value @option
                     :on-change #(reset! option (tgt-val %))}
         (for [opt search-options]
           [menu-item {:key (:key opt)
                       :value (:name opt)} (:name opt)])]
        [icon-button {:variant "outlined"
                      :on-click #(check-inputs @search-string @option)}
         [search]]
        [input-base {:class "input"
                     :on-change #(reset! search-string (tgt-val %))
                     :on-key-down #(when (= 13 (get-key %))
                                     (check-inputs @search-string @option))
                     :placeholder (str "Search " (.toUpperCase @option) " here")}]]])))

(def search-bar
  "Styled search-bar"
  (styled search-bar* search-bar-style))
