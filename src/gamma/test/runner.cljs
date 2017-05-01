(ns gamma.test.runner
  (:require [gamma.test.constructors]
            [gamma.test.compiler]))

(enable-console-print!)

(cljs.test/run-tests 'gamma.test.constructors)
(cljs.test/run-tests 'gamma.test.compiler)
