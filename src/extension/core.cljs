(ns extension.core
  (:require ["vscode" :as vscode]
            [cljs.tools.reader.edn :as edn]
            [clojure.pprint :as pp]))

(defn json->edn
  []
  (let [editor (.. vscode -window -activeTextEditor)]
    (when editor
      (let [document (.-document editor)
            selection (.-selection editor)
            selected-text (.getText document selection)]
        (try
          (let [json-data (js->clj (js/JSON.parse selected-text) :keywordize-keys true)
                edn-str (with-out-str (pp/pprint json-data))]
            (-> (.edit editor
                      (fn [edit]
                        (.replace edit selection edn-str)))
                (.then #(println "JSON converted to EDN successfully"))))
          (catch :default e
            (.. vscode -window (showErrorMessage (str "Error converting JSON: " (.-message e))))))))))

(defn activate
  [context]
  (let [disposable (.. vscode -commands
                      (registerCommand
                       "clj-vsc-formatter.jsonToEdn"
                       json->edn))]
    (.push (.-subscriptions context) disposable)
    (println "ClojureScript JSON to EDN converter activated!")))