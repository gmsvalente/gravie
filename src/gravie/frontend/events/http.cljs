(ns gravie.frontend.events.http
  (:require [ajax.core :as ajax]
            [day8.re-frame.http-fx]
            [re-frame.core :as rf]))

(rf/reg-event-db
 ::success
 (fn [db [_ response]]
   (assoc db :response response)))

(rf/reg-event-db
 ::error
 (fn [db [_ response]]
   (assoc db :response response)))

(rf/reg-event-fx
 ::request
 (fn [_ [_ search-string type]]
   {:http-xhrio {:method :get
                 :uri "/search/"
                 :params {:query search-string
                          :resources type}
                 :format (ajax/json-request-format )
                 :response-format (ajax/json-response-format  {:keywords? true})
                 :on-success [::success]
                 :on-failure [::error]}}))
