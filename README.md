# 🔴🟢 Colour Mind Game 🔵🟡

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Build-Maven-C71A36?style=flat-square&logo=apachemaven&logoColor=white)
![JUnit](https://img.shields.io/badge/Tests-JUnit%204-25A162?style=flat-square&logo=junit5&logoColor=white)
![Patterns](https://img.shields.io/badge/Design-Strategy%20%2B%20Factory-6f42c1?style=flat-square)
![CLI](https://img.shields.io/badge/Interface-CLI-000000?style=flat-square)

**ChromaClash** is a Java command-line game where a human player faces off against an AI opponent (**HAL-9000**) in a colour-guessing duel. Built as a personal project to apply object-oriented design patterns to a non-trivial, testable system.

## How the game works

Each round, both the player and the AI simultaneously **pick** a colour and **guess** the other's colour (`RED`, `GREEN`, `BLUE`, `YELLOW`). A correct guess scores a point. Every third round is a **Power Round**: a bonus colour is revealed, and correctly guessing *that* colour on top of your opponent's pick is worth extra points. Whoever has the higher score after the configured number of rounds wins.

The game is driven entirely from an interactive CLI:

| Command                          | Description                                                                 |
| -------------------------------- | --------------------------------------------------------------------------- |
| `NEW_GAME <DIFFICULTY> <ROUNDS>` | Start a new game (`EASY`, `MEDIUM`, or `HARD`) for a given number of rounds |
| `PLAY`                           | Play the next round                                                         |
| `SHOW_STATS`                     | Show the current score                                                      |
| `HELP`                           | Print usage information                                                     |
| `EXIT`                           | Quit                                                                        |

## Design & architecture

The main goal of this project was to design a system that could grow in complexity (more AI behaviours, more difficulty tiers) without rewriting existing code — so it's built around two classic Gang-of-Four patterns:

- **Strategy** — AI decision-making is abstracted behind an `AiStrategy` interface, with interchangeable implementations (`RandomStrategy`, `AvoidLastStrategy`, `LeastUsedStrategy`) that pick colours and guess the player's next move using different heuristics.
- **Strategy (composed)** — A second `DifficultyStrategy` layer (`EasyDifficulty`, `MediumDifficulty`, `HardDifficulty`) decides *which* `AiStrategy` the AI should use, and can adapt it dynamically round-to-round based on how the AI is performing (this is where `HARD` mode gets genuinely adaptive, switching strategies when it starts losing).
- **Factory** — `StrategyFactory` encapsulates the construction of the correct `DifficultyStrategy` for a chosen difficulty, keeping the game engine decoupled from concrete strategy classes.
- **Separation of concerns** — CLI parsing/formatting (`Main`, `MessageCli`) is kept independent of game rules and state (`Game`), which is independent of the AI's decision logic (`engine` package).

```text
src/main/java/nz/ac/auckland/se281/
├── Main.java                 # CLI entry point, command parsing, help text
├── cli/
│   ├── MessageCli.java       # Centralised, testable output messages
│   └── Utils.java            # Shared scanner/RNG utilities
├── model/
│   └── Colour.java           # Colour enum + parsing helpers
└── engine/
    ├── Game.java              # Core game loop, scoring, round/power-round logic
    ├── AiStrategy.java        # Strategy interface: how the AI picks & guesses
    ├── RandomStrategy.java
    ├── AvoidLastStrategy.java
    ├── LeastUsedStrategy.java
    ├── DifficultyStrategy.java # Strategy interface: which AiStrategy to use
    ├── EasyDifficulty.java
    ├── MediumDifficulty.java
    ├── HardDifficulty.java
    └── StrategyFactory.java   # Factory: builds the right DifficultyStrategy
```

## Tech stack

- **Java 21**
- **Maven** for builds and dependency management
- **JUnit 4** for unit and system-level CLI tests (`src/test`)
- A bundled static-analysis tool (`codestyle.jar`) enforcing formatting and style conventions on every build

## Getting started

**Prerequisites:** JDK 21, Maven (or use the bundled `mvnw` / `mvnw.cmd` wrapper).

```bash
# Compile
./mvnw compile

# Run the game
./mvnw exec:java@run

# Run the test suite
./mvnw test

# Run the style/formatting checker
./mvnw exec:java@style
```

## What this project demonstrates

- Applying **Strategy** and **Factory** design patterns to solve a real extensibility problem, rather than as a textbook exercise
- Iterative refactoring driven by an automated test suite and a static style checker (visible across the commit history)
- Designing a clear CLI contract (commands, argument validation, help text) around a decoupled game engine
- Writing testable code: game logic and AI behaviour are unit-testable independently of the CLI layer

---

![ASC](https://i.imgur.com/KkTk2Lms.png) Rama Anumanchipalli
