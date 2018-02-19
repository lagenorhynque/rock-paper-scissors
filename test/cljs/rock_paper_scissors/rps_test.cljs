(ns rock-paper-scissors.rps-test
  (:require [cljs.spec.test.alpha :as stest :include-macros true]
            [cljs.test :as t :include-macros true]
            [rock-paper-scissors.rps :as sut]
            [rock-paper-scissors.rps.specs]))

(t/use-fixtures
  :once {:before #(stest/instrument)})

(t/deftest test-fight
  (t/testing "rock-paper-scissors"
    (t/is (= ::sut/win (sut/fight ::sut/rock ::sut/scissors)))
    (t/is (= ::sut/lose (sut/fight ::sut/scissors ::sut/rock)))
    (t/is (= ::sut/draw (sut/fight ::sut/paper ::sut/paper)))))
