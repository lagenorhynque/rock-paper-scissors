(ns rock-paper-scissors.subs
  (:require [re-frame.core :as re-frame]
            [rock-paper-scissors.rps :as rps]))

(re-frame/reg-sub
  ::scene
  (fn [db _]
    (:scene db)))

(re-frame/reg-sub
  ::you-enemy
  (fn [db _]
    (select-keys db [:you :enemy])))

(re-frame/reg-sub
  ::you-enemy-hands
  :<- [::you-enemy]
  (fn [{:keys [you enemy]} _]
    (str (name you) "(YOU) VS " (name enemy) "(ENEMY)")))

(re-frame/reg-sub
  ::fight-result
  :<- [::you-enemy]
  (fn [{:keys [you enemy]} _]
    (rps/fight you enemy)))

(re-frame/reg-sub
  ::result-color
  :<- [::fight-result]
  (fn [r _]
    (case r
      ::rps/win "red"
      ::rps/lose "blue"
      ::rps/draw "gray")))
