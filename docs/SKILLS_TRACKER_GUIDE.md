# 🎯 Skills Tracker System - Complete Guide

## Overview

The Skills Tracker automatically monitors and records every concept, pattern, data structure, and algorithm you use across all your DSA problems. It tracks your first use, every subsequent reuse, and progression through mastery levels.

---

## Why Skills Tracking?

### Problems It Solves

1. **"What have I actually learned?"** - Clear inventory of all concepts you've mastered
2. **"Which concepts do I use most?"** - Identify your strengths and go-to patterns
3. **"Am I improving?"** - Track mastery progression from Beginner → Master
4. **"When did I learn X?"** - Historical record of when you first used each concept
5. **"Where did I use Y?"** - Find all problems where you applied a specific concept

### Key Benefits

- **No manual tracking** - Automatically detected during `/analyse` and `/update`
- **First-class citizen** - Concepts detected from YOUR solution code only (not AI-generated tests)
- **Chronological history** - See your learning journey over time
- **Gamification** - Progress through mastery levels as you reuse concepts
- **Quick reference** - Run `/current` to see top skills without opening files

---

## How It Works

### 1. Concept Detection (During `/analyse`)

When you run `/analyse`, the AI scans your solution method and identifies:

#### Data Structures
- **Hash-based:** HashMap, HashSet, LinkedHashMap, TreeMap, TreeSet
- **Linear:** ArrayList, LinkedList, Array operations
- **Queue-based:** Queue, Deque, PriorityQueue (Heap)
- **Stack-based:** Stack, monotonic stack

#### Algorithms
- **Sorting:** Arrays.sort(), Collections.sort(), custom sorting
- **Searching:** Binary search, linear search
- **Traversal:** DFS (recursive/iterative), BFS
- **Graph:** Dijkstra, Union-Find, Topological sort
- **Other:** Recursion, backtracking

#### Patterns & Techniques
- **Array patterns:** Two Pointers, Sliding Window, Prefix Sum, Kadane's Algorithm
- **Pointer techniques:** Fast-Slow Pointers, Cyclic detection
- **Stack patterns:** Monotonic Stack, Monotonic Queue
- **DP patterns:** Memoization, Tabulation, Bottom-up, Top-down
- **Greedy:** Greedy choice, local optimum
- **Divide & Conquer:** Merge sort pattern, quickselect

#### Optimizations
- **Space:** In-place operations, Constant space (O(1))
- **Time:** Memoization, caching strategies
- **Techniques:** Early termination, pruning

### 2. Storage (During `/update`)

After analysis, when you run `/update`, concepts are stored in SKILLS.md:

#### For New Concepts (First Time)
```markdown
| HashMap | Data Structure | 2026-02-13 | Two Sum (#1) | 1 | Two Sum (#1) (2026-02-13) |
```

Creates:
- Entry in main Skills Tracking Table
- Entry in appropriate category breakdown (Data Structures/Algorithms/Patterns/Optimization)
- Adds to "Recently Learned (Last 5)" section
- Assigns mastery level: 🌱 Beginner (1 use)
- Updates Quick Stats

#### For Existing Concepts (Reuse)
```markdown
| HashMap | Data Structure | 2026-02-13 | Two Sum (#1) | 5 | Two Sum (#1) (2026-02-13), Group Anagrams (#49) (2026-02-13), Top K Frequent (#347) (2026-02-15), Valid Sudoku (#36) (2026-02-27), Encode and Decode Strings (#271) (2026-03-02) |
```

Updates:
- Increments "Times Used" count
- Appends new problem to "Usage History" (comma-separated, chronological)
- Updates "Latest Use" in category breakdown
- Recalculates mastery level if threshold crossed
- Updates "Top 5 Most Used Concepts" ranking if needed

---

## Mastery Progression System

### Levels

| Symbol | Level | Uses | Status |
|--------|-------|------|--------|
| 🌱 | **Beginner** | 1-2 | Just learned, needs practice |
| 🌿 | **Intermediate** | 3-5 | Getting comfortable |
| 🌳 | **Proficient** | 6-10 | Solid understanding |
| 🏆 | **Master** | 11+ | Expert level, second nature |

### Level-Up Moments

```
Use #1:  🌱 Beginner - "First time using HashMap!"
Use #2:  🌱 Beginner - "Used it again"
Use #3:  🌿 Intermediate - "🎉 Level up! Now intermediate"
Use #6:  🌳 Proficient - "🎉 Level up! Now proficient"
Use #11: 🏆 Master - "🎉 Level up! Mastered HashMap!"
```

---

## SKILLS.md File Structure

### 1. Quick Stats (Top of file)
```markdown
- **Total Concepts Mastered:** 9
- **Most Used Concept:** HashMap (5 times)
- **Recently Learned:** Array Reversal (2026-02-26)
- **Average Reuse Rate:** 1.8 times per concept
```

### 2. Skills Tracking Table (Main table)
Complete inventory with all concepts, sorted by first-use date (oldest first).

| Column | Description |
|--------|-------------|
| Concept/Pattern | Name of the concept |
| Category | Data Structure / Algorithm / Pattern / Optimization |
| First Used | Date you first used this concept |
| First Problem | Problem where you learned it |
| Times Used | Total count (including first use) |
| Usage History | Comma-separated list: `Problem (#Num) (Date), ...` |

### 3. Category Breakdown (4 sections)
Organized by type with quick reference tables:
- **Data Structures** - HashMap, HashSet, Arrays, etc.
- **Algorithms** - Sorting, Binary Search, DFS, BFS, etc.
- **Patterns & Techniques** - Two Pointers, Sliding Window, Kadane's, etc.
- **Time/Space Optimization** - In-place, Constant space, Memoization, etc.

### 4. Mastery Levels (Gamification)
Visual progress tracking with counts at each level.

### 5. Concept Usage Trends
- **Top 5 Most Used Concepts** - Your go-to skills
- **Recently Learned (Last 5)** - Latest additions to your toolkit

### 6. Usage Instructions
Documentation for manual updates (rare) and how automatic tracking works.

### 7. Skill Development Goals
Common patterns you should aim to learn based on interview prep.

---

## Viewing Your Skills

### Method 1: Run `/current` Command

Shows concise summary in conversation:

```
🎯 Skills Tracker Summary

Top Concepts:
| Concept          | Category        | Times Used | Mastery |
|------------------|-----------------|------------|---------|
| HashMap          | Data Structure  | 5          | 🌿      |
| Kadane's Algo    | Pattern         | 2          | 🌱      |
| HashSet          | Data Structure  | 2          | 🌱      |

Recent Additions: Array Reversal, Constant Space, In-place Operation
Mastery: 🏆 0 | 🌳 0 | 🌿 1 | 🌱 8 concepts
```

### Method 2: Open SKILLS.md

Full details with complete usage history for every concept.

---

## Workflow Integration

### During Problem Solving

1. **Start problem:** `/start <link>`
   - No skills tracking yet

2. **Implement solution:** (You code)
   - No tracking during implementation

3. **Analyze solution:** `/analyse`
   - 🔍 AI detects concepts used in your code
   - 💬 Mentions detected concepts at end of analysis
   - 📝 Stores concepts temporarily for `/update`

4. **Summarize learnings:** `/summarise` (optional)
   - 📋 Includes skills recap from problem

5. **Update documentation:** `/update`
   - ✅ Records concepts in SKILLS.md
   - 📊 Updates Quick Stats
   - 🎯 Recalculates mastery levels
   - 🔥 Updates Top 5 rankings

6. **Check status:** `/current`
   - 📈 See updated skills summary

### What Gets Tracked vs Ignored

✅ **Tracked (From YOUR solution code):**
- Data structures you instantiate and use
- Algorithms you implement or call
- Patterns your logic follows
- Optimization techniques you apply

❌ **Ignored (AI-generated infrastructure):**
- Test case setup code
- Main function boilerplate
- Helper methods for testing (runTest, printSummary)
- JavaDoc documentation
- Import statements

---

## Examples

### Example 1: First Time Using HashMap

**Problem:** Two Sum (#1)  
**Date:** 2026-02-13  
**Your Solution:**
```java
public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> numMap = new HashMap<>();  // ← Detected!
    for(int i=0; i<nums.length; i++) {
        int complement = target - nums[i];
        if(numMap.containsKey(complement)) {
            return new int[]{i, numMap.get(complement)};
        }
        numMap.put(nums[i], i);
    }
    return new int[]{};
}
```

**After `/analyse`:**
```
🎯 Skills Applied: HashMap

These will be tracked in your Skills Tracker when you run `/update`
```

**After `/update`:**
New entry in SKILLS.md:
```markdown
| HashMap | Data Structure | 2026-02-13 | Two Sum (#1) | 1 | Two Sum (#1) (2026-02-13) |
```

Mastery: 🌱 Beginner (1 use)

---

### Example 2: Reusing HashMap

**Problem:** Group Anagrams (#49)  
**Date:** 2026-02-13 (same day)  
**Your Solution:**
```java
public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> group = new HashMap<>();  // ← Detected again!
    for (String str : strs) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);  // ← Also detected: Sorting!
        String key = new String(chars);
        if (!group.containsKey(key)) {
            group.put(key, new ArrayList<>());
        }
        group.get(key).add(str);
    }
    return new ArrayList<>(group.values());
}
```

**After `/update`:**
Updated SKILLS.md:
```markdown
| HashMap | Data Structure | 2026-02-13 | Two Sum (#1) | 2 | Two Sum (#1) (2026-02-13), Group Anagrams (#49) (2026-02-13) |
| Sorting | Algorithm | 2026-02-13 | Group Anagrams (#49) | 1 | Group Anagrams (#49) (2026-02-13) |
```

Mastery:
- HashMap: 🌱 Beginner (2 uses)
- Sorting: 🌱 Beginner (1 use)

---

### Example 3: Level Up to Intermediate

**Problem:** Top K Frequent Elements (#347)  
**Date:** 2026-02-15  
**Your Solution:**
```java
public int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> freqMap = new HashMap<>();  // ← 3rd use!
    for(int num : nums) {
        freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
    }
    // ... rest of code
}
```

**After `/update`:**
```markdown
| HashMap | Data Structure | 2026-02-13 | Two Sum (#1) | 3 | Two Sum (#1) (2026-02-13), Group Anagrams (#49) (2026-02-13), Top K Frequent Elements (#347) (2026-02-15) |
```

Mastery: 🌿 Intermediate (3 uses) ← **Level up!**

Quick Stats updated:
```markdown
- **Total Concepts Mastered:** 3
- **Most Used Concept:** HashMap (3 times)
```

---

## Tips & Best Practices

### 1. Trust the Automatic Detection
- Don't manually edit SKILLS.md unless fixing errors
- The AI only tracks concepts YOU implemented
- Test infrastructure is automatically ignored

### 2. Focus on Reuse
- The system rewards applying learned concepts to new problems
- Aim to hit 🌿 Intermediate (3+ uses) for core patterns
- 🏆 Master level (11+ uses) shows production-ready skills

### 3. Check Progress Regularly
- Run `/current` before starting each session
- Review your Top 5 concepts monthly
- Set goals to master 3-5 core patterns

### 4. Balance Breadth and Depth
- Don't just chase one concept to Master level
- Aim for 🌿 Intermediate on diverse concepts
- Master the fundamentals (HashMap, Arrays, etc.) first

### 5. Use for Interview Prep
- Review "Recently Learned" before interviews
- Practice problems using your 🌱 Beginner concepts
- Showcase 🌳 Proficient/🏆 Master concepts in interviews

---

## Troubleshooting

### "Concept not detected"
**Cause:** You might have used it in test code, not solution code.  
**Fix:** Move the logic into your solution method.

### "Wrong mastery level"
**Cause:** Manual SKILLS.md edits or missed `/update`.  
**Fix:** Trust the system. Next `/update` will recalculate.

### "Usage history out of order"
**Cause:** Backfilling problems after initial creation.  
**Fix:** Dates should be in order. Manually sort if needed (rare).

### "Concept missing from category breakdown"
**Cause:** Incomplete backfill or manual edit error.  
**Fix:** Re-run `/update` on a problem using that concept.

---

## Advanced: Manual Tracking (Rare)

If you need to manually add a concept (e.g., backfilling old problems):

1. Open SKILLS.md
2. Add row to main Skills Tracking Table:
   ```markdown
   | Concept | Category | Date | Problem | Count | History |
   ```
3. Add entry to appropriate category breakdown
4. Update Quick Stats (total, most used, average)
5. Update Mastery Levels table
6. Update Top 5 / Recent if applicable

**Note:** Only do this for backfilling. Always prefer automatic tracking.

---

## Future Enhancements (Ideas)

- **Heatmap:** Visualize concept usage over time
- **Weak spots:** Identify patterns you avoid
- **Recommendations:** "Try Two Pointers in your next problem"
- **Export:** Generate resume-friendly skills summary
- **Compare:** Benchmark against other learners

---

## Summary

The Skills Tracker is your **automatic learning journal** that:
- ✅ Tracks every concept you learn and reuse
- ✅ Shows your progression from Beginner → Master
- ✅ Identifies your strengths (most used concepts)
- ✅ Maintains complete historical record
- ✅ Integrates seamlessly with your workflow
- ✅ Requires zero manual effort

**Key Commands:**
- `/analyse` - Detects concepts
- `/update` - Records to SKILLS.md
- `/current` - View summary
- `/summarise` - Review skills from problem

**Key Files:**
- `SKILLS.md` - Complete skills inventory
- `.cursor/rules/dsa-workflow.mdc` - Automatic tracking logic

---

*Last Updated: Tuesday, March 3, 2026*
