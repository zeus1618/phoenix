# TODO Management System - Quick Reference

## 📋 Overview

The TODO management system helps you track alternative problem-solving approaches to revisit once you learn the required patterns. It automatically detects when you've mastered patterns and marks related TODOs as ready.

## 🎯 Files Created

1. **`TODOS.md`** - Main TODO tracking file
   - Quick stats dashboard
   - Pending TODOs (not ready yet)
   - Ready to approach TODOs
   - Completed TODOs
   - Pattern → Problems mapping

2. **`.cursor/commands/todos.md`** - Command definition
   - Defines `/todos` command and variants
   - Links to Rule 8 in workflow

3. **Updated `.cursor/rules/dsa-workflow.mdc`**
   - Added Rule 8: Todos management
   - Updated Rule 2 (Analyse) to mention TODOs
   - Updated Rule 4 (Summarise) to mention TODOs
   - Updated Rule 5 (Current) to include TODO stats

4. **Updated command files:**
   - `/current` - Now shows TODO summary
   - `/analyse` - Now mentions potential TODOs
   - `/summarise` - Now mentions ready TODOs

## 🚀 Commands

### `/todos` or `/todos status`
Shows current TODO status with statistics.

**Example output:**
```
📝 TODO Summary
- Total: 3
- Pending (not ready): 3
- Ready to approach: 0
- Completed: 0

🔄 Pending TODOs require learning patterns first
✅ No TODOs ready yet - keep solving problems!
```

### `/todos ready`
Lists only TODOs that are ready to approach now (you've learned the prerequisites).

**Example output:**
```
✅ Ready to Approach (2)

1. K Closest Points to Origin (#973) - Max Heap
   Prerequisites met: Heap/Priority Queue
   Learned from: Top K Frequent Elements (#347)
   Ready since: 2026-03-05

2. K Closest Points to Origin (#973) - Min Heap Extract
   Prerequisites met: Heap/Priority Queue
   Learned from: Top K Frequent Elements (#347)
   Ready since: 2026-03-05
```

### `/todos add <problem> <approach> <pattern>`
Manually add a new TODO.

**Example:**
```
/todos add "Two Sum" "Two Pointers" "Two Pointers"
```

### `/todos complete <id>`
Mark a TODO as completed.

**Example:**
```
/todos complete 1
```

## 🔄 Automatic Pattern Detection

The system automatically detects when you learn patterns:

| Pattern You Learn | Unlocks TODOs With |
|------------------|-------------------|
| Heap/Priority Queue | Max Heap, Min Heap, Heap approaches |
| Quickselect | Quickselect, Selection Algorithm |
| Two Pointers | Two Pointers |
| Binary Search | Binary Search |
| Dynamic Programming | DP, Memoization |
| DFS/BFS | Graph Traversal, DFS, BFS |
| Sliding Window | Sliding Window |
| Backtracking | Backtracking |
| Trie | Trie, Prefix Tree |

## 📊 Integration with Workflow

### During `/analyse`
After analyzing your solution, the AI will:
- Suggest alternative approaches
- Mention which TODOs could be added for approaches you haven't learned
- Show which existing TODOs are now unlocked based on current problem's patterns

### During `/update`
When documenting a completed problem:
- System checks if patterns used unlock any pending TODOs
- Automatically moves unlocked TODOs to "Ready to Approach"
- Updates pattern mapping

### During `/current`
Status report includes:
- TODO statistics (X pending, Y ready)
- Recent TODOs that became ready
- Suggestion to work on ready TODOs

### During `/summarise`
Summary includes:
- Patterns learned in current problem
- Which TODOs can now be approached

## 💡 Example Workflow

1. **Solve problem with one approach:**
   ```
   You solve "K Closest Points to Origin" using full sort
   ```

2. **Run `/analyse`:**
   ```
   AI suggests: Max Heap (O(n log k)), Quickselect (O(n))
   AI mentions: "These are alternative approaches you could add as TODOs"
   ```

3. **Add TODOs for approaches you want to learn:**
   ```
   /todos add "K Closest Points to Origin" "Max Heap" "Heap/Priority Queue"
   /todos add "K Closest Points to Origin" "Quickselect" "Quickselect"
   ```

4. **TODOs added to TODOS.md as Pending (need to learn patterns first)**

5. **Later, solve another problem using Heap:**
   ```
   You solve "Top K Frequent Elements" using PriorityQueue
   ```

6. **Run `/update`:**
   ```
   System detects: You used Heap/Priority Queue pattern
   System automatically: Moves "K Closest Points - Max Heap" to "Ready to Approach"
   AI mentions: "🎯 TODO #1 is now ready! You've learned Heap pattern."
   ```

7. **Check ready TODOs:**
   ```
   /todos ready
   Shows: K Closest Points - Max Heap is ready
   ```

8. **Implement alternative approach**

9. **Mark as complete:**
   ```
   /todos complete 1
   ```

## 🎯 Your Current TODOs

You already have 3 TODOs set up for K Closest Points to Origin:

1. ⏳ Max Heap approach - Waiting to learn Heap/Priority Queue
2. ⏳ Quickselect approach - Waiting to learn Quickselect
3. ⏳ Min Heap Extract approach - Waiting to learn Heap/Priority Queue

**Next steps:**
- Solve problems using Heap/Priority Queue (like Top K Frequent Elements #347)
- System will automatically mark TODOs #1 and #3 as ready
- Solve problems using Quickselect (like Kth Largest Element #215)
- System will automatically mark TODO #2 as ready

## 📝 Notes

- TODOs are tracked in `TODOS.md`
- System automatically detects pattern learning
- Use `/todos` anytime to check status
- Add TODOs during `/analyse` when you discover new approaches
- System prevents duplicate TODOs for same problem+approach combination
- You can manually manage TODOs or let the system auto-detect patterns
