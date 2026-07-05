---
description: Setup a new DSA problem from a link
---

# Start a New Problem

Problem link or description: $ARGUMENTS

Set up a new DSA problem from a link (LeetCode, HackerRank, NeetCode, etc.) or a pasted description.

**Rule 0 applies (see root `CLAUDE.md`): never write the solution class or solution method logic. The user implements the solution themselves.**

## Do

- Fetch and read the problem description from the link.
- Create an empty solution method stub (signature only, with a `// TODO` inside).
- Create reusable private helper methods for test execution to avoid repetitive code:
  - A `runTest` helper that accepts test number, input, expected output, actual output, and description.
  - A `printSummary` helper that prints the final test results table and statistics.
- Create a `main` function that uses the helpers to run comprehensive test cases covering:
  - All examples from the problem.
  - Edge cases (empty input, single element, minimum/maximum constraints).
  - Normal cases and boundary cases.
- Add detailed JavaDoc documentation including:
  - Problem number, title, and difficulty.
  - Problem description and constraints.
  - Placeholder for approach, time and space complexity.
- Add the new problem as 🔄 In Progress in `PROGRESS.md` (Overview Table + Daily Progress Log + category checklist).

## Code Quality

- Keep test code DRY — extract common patterns into helper methods.
- Each test case should be a single method call with clear parameters.
- Test summary should use stored results, not inline comparisons.

## Do Not

- Never create the solution class or solution method logic.
- Never implement the actual algorithm.
- The user will implement the solution themselves.
