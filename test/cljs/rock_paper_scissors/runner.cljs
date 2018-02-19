(ns rock-paper-scissors.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [rock-paper-scissors.rps-test]))

(doo-tests 'rock-paper-scissors.rps-test)
