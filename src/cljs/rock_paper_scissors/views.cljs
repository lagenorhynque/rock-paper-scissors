(ns rock-paper-scissors.views
  (:require [re-frame.core :as re-frame]
            [rock-paper-scissors.subs :as subs]
            ))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div "Hello from " @name]))
