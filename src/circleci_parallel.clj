(ns circleci-parallel
  (:require [clojure.string :as s]
            [clojure.java.shell :as sh]
            [kaocha.repl :as kr])
  (:gen-class))

(defn extract-namespaces []
  (->>  (kaocha.repl/test-plan)
        kaocha.testable/test-seq
        (filter #(= :kaocha.type/ns (:kaocha.testable/type %)))
        (map :kaocha.testable/id)
        sort))

(defn split-tests
  []
  (-> (sh/sh "circleci"
             "tests"
             "split"
             "--split-by=timings"
             :in (s/join "\n" (map name (extract-namespaces))))
      :out
      (s/split #"\n")))

(defn -main
 [& _args]
 (let [result (apply kr/run
                     (conj (vec (split-tests))
                           {:plugins                             [:kaocha.plugin/junit-xml]
                            :kaocha.plugin.junit-xml/target-file "test-results/kaocha/results.xml"}))]
   (System/exit (if (or (pos? (:kaocha.result/error result))
                        (pos? (:kaocha.result/fail result)))
                  1
                  0))))
