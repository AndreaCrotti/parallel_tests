(ns a-test
  (:require [clojure.test :refer [deftest is]]))

(deftest a-test
  (Thread/sleep (* 30 1000))
  (is (< 0.8 (Math/random))))
