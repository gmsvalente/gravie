(ns gravie.frontend.events.http
  (:require [ajax.core :as ajax]
            [day8.re-frame.http-fx]
            [re-frame.core :as rf]))

;;; on sucess-get insert app-db search-response
(rf/reg-event-db
 ::success-get
 (fn [db [_ response]]
   (assoc db :search-response response)))

;;; on error-get insert app-db error-response
(rf/reg-event-db
 ::error-get
 (fn [db [_ response]]
   (assoc db :error-response response)))

;;; http server get request
(rf/reg-event-fx
 ::request-get
 (fn [_ [_ search-string type]]
   {:http-xhrio {:method :get
                 :uri "/search"
                 :params {:query search-string
                          :resources type}
                 :format (ajax/json-request-format )
                 :response-format (ajax/json-response-format  {:keywords? true})
                 :on-success [::success-get]
                 :on-failure [::error-get]}}))
