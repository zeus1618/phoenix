# DSA Learning Progress Tracker

> **Last Updated:** Sunday, February 15, 2026

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

- **Total Problems Solved:** 5
- **Problems In Progress:** 1
- **Categories Covered:** Arrays & Hashing
- **Current Streak:** 3 days

---

## 📋 Problems Overview Table

| # | Date | Problem | Difficulty | Category | Time | Space | AI Help | Status |
|---|------|---------|------------|----------|------|-------|---------|--------|
| 1 | 2026-02-13 | [Contains Duplicate (#217)](https://leetcode.com/problems/contains-duplicate/) | Easy | Arrays & Hashing | O(n) | O(n) | ❌ No | ✅ Done |
| 2 | 2026-02-13 | [Valid Anagram (#242)](https://leetcode.com/problems/valid-anagram/) | Easy | Arrays & Hashing | O(n) | O(1) | ❌ No | ✅ Done |
| 3 | 2026-02-13 | [Two Sum (#1)](https://leetcode.com/problems/two-sum/) | Easy | Arrays & Hashing | O(n) | O(n) | ❌ No | ✅ Done |
| 4 | 2026-02-13 | [Group Anagrams (#49)](https://leetcode.com/problems/group-anagrams/) | Medium | Arrays & Hashing | O(nk log k) | O(nk) | ❌ No | ✅ Done |
| 5 | 2026-02-15 | [Top K Frequent Elements (#347)](https://leetcode.com/problems/top-k-frequent-elements/) | Medium | Arrays & Hashing | TBD | TBD | TBD | 🔄 In Progress |
| 6 | 2026-02-15 | [Maximum Subarray (#53)](https://leetcode.com/problems/maximum-subarray/) | Medium | Arrays & Hashing | O(n) | O(1) | ❌ No | ✅ Done |

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

#### ✅ Problem 3: Two Sum
- **Platform:** LeetCode
- **Problem Number:** #1
- **Difficulty:** Easy
- **Link:** https://leetcode.com/problems/two-sum/description/
- **Category:** Arrays & Hashing

**Problem:**
Given an array of integers and a target sum, return the indices of two numbers that add up to the target. Each input has exactly one solution, and you cannot use the same element twice.

**Approach:**
Used HashMap to store complement pairs. For each element, calculated its complement (target - current) and checked if it exists in the map. If found, returned the indices immediately. Otherwise, stored current element with its index for future lookups. Single pass solution.

**Complexity Analysis:**
- **Time Complexity:** O(n) - Single pass through array with O(1) HashMap operations
- **Space Complexity:** O(n) - HashMap storage for up to n elements

**Evolution of Understanding:**
1. **Initial Attempt:** Two-pointer variation checking from both ends - O(n²) but creative
2. **Realization:** Two-pointer only works on SORTED arrays, degrades to O(n²) on unsorted
3. **Final Solution:** HashMap with complement pattern - Optimal O(n) solution
4. **Deep Analysis:** Compared with gap-based O(n²) solution from LeetCode

**Key Learnings:**
- **HashMap Complement Pattern:** For "find pair with sum" → use `complement = target - current`
- **Theory vs Practice:** O(n) HashMap can be slower than O(n²) for small arrays (n<100) due to:
  - Hash computation overhead (~5 units vs 1 for array access)
  - Memory allocation and garbage collection
  - Cache misses (HashMap has poor locality vs sequential array access)
  - Constant factor: ~21 units per HashMap operation vs ~3 for direct array access
- **Two-Pointer Limitation:** Requires sorted data or sorted property. On unsorted arrays without sorting first, it's just disguised O(n²) brute force
- **LeetCode Bias:** Test cases favor early answers and small arrays (n<100), making O(n²) solutions appear faster. Production needs worst-case guarantees
- **Early Termination Power:** Gap-based solutions check nearby pairs first, finding [0,1] in 1 check vs HashMap's n iterations, but explodes to O(n²) on worst case
- **Performance Reality:** For n=100 early answer: Gap=3 CPU units, HashMap=2,100 units (700x slower!). For n=10,000 worst case: Gap=50M checks, HashMap=10k ops (5000x faster!)
- **Production Principle:** Always choose guaranteed O(n) over best-case-optimized O(n²)

**Alternative Approaches Analyzed:**
1. Brute Force - O(n²) time, O(1) space
2. Two-Pointer on Sorted - O(n log n) time, O(n) space (must track original indices)
3. Gap-Based - O(n²) time, O(1) space (fast on LeetCode, fails in production)

**AI Assistance:**
- ❌ No - Solution implemented independently
- ℹ️ Note: AI provided extensive analysis AFTER implementation, explaining:
  - Why O(n²) can beat O(n) on small inputs
  - HashMap overhead breakdown
  - Cache locality impact
  - LeetCode test case bias
  - Real-world performance considerations

**Status:** ✅ Completed  
**Implementation File:** `arraysAndHashing/TwoSum.java`

---

#### 🔄 Problem 4: Group Anagrams (Completed on Friday, February 13, 2026)
- **Platform:** LeetCode
- **Problem Number:** #49
- **Difficulty:** Medium
- **Link:** https://leetcode.com/problems/group-anagrams/
- **Category:** Arrays & Hashing

**Problem:**
Given an array of strings, group the anagrams together. You can return the answer in any order. An anagram is a word formed by rearranging the letters of another word, using all original letters exactly once.

**Constraints:**
- 1 <= strs.length <= 10^4
- 0 <= strs[i].length <= 100
- strs[i] consists of lowercase English letters

**Approach:**
[To be documented after implementation]

**Complexity Analysis:**
- **Time Complexity:** [To be analyzed]
- **Space Complexity:** [To be analyzed]

**AI Assistance:**
- TBD - Solution not yet implemented

**Status:** 🔄 In Progress  
**Implementation File:** `arraysAndHashing/GroupAnagrams.java`

---

#### 🔄 Problem 5: Top K Frequent Elements (In Progress)
- **Platform:** LeetCode
- **Problem Number:** #347
- **Difficulty:** Medium
- **Link:** https://leetcode.com/problems/top-k-frequent-elements/
- **Category:** Arrays & Hashing

**Problem:**
Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

**Constraints:**
- 1 <= nums.length <= 10^5
- -10^4 <= nums[i] <= 10^4
- k is in the range [1, the number of unique elements in the array]
- It is guaranteed that the answer is unique

**Follow-up:**
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

**Approach:**
[To be documented after implementation]

**Complexity Analysis:**
- **Time Complexity:** [To be analyzed]
- **Space Complexity:** [To be analyzed]

**AI Assistance:**
- TBD - Solution not yet implemented

**Status:** 🔄 In Progress  
**Implementation File:** `arraysAndHashing/TopKFrequentElements.java`

---

#### 🔄 Problem 6: Maximum Subarray (Completed)
- **Platform:** LeetCode
- **Problem Number:** #53
- **Difficulty:** Medium
- **Link:** https://leetcode.com/problems/maximum-subarray/
- **Category:** Arrays & Hashing

**Problem:**
Given an integer array nums, find the contiguous subarray with the largest sum, and return its sum. A subarray is a contiguous non-empty sequence of elements within an array.

**Constraints:**
- 1 <= nums.length <= 10^5
- -10^4 <= nums[i] <= 10^4

**Follow-up:**
If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

**Approach:**
Implemented Kadane's Algorithm - a greedy dynamic programming approach that finds the maximum subarray sum in linear time. The algorithm maintains a running sum and a global maximum. At each position, we add the current element to the running sum, update the global maximum, and reset the running sum to 0 if it becomes negative (start fresh). The key insight: a negative sum will only reduce future positive numbers, so it's better to abandon it and start a new subarray.

**Complexity Analysis:**
- **Time Complexity:** O(n) - Single pass through the array, each element visited exactly once
- **Space Complexity:** O(1) - Only two integer variables regardless of input size

**Key Learnings:**
- Kadane's Algorithm is the optimal solution combining greedy choice with dynamic programming
- The "reset when negative" strategy is the critical insight
- Order of operations matters: add → update max → reset (ensures correctness for all-negative arrays)
- This is a foundational pattern that appears in many subarray optimization problems
- Initialize maxSum to Integer.MIN_VALUE to handle all-negative arrays correctly
- The algorithm is asymptotically optimal - can't do better than O(n) since we must examine each element

**Alternative Approaches Considered:**
1. Dynamic Programming with explicit array - O(n) time, O(n) space (Kadane's is space-optimized version)
2. Divide and Conquer - O(n log n) time, O(log n) space (slower but demonstrates technique)
3. Brute Force - O(n²) time, O(1) space (correct but 100x slower)

**AI Assistance:**
- ❌ No - Solution implemented independently
- ℹ️ Note: AI provided analysis and alternative approaches AFTER implementation

**Status:** ✅ Completed  
**Implementation File:** `arraysAndHashing/MaximumSubarray.java`

---

## 📈 Progress by Category

### Arrays & Hashing
- [x] Contains Duplicate (Easy) - #217
- [x] Valid Anagram (Easy) - #242
- [x] Two Sum (Easy) - #1
- [x] Group Anagrams (Medium) - #49
- [ ] Top K Frequent Elements (Medium) - #347
- [x] Maximum Subarray (Medium) - #53

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
- **HashMap complement pattern:** For "find pair" problems, store complements for O(1) lookup
- **Theory vs Practice gap:** O(n) can be slower than O(n²) for small inputs (n<100) due to constant factors
- **Two-pointer requires sorting:** On unsorted data, two-pointer degrades to O(n²)
- **LeetCode metrics mislead:** Small test cases and early answers bias toward O(n²) solutions
- **Cache locality impact:** Sequential array access (~3 CPU units) vs HashMap lookup (~21 units)
- **Production over benchmarks:** Always choose worst-case guarantees over best-case optimizations
- **Kadane's Algorithm:** Greedy + DP hybrid for subarray optimization - reset when sum goes negative
- **Order of operations matters:** In Kadane's, add → update → reset sequence ensures correctness for all cases
- **Initialization importance:** Use Integer.MIN_VALUE for max tracking to handle all-negative arrays
- **Asymptotic optimality:** Some problems have provable lower bounds (e.g., must examine all elements)

### Patterns Identified
- **Duplicate Detection Pattern:** Use HashSet for O(n) time complexity vs O(n²) brute force
- **Character Frequency Pattern:** Use fixed-size array (26) for lowercase English letters instead of HashMap for O(1) space
- **String Processing Optimization:** For tight loops with heavy string access, consider converting to char array first to avoid method call overhead
- **Complement/Pair Finding Pattern:** Use HashMap to store seen elements, check for complement in O(1) time
- **Two-Pointer Pattern:** Only applicable on sorted data or when you can sort; requires monotonic property
- **Early Termination Strategy:** Check likely candidates first (nearby pairs, common patterns) but ensure worst-case bounds
- **Kadane's Algorithm Pattern:** For maximum/minimum subarray problems, track running sum and reset when it hurts future elements
- **Greedy + DP Hybrid:** Make locally optimal choices (extend or start fresh) while maintaining global optimum
- **Subarray Optimization Template:** currentValue = max(element, currentValue + element) - decide extend vs restart

---

## 🔄 Next Steps

1. Continue with Arrays & Hashing category
2. Focus on understanding space-time tradeoffs
3. Practice explaining approach before coding

---

*This tracker is automatically maintained. Last entry added: Friday, February 13, 2026*
