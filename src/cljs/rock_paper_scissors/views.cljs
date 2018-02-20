(ns rock-paper-scissors.views
  (:require [re-frame.core :as re-frame]
            [rock-paper-scissors.db :as db]
            [rock-paper-scissors.events :as events]
            [rock-paper-scissors.rps :as rps]
            [rock-paper-scissors.subs :as subs]))

(defn hands []
  [:div (map (fn [h]
               ^{:key h}
               [:input {:type "button"
                        :on-click #(re-frame/dispatch [::events/select-your-hand h])
                        :value h}])
             [::rps/rock ::rps/paper ::rps/scissors])])

(defn result [you enemy]
  (let [r (rps/fight you enemy)]
    (case r
      ::rps/win [:h1 {:style {:color "red"}}
                 r]
      ::rps/lose [:h1 {:style {:color "blue"}}
                  r]
      ::rps/draw [:h1 {:style {:color "gray"}}
                  r])))

(defn main-panel []
  (let [scene @(re-frame/subscribe [::subs/scene])]
    (case scene
      ::db/start [:input {:type "button"
                          :on-click #(re-frame/dispatch [::events/next-game])
                          :value "Game Start"}]
      ::db/now-playing [hands]
      ::db/over (let [{:keys [you enemy]} @(re-frame/subscribe [::subs/you-enemy])]
                  [:div
                   [:h1 (str (name you) "(YOU) VS " (name enemy) "(ENEMY)")]
                   [result you enemy]
                   [:input {:type "button"
                            :on-click #(re-frame/dispatch [::events/next-game])
                            :value "Next Game"}]]))))
