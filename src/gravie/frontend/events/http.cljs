(ns gravie.frontend.events.http
  (:require [ajax.core :as ajax]
            [day8.re-frame.http-fx]
            [re-frame.core :as rf]))

(rf/reg-event-db
 ::results-view
 (fn [db [_ type]]
   (assoc db :result-view type)))

(rf/reg-event-fx
 ::success
 (fn [{:keys [db]} [_ type response]]
   {:db (-> db
            (assoc :search-response response)
            (assoc-in [:search-response :type] type ))
    :fx [[:dispatch [::results-view type]]]}))

(rf/reg-event-db
 ::error
 (fn [db [_ response]]
   (assoc db :error-response response)))

(rf/reg-event-fx
 ::request
 (fn [_ [_ search-string type]]
   {:http-xhrio {:method :get
                 :uri "/search"
                 :params {:query search-string
                          :resources type}
                 :format (ajax/json-request-format )
                 :response-format (ajax/json-response-format  {:keywords? true})
                 :on-success [::success (keyword type)]
                 :on-failure [::error]}}))
