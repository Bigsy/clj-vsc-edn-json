(ns extension.core
  (:require ["vscode" :as vscode]
            [cljs.tools.reader.edn :as edn]
            [clojure.pprint :as pp]))

(defn edn->json
  []
  (let [editor (.. vscode -window -activeTextEditor)]
    (when editor
      (let [document (.-document editor)
            selection (.-selection editor)
            selected-text (.getText document selection)]
        (try
          (let [edn-data (edn/read-string selected-text)
                json-str (js/JSON.stringify (clj->js edn-data) nil 2)]
            (-> (.edit editor
                      (fn [edit]
                        (.replace edit selection json-str)))
                (.then #(println "EDN converted to JSON successfully"))))
          (catch :default e
            (.. vscode -window (showErrorMessage (str "Error converting EDN: " (.-message e))))))))))

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
  (let [json-to-edn (.. vscode -commands
                       (registerCommand
                        "clj-vsc-formatter.jsonToEdn"
                        json->edn))
        edn-to-json (.. vscode -commands
                       (registerCommand
                        "clj-vsc-formatter.ednToJson"
                        edn->json))]
    (.push (.-subscriptions context) json-to-edn)
    (.push (.-subscriptions context) edn-to-json)
    (println "ClojureScript JSON/EDN converter activated!")))