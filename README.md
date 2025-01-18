# ClojureScript JSON to EDN Converter for VSCode

A Visual Studio Code extension written in ClojureScript that converts between JSON and Clojure EDN formats. Useful when working with RESTful APIs and without a REPL for conversion. https://marketplace.visualstudio.com/items?itemName=billhedworth.clj-vsc-formatter

## Features

- Convert JSON to EDN: Transform JSON data into Clojure EDN format
- Convert EDN to JSON: Transform Clojure EDN back to JSON format
- Flatten: Compress EDN or JSON into a single line while preserving structure
- Preserves data structure and formatting
- Accessible through both Command Palette and editor context menu
- Graceful error handling:
  - Clear error messages for invalid JSON/EDN input
  - Handles invalid selections or editor states

## Installation

1. Install from VSCode Marketplace https://marketplace.visualstudio.com/items?itemName=billhedworth.clj-vsc-formatter
2. Reload VSCode

## Usage

The extension provides three main commands through both the Command Palette (Ctrl+Shift+P / Cmd+Shift+P) and the editor context menu under "EDN/JSON Convert":

1. **JSON to EDN**: Convert JSON data to EDN format
   - Select JSON text in your editor
   - Right-click to open the context menu
   - Select "Convert JSON to EDN"

2. **EDN to JSON**: Convert EDN data to JSON format
   - Select EDN text in your editor
   - Right-click to open the context menu
   - Select "Convert EDN to JSON"

3. **Flatten**: Compress EDN or JSON to a single line
   - Select EDN or JSON text in your editor
   - Right-click to open the context menu
   - Select "Flatten EDN/JSON"

Examples:

1. JSON to EDN conversion:
```json
{"name": "example", "numbers": [1, 2, 3]}
```

Converts to:
```clojure
{:name "example"
 :numbers [1 2 3]}
```

2. Flatten EDN/JSON:
```clojure
{:name "example"
 :numbers [1 2 3]}
```

Converts to:
```clojure
{:name "example" :numbers [1 2 3]}
```

### Prerequisites
- npm

### Setup

1. Clone the repository
2. Install dependencies:
   ```bash
   npm install
   ```

### Development Commands

- Watch mode (auto-recompile on changes):
  ```bash
  npm run watch
  ```

- Compile once:
  ```bash
  npm run compile
  ```

- Create production build:
  ```bash
  npm run release
  ```

### Packaging the Extension

To create a .vsix file for local installation:

1. Ensure you have built the extension:
   ```bash
   npm run release
   ```

2. Package the extension:
   ```bash
   npm run package
   ```

This will create a `clj-vsc-formatter-[version].vsix` file in the root directory.

### Local Installation

To install the packaged extension:

1. Open VSCode
2. Open the Command Palette (Ctrl+Shift+P / Cmd+Shift+P)
3. Type "Install from VSIX" and select it
4. Navigate to and select the generated .vsix file