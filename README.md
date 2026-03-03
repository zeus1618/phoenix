# 🚀 Phoenix - DSA Learning Journey

A structured approach to mastering Data Structures and Algorithms through consistent practice and documentation.

---

## 📖 About

This repository tracks my journey learning Data Structures and Algorithms (DSA). Each problem is implemented in Java with comprehensive test cases, documentation, and complexity analysis. The focus is on understanding problem-solving patterns and building strong fundamentals.

---

## 🎯 Goals

- ✅ Solve problems across all major DSA categories
- ✅ Understand time and space complexity tradeoffs
- ✅ Identify and master common problem-solving patterns
- ✅ Build a reference library of solutions
- ✅ Maintain daily practice consistency

---

## 📁 Project Structure

```
phoenix/
├── README.md                          # Project overview (this file)
├── PROGRESS.md                        # Detailed progress tracker
├── SKILLS.md                          # Skills & concepts mastery tracker
├── TODOS.md                           # Alternative approaches to learn
├── .cursor/
│   ├── rules/
│   │   └── dsa-workflow.mdc          # Automated workflow rules
│   └── commands/                      # Workflow command definitions
│       ├── start.md                   # Start new problem
│       ├── analyse.md                 # Analyze solution
│       ├── compare.md                 # Compare solutions
│       ├── summarise.md               # Summarize learnings
│       ├── current.md                 # View current status
│       ├── update.md                  # Update documentation
│       ├── todos.md                   # Manage learning TODOs
│       └── gitpush.md                 # Commit and push
├── arraysAndHashing/
│   ├── ContainsDuplicate.java        # Problem solutions
│   ├── ValidAnagram.java
│   ├── TwoSum.java
│   ├── GroupAnagrams.java
│   ├── MaximumSubarray.java
│   ├── BestTimeToBuyAndSellStock.java
│   ├── MaximumProductSubarray.java
│   ├── ProductOfArrayExceptSelf.java
│   ├── RotateArray.java
│   ├── ValidSudoku.java
│   ├── EncodeAndDecodeStrings.java
│   ├── MaxConsecutiveOnes.java
│   ├── KClosestPointsToOrigin.java
│   └── learnings/                     # Deep dive learnings for complex problems
│       ├── GroupAnagrams-Learning.md
│       ├── MaximumProductSubarray-Learning.md
│       ├── ProductOfArrayExceptSelf-Learning.md
│       ├── ValidSudoku-Learning.md
│       └── EncodeAndDecodeStrings-Learning.md
├── twoPointers/                       # (Future problems)
├── slidingWindow/                     # (Future problems)
├── stack/                             # (Future problems)
└── ...                                # More categories as we progress
```

---

## 🛠️ Tech Stack

- **Language:** Java
- **IDE:** Cursor with AI-assisted workflow
- **Problem Sources:** LeetCode, HackerRank, others
- **Documentation:** Markdown

---

## 📋 Categories & Progress

| Category | Problems Solved | Status |
|----------|----------------|--------|
| Arrays & Hashing | 13 | 🔄 In Progress |
| Two Pointers | 0 | ⏳ Not Started |
| Sliding Window | 0 | ⏳ Not Started |
| Stack | 0 | ⏳ Not Started |
| Binary Search | 0 | ⏳ Not Started |
| Linked Lists | 0 | ⏳ Not Started |
| Trees | 0 | ⏳ Not Started |
| Graphs | 0 | ⏳ Not Started |
| Dynamic Programming | 0 | ⏳ Not Started |

> See [PROGRESS.md](PROGRESS.md) for detailed progress with individual problem tracking.

---

## 🔄 Workflow

### 1. **Problem Selection**
- Choose a problem from LeetCode or other platforms
- Share the problem link

### 2. **Setup (Automated via AI)**
- AI reads the problem description
- Creates file with:
  - Comprehensive JavaDoc documentation
  - Main function with test cases
  - Empty solution method for implementation

### 3. **Implementation**
- Implement the solution independently
- Focus on understanding the approach
- Optimize for time and space complexity

### 4. **Testing**
- Run the provided test cases
- Verify edge cases
- Validate against problem constraints

### 5. **Progress Tracking (Automated)**
- Progress tracker updates automatically when moving to next problem
- Includes: problem details, approach, complexity, AI assistance status

---

## 🚀 How to Run

### Prerequisites
- Java JDK 8 or higher
- Terminal/Command Prompt

### Running a Solution

1. **Compile the Java file:**
   ```bash
   javac arraysAndHashing/ContainsDuplicate.java
   ```

2. **Run the program:**
   ```bash
   java arraysAndHashing.ContainsDuplicate
   ```

3. **View test results:**
   - Each test case displays input, output, expected result, and pass/fail status

### Example Output
```
Test Case 1:
Input: [1, 2, 3, 1]
Output: true
Expected: true
Status: PASS ✓
```

---

## 📊 Progress Tracking

Progress is tracked across multiple files:

### [PROGRESS.md](PROGRESS.md)
- **📋 Problems Overview Table** - Quick high-level view
- **📅 Daily Progress Log** - Detailed entries with approaches
- **📈 Category Breakdown** - Progress organized by DSA topics
- **📝 Notes & Insights** - Key learnings and patterns
- **🎯 Goals & Milestones** - Track learning objectives

### [SKILLS.md](SKILLS.md) - **NEW!**
- **🎯 Concepts Mastered** - Track all skills you've learned
- **📊 Usage Patterns** - See which concepts you use most
- **🔥 Mastery Levels** - Track progress from Beginner → Master
- **📈 Reuse Tracking** - Every problem you apply each concept to
- **Automatic Updates** - Skills tracked during `/analyse` and `/update`

### [TODOS.md](TODOS.md)
- **Alternative Approaches** - Track solutions you want to revisit
- **Pattern Prerequisites** - Learn patterns before attempting approaches
- **Auto-Unlock System** - TODOs become ready when you learn required patterns

---

## 🤖 Automated Workflow

This project uses Cursor AI rules (`.cursor/rules/dsa-workflow.mdc`) for automated workflows with command-based execution:

### Available Commands

#### `/start <problem_link>`
Generates boilerplate code for a new problem:
- ✅ Fetches problem description
- ✅ Creates main function with comprehensive test cases
- ✅ Adds detailed JavaDoc documentation
- ❌ Does NOT create solution (you implement it)

#### `/analyse`
Analyzes your implemented solution:
- Evaluates time and space complexity
- Identifies strengths and weaknesses
- Suggests alternative approaches
- Compares with optimal solutions
- **Detects and tracks concepts/skills used**

#### `/compare <alternative_solution>`
Compares two solutions in detail:
- Performance analysis (runtime comparison)
- Complexity breakdown
- Constant factor analysis
- Best practices and trade-offs

#### `/summarise`
Consolidates all learnings from current problem:
- Problem evolution journey
- Key insights and takeaways
- Pattern recognition
- Performance lessons
- **Skills recap from problem**

#### `/current`
Shows detailed status overview:
- Current problem progress
- Overall statistics
- Session summary
- **Skills tracker summary (top concepts, mastery levels)**
- Suggested next steps

#### `/update [--detailed-learning]`
Updates all documentation:
- Syncs problem file JavaDoc
- Updates PROGRESS.md
- Updates README.md statistics
- **Updates SKILLS.md with concepts used**
- Optional: Creates detailed learning guide for complex problems

#### `/todos`
Manages learning TODOs for alternative approaches:
- View pending and ready TODOs
- Add new TODOs for approaches to learn later
- Mark TODOs as completed
- Automatically unlocks TODOs when prerequisites learned

#### `/gitpush`
Commits and pushes all changes:
- Generates descriptive commit message
- Commits all tracked changes
- Pushes to remote repository

---

## 🎯 Skills Tracking System

### What Gets Tracked

The Skills Tracker automatically identifies and logs concepts you use in your solutions:

**Data Structures:**
- HashMap, HashSet, ArrayList, LinkedList, Stack, Queue, PriorityQueue, TreeMap, TreeSet, etc.

**Algorithms:**
- Sorting (Arrays.sort, Collections.sort), Binary Search, DFS, BFS, Recursion, etc.

**Patterns & Techniques:**
- Two Pointers, Sliding Window, Kadane's Algorithm, Prefix Sum, Fast-Slow Pointers, Monotonic Stack, etc.

**Optimizations:**
- In-place operations, Constant space, Memoization, Tabulation, etc.

### How It Works

1. **During `/analyse`:** AI scans your solution code and detects concepts used
2. **During `/update`:** Concepts are recorded in SKILLS.md:
   - **First time:** Creates new entry with first-use date and problem
   - **Reuse:** Appends problem to usage history, updates count and mastery level

### Mastery Progression

As you reuse concepts across problems, you progress through mastery levels:
- 🌱 **Beginner** (1-2 uses) → Just learned
- 🌿 **Intermediate** (3-5 uses) → Getting comfortable
- 🌳 **Proficient** (6-10 uses) → Solid understanding
- 🏆 **Master** (11+ uses) → Expert level

### Example: HashMap Journey

```
First Use: Two Sum (#1) - 2026-02-13
Status: 🌱 Beginner (1 use)

After Group Anagrams (#49):
Status: 🌱 Beginner (2 uses)

After Top K Frequent (#347):
Status: 🌿 Intermediate (3 uses) ← Level up!

Usage History:
Two Sum (#1) (2026-02-13), Group Anagrams (#49) (2026-02-13),
Top K Frequent Elements (#347) (2026-02-15), Valid Sudoku (#36) (2026-02-27),
Encode and Decode Strings (#271) (2026-03-02)

Current: 🌿 Intermediate (5 uses)
```

### View Your Skills

Run `/current` to see:
- Top 5 most used concepts
- Recent concepts learned
- Mastery distribution (how many at each level)

Or open [SKILLS.md](SKILLS.md) for full details with complete usage history.

---

## 📈 Statistics

- **Total Problems Solved:** 13
- **Concepts Mastered:** 9
- **Most Used Skill:** HashMap (5 times)
- **Current Streak:** 5 days
- **Categories Covered:** 1
- **Difficulty Breakdown:**
  - Easy: 5 problems
  - Medium: 8 problems
- **Last Updated:** Monday, March 2, 2026

---

## 🎓 Learning Approach

### Focus Areas
1. **Understanding over Memorization** - Focus on the "why" behind each solution
2. **Pattern Recognition** - Identify common problem-solving patterns
3. **Complexity Analysis** - Always analyze time and space tradeoffs
4. **Independent Implementation** - Implement solutions without looking at answers

### AI Assistance Policy
- ✅ **AI helps with:** Test case creation, documentation, setup
- ❌ **AI does NOT help with:** Solution logic and algorithm implementation
- 📊 **Tracking:** Progress tracker marks if AI helped with solution

This ensures honest tracking of independently solved problems.

---

## 🔗 Resources

- [LeetCode](https://leetcode.com/) - Primary problem source
- [NeetCode](https://neetcode.io/) - Problem roadmap and patterns
- [Big-O Cheat Sheet](https://www.bigocheatsheet.com/) - Complexity reference

---

## 📝 License

This is a personal learning project. Feel free to use it as inspiration for your own DSA journey!

---

## 📬 Contact

This is a personal learning repository. The focus is on consistent practice and growth.

---

**Last Updated:** March 2, 2026  
**Status:** 🔥 Active - Daily Practice

---

*"The only way to learn a new programming language is by writing programs in it." - Dennis Ritchie*
