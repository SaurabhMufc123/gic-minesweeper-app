# gic-minesweeper-app
GIC assessment to build a Minesweeper App

## Architecture and Design Principles

The project follows:

-   Strategy Pattern
-   Test‑Driven Development (TDD)
-   Clean and modular design

## 1. Strategy Pattern

**Purpose:** Allow flexible mine generation logic.

Implemented via:

    MineGeneratorStrategy

This interface allows multiple implementations such as:

-   RandomMineGenerator

Benefits:

-   Easily swap mine generation logic
-   Makes testing focused
-   Improves extensibility

Example:

    MatrixEngine(Matrix matrix, MineGeneratorStrategy mineGenerator)

------------------------------------------------------------------------

## 2. Encapsulation

Each class has a clear responsibility:

-   Cell → state of a single cell
-   Matrix → board structure
-   MatrixEngine → game logic

------------------------------------------------------------------------


## 3. Test‑Driven Development (TDD) Approach

TDD cycle followed:

1.  Write failing test

------------------------------------------------------------------------

## 4. Unit Testing Strategy

Tools used:

-   JUnit
-   Mockito

Tests cover:

-   Mine generation
-   Cell reveal logic
-   Win/Lose conditions
-   Game flow

------------------------------------------------------------------------

## 5. Conclusion

This implementation demonstrates:

-   Proper use of Strategy Pattern
-   Clean separation of logic
-   Fully testable architecture
-   Real‑world TDD workflow

The design ensures maintainability, scalability, and reliability.