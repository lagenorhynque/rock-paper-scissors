(ns rock-paper-scissors.db.specs
  (:require [cljs.spec.alpha :as s :include-macros true]
            [rock-paper-scissors.db :as db]
            [rock-paper-scissors.rps.specs :as rps.specs]))

(s/def ::you ::rps.specs/hand)

(s/def ::enemy ::rps.specs/hand)

(s/def ::scene #{::db/start ::db/now-playing ::db/over})

(s/def ::db (s/keys :req-un [::you ::enemy ::scene]))
