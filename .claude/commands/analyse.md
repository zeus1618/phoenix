---
description: Analyse the user's solution for the current problem
---

# Analyse Current Solution

Read the user's current solution from the active problem file and evaluate it.

**Rule 0 applies (see root `CLAUDE.md`): never modify the user's solution code during this command.**

## Focus Only On

- The user's solution method/algorithm implementation.
- The approach and logic used to solve the problem.
- Time and space complexity of the solution.
- Algorithmic improvements and optimizations.

## Do

- Read the user's solution method carefully (ignore AI-generated parts).
- List what was done RIGHT in the algorithm (correct patterns, good practices).
- List what was done WRONG or could be improved in the approach.
- Analyse time and space complexity.
- Suggest better algorithmic approaches with code examples and complexity comparison.
- Include a comparison table of all solution approaches.

## Explicitly Ignore

- Test cases and test infrastructure (AI-generated).
- Documentation and JavaDoc comments (AI-generated).
- Helper methods like `runTest`, `printSummary` (AI-generated).
- Main function and test setup code (AI-generated).
- Debug print statements in solution (mention to remove, but don't analyze deeply).

## TODO Management (Automatic Pattern Detection)

After completing the analysis, perform automatic TODO management:

1. **Detect Pattern Used in Current Solution** — map the user's code to a standard pattern using the table below. Example: user used `PriorityQueue` → pattern is "Heap/Priority Queue".
2. **Check for Matching Pending TODOs** — read `TODOS.md`, look in the "Pending" section, and check if any TODO's "Prerequisites" field matches the detected pattern.
3. **Offer to Mark TODOs as Ready** — if matches found, ask the user:
   ```
   🎉 Pattern Unlocked: [Pattern Name]

   You've learned [pattern] in this problem! The following TODOs are now ready:
   - TODO #X: [Problem Name] - [Approach]
   - TODO #Y: [Problem Name] - [Approach]

   Would you like to mark these TODOs as ready to approach?
   ```
4. **Update `TODOS.md` if User Confirms:**
   - Move matching TODOs from "Pending" to "Ready to Approach".
   - For each moved TODO, add "Learned From" (current problem name/number), "Date Ready" (current date), and change status from 🔄 Pending to ✅ Ready.
   - Update "Pattern → Problems Mapping": change pattern status to ✅ Learned, add current problem to the "Learned from" list.
   - Update Quick Stats at top (decrement Pending, increment Ready).

### Pattern Detection Table

| Code Indicators | Pattern Name | Matches TODO Prerequisites |
|-----------------|--------------|---------------------------|
| `PriorityQueue`, `new PriorityQueue<>()`, heap operations | Heap/Priority Queue | "Heap", "Priority Queue", "Max Heap", "Min Heap" |
| Partition logic, kth smallest/largest with recursion | Quickselect | "Quickselect", "Selection Algorithm" |
| Two variables scanning from both ends, `left`/`right` pointers | Two Pointers | "Two Pointers" |
| `while (left <= right)`, binary search pattern | Binary Search | "Binary Search" |
| `dp[]` array, memoization, recursive + cache | Dynamic Programming | "DP", "Dynamic Programming", "Memoization" |
| DFS recursive, `visited` set, graph traversal | DFS | "DFS", "Depth First Search" |
| Queue for level-order, `queue.offer()` pattern | BFS | "BFS", "Breadth First Search" |
| Window start/end pointers, sliding range | Sliding Window | "Sliding Window" |
| Backtracking with choose/explore/unchoose | Backtracking | "Backtracking" |
| Trie node structure, prefix tree | Trie | "Trie", "Prefix Tree" |
| `Arrays.sort()`, `Collections.sort()`, custom comparator | Sorting | "Sorting", "Sort" |

### Example Flow

```
User solves "Top K Frequent Elements" using PriorityQueue

1. Analysis completes (what's right, wrong, complexity, alternatives)
2. Pattern Detection: code contains PriorityQueue<int[]> maxHeap = new PriorityQueue<>()
   -> Detected pattern: "Heap/Priority Queue"
3. Check TODOS.md: TODO #1 and #3 both require "Heap/Priority Queue" -> both match!
4. Ask user: "🎉 You've learned Heap/Priority Queue! TODOs #1, #3 are now ready. Mark them as ready?"
5. If Yes: move TODOs to Ready section, add "Learned From: Top K Frequent Elements (#347)",
   add "Date Ready: <today>", update stats (Pending: 3→1, Ready: 0→2)
```

## Skills Tracker Update

After completing the analysis, detect and track concepts/skills used in the solution:

1. **Concept Detection** — analyze the user's solution code and identify:
   - **Data Structures:** Arrays, HashMap, HashSet, Stack, Queue, PriorityQueue, LinkedList, TreeMap, TreeSet, Deque, etc.
   - **Algorithms:** Sorting (`Arrays.sort`, `Collections.sort`), Binary Search, Recursion, DFS, BFS, Dijkstra, Union-Find, etc.
   - **Patterns/Techniques:** Two Pointers, Sliding Window, Prefix Sum, Kadane's Algorithm, Fast-Slow Pointers, Monotonic Stack/Queue, Divide & Conquer, Greedy, Dynamic Programming, Backtracking, etc.
   - **Optimizations:** In-place operations, Constant space optimization, Memoization, Tabulation, etc.
2. **Store Detected Concepts** for later use in `/update`. Format:
   ```
   Detected Concepts:
   - [Concept Name] (Category: Data Structure/Algorithm/Pattern/Optimization)
   ```
3. **Mention Skills in Analysis** — at the end, briefly note: "🎯 Skills Applied: [list detected concepts]" and "These will be tracked in your Skills Tracker when you run `/update`."

### Concept Detection Examples

| Code Pattern | Detected Concept | Category |
|--------------|------------------|----------|
| `new HashMap<>()`, `map.put()`, `map.get()` | HashMap | Data Structure |
| `new HashSet<>()`, `set.add()`, `set.contains()` | HashSet | Data Structure |
| `new PriorityQueue<>()`, heap operations | Priority Queue (Heap) | Data Structure |
| `Arrays.sort()`, `Collections.sort()` | Sorting | Algorithm |
| Two pointers scanning from ends (`left`, `right`) | Two Pointers | Pattern |
| Window start/end with expanding/shrinking | Sliding Window | Pattern |
| Running sum/product tracking | Prefix Sum/Product | Pattern |
| Track min/max while iterating | Kadane's Algorithm | Pattern |
| Operations modify input array directly | In-place Operation | Optimization |
| No extra data structures (O(1) space) | Constant Space | Optimization |
| Recursive with memoization cache | Memoization | Optimization |

## Do Not

- Never modify the user's solution code (Rule #0).
- Do not update `PROGRESS.md` or `SKILLS.md` at this step — that happens in `/update`.
- Only update `TODOS.md` if the user confirms.
- Do not spend time analyzing AI-generated test coverage or infrastructure.
