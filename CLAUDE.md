# Phoenix - DSA Learning Journey

This is a personal Java DSA (Data Structures & Algorithms) practice repository. Problems are solved independently, with AI assisting only on scaffolding, documentation, and progress tracking — never on the actual algorithm.

This file is always loaded by Claude Code. It replaces the old Cursor rule file (`.cursor/rules/dsa-workflow.mdc`, `alwaysApply: true`) as the source of truth for how to behave in this repo. The detailed per-workflow-step instructions now live in `.claude/commands/*.md` as slash commands (`/start`, `/analyse`, `/compare`, `/summarise`, `/current`, `/update`, `/todos`, `/gitpush`). Reference guides live in `docs/`.

---

## ⚠️ CRITICAL RULE #0: NEVER MODIFY USER'S SOLUTION CODE

**This applies at all times, regardless of which command is running.**

**ABSOLUTELY FORBIDDEN:**
- Never modify, edit, refactor, or change the user's solution method/function.
- Never touch the logic/algorithm code written by the user.
- Never change formatting, spacing, or style in solution methods.
- Never "improve" or "optimize" the user's solution code.
- Never fix bugs in solution code unless explicitly asked.

**Only modify solution code when the user explicitly says:**
- "fix my solution", "change my code", "update my implementation"
- Asks for debugging help on their solution
- Requests refactoring or optimization

**What you CAN modify without asking:**
- Main function and test cases (setup code)
- Documentation and comments
- Progress tracker files (`PROGRESS.md`, `SKILLS.md`, `TODOS.md`, `README.md`)
- README and other non-solution files

**When in doubt: ask before touching any solution method.**

---

## AI Assistance Policy

- ✅ AI helps with: test case creation, documentation, boilerplate/scaffolding, progress tracking.
- ❌ AI does NOT help with: solution logic and algorithm implementation.
- Progress tracker (`PROGRESS.md`) marks "AI Help: Yes" **only** if AI wrote solution/algorithm logic, provided the approach, or debugged the solution code. Scaffolding, tests, and docs do not count as AI help.

This is a strict honesty policy for tracking independently-solved problems — do not mark "AI Help: Yes" loosely.

---

## Project Structure

```
phoenix/
├── CLAUDE.md                # This file — always-loaded context
├── README.md                 # Project overview
├── PROGRESS.md                # Problems overview table + daily log
├── SKILLS.md                  # Concepts/skills mastery tracker
├── TODOS.md                   # Alternative approaches to revisit
├── docs/
│   ├── SKILLS_TRACKER_GUIDE.md
│   └── TODO-SYSTEM-GUIDE.md
├── .claude/
│   └── commands/               # Slash commands (start, analyse, compare, summarise, current, update, todos, gitpush)
├── .cursor/                   # Legacy Cursor config, kept for reference/portability only — not used by Claude Code
├── algorithms/
│   └── sorting/                # Sorting algorithm reference implementations
├── arraysAndHashing/
│   └── learnings/               # Deep-dive learning files for complex problems
└── <category>/                # More categories as they're added (twoPointers, slidingWindow, stack, ...)
```

State files (`PROGRESS.md`, `SKILLS.md`, `TODOS.md`) are data, not config — commands read and update them, but their structure/format is documented in `docs/`.

---

## Workflow at a Glance

1. `/start <link>` — scaffold a new problem (stub + tests only, no solution).
2. Implement the solution yourself.
3. `/analyse` — evaluate the solution, detect skills/patterns used, surface unlockable TODOs.
4. `/compare <external solution>` — optional, compare against another solution.
5. `/summarise` — optional, consolidate learnings from the conversation.
6. `/update` — sync JavaDoc, `PROGRESS.md`, `README.md`, `SKILLS.md` (and optionally create a detailed learning file).
7. `/todos` — manage alternative-approach TODOs (`status`, `ready`, `add`, `complete`).
8. `/gitpush` — commit changes in logical, atomic groups and push.

Full detail for each step is in its command file under `.claude/commands/`.
