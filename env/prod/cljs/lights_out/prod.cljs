(ns lights-out.prod
  (:require [lights-out.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
