(ns gravie.frontend.utils
  (:require [re-frame.core :as rf]))

;;; LIN (lambdaisland naming) :)
;;; https://lambdaisland.com/blog/11-02-2017-re-frame-form-1-subscriptions

(def <sub (comp deref rf/subscribe))
(def >evt rf/dispatch)

(defn get-key
  "Keycode helper"
  [^js e]
  (.. e -keyCode))

(defn tgt-val
  "Target value helper"
  [^js e]
  (.. e -target -value))
