# ClojureScript JSON to EDN Converter Architecture

## Overview
This VSCode extension is built using ClojureScript and provides functionality to convert JSON to EDN format directly within the editor. The extension is compiled to JavaScript using shadow-cljs for VSCode compatibility.

## Core Components

### Command Registration
- The extension registers a single command `clj-vsc-formatter.jsonToEdn`
- Command is accessible through the editor context menu when text is selected
- Activation occurs when the extension is loaded via the `activate` function

### Core Functionality (`json->edn`)
The main conversion logic is implemented in the `json->edn` function which:
1. Accesses the active text editor
2. Retrieves the selected text
3. Parses JSON into ClojureScript data structures
4. Converts to EDN format using pretty-printing
5. Replaces the selected text with the formatted EDN

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