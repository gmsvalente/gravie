(ns gravie.frontend.utils)

(defn get-key [e]
  (.. e -keyCode))

(defn tgt-val [e]
  (.. e -target -value))
