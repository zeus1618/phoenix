---
description: Manage learning TODOs for alternative problem approaches
---

# Manage Learning TODOs

Subcommand and args: $ARGUMENTS

Track alternative approaches to revisit once you learn the required patterns.

## Overview

TODOs track alternative approaches/patterns you want to revisit once you learn the required concepts. The system automatically detects when you've learned patterns and marks related TODOs as "ready to approach."

## Usage

- `/todos` or `/todos status` — Show current TODO status.
- `/todos ready` — Show TODOs ready to approach now.
- `/todos add <problem-name> <approach> <pattern>` — Add new TODO manually.
- `/todos complete <id>` — Mark TODO as completed.
- `/todos list` — List all TODOs with full details.

### `/todos` or `/todos status`

**Do:**
- Read `TODOS.md`.
- Display quick stats: Total, Pending (not ready), Ready, Completed.
- Show summary of pending vs ready TODOs.
- Suggest next action (learn patterns OR approach ready TODOs).

### `/todos ready`

**Do:**
- Read `TODOS.md`.
- Filter and display only TODOs in "Ready to Approach" section.
- Show which patterns were learned that unlocked these.
- Encourage the user to implement these alternative approaches.

### `/todos add <problem-name> <approach> <pattern>`

Example: `/todos add "Two Sum" "Sorting" "Two Pointers"`

**Do:**
- Append to `TODOS.md` in the "Pending" section.
- Auto-increment TODO ID.
- Add to pattern mapping section.
- Set status as 🔄 Pending.
- Add current date.
- Format: problem link, approach to learn, prerequisites.

### `/todos complete <id>`

**Do:**
- Move TODO from current section to "Completed" section.
- Add completion date.
- Update stats at top of `TODOS.md`.
- Ask the user for notes about what they learned.

## Automatic Pattern Detection

When the user solves a problem (during `/analyse` or `/update`):

1. Read `TODOS.md` to check pending TODOs.
2. Analyze the current problem's approach/patterns used (see Pattern Matching Rules below).
3. If the current problem uses a pattern that matches pending TODO prerequisites:
   - Move matching TODOs to "Ready to Approach" section.
   - Update "Pattern → Problems Mapping" to show the pattern is learned.
   - Add "Learned From" reference (current problem).
   - Add "Date Ready" timestamp.
4. During `/analyse`: mention "📝 TODOs unlocked: You can now approach TODO #X, #Y using the [pattern] you just learned!"
5. During `/summarise`: include "🎯 Next Steps: You've mastered [pattern], consider revisiting TODO #X, #Y".

### Pattern Matching Rules

| Problem Pattern Used | Unlocks TODOs With Prerequisites |
|---------------------|-----------------------------------|
| Heap/Priority Queue (PriorityQueue, MaxHeap, MinHeap) | "Heap/Priority Queue", "Max Heap", "Min Heap" |
| Quickselect | "Quickselect", "Selection Algorithm" |
| Two Pointers | "Two Pointers" |
| Binary Search | "Binary Search" |
| Dynamic Programming (DP array, memoization) | "Dynamic Programming", "DP", "Memoization" |
| DFS/BFS | "DFS", "BFS", "Graph Traversal" |
| Sliding Window | "Sliding Window" |
| Backtracking | "Backtracking" |
| Trie | "Trie", "Prefix Tree" |

## `TODOS.md` Structure

**File sections:**
1. Quick Stats (auto-updated)
2. Pending TODOs table (not ready yet — missing prerequisites)
3. Ready to Approach table (learned required patterns)
4. Completed table (finished alternative implementations)
5. Pattern → Problems Mapping (tracks what's learned)
6. Next Steps guidance
7. Usage instructions

**Table columns:**
- Pending: #, Problem, Approach to Learn, Status, Prerequisites, Date Added
- Ready: #, Problem, Approach to Apply, Status, Learned From, Date Ready
- Completed: #, Problem, Approach, Completed Date, Notes

## Integration With Other Commands

- **`/analyse`:** after analyzing the user's solution and suggesting alternatives, automatically suggest adding TODOs for approaches not yet learned.
- **`/update`:** when updating `PROGRESS.md`, also update `TODOS.md` if patterns were learned.
- **`/current`:** include TODO stats in status report (X pending, Y ready).

## Example Workflow

1. User solves "K Closest Points to Origin" with sorting.
2. During `/analyse`, AI suggests Max Heap (O(n log k)) and Quickselect (O(n)).
3. User runs: `/todos add "K Closest Points to Origin" "Max Heap" "Heap/Priority Queue"`.
4. TODO added to `TODOS.md` as Pending (needs to learn Heap pattern).
5. Later, user solves "Top K Frequent Elements" using Priority Queue.
6. During `/update`, system detects Heap pattern was used.
7. System automatically moves "K Closest Points - Max Heap" TODO to "Ready to Approach".
8. User runs `/todos ready` and sees it's ready to implement.
9. User implements Max Heap approach for K Closest Points.
10. User runs `/todos complete 1` to mark it done.

## Do Not

- Do not modify the user's solution code.
- Do not automatically implement alternative approaches.
- Do not remove TODOs without user confirmation.
