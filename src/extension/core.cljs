(ns extension.core
  (:require ["vscode" :as vscode]))

(defn hello-world
  []
  (let [editor (.. vscode -window -activeTextEditor)]
    (when editor
      (let [position (.-selection editor)]
        (-> (.edit editor
                  (fn [edit]
                    (.insert edit (.-active position) "Hello World!")))
            (.then #(println "Text inserted")))))))

(defn activate
  [context]
  (let [disposable (.. vscode -commands
                      (registerCommand
                       "cljs-hello-world.helloWorld"
                       hello-world))]
    (.push (.-subscriptions context) disposable)
    (println "ClojureScript extension activated!")))