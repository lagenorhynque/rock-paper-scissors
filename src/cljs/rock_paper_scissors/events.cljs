(ns rock-paper-scissors.events
  (:require [re-frame.core :as re-frame]
            [rock-paper-scissors.cofx :as cofx]
            [rock-paper-scissors.db :as db]))

(re-frame/reg-event-db
  ::initialize-db
  (fn  [_ _]
    db/default-db))

(re-frame/reg-event-db
  ::next-game
  (fn [db _]
    (assoc db :scene ::db/now-playing)))

(re-frame/reg-event-fx
  ::select-your-hand
  [(re-frame/inject-cofx ::cofx/select-enemy-hand)]
  (fn [{:keys [db enemy-hand]} [_ h]]
    {:db (assoc db
                :you h
                :enemy enemy-hand
                :scene ::db/over)}))
