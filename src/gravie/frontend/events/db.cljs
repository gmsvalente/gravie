(ns gravie.frontend.events.db
  (:require [re-frame.core :as rf]
            [gravie.frontend.theme :refer [custom-theme]]))

(def init-db {:theme custom-theme
              :ui {:logo {:giantbomb nil
                          :gravie nil}
                   :checkout {:open? false}}
              :cart []
              :games {}})

;;; populate re-frame app-db event
(rf/reg-event-db
 ::init-db
 (fn [_]
   init-db))

;;; set logo reagent [:img] element event
(rf/reg-event-db
 ::set-logo
 (fn [db [_ in logo]]
   (assoc-in db [:ui :logo in] logo)))
