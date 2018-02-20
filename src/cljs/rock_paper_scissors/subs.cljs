(ns rock-paper-scissors.subs
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
  ::scene
  (fn [db _]
    (:scene db)))

(re-frame/reg-sub
  ::you-enemy
  (fn [db _]
    (select-keys db [:you :enemy])))
