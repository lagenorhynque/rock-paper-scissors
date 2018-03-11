(ns rock-paper-scissors.rps-test
  (:require [cljs.spec.alpha :as s]
            [cljs.spec.test.alpha :as stest :include-macros true]
            [cljs.test :as t :include-macros true]
            [clojure.test.check.clojure-test :as tc :include-macros true]
            [clojure.test.check.properties :as prop :include-macros true]
            [rock-paper-scissors.rps :as sut]
            [rock-paper-scissors.rps.specs]))

(t/use-fixtures
  :once {:before #(stest/instrument)})

(t/deftest test-fight
  (t/testing "rock-paper-scissors"
    (t/is (= ::sut/win (sut/fight ::sut/rock ::sut/scissors)))
    (t/is (= ::sut/lose (sut/fight ::sut/scissors ::sut/rock)))
    (t/is (= ::sut/draw (sut/fight ::sut/paper ::sut/paper)))))

(tc/defspec prop-test-<-hand
  1000
  (let [fspec (s/get-spec #'sut/<-hand)]
    (prop/for-all [[hand] (-> fspec :args s/gen)]
      (s/valid? (:ret fspec)
                (sut/<-hand hand)))))

(tc/defspec prop-test-->hand
  1000
  (let [fspec (s/get-spec #'sut/->hand)]
    (prop/for-all [[num] (-> fspec :args s/gen)]
      (s/valid? (:ret fspec)
                (sut/->hand num)))))

(tc/defspec prop-test-fight
  1000
  (let [fspec (s/get-spec #'sut/fight)]
    (prop/for-all [[you enemy] (-> fspec :args s/gen)]
      (s/valid? (:ret fspec)
                (sut/fight you enemy)))))
