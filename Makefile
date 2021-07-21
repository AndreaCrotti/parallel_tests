local-test:
	clojure -M -m kaocha.runner "$@"

ci-test:
	clojure -M -m circleci-parallel
