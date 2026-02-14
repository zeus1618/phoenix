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
├── .cursor/
│   └── rules/
│       └── dsa-workflow.mdc          # Automated workflow rules
├── arraysAndHashing/
│   └── ContainsDuplicate.java        # Problem solutions organized by category
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
| Arrays & Hashing | 3 | 🔄 In Progress |
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

Progress is tracked in [PROGRESS.md](PROGRESS.md) with:

- **📋 Problems Overview Table** - Quick high-level view
- **📅 Daily Progress Log** - Detailed entries with approaches
- **📈 Category Breakdown** - Progress organized by DSA topics
- **📝 Notes & Insights** - Key learnings and patterns
- **🎯 Goals & Milestones** - Track learning objectives

---

## 🤖 Automated Workflow

This project uses Cursor AI rules (`.cursor/rules/dsa-workflow.mdc`) for automated workflows:

### Rule 1: Problem Setup
When a problem link is shared:
- ✅ Fetches problem description
- ✅ Creates main function with test cases
- ✅ Adds documentation
- ❌ Does NOT create solution (you implement it)

### Rule 2: Progress Updates
On request to "update progress":
- Checks last update date
- Syncs all problems solved since then
- Updates statistics and tables

### Rule 3: Auto-Tracking
When moving to next problem:
- Automatically logs completed problem
- Updates progress tracker
- Maintains statistics

---

## 📈 Statistics

- **Total Problems Solved:** 3
- **Current Streak:** 1 day
- **Categories Covered:** 1
- **Last Updated:** Friday, February 13, 2026

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

**Last Updated:** February 13, 2026  
**Status:** 🔥 Active - Daily Practice

---

*"The only way to learn a new programming language is by writing programs in it." - Dennis Ritchie*
