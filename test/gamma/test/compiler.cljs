(ns gamma.test.compiler
  (:require [cljs.test]
            [gamma.api :as g]
            [gamma.compiler.core :refer [compile]]
            [gamma.tools :refer [glsl-string]]
            [clojure.string :as str])
  (:require-macros [cljs.test :refer [is deftest testing]]))

(enable-console-print!)

(defn ->glsl [x]
  (clojure.string/replace
    (glsl-string x)
    #"\s" ""))

(defn mush-vars [glsl]
  (str/replace glsl #"v[0-9]+" "v%"))

(deftest
  nested-assignments
  (let [e (g/* 1.0 1.0)
        ee (g/block (g/set (g/gl-position) (g/if true (g/+ e e) 3.0)))]
    (is (= "if(true){(v%=(1.0*1.0));(v%=(v%+v%));}else{(v%=3.0);}(gl_Position=v%);"
           (mush-vars (->glsl ee))))))
