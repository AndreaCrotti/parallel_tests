(ns e-test
  (:require [clojure.test :refer [deftest is]]))

(deftest e-test
  (Thread/sleep (* 30 1000))
  (is (< 0.8 (Math/random))))
