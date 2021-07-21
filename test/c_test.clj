(ns c-test
  (:require [clojure.test :refer [deftest is]]))

(deftest c-test
  (Thread/sleep (* 30 1000))
  (is (< 0.8 (Math/random))))
