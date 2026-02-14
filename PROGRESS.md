# DSA Learning Progress Tracker

> **Last Updated:** Friday, February 13, 2026

---

## 📑 Quick Navigation

- [📊 Summary Statistics](#-summary-statistics)
- [📋 Problems Overview Table](#-problems-overview-table)
- [📅 Daily Progress Log](#-daily-progress-log)
- [📈 Progress by Category](#-progress-by-category)
- [🎯 Goals & Milestones](#-goals--milestones)
- [📝 Notes & Insights](#-notes--insights)

---

## 📊 Summary Statistics

- **Total Problems Solved:** 2
- **Problems In Progress:** 0
- **Categories Covered:** Arrays & Hashing
- **Current Streak:** 1 day

---

## 📋 Problems Overview Table

| # | Date | Problem | Difficulty | Category | Time | Space | AI Help | Status |
|---|------|---------|------------|----------|------|-------|---------|--------|
| 1 | 2026-02-13 | [Contains Duplicate (#217)](https://leetcode.com/problems/contains-duplicate/) | Easy | Arrays & Hashing | O(n) | O(n) | ❌ No | ✅ Done |
| 2 | 2026-02-13 | [Valid Anagram (#242)](https://leetcode.com/problems/valid-anagram/) | Easy | Arrays & Hashing | O(n) | O(1) | ❌ No | ✅ Done |

---

## 📅 Daily Progress Log

### Friday, February 13, 2026

#### ✅ Problem 1: Contains Duplicate
- **Platform:** LeetCode
- **Problem Number:** #217
- **Difficulty:** Easy
- **Link:** https://leetcode.com/problems/contains-duplicate/description/
- **Category:** Arrays & Hashing

**Approach:**
Used HashSet to track seen elements. Iterate through the array and check if each element already exists in the set. If found, return true (duplicate exists). If loop completes without finding duplicates, return false.

**Complexity Analysis:**
- **Time Complexity:** O(n) - Single pass through the array
- **Space Complexity:** O(n) - HashSet storage for worst case (all unique elements)

**AI Assistance:**
- ❌ No - Solution implemented independently by user
- ℹ️ Note: AI only helped with main function, test cases, and documentation (as per workflow)

**Status:** ✅ Completed  
**Implementation File:** `arraysAndHashing/ContainsDuplicate.java`

---

#### ✅ Problem 2: Valid Anagram
- **Platform:** LeetCode
- **Problem Number:** #242
- **Difficulty:** Easy
- **Link:** https://leetcode.com/problems/valid-anagram/description/
- **Category:** Arrays & Hashing

**Problem:**
Determine if two strings are anagrams - words formed by rearranging letters of another word using all original letters exactly once.

**Approach:**
Used a frequency counter array (size 26 for lowercase English letters). Single loop increments count for characters in string s and decrements for string t. If all frequency counts are zero after processing both strings, they are anagrams.

**Complexity Analysis:**
- **Time Complexity:** O(n) - Single pass through both strings plus constant 26-element check
- **Space Complexity:** O(1) - Fixed array of 26 elements regardless of input size

**Key Learning:**
Discovered that method calls like `charAt()` have significant overhead compared to direct array access. Analyzed a LeetCode solution that used `toCharArray()` and learned it achieves ~2.5x better performance despite having more loops. This highlighted an important lesson: Big-O complexity doesn't capture constant factors - the cost of individual operations matters greatly in practice.

**Evolution of Solution:**
1. **First Attempt:** Two HashMaps - O(2n) space, more complex logic
2. **Final Solution:** Single frequency array - O(1) space, cleaner implementation
3. **Performance Insight:** Compared with `toCharArray()` approach to understand real-world performance vs theoretical complexity

**AI Assistance:**
- ❌ No - Solution implemented independently
- ℹ️ Note: AI provided analysis and insights AFTER implementation on request, explaining performance differences between approaches

**Status:** ✅ Completed  
**Implementation File:** `arraysAndHashing/ValidAnagram.java`

---

## 📈 Progress by Category

### Arrays & Hashing
- [x] Contains Duplicate (Easy) - #217
- [x] Valid Anagram (Easy) - #242

### Two Pointers
- [ ] *No problems yet*

### Sliding Window
- [ ] *No problems yet*

### Stack
- [ ] *No problems yet*

### Binary Search
- [ ] *No problems yet*

### Linked Lists
- [ ] *No problems yet*

### Trees
- [ ] *No problems yet*

### Tries
- [ ] *No problems yet*

### Heap / Priority Queue
- [ ] *No problems yet*

### Backtracking
- [ ] *No problems yet*

### Graphs
- [ ] *No problems yet*

### Dynamic Programming
- [ ] *No problems yet*

---

## 🎯 Goals & Milestones

- [ ] Complete 10 Array & Hashing problems
- [ ] Complete 5 Two Pointers problems
- [ ] Complete 50 total problems
- [ ] Master all Easy difficulty problems in top categories
- [ ] Solve at least one problem daily

---

## 📝 Notes & Insights

### Key Learnings
- HashSet provides O(1) average lookup time, making it ideal for duplicate detection
- The `add()` method in HashSet returns false if element already exists
- **Method call overhead matters:** `charAt()` calls are significantly slower than direct array access
- **Big-O doesn't tell the full story:** Two O(n) solutions can have vastly different performance due to constant factors
- **Performance optimization insight:** Converting strings to char arrays first can provide ~2.5x speedup in tight loops

### Patterns Identified
- **Duplicate Detection Pattern:** Use HashSet for O(n) time complexity vs O(n²) brute force
- **Character Frequency Pattern:** Use fixed-size array (26) for lowercase English letters instead of HashMap for O(1) space
- **String Processing Optimization:** For tight loops with heavy string access, consider converting to char array first to avoid method call overhead

---

## 🔄 Next Steps

1. Continue with Arrays & Hashing category
2. Focus on understanding space-time tradeoffs
3. Practice explaining approach before coding

---

*This tracker is automatically maintained. Last entry added: Friday, February 13, 2026*
