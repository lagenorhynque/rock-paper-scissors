(ns rock-paper-scissors.db
  (:require [rock-paper-scissors.rps :as rps]))

(def default-db
  {:you ::rps/rock
   :enemy ::rps/rock
   :scene ::start})
