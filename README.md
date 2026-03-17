# Calculator Language – Project README

## Phase 1: Flex and Bison Calculator

### Overview
Phase 1 builds a calculator using two classical tools: **Flex** for lexical analysis and **Bison** for parsing. Together they implement a full pipeline from raw text input to computed integer results.

### Key Considerations

#### Lexical Analysis with Flex
The lexer scans the raw input string and breaks it into meaningful tokens using regular expression rules. Flex always applies the **longest match** rule — if two patterns match, the one consuming more characters wins. If two patterns match the same length, the **first rule defined** takes priority.

Tokens recognized: `NUMBER`, `PLUS`, `MINUS`, `MULT`, `DIVISION`, `OPEN`, `CLOSING`, `EOL`. Whitespace is silently consumed so the parser works regardless of spacing.

#### Parsing with Bison
The grammar is structured into three levels to handle **operator precedence** and **left-associativity**:
- `expression` — handles `+` and `-`
- `term` — handles `*` and `/`
- `factor` — handles numbers and parenthesized sub-expressions

This layering ensures `5 + 3 * 2 = 11` and not `16`. Left-associativity is declared using `%left`, ensuring `10 - 3 - 2 = 5`.

#### Error Handling
- **Division by zero** — detected inside the `term` rule, prints an error and sets `error_flag` to suppress output for that line
- **Syntax errors** — handled by `yyerror()` which prints a descriptive message to stderr

#### Multiple Line Input
The grammar uses a recursive `input` rule that repeatedly matches `line` until EOF, allowing the calculator to process as many expressions as provided without restarting.

---

## Phase 2: Object-Oriented AST in Java

### Overview
Phase 2 reimplements the calculator in Java using an **Abstract Syntax Tree (AST)**. Instead of computing values directly during parsing, the parser builds a tree of objects in memory, which is then traversed to evaluate or display the expression.

### Arithmetic Expression Modeling with OOP

#### The Node Hierarchy
```
ASTNode  (abstract)
  ├── NumNode       — leaf node, holds an integer value
  └── OperatorNode  — internal node, holds operator + left/right children
```
Every expression is either a **number** (base case) or an **operation applied to two sub-expressions** (recursive case).

#### NumNode
Represents a literal integer with no children. Its `evaluate()` simply returns its stored value — this is the **base case** that stops recursion.

#### OperatorNode
Represents a binary operation (`+`, `-`, `*`, `/`). Holds references to left and right `ASTNode` children. Its `evaluate()` recursively evaluates both children then applies the operator. Division by zero is caught here with an `ArithmeticException`.

#### The Parser
A recursive descent parser reads space-separated tokens and builds the AST using two methods:
- `parseExpr()` — handles `+` and `-`, calls `parseTerm()` for each operand
- `parseTerm()` — handles `*` and `/`, calls `parseNum()` for each operand

This two-level structure enforces operator precedence without explicit declarations. Higher-precedence operations always appear **deeper** in the tree, so they are evaluated first.

#### AST Visualization
The `print(String prefix)` method produces an ASCII tree. `OperatorNode` recursively prints children with indented prefixes, deriving the indentation level from the current prefix length.

Example for `5 + 3 * 2`:
```
+
|-- 5
\-- *
    |-- 3
    \-- 2
```

---

## Phase 3: Prefix Transformation and Functional Evaluation

### Overview
Phase 3 transforms the AST into Lisp-style **prefix notation** and evaluates that prefix string recursively — without modifying the original tree.

### Building the Prefix Form
`AstToPrefix` walks the AST using `instanceof` checks:
- `NumNode` → return its value as a string
- `OperatorNode` → recursively transform left and right, wrap as `(operator left right)`

For `5 + 3 * 2`:
```
(+ 5 (* 3 2))
```

### Evaluating the Prefix Form
`EvaluatePrefix` parses and evaluates the prefix string using a recursive `evalrec()` method. The string is pre-processed so parentheses are space-separated, producing a clean token array.

The evaluator uses an instance-level index `i` to track position:
- Current token is a **number** → parse and return it (base case)
- Current token is `(` → skip it, read operator, recurse left, recurse right, skip `)`, apply operator and return

The index resets to `0` at the start of each `evaluate()` call, making the class safe for multiple inputs.

### Functional Constraints Satisfied
- **No global mutable variables** — `i` and `tokens` are instance fields scoped to each object
- **Fully recursive evaluation** — `evalrec()` calls itself for every sub-expression
- **Original AST never modified** — `AstToPrefix` only reads from nodes
