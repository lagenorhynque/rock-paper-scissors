(ns rock-paper-scissors.rps)

(defn <-hand [hand]
  (case hand
    ::rock 0
    ::scissors 1
    ::paper 2))

(defn ->hand [num]
  (case num
    0 ::rock
    1 ::scissors
    2 ::paper))

(defn fight [you enemy]
  (letfn [(result [n]
            (case n
              0 ::draw
              1 ::lose
              2 ::win))]
    (-> (- (<-hand you) (<-hand enemy))
        (+ 3)
        (mod 3)
        result)))
