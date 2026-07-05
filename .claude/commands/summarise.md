---
description: Summarise learnings from the current problem conversation
---

# Summarise Current Problem

Review the entire conversation for the current problem and compile key learnings.

**Rule 0 applies (see root `CLAUDE.md`): never modify the user's solution code during this command. This is a conversation-only step — do not create or update any files.**

## Do

- Compile all learnings from the conversation into a structured summary.
- Cover: problem understanding, approaches explored, evolution of solution, key insights.
- Include performance analysis, pattern recognition, and best practices learned.
- Highlight what to remember for future problems.
- **TODO Management:** mention any pending TODOs (from `TODOS.md`) that can now be approached based on patterns learned in the current problem.
- **Skills Recap:** list all concepts/skills used in this problem (as detected during `/analyse`).

## Do Not

- Never modify the user's solution code (Rule #0).
- Do not create or update any files at this step — this is conversation-only.
