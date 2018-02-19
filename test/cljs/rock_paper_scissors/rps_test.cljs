(ns rock-paper-scissors.rps-test
  (:require [cljs.test :as t :include-macros true]
            [rock-paper-scissors.rps :as sut]))

(t/deftest test-fight
  (t/testing "rock-paper-scissors"
    (t/is (= ::sut/win (sut/fight ::sut/rock ::sut/scissors)))
    (t/is (= ::sut/lose (sut/fight ::sut/scissors ::sut/rock)))
    (t/is (= ::sut/draw (sut/fight ::sut/paper ::sut/paper)))))
