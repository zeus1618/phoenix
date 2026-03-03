# DSA Learning Progress Tracker

> **Last Updated:** Monday, March 2, 2026

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

- **Total Problems Solved:** 13
- **Problems In Progress:** 1
- **Categories Covered:** Arrays & Hashing
- **Current Streak:** 5 days

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
| 7 | 2026-02-18 | [Best Time to Buy and Sell Stock (#121)](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/) | Easy | Arrays & Hashing | O(n) | O(1) | ❌ No | ✅ Done |
| 8 | 2026-02-18 | [Maximum Product Subarray (#152)](https://leetcode.com/problems/maximum-product-subarray/) | Medium | Arrays & Hashing | O(n) | O(1) | ❌ No | ✅ Done |
| 9 | 2026-02-18 | [Product of Array Except Self (#238)](https://leetcode.com/problems/product-of-array-except-self/) | Medium | Arrays & Hashing | O(n) | O(1) | ❌ No | ✅ Done |
| 10 | 2026-02-26 | [Rotate Array (#189)](https://leetcode.com/problems/rotate-array/) | Medium | Arrays & Hashing | O(n) | O(n) | ❌ No | ✅ Done |
| 11 | 2026-02-27 | [Valid Sudoku (#36)](https://leetcode.com/problems/valid-sudoku/) | Medium | Arrays & Hashing | O(1) | O(1) | ❌ No | ✅ Done |
| 12 | 2026-03-02 | [Encode and Decode Strings (#271)](https://neetcode.io/problems/string-encode-and-decode) | Medium | Arrays & Hashing | O(m) | O(m) | ❌ No | ✅ Done |
| 13 | 2026-03-02 | [Max Consecutive Ones (#485)](https://leetcode.com/problems/max-consecutive-ones/) | Easy | Arrays & Hashing | O(n) | O(1) | ❌ No | ✅ Done |
| 14 | 2026-03-02 | [K Closest Points to Origin (#973)](https://leetcode.com/problems/k-closest-points-to-origin/) | Medium | Arrays & Hashing | O(n log n) | O(n) | ❌ No | ✅ Done |

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

#### ✅ Problem 7: Best Time to Buy and Sell Stock
- **Platform:** LeetCode
- **Problem Number:** #121
- **Difficulty:** Easy
- **Link:** https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
- **Category:** Arrays & Hashing

**Problem:**
You are given an array prices where prices[i] is the price of a given stock on the ith day. You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock. Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

**Constraints:**
- 1 <= prices.length <= 10^5
- 0 <= prices[i] <= 10^4
- You must buy before you can sell

**Approach:**
Single pass: maintain the minimum price seen so far (best buy candidate) and the maximum profit so far. At each day, compute profit if we sold today (current price − min so far), update max profit, then update the minimum. Updating profit before updating the minimum ensures we never use "buy today" when evaluating "sell today."

**Complexity Analysis:**
- **Time Complexity:** O(n) — one pass through the array
- **Space Complexity:** O(1) — two integer variables only

**Key Learnings:**
- "Minimum so far" plus "max profit so far" yields the optimal single-buy single-sell in one pass.
- Pattern generalizes to other "best ordered pair" problems.

**Alternative Approaches:**
- Brute force: all pairs (i, j) with i < j — O(n²) time, O(1) space.
- Suffix max array — O(n) time, O(n) space (unnecessary).

**AI Assistance:**
- ❌ No - Solution implemented independently
- ℹ️ Note: AI provided analysis and documentation updates after implementation

**Status:** ✅ Completed  
**Implementation File:** `arraysAndHashing/BestTimeToBuyAndSellStock.java`

---

#### ✅ Problem 8: Maximum Product Subarray
- **Platform:** LeetCode
- **Problem Number:** #152
- **Difficulty:** Medium
- **Link:** https://leetcode.com/problems/maximum-product-subarray/
- **Category:** Arrays & Hashing

**Problem:**
Given an integer array nums, find a subarray that has the largest product, and return the product. Unlike maximum sum, products can flip signs (negative × negative = positive), and zeros act as boundaries breaking continuity.

**Constraints:**
- 1 <= nums.length <= 2 * 10^4
- -10 <= nums[i] <= 10
- The product of any subarray is guaranteed to fit in a 32-bit integer

**Approach:**
Dynamic programming with max/min tracking. At each position, track both the maximum and minimum products ending there. At each element, three choices: start fresh (use element alone), extend max (multiply by currMax), or flip sign (multiply by currMin). Zeros reset both to 1 (neutral identity). Pre-scan initializes answer with max single element to handle all-negative arrays.

**Evolution of Solution:**
1. **First Attempt:** Split-by-zero + explicit segment logic (O(n) time, O(n) space) — clear but verbose
2. **Second Attempt:** DP max/min tracking (O(n) time, O(1) space) — optimal standard solution
3. **Third Attempt:** Two-pass Kadane variant (O(n) time, O(1) space) — most intuitive

**Complexity Analysis:**
- **Time Complexity:** O(n) — one pre-scan + one main pass (DP approach)
- **Space Complexity:** O(1) — only a few integer variables

**Key Learnings:**
- **Products ≠ Sums:** Unlike Kadane's for sums, products require tracking BOTH max and min due to sign flips
- **Why track min:** Large negative min × negative number = large positive (min can become max)
- **Zeros are boundaries:** No optimal subarray spans zero; reset state when encountered
- **Three approaches, all O(n):** DP max/min (standard), two-pass (intuitive), segment logic (educational)
- **Even/odd negative parity:** Even negatives → full product; odd negatives → exclude first or last
- **Direction symmetry:** Left-to-right finds product excluding last neg; right-to-left excludes first neg

**Alternative Approaches Analyzed:**
1. **DP Max/Min Tracking** — O(n) time, O(1) space, single pass (optimal standard solution)
2. **Two-Pass Kadane Variant** — O(n) time, O(1) space, two passes (most intuitive)
3. **Split-by-Zero + Segment Logic** — O(n) time, O(n) space (clear explicit logic)
4. **Brute Force** — O(n²) time, O(1) space (try all subarrays)

**AI Assistance:**
- ❌ No - All three solutions implemented independently
- ℹ️ Note: AI provided analysis, explained why split-by-zero was correct (after user correction), added Kadane variant, and created detailed learning guide documenting all approaches

**Status:** ✅ Completed  
**Implementation File:** `arraysAndHashing/MaximumProductSubarray.java`  
**Detailed Learning Guide:** `arraysAndHashing/learnings/MaximumProductSubarray-Learning.md`

---

#### ✅ Problem 9: Product of Array Except Self
- **Platform:** LeetCode
- **Problem Number:** #238
- **Difficulty:** Medium
- **Link:** https://leetcode.com/problems/product-of-array-except-self/
- **Category:** Arrays & Hashing

**Problem:**
Given an integer array nums, return an array answer such that answer[i] is equal to the product of all elements of nums except nums[i]. Must run in O(n) time **without using division**. The product of any prefix or suffix is guaranteed to fit in a 32-bit integer.

**Constraints:**
- 2 <= nums.length <= 10^5
- -30 <= nums[i] <= 30
- **Cannot use division operation**
- Must be O(n) time complexity

**Follow-up:**
Can you solve it in O(1) extra space? (Output array doesn't count toward space complexity)

**Approach:**
Prefix and suffix products using output array as workspace. Pass 1 (left-to-right): Build prefix products in output array where answer[i] = product of nums[0..i-1]. Pass 2 (right-to-left): Multiply suffix products on-the-fly using a single variable, where suffix tracks product of nums[i+1..n-1]. Initialize answer[0]=1 (no elements to left) and suffix=1 (multiplicative identity). Key insight: decompose "product except self" into left product × right product, avoiding division entirely.

**Evolution of Solution:**
1. **First Attempt:** Total product ÷ element with special zero handling — O(n) time, O(1) space, but **violates "no division" constraint**
2. **Final Solution:** Prefix/suffix with output array reuse — O(n) time, O(1) space, meets all constraints including follow-up

**Complexity Analysis:**
- **Time Complexity:** O(n) — two passes (prefix left-to-right, suffix right-to-left)
- **Space Complexity:** O(1) — only output array (doesn't count) + one suffix variable

**Key Learnings:**
- **Prefix/suffix decomposition:** Split "all except me" into "before me" × "after me" to avoid division
- **Division forbidden for a reason:** Constraint guides toward prefix/suffix pattern discovery
- **Space optimization:** Reuse output array for prefix, compute suffix on-the-fly
- **Identity initialization:** answer[0]=1 and suffix=1 (multiplicative identity for clean edge handling)
- **Order matters:** In Pass 2, multiply suffix into answer before updating suffix
- **Constraint-driven design:** Unusual constraints aren't arbitrary; they hint at intended approach
- **Zeros handled naturally:** No special logic needed; zeros propagate through products correctly

**Alternative Approaches Analyzed:**
1. **Total Product ÷ Element** — O(n) time, O(1) space — Violates "no division" constraint
2. **Prefix/Suffix with Two Arrays** — O(n) time, O(n) space — Correct but not space-optimal
3. **Prefix/Suffix with Output Array Reuse** — O(n) time, O(1) space — **Optimal solution**
4. **Brute Force** — O(n²) time, O(1) space — Too slow

**AI Assistance:**
- ❌ No - Solution implemented independently
- ℹ️ Note: AI identified the division constraint violation in first attempt, explained prefix/suffix pattern, and created detailed learning guide covering the evolution from division approach to optimal prefix/suffix solution

**Status:** ✅ Completed  
**Implementation File:** `arraysAndHashing/ProductOfArrayExceptSelf.java`  
**Detailed Learning Guide:** `arraysAndHashing/learnings/ProductOfArrayExceptSelf-Learning.md`

---

#### ✅ Problem 10: Rotate Array
- **Platform:** LeetCode
- **Problem Number:** #189
- **Difficulty:** Medium
- **Link:** https://leetcode.com/problems/rotate-array/
- **Category:** Arrays & Hashing

**Problem:**
Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.

**Constraints:**
- 1 <= nums.length <= 10^5
- -2^31 <= nums[i] <= 2^31 - 1
- 0 <= k <= 10^5
- Must modify the array in-place

**Follow-up:**
- Try to come up with at least three different ways to solve this problem
- Could you do it in-place with O(1) extra space?

**Approach:**
Extra array approach. Normalize k with modulo operation (k % n) to handle k > array length. Convert right rotation by k to left rotation by (n-k). Copy elements to temporary array starting from calculated position with manual wrap-around. Copy back to original array. Two-pass solution.

**Complexity Analysis:**
- **Time Complexity:** O(n) — Two passes through array (copy to temp, copy back)
- **Space Complexity:** O(n) — Extra array of same size as input

**Key Learnings:**
- **Current solution works but doesn't meet O(1) space follow-up requirement**
- **Reversal algorithm is optimal:** Three reversals achieve O(n) time, O(1) space:
  1. Reverse entire array
  2. Reverse first k elements
  3. Reverse remaining n-k elements
- **Array reversal pattern:** Powerful technique for in-place array transformations and rotations
- **Modulo normalization:** Essential for rotation problems when k > array length
- **Follow-up importance:** Space constraints often indicate the intended optimal approach
- **Mathematical elegance:** Clever array manipulation can eliminate auxiliary space needs

**Alternative Approaches Analyzed:**
1. **Reversal Algorithm** — O(n) time, O(1) space — **OPTIMAL, meets follow-up**
2. **Cyclic Replacements** — O(n) time, O(1) space — Complex but space-efficient
3. **Extra Array (implemented)** — O(n) time, O(n) space — Correct but not space-optimal
4. **Brute Force (rotate one by one)** — O(n×k) time, O(1) space — Too slow for large k

**AI Assistance:**
- ❌ No - Solution implemented independently
- ℹ️ Note: AI analyzed solution after implementation, identified space constraint issue, explained optimal reversal algorithm approach

**Status:** ✅ Completed  
**Implementation File:** `arraysAndHashing/RotateArray.java`

---

#### ✅ Problem 11: Valid Sudoku (Completed)
- **Platform:** LeetCode
- **Problem Number:** #36
- **Difficulty:** Medium
- **Link:** https://leetcode.com/problems/valid-sudoku/
- **Category:** Arrays & Hashing

**Problem:**
Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
1. Each row must contain the digits 1-9 without repetition.
2. Each column must contain the digits 1-9 without repetition.
3. Each of the nine 3x3 sub-boxes must contain the digits 1-9 without repetition.

**Constraints:**
- board.length == 9
- board[i].length == 9
- board[i][j] is a digit '1'-'9' or '.'
- A Sudoku board could be valid but not necessarily solvable
- Only filled cells need to be validated

**Approach:**
Single-pass validation using HashSet arrays. Created three arrays of HashSets (9 sets each) to track digits seen in each row, column, and 3x3 box. In one pass through the board, each non-empty cell is checked against all three corresponding sets simultaneously. Box index calculated with formula: `(row/3)*3 + (col/3)`. Early return on first duplicate found.

**Evolution of Solution:**
1. **First Attempt:** ArrayList with three separate passes (rows, columns, boxes) — correct logic but inefficient
   - Used `ArrayList.contains()` for O(n) linear search
   - Made 243 cell visits (81×3)
   - Manual x/y tracking for box iteration
2. **Final Solution:** HashSet arrays with single pass — optimal approach
   - HashSet O(1) lookups
   - Single pass through 81 cells
   - Elegant box index formula

**Complexity Analysis:**
- **Time Complexity:** O(1) — Board is fixed 9×9 = 81 cells, each cell checked once with O(1) HashSet operations
- **Space Complexity:** O(1) — 27 HashSets (9 rows + 9 cols + 9 boxes) with max 9 elements each = constant space

**Key Learnings:**
- **Data structure choice matters:** ArrayList.contains() O(n) vs HashSet.contains() O(1) — even for small n=9
- **Single pass vs multiple passes:** One pass (81 cells) vs three passes (243 cells) reduces redundant work
- **Box index formula magic:** `(i/3)*3 + (j/3)` elegantly maps 2D coordinates to box numbers 0-8
  - Row 0-2, Col 0-2 → (0/3)*3 + (0/3) = 0 (top-left)
  - Row 0-2, Col 3-5 → (0/3)*3 + (3/3) = 1 (top-middle)
  - Row 3-5, Col 0-2 → (3/3)*3 + (0/3) = 3 (middle-left)
- **HashSet arrays optimal:** Best balance of O(1) lookups, clear code, reasonable memory
- **Fixed-size problems:** 9×9 board simplifies to O(1) complexity, but constant factors still impact performance
- **Early termination:** Return false immediately on first duplicate found

**Alternative Approaches Analyzed:**
1. **HashSet Arrays (implemented)** — O(1) time, O(1) space, single pass — **Optimal for clarity and performance**
2. **Boolean Arrays** — O(1) time, O(1) space, single pass — Fastest (no hashing) but fixed to 9 digits
3. **String Encoding** — O(1) time, O(1) space, single pass — Elegant single HashSet but string overhead
4. **Three-Pass HashSet** — O(1) time, O(1) space, three passes — Clear separation but redundant traversals
5. **ArrayList Three-Pass** — O(1) time, O(1) space, three passes — Correct but slowest due to linear search

**AI Assistance:**
- ❌ No - Both solutions (first attempt and final) implemented independently
- ℹ️ Note: AI analyzed solution after implementation, explained performance differences, and suggested optimization from ArrayList to HashSet arrays

**Status:** ✅ Completed  
**Implementation File:** `arraysAndHashing/ValidSudoku.java`  
**Detailed Learning Guide:** `arraysAndHashing/learnings/ValidSudoku-Learning.md`

---

#### ✅ Problem 12: Encode and Decode Strings (Completed)
- **Platform:** NeetCode
- **Problem Number:** #271
- **Difficulty:** Medium
- **Link:** https://neetcode.io/problems/string-encode-and-decode
- **Category:** Arrays & Hashing

**Problem:**
Design an algorithm to encode a list of strings to a single string, and decode it back to the original list. The encoded string is sent over the network. The algorithm must handle any possible ASCII characters (256 valid characters).

**Constraints:**
- 0 <= strs.length < 100
- 0 <= strs[i].length < 200
- strs[i] contains any possible characters out of 256 valid ASCII characters

**Follow-up:**
Could you write a generalized algorithm to work on any possible set of characters?

**Approach:**
Fixed-width length encoding. Format: `[listSize][len1][str1][len2][str2]...` where each length is a 3-digit zero-padded integer. Used `String.format("%03d")` for clean padding. Decode parses lengths at fixed positions (0, 3, len1+3, ...) and extracts strings of specified lengths. This eliminates delimiter conflicts since any ASCII character can appear in strings - the length prefix tells decoder exactly how many bytes to read.

**Evolution of Solution:**
1. **Fixed-Width Encoding (Primary):** 3-digit length prefix with predictable parsing - O(1) position calculation
2. **Length + Delimiter (Alternative):** Variable-width length with '#' delimiter - more space-efficient but requires scanning

**Complexity Analysis:**
- **Time Complexity:** O(m) where m = sum of all string lengths - optimal since we must process every character
- **Space Complexity:** O(m) for encoded string output - optimal for storing the data

**Key Learnings:**
- **Length-prefixed encoding is fundamental:** Used in HTTP (Content-Length), TCP, file formats (PNG, ZIP), serialization protocols
- **Why delimiters fail:** Any delimiter could appear in input strings; escaping is complex and error-prone
- **Fixed vs variable-width trade-off:**
  - Fixed-Width: Faster parsing (O(1) positions), predictable, easier debugging, always 3 bytes overhead
  - Length+Delim: More space-efficient (2-3 bytes), human-readable, requires delimiter scanning
- **Space efficiency comparison:** Length+Delim saves 0-50% space for short strings, negligible for long strings
- **Protocol design pattern:** Telling decoder exact byte count eliminates ambiguity without escaping
- **StringBuilder efficiency:** O(n) amortized append vs O(n²) String concatenation
- **String.format("%03d"):** Clean zero-padding vs manual if-else chains
- **Input validation matters:** Check null/empty to prevent exceptions

**Alternative Approaches Analyzed:**
1. **Fixed-Width Length (implemented)** — O(m) time, O(m) space — Predictable parsing, slight space overhead
2. **Length + Delimiter (implemented as V2)** — O(m) time, O(m) space — Space-efficient, requires scanning
3. **Simple Delimiter** — Fails: delimiter could appear in data
4. **Escape Sequences** — Complex: recursive escaping needed, error-prone

**Real-World Applications:**
- HTTP Content-Length header
- TCP packet framing
- Protocol Buffers (Protobuf)
- Binary file formats (PNG, ZIP)
- Database variable-length fields

**AI Assistance:**
- ❌ No - Both solutions (fixed-width and length+delimiter) implemented independently
- ℹ️ Note: AI provided analysis after implementation, explained trade-offs, created comprehensive learning guide covering protocol design patterns and real-world applications

**Status:** ✅ Completed  
**Implementation File:** `arraysAndHashing/EncodeAndDecodeStrings.java`  
**Detailed Learning Guide:** `arraysAndHashing/learnings/EncodeAndDecodeStrings-Learning.md`

---

#### ✅ Problem 13: Max Consecutive Ones (Completed)
- **Platform:** LeetCode
- **Problem Number:** #485
- **Difficulty:** Easy
- **Link:** https://leetcode.com/problems/max-consecutive-ones/description/
- **Category:** Arrays & Hashing

**Problem:**
Given a binary array nums containing only 0s and 1s, find the maximum number of consecutive 1s in the array.

**Constraints:**
- 1 <= nums.length <= 10^5
- nums[i] is either 0 or 1

**Approach:**
Single-pass streaming algorithm with two variables: max (global maximum) and currMax (current streak). Iterate through array: when encountering 0, reset currMax to 0 (zeros act as boundaries); when encountering 1, increment currMax and update max. This treats zeros as natural delimiters that break consecutive sequences.

**Complexity Analysis:**
- **Time Complexity:** O(n) — Single pass through array, each element visited exactly once
- **Space Complexity:** O(1) — Only two integer variables (max, currMax)

**Key Learnings:**
- **Zeros as boundaries:** Similar to Maximum Product Subarray, zeros segment the problem into independent sections
- **Single-pass sufficiency:** Unlike prefix/suffix problems, one forward pass captures all information needed
- **Enhanced for-loop appropriateness:** When indices aren't needed, `for(element : array)` produces cleaner code
- **Continuous max update:** Updating max after each increment ensures you capture maximum anywhere in array
- **Asymptotic optimality:** O(n) is provably optimal since you must examine every element at least once
- **"Count until boundary" pattern:** Track current streak counter, reset on boundary element, maintain global maximum
- **Simplicity wins:** Not every problem needs complex data structures or multiple passes - simple solutions for simple problems

**Alternative Approaches Analyzed:**
1. **Reset on Zero (implemented)** — O(n) time, O(1) space — Uses `continue` statement to skip increment after reset
2. **Conditional Increment** — O(n) time, O(1) space — Slightly cleaner, explicit `if (n == 1)` handling without `continue`
3. **Ternary Operator** — O(n) time, O(1) space — Most concise with `currMax = (n == 1) ? currMax + 1 : 0`
4. **Kadane's Pattern** — O(n) time, O(1) space — Demonstrates pattern generalization but overkill for this problem

**AI Assistance:**
- ❌ No - Solution implemented independently by user on first attempt
- ℹ️ Note: AI provided analysis AFTER implementation, comparing alternative approaches and identifying the "count until boundary" pattern

**Status:** ✅ Completed  
**Implementation File:** `arraysAndHashing/MaxConsecutiveOnes.java`

---

#### ✅ Problem 14: K Closest Points to Origin (Completed)
- **Platform:** LeetCode
- **Problem Number:** #973
- **Difficulty:** Medium
- **Link:** https://leetcode.com/problems/k-closest-points-to-origin/
- **Category:** Arrays & Hashing

**Problem:**
Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0). The distance between two points is the Euclidean distance (√(x² + y²)). You may return the answer in any order.

**Constraints:**
- -10^4 <= xi, yi <= 10^4
- 1 <= k <= points.length <= 10^4
- Answer is guaranteed to be unique (except for order)

**Approach:**
Full sort with custom comparator. Created Pair class to encapsulate each point with its squared distance (x² + y²). Sorted all n points by distance using `Integer.compare()` to prevent overflow. Extracted first k points from sorted list. Traded extra O(n) memory for code clarity and OOP design.

**Complexity Analysis:**
- **Time Complexity:** O(n log n) — Creating Pair objects O(n) + sorting n points O(n log n) + extracting k points O(k) = O(n log n) dominates
- **Space Complexity:** O(n) — ArrayList and n Pair objects

**Key Learnings:**
- **Sqrt optimization is critical:** Used x² + y² instead of √(x² + y²) for distance comparisons. Since √a < √b ⟺ a < b for non-negative numbers, this saves ~30-50 CPU cycles per comparison (~300,000 cycles total for 10,000 comparisons during sort)
- **Integer.compare() prevents overflow:** Subtraction-based comparators (a-b) can overflow when values differ by > 2³¹. Always use `Integer.compare(a, b)` for safe comparisons. With max distance² = 2×10⁸, subtraction could overflow
- **Full sort is acceptable:** O(n log n) is perfectly fine when k ≈ n or simplicity is priority. Modern sorting (TimSort) is highly optimized
- **Theory vs Practice:** Don't over-optimize prematurely. Simple code often runs faster due to better compiler optimization and cache friendliness
- **OOP tradeoff:** Pair class makes code readable at cost of extra memory. Could sort points array directly for O(1) space but less clear
- **Alternative exists for small k:** When k << n (e.g., k=10, n=10,000), Max Heap approach is O(n log k) which is 4x faster than O(n log n). But for k ≈ n, full sort is optimal

**Alternative Approaches Analyzed:**
1. **Full Sort (implemented)** — O(n log n) time, O(n) space — Simple, clean, optimal when k ≈ n
2. **Max Heap (Priority Queue)** — O(n log k) time, O(k) space — Better when k << n (e.g., k=10, n=10,000)
3. **Quickselect** — O(n) average time, O(1) space — Optimal average but O(n²) worst case, complex
4. **In-place Sort** — O(n log n) time, O(1) space — Sort points array directly with lambda

**TODOs Created:**
Added 3 TODOs to revisit this problem with alternative approaches once patterns are learned:
- TODO #1: Implement with Max Heap (needs: Heap/Priority Queue pattern)
- TODO #2: Implement with Quickselect (needs: Quickselect algorithm)
- TODO #3: Implement with Min Heap Extract (needs: Heap/Priority Queue pattern)

**AI Assistance:**
- ❌ No - Solution implemented independently by user
- ℹ️ Note: AI provided analysis AFTER implementation, identified sqrt optimization insight, warned about overflow risk in comparator, suggested Integer.compare() fix, explained alternative approaches with complexity tradeoffs

**Status:** ✅ Completed  
**Implementation File:** `arraysAndHashing/KClosestPointsToOrigin.java`

---

## 📈 Progress by Category

### Arrays & Hashing
- [x] Contains Duplicate (Easy) - #217
- [x] Valid Anagram (Easy) - #242
- [x] Two Sum (Easy) - #1
- [x] Group Anagrams (Medium) - #49
- [ ] Top K Frequent Elements (Medium) - #347
- [x] Maximum Subarray (Medium) - #53
- [x] Best Time to Buy and Sell Stock (Easy) - #121
- [x] Maximum Product Subarray (Medium) - #152
- [x] Product of Array Except Self (Medium) - #238
- [x] Rotate Array (Medium) - #189
- [x] Valid Sudoku (Medium) - #36
- [x] Encode and Decode Strings (Medium) - #271
- [x] Max Consecutive Ones (Easy) - #485
- [x] K Closest Points to Origin (Medium) - #973

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
- **Minimum-so-far pattern:** For "best buy then sell" (ordered pair), track minimum seen so far and max profit in one pass — O(n) time, O(1) space
- **Products vs sums:** Unlike Kadane's for maximum sum, maximum product requires tracking BOTH max and min due to sign flips (negative × negative = positive)
- **Track extremes for sign flips:** When operations flip signs, must track both max AND min; min can become max when multiplied by negative
- **Zeros as boundaries:** In product problems, zeros break continuity; no optimal subarray spans zero
- **Even/odd negative parity:** Even negatives → full product positive; odd negatives → must exclude first or last negative
- **Prefix/suffix decomposition:** Split "all except current" into "before current" × "after current" to avoid division
- **Constraint-driven design:** Unusual constraints (like "no division") guide toward intended solution approach
- **Identity element initialization:** Use 1 for multiplication, 0 for addition, MIN/MAX for comparisons
- **Array reversal for rotation:** Three reversals achieve in-place rotation: reverse all → reverse first k → reverse last n-k
- **Space follow-ups matter:** O(1) space constraints indicate intended optimal approach; auxiliary space solution often insufficient
- **Data structure choice for lookups:** ArrayList.contains() is O(n) vs HashSet.contains() is O(1) — matters even for small datasets
- **Single-pass optimization:** Combining multiple validation checks in one pass reduces redundant work (81 cells vs 243)
- **Box index formula:** `(row/3)*3 + (col/3)` elegantly maps 2D grid coordinates to linear box index without complex conditionals
- **HashSet arrays pattern:** Array of HashSets provides O(1) lookups per category while keeping code clear and organized
- **Fixed-size simplification:** Problems with fixed dimensions (9×9 board) have O(1) complexity but constant factors still matter
- **Zeros as natural boundaries:** In consecutive element problems, zeros often segment the problem into independent sections
- **Single-pass streaming:** For simple counting/tracking problems, one forward pass with state variables is often optimal
- **Enhanced for-loop appropriateness:** Use `for(element : array)` when indices aren't needed for cleaner code
- **Continuous max update:** In streaming algorithms, update global max after each local change to capture maximum anywhere
- **Simplicity principle:** Simple problems deserve simple solutions - avoid over-engineering with complex data structures
- **Sqrt optimization for distance:** Use x² + y² instead of √(x² + y²) for distance comparisons (saves ~30-50 CPU cycles per comparison)
- **Integer.compare() for safety:** Never use subtraction (a-b) in comparators - can overflow. Always use Integer.compare(a, b)
- **Full sort is acceptable:** O(n log n) sorting is perfectly fine when k ≈ n or simplicity is priority - don't over-optimize prematurely
- **Know when heap beats sort:** When k << n (e.g., k=10, n=10,000), Max Heap O(n log k) is 4x faster than full sort O(n log n)

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
- **Single-pass min/max profit:** Track minimum price so far; at each step, profit = price − minSoFar, then update minSoFar — optimal for one buy, one sell
- **DP max/min tracking:** For problems with sign flips (products, alternating), track both maximum and minimum ending at each position
- **Bidirectional scanning:** When direction matters (first vs last), scan both left-to-right and right-to-left; answer found in one direction
- **Boundary reset pattern:** Elements that break continuity (zeros, delimiters) split problem into independent segments
- **Prefix/suffix product pattern:** For "all except current", build prefix (left products) and suffix (right products), then combine
- **Output array as workspace:** Reuse output array for intermediate results to achieve O(1) extra space
- **Two-pass accumulation:** Pass 1 builds one component, Pass 2 refines/combines with second component
- **Array reversal pattern:** For in-place array rotation/transformation, use reversal techniques to eliminate auxiliary space
- **Modulo for circular operations:** Normalize rotation amounts with modulo to handle values exceeding array length
- **Multi-dimensional validation pattern:** Use array of HashSets to track multiple dimensions (rows, columns, boxes) with O(1) lookup per dimension
- **Box/Grid index mapping:** For 2D grids divided into sub-boxes, use formula `(row/boxSize)*numBoxesPerRow + (col/boxSize)` to map coordinates to box index
- **Single-pass multi-check:** When validating multiple independent constraints, combine checks in one pass to reduce redundant iterations
- **Length-prefixed encoding:** When no character can be reserved, store length before data to tell decoder exact byte count (fundamental protocol pattern)
- **Fixed vs variable-width encoding:** Fixed = predictable parsing, variable = space efficient (choose based on requirements)
- **Delimiter-free communication:** Length-prefix eliminates need for delimiters that could conflict with data content
- **Count until boundary pattern:** Track current streak counter, reset on boundary element (0, delimiter), maintain global maximum - applies to consecutive element counting problems
- **Distance comparison pattern:** For problems involving Euclidean distance, avoid sqrt by comparing squared distances (√a < √b ⟺ a < b for non-negative)
- **K-selection pattern:** Three approaches based on requirements: Full sort O(n log n) for simplicity, Max Heap O(n log k) when k << n, Quickselect O(n) for optimal average

---

## 🔄 Next Steps

1. Continue with Arrays & Hashing category
2. Focus on understanding space-time tradeoffs
3. Practice explaining approach before coding

---

*This tracker is automatically maintained. Last entry added: Monday, March 2, 2026 (K Closest Points to Origin #973)*
