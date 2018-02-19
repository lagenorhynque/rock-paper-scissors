(ns rock-paper-scissors.events
  (:require [re-frame.core :as re-frame]
            [rock-paper-scissors.db :as db]))

(re-frame/reg-event-db
 ::initialize-db
 (fn  [_ _]
   db/default-db))
