(ns d-test
  (:require [clojure.test :refer [deftest is]]))

(deftest d-test
  (Thread/sleep (* 30 1000))
  (is (< 0.8 (Math/random))))
