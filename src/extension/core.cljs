(ns extension.core
  (:require ["vscode" :as vscode]
            [cljs.tools.reader.edn :as edn]
            [clojure.pprint :as pp]))

(defn flatten-string
  []
  (let [editor (.. vscode -window -activeTextEditor)]
    (when editor
      (let [document (.-document editor)
            selection (.-selection editor)
            selected-text (.getText document selection)]
        (try
          (let [[parsed-data is-json?] (try
                                        ;; Try JSON first
                                        [(js->clj (js/JSON.parse selected-text) :keywordize-keys true) true]
                                        (catch :default _
                                          ;; If JSON fails, try EDN
                                          [(edn/read-string selected-text) false]))
                flattened-str (if (string? parsed-data)
                               parsed-data
                               (if is-json?
                                 (js/JSON.stringify (clj->js parsed-data))
                                 (pr-str parsed-data)))]
            (-> (.edit editor
                      (fn [edit]
                        (.replace edit selection flattened-str)))
                (.then #(println "Text flattened successfully"))))
          (catch :default e
            (.. vscode -window (showErrorMessage (str "Error flattening text: " (.-message e))))))))))

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
                         edn->json))
        flatten-cmd (.. vscode -commands
                        (registerCommand
                         "clj-vsc-formatter.flatten"
                         flatten-string))]
    (.push (.-subscriptions context) json-to-edn)
    (.push (.-subscriptions context) edn-to-json)
    (.push (.-subscriptions context) flatten-cmd)
    (println "ClojureScript JSON/EDN converter activated!")))