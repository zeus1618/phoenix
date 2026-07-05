---
description: Show current status, progress, and where things were left off
---

# Current Status

Read project state and present a concise status report to help resume quickly. This is a **read-only** status check.

## Do

1. Read `PROGRESS.md` to determine:
   - Last update date.
   - Overall statistics (total solved, categories, streak).
   - Any problem marked 🔄 In Progress in the Overview Table.
2. If a problem is In Progress, read the problem file to check solution method status (empty TODO vs implemented) and determine which workflow step was last completed:
   - **Start**: file exists, solution method is empty/TODO.
   - **Solving**: solution method has code but tests may be failing.
   - **Analyse/Compare**: solution works, user was exploring alternatives.
   - **Update pending**: solution done but docs/tracker not yet updated.
   - **GitPush pending**: everything updated but not committed.
3. Read `TODOS.md` to check TODO status: total pending, number ready to approach, recently unlocked TODOs (if any).
4. Read `SKILLS.md` to check skills overview: total concepts mastered, most frequently used concept, recent skill additions, mastery level distribution.
5. Present a concise status report:
   - 📊 Overall Stats (total solved, streak, categories)
   - 📋 Last 3 problems from Overview Table
   - 🔄 Current Problem (if any): name, link, status, last workflow step
   - 📝 TODO Summary: X pending, Y ready to approach
   - 🎯 Skills Summary (see table format below)
   - 📁 Uncommitted changes (run `git status`)
   - 🎯 Suggested next action

### Skills Summary Table Format

```
🎯 Skills Tracker Summary

Top Concepts:
| Concept | Category | Times Used | Mastery |
|---------|----------|------------|---------|
| [Name]  | [Cat]    | X          | 🌱/🌿/🌳/🏆 |

Recent Additions: [Concept1], [Concept2], [Concept3]
Mastery: 🏆 X | 🌳 Y | 🌿 Z | 🌱 W concepts
```

Format the report clearly with sections, not walls of text.

## Do Not

- Do not modify any files.
- Do not start new problems.
- This is a read-only status check.
