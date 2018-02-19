(ns rock-paper-scissors.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [rock-paper-scissors.core-test]))

(doo-tests 'rock-paper-scissors.core-test)
