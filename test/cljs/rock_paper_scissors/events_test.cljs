(ns rock-paper-scissors.events-test
  (:require [cljs.spec.test.alpha :as stest :include-macros true]
            [cljs.test :as t :include-macros true]
            [day8.re-frame.test :as re-frame.test :include-macros true]
            [re-frame.core :as re-frame]
            [rock-paper-scissors.cofx :as cofx]
            [rock-paper-scissors.db :as db]
            [rock-paper-scissors.db.specs]
            [rock-paper-scissors.events :as sut]
            [rock-paper-scissors.rps :as rps]
            [rock-paper-scissors.rps.specs]
            [rock-paper-scissors.subs :as subs]))

(t/use-fixtures
  :once {:before #(stest/instrument)})

(t/deftest test-initialize-db
  (re-frame.test/run-test-sync
   (re-frame/dispatch [::sut/initialize-db])
   (t/is ::db/start @(re-frame/subscribe [::subs/scene]))
   (t/is {:you ::rps/rock
          :enemy ::rps/rock}
         @(re-frame/subscribe [::subs/you-enemy]))))

(t/deftest test-next-game
  (re-frame.test/run-test-sync
   (re-frame/dispatch [::sut/initialize-db])
   (re-frame/dispatch [::sut/next-game])
   (t/is ::db/now-playing @(re-frame/subscribe [::subs/scene]))))

(t/deftest test-select-your-hand
  (re-frame.test/run-test-sync
   (re-frame/reg-cofx
     ::cofx/select-enemy-hand
     (fn [cofx _]
       (assoc cofx
              :enemy-hand ::rps/rock)))
   (re-frame/dispatch [::sut/initialize-db])
   (t/testing "draw"
     (re-frame/dispatch [::sut/next-game])
     (re-frame/dispatch [::sut/select-your-hand ::rps/rock])
     (t/is ::db/over @(re-frame/subscribe [::subs/scene]))
     (t/is "rock(YOU) VS rock(ENEMY)" @(re-frame/subscribe [::subs/you-enemy-hands]))
     (t/is ::rps/draw @(re-frame/subscribe [::subs/fight-result]))
     (t/is "gray" @(re-frame/subscribe [::subs/result-color])))
   (t/testing "win"
     (re-frame/dispatch [::sut/next-game])
     (re-frame/dispatch [::sut/select-your-hand ::rps/paper])
     (t/is ::db/over @(re-frame/subscribe [::subs/scene]))
     (t/is "paper(YOU) VS rock(ENEMY)" @(re-frame/subscribe [::subs/you-enemy-hands]))
     (t/is ::rps/win @(re-frame/subscribe [::subs/fight-result]))
     (t/is "red" @(re-frame/subscribe [::subs/result-color])))
   (t/testing "lose"
     (re-frame/dispatch [::sut/next-game])
     (re-frame/dispatch [::sut/select-your-hand ::rps/scissors])
     (t/is ::db/over @(re-frame/subscribe [::subs/scene]))
     (t/is "scissors(YOU) VS rock(ENEMY)" @(re-frame/subscribe [::subs/you-enemy-hands]))
     (t/is ::rps/lose @(re-frame/subscribe [::subs/fight-result]))
     (t/is "blue" @(re-frame/subscribe [::subs/result-color])))))
