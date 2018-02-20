(ns rock-paper-scissors.events
  (:require [re-frame.core :as re-frame]
            [rock-paper-scissors.db :as db]))

(re-frame/reg-event-db
  ::initialize-db
  (fn  [_ _]
    db/default-db))

(re-frame/reg-event-db
  ::next-game
  (fn [db _]
    (assoc db :scene ::db/now-playing)))

(re-frame/reg-event-db
  ::select-your-hand
  (fn [db [_ h]]
    (assoc db
           :you h
           :scene ::db/over)))
