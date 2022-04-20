(ns gravie.frontend.utils
  (:require [re-frame.core :as rf]))

(def <sub (comp deref rf/subscribe))

(defn get-key
  "Keycode helper"
  [^js e]
  (.. e -keyCode))

(defn tgt-val
  "Target value helper"
  [^js e]
  (.. e -target -value))
