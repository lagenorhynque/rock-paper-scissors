(ns rock-paper-scissors.cofx
  (:require [re-frame.core :as re-frame]
            [rock-paper-scissors.rps :as rps]))

(re-frame/reg-cofx
  ::select-enemy-hand
  (fn [cofx _]
    (assoc cofx
           :enemy-hand (rps/->hand (rand-int 3)))))
