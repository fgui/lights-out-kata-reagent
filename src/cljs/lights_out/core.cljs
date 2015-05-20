(ns lights-out.core
    (:require [reagent.core :as reagent :refer [atom]]))

;; -------------------------
;; Model

;; to represent a grid we use vector of vectors 2x2 => [[1 1] [1 1]]

(def neighbor [[0 0] [1 0] [0 1] [-1 0] [0 -1]])

(defn toggle-val [l]
  (if (= 0 l) 1 0)
)

(defn flip-light [lights [x y]]
  (update-in lights [y x] toggle-val)
)

(defn add-pos [a b]
  (mapv + a b)
)

(defn out-grid [min max [x y]]
  (not (or (or (> x max) (< x min))
           (or (> y max) (< y min))))
)

;; assumes square box
(defn neighbors [pos-x-y max]
  (filter (partial out-grid 0 max) (map add-pos (repeat pos-x-y) neighbor))
)

(defn flip [lights [x y]]
  (let [n (neighbors [x y] (dec (count lights)))]
    (reduce flip-light lights n)
    )
)

;; -------------------------
;; Views


(defn light-component [a val x y]
  ^{:key x}
  [:div {:style {:float :left
                 :cursor :pointer
                 :margin "1px"
                 :padding "15px"
                 :font-weight :bold
                 :background-color (if (= 1 val) "#AAA" "#EEE")}
         :on-click #(swap! a flip [x y])
         } val]
)

(defn row-component [a r-lights y]
  ^{:key y}
  [:div {:style {:clear :both}}
   (map (partial  light-component a)
        r-lights (iterate inc 0) (repeat y))]
)

(defn lights-component [a-lights]
  [:div {:style {:float :left :margin "10px"} }
   (map (partial row-component a-lights) @a-lights (iterate inc 0))
   ]
)

(defn current-page []
  [:div [:h3 "lights out!!"]
   [lights-component (atom [[1 1 1][1 1 1][1 1 1]])]
   [lights-component (atom (vec (repeat 4 [1 1 1 1])))]
   [lights-component (atom (vec (repeat 5 [1 1 1 1 1])))]
   ])

;; -------------------------
;; Initialize app
(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
