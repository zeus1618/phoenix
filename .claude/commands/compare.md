---
description: Compare user's solution with an external solution
---

# Compare With External Solution

External solution (code or description): $ARGUMENTS

The user will provide an external solution either as code in a file or directly in the prompt, for comparison against their own solution for the current problem.

**Rule 0 applies (see root `CLAUDE.md`): never modify the user's solution code during this command.**

## Do

- Trace through the external solution step-by-step using a test case if requested.
- Compare with the user's solution on: correctness, complexity, readability, performance.
- Explain WHY one performs better than the other.
- Cover real-world factors: cache locality, constant factors, method overhead, JIT optimization.
- Highlight when LeetCode benchmarks can be misleading vs production reality.

## Do Not

- Never modify the user's solution code (Rule #0).
- Do not create files or update documentation at this step.
