# ClojureScript JSON to EDN Converter

A Visual Studio Code extension that converts JSON to Clojure EDN format. This extension makes it easy to transform JSON data into EDN format directly within your editor.

## Features

- Convert selected JSON text to EDN format
- Preserves data structure and formatting
- Accessible through context menu
- Shows helpful error messages for invalid JSON

## Installation

1. Install from VSCode Marketplace (link TBD)
2. Reload VSCode

## Usage

1. Select JSON text in your editor
2. Right-click to open the context menu
3. Select "Convert JSON to EDN"
4. The selected JSON will be converted to EDN format

Example:

```json
{"name": "example", "numbers": [1, 2, 3]}
```

Converts to:

```clojure
{:name "example"
 :numbers [1 2 3]}
```

## Development

### Prerequisites

- Node.js
- npm or yarn
- shadow-cljs

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