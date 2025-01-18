# ClojureScript JSON to EDN Converter Architecture

## Overview
This VSCode extension is built using ClojureScript and provides functionality to convert JSON to EDN format directly within the editor. The extension is compiled to JavaScript using shadow-cljs for VSCode compatibility.

## Core Components

### Command Registration
- The extension registers three commands:
  1. `clj-vsc-formatter.jsonToEdn`
  2. `clj-vsc-formatter.ednToJson`
  3. `clj-vsc-formatter.flatten`
- Both commands are accessible through a submenu called "EDN/JSON Convert" in the editor context menu when text is selected
- Activation occurs when the extension is loaded via the `activate` function

### Core Functionality
#### JSON to EDN (`json->edn`)
The main conversion logic is implemented in the `json->edn` function which:
1. Accesses the active text editor
2. Retrieves the selected text
3. Parses JSON into ClojureScript data structures
4. Converts to EDN format using pretty-printing
5. Replaces the selected text with the formatted EDN

#### EDN to JSON (`edn->json`)
The reverse conversion logic is implemented in the `edn->json` function which:
1. Accesses the active text editor
2. Retrieves the selected text
3. Parses EDN into ClojureScript data structures using `cljs.tools.reader.edn`
4. Converts to JSON format with 2-space indentation
5. Replaces the selected text with the formatted JSON

#### Flatten (`flatten-string`)
The flatten functionality is implemented in the `flatten-string` function which:
1. Accesses the active text editor
2. Retrieves the selected text
3. Attempts to parse as JSON first, falls back to EDN if JSON parsing fails
4. Converts the parsed data structure back to a single-line string format
5. Replaces the selected text with the flattened string

### Error Handling
- JSON parsing errors are caught and displayed as VSCode error messages
- Invalid selections or editor states are handled gracefully

## Build System

### shadow-cljs Configuration
- Target: `:node-library`
- Main export: `activate` function
- Output: `out/main.js`

### Dependencies
- Minimal external dependencies
- Uses core ClojureScript libraries for EDN reading and pretty-printing
- VSCode API integration through `vscode` npm package

## Extension Integration
- Integrates with VSCode's command palette
- Context menu integration when text is selected
- Supports VSCode's standard extension lifecycle
- Custom extension icon

## Assets
- Extension icon located at src/extension/imgs/edn-256-dark.png