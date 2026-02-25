# Maximum Product Subarray - Deep Learning Guide

**Problem:** [LeetCode #152 - Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray/)  
**Difficulty:** Medium  
**Category:** Arrays & Hashing, Dynamic Programming

---

## Table of Contents

1. [Problem Evolution Journey](#problem-evolution-journey)
2. [Solution Comparison: Three Valid Approaches](#solution-comparison-three-valid-approaches)
3. [Performance Deep Dive](#performance-deep-dive)
4. [Key Insights](#key-insights)
5. [Major Lessons](#major-lessons)
6. [Best Practices](#best-practices)
7. [Pattern Recognition](#pattern-recognition)
8. [Mistakes to Avoid](#mistakes-to-avoid)

---

## Problem Evolution Journey

### Initial Understanding

**Given:** An integer array `nums`  
**Find:** Contiguous subarray with the largest product  
**Return:** The maximum product value

**Key Constraints:**
- `1 <= nums.length <= 2 * 10^4`
- `-10 <= nums[i] <= 10`
- Product guaranteed to fit in 32-bit integer

**What makes this different from Maximum Subarray (sum)?**
- **Negatives can help:** `negative × negative = positive` (sign flips)
- **Zeros are boundaries:** Any subarray containing zero has product ≤ 0
- **Can't use Kadane's directly:** Sum problems reset on negative; products need both max AND min tracking

---

### Attempt 1: Split-by-Zero + Explicit Segment Logic

**Intuition:** Zeros break continuity, so split the array into zero-free segments and solve each independently.

**Algorithm:**
1. Split array by zeros into segments (no zeros within a segment)
2. If array contains zero, initialize max candidate to 0
3. For each segment:
   - Count negative numbers
   - **If even negatives:** Full segment product is positive → that's the answer for this segment
   - **If odd negatives and only 1 negative:** Max of (product left of negative, product right of negative)
   - **If odd negatives and 2+ negatives:** Max of (exclude first negative, exclude last negative)

**Code Structure:**
```java
// Split by zeros
List<List<Integer>> splitList = new ArrayList<>();
for each element:
    if zero: save current segment, start new segment
    else: add to current segment

// Process each segment
for each segment:
    count negatives
    if even: multiply full segment
    if odd with 1 neg: max(left, right)
    if odd with 2+ negs: max(product[0..lastNeg-1], product[firstNeg+1..end])
```

**Complexity:**
- **Time:** O(n) — one pass to split, one pass per segment for counting/products
- **Space:** O(n) — store all elements in segment lists

**Correctness Proof:**

For zero-free segments with all `|x| ≥ 1`:
- **Even negatives:** Full product is positive; no sub-product can be larger (multiplying by more elements with `|x| ≥ 1` can't shrink absolute value)
- **Odd negatives with 1 negative:** Left and right are all-positive segments; each side's full product is optimal
- **Odd negatives with 2+ negatives:** 
  - `leftProduct × centerProduct` = `product[0..lastNeg-1]` has (totalNegs - 1) negatives → even → positive ✓
  - `centerProduct × rightProduct` = `product[firstNeg+1..end]` has (totalNegs - 1) negatives → even → positive ✓
  - `centerProduct` alone has (totalNegs - 2) negatives → same parity as totalNegs → odd → negative → never optimal

**Verdict:**
- ✅ **Correct:** Logic is sound and provable
- ✅ **Clear:** Explicit handling of even/odd cases makes reasoning easy
- ❌ **Space inefficient:** O(n) space for segment lists
- ❌ **Verbose:** ~95 lines of code

---

### Attempt 2: DP Max/Min Tracking (Optimal Solution)

**Key Insight:** Unlike sums, products can flip signs. Track BOTH maximum and minimum products ending at current position.

**Why track minimum?**
- A large negative min (`-100`) × negative number (`-2`) = large positive (`200`)
- Min can become max when multiplied by negative

**Algorithm:**
```java
// Pre-scan: handle all-negative arrays
answer = max of all single elements

// Main loop
currMax = 1, currMin = 1
for each element:
    if element == 0:
        reset currMax = 1, currMin = 1
        continue
    
    temp = currMax × element  // save old max
    currMax = max(element, currMax × element, currMin × element)
    currMin = min(element, temp, currMin × element)
    answer = max(answer, currMax)
```

**At each position, three choices:**
1. **Start fresh:** Use element alone (abandon previous products)
2. **Extend max:** Multiply previous max by current element
3. **Flip sign:** Multiply previous min by current element (handles negative × negative)

**Example trace: `[2, 3, -2, 4]`**

```
Initial: answer = max(2,3,-2,4) = 4

i=0, num=2:
  currMax = max(2, 1×2, 1×2) = 2
  currMin = min(2, 1×2, 1×2) = 2
  answer = max(4, 2) = 4

i=1, num=3:
  temp = 2×3 = 6
  currMax = max(3, 2×3, 2×3) = 6  ✓ [2,3]
  currMin = min(3, 6, 2×3) = 3
  answer = max(4, 6) = 6

i=2, num=-2:
  temp = 6×(-2) = -12
  currMax = max(-2, 6×(-2), 3×(-2)) = -2
  currMin = min(-2, -12, 3×(-2)) = -12
  answer = max(6, -2) = 6

i=3, num=4:
  temp = -2×4 = -8
  currMax = max(4, -2×4, -12×4) = 4
  currMin = min(4, -8, -12×4) = -48
  answer = max(6, 4) = 6

Result: 6
```

**Complexity:**
- **Time:** O(n) — one pre-scan + one main loop
- **Space:** O(1) — only a few integer variables

**Verdict:**
- ✅ **Optimal:** O(n) time, O(1) space
- ✅ **Standard:** Most common interview solution
- ✅ **Concise:** ~20 lines of code
- ❌ **Requires understanding:** Min tracking is not immediately obvious

---

### Attempt 3: Kadane's Variant - Two-Pass Approach

**Key Insight:** The answer is either found scanning left-to-right OR right-to-left. Together, both passes cover all optimal candidates.

**Why two passes?**

For segments with **odd negatives**:
- **Left-to-right pass:** Finds best product by excluding the last negative
  - Running product grows until last negative, then turns negative
  - We capture the max before it turns negative
- **Right-to-left pass:** Finds best product by excluding the first negative
  - Running product grows from right until first negative, then turns negative
  - We capture the max before it turns negative

**Algorithm:**
```java
maxProduct = Integer.MIN_VALUE
product = 1

// Left-to-right
for i=0 to n-1:
    product *= nums[i]
    maxProduct = max(maxProduct, product)
    if product == 0:
        product = 1  // reset

// Right-to-left
product = 1
for i=n-1 to 0:
    product *= nums[i]
    maxProduct = max(maxProduct, product)
    if product == 0:
        product = 1  // reset
```

**Example: `[-2, -3, -4]` (3 negatives - odd)**

**Left-to-right:**
```
i=0: product = -2,    max = -2
i=1: product = 6,     max = 6   ✓ finds [-2,-3]
i=2: product = -24,   max = 6
```

**Right-to-left:**
```
i=2: product = -4,    max = 6
i=1: product = 12,    max = 12  ✓ finds [-3,-4]
i=0: product = -24,   max = 12
```

**Result: 12** (right-to-left found the better candidate)

**Why this works:**

| Negative Count | Left-to-right | Right-to-left | Winner |
|----------------|---------------|---------------|--------|
| 0 (all positive) | Full product | Full product | Either |
| Even | Full product | Full product | Either |
| Odd | Product up to (not including) last neg | Product from (not including) first neg | Max of both |

**Complexity:**
- **Time:** O(n) — two passes
- **Space:** O(1) — two variables

**Verdict:**
- ✅ **Most intuitive:** "Try both directions" is easy to explain
- ✅ **Optimal space:** O(1)
- ✅ **Clean code:** ~25 lines, very simple logic
- ❌ **Two passes:** Slightly less efficient than single-pass DP (though still O(n))

---

## Solution Comparison: Three Valid Approaches

### Side-by-Side Comparison

| Aspect | Split-by-Zero | DP Max/Min | Two-Pass Kadane |
|--------|---------------|------------|-----------------|
| **Time Complexity** | O(n) | O(n) | O(n) |
| **Space Complexity** | O(n) | O(1) | O(1) |
| **Number of Passes** | Multiple (split + per-segment) | 1 (+ pre-scan) | 2 |
| **Code Lines** | ~95 | ~20 | ~25 |
| **Intuition** | "Handle even/odd negatives explicitly" | "Track both extremes for sign flips" | "Try both directions" |
| **Interview Preference** | Valid but verbose | **Most common** | Second most common |
| **Easiest to Explain** | No (complex logic) | No (min tracking not obvious) | **Yes** |
| **Easiest to Prove Correct** | **Yes** (explicit cases) | No (requires DP reasoning) | Somewhat (direction symmetry) |

### When to Use Each

**Split-by-Zero:**
- When you want explicit control over segment logic
- When explaining to someone unfamiliar with DP
- When space is not a constraint

**DP Max/Min:**
- **Default choice for interviews** (most common expected solution)
- When single-pass is important
- When you want O(1) space

**Two-Pass Kadane:**
- When you want the most intuitive solution
- When explaining to beginners
- When you want O(1) space with simple logic

---

## Performance Deep Dive

### Constant Factor Analysis

Let's analyze actual operation counts for `n = 1000`:

**Split-by-Zero:**
- Split pass: 1000 comparisons, ~k segment creations
- Per-segment negative counting: 1000 comparisons
- Per-segment products: up to 1000 multiplications
- **Total:** ~3000 operations + O(n) memory allocations

**DP Max/Min:**
- Pre-scan: 1000 comparisons
- Main loop: 1000 iterations × (1 zero check + 3 multiplications + 2 max + 2 min) = ~8000 operations
- **Total:** ~9000 operations, minimal memory

**Two-Pass Kadane:**
- Left-to-right: 1000 iterations × (1 multiply + 1 max + 1 zero check) = ~3000 operations
- Right-to-left: same = ~3000 operations
- **Total:** ~6000 operations, minimal memory

### Real-World Performance

On modern CPUs with caching and branch prediction:

**Memory access patterns:**
- Split-by-zero: Poor locality (scattered List allocations)
- DP max/min: Excellent locality (sequential array access)
- Two-pass: Excellent locality (two sequential passes)

**Branch prediction:**
- Split-by-zero: Many branches (if-else chains per segment)
- DP max/min: Minimal branches (simple max/min)
- Two-pass: Minimal branches (simple multiply + max)

**Expected runtime ranking (fastest to slowest):**
1. **Two-pass Kadane** — fewest operations, good locality
2. **DP Max/Min** — slightly more operations, good locality
3. **Split-by-Zero** — most operations, poor locality

**LeetCode typical results (n=20,000):**
- Two-pass: ~1-2ms
- DP max/min: ~2-3ms
- Split-by-zero: ~3-5ms

All are effectively O(n), but constant factors and memory patterns matter.

---

## Key Insights

### 1. Products vs Sums: Fundamental Difference

**Maximum Subarray (Sum) — Kadane's Algorithm:**
- Negatives **always hurt** → reset when sum goes negative
- Only need to track **one value** (max sum so far)
- Simple rule: extend or start fresh

**Maximum Product Subarray:**
- Negatives **can help** → negative × negative = positive
- Must track **two values** (max AND min for sign flips)
- Complex rule: extend max, flip via min, or start fresh

### 2. Zero as a Natural Boundary

No optimal subarray can span a zero because:
- Any subarray containing zero has product ≤ 0
- Can always do better by excluding the zero

This allows us to:
- Split into zero-free segments
- Process independently
- Take max across segments + zero itself

### 3. Even/Odd Negative Parity

For zero-free segments where all `|x| ≥ 1`:

**Even negatives:**
- Full product is positive
- No sub-product can be larger (more factors with `|x| ≥ 1` → larger absolute value)
- Answer: full segment

**Odd negatives:**
- Full product is negative
- Must exclude at least one negative to get positive product
- Optimal: exclude either first or last negative (maximize remaining product)

### 4. Direction Symmetry

For odd-negative segments:
- **From left:** Product grows positive until last negative → capture before
- **From right:** Product grows positive until first negative → capture before
- **Together:** Both passes cover the two optimal candidates

This is why two-pass works!

### 5. Min Tracking is Not Optional

Many students try to adapt Kadane's by only tracking max. This fails:

**Example: `[2, -5, -3]`**
- Tracking only max: `max(2) = 2`, `max(-5) = 2`, `max(-3) = 2` → wrong
- Tracking max AND min:
  - After `2`: max=2, min=2
  - After `-5`: max=2, min=-10
  - After `-3`: max=30 ✓ (min × -3 = -10 × -3 = 30)

---

## Major Lessons

### 1. Sign Flips Require Bidirectional Tracking

**Lesson:** When operations can flip signs (negatives, division), track both extremes.

**Application:** Any problem where current value can make large negative → large positive.

**Related problems:**
- Maximum Product Subarray ✓
- Product of Array Except Self (handle negatives)
- Best Time to Buy and Sell Stock with Transaction Fee (profit can go negative)

### 2. Multiple Solutions Show Mastery

Having three working approaches demonstrates:
- **Breadth:** Understanding different paradigms (segment logic, DP, direction symmetry)
- **Depth:** Knowing tradeoffs (space vs clarity, passes vs complexity)
- **Flexibility:** Can adapt to interviewer hints or constraints

### 3. Space-Time Tradeoffs Are Real

**Split-by-zero approach:**
- Pro: Very clear logic, easy to prove correct
- Con: O(n) space, more complex code

**DP max/min approach:**
- Pro: O(1) space, standard solution
- Con: Less intuitive (why track min?)

**Lesson:** Always ask about constraints. If space is unlimited, clarity wins. If space is tight, optimize.

### 4. Intuition Beats Memorization

Two-pass Kadane is easy to remember because the intuition is simple:
- "Try both directions, max will be found in one of them"

DP max/min requires remembering:
- "Track max and min because... uh... sign flips?"

**Lesson:** Understand the WHY, not just the HOW. In interviews, explaining intuition matters more than perfect code.

### 5. Edge Cases Drive Solution Design

**Edge cases in this problem:**
- All negative numbers → need pre-scan or single-element handling
- Contains zeros → need reset logic
- Single element → base case
- All positive → any approach works (easy case)

**Lesson:** List edge cases first, then ensure your solution handles them explicitly.

---

## Best Practices

### 1. Start with Clarity, Optimize Later

**Process:**
1. **First:** Solve correctly (split-by-zero approach)
2. **Second:** Optimize space (DP max/min)
3. **Third:** Simplify intuition (two-pass)

Don't jump to optimal solution immediately. Build understanding incrementally.

### 2. Pre-Scan for Defensive Initialization

```java
int answer = Integer.MIN_VALUE;
for (int n : nums) {
    answer = Math.max(answer, n);
}
```

**Why this is good:**
- Handles all-negative arrays automatically
- Handles single-element arrays
- Makes subsequent logic simpler (don't need to special-case these)

**Lesson:** A simple pre-pass can eliminate many edge case branches later.

### 3. Temp Variables Prevent Update Dependencies

```java
temp = currMax * nums[i];  // Save old max
currMax = max(nums[i], currMax*nums[i], currMin*nums[i]);
currMin = min(nums[i], temp, currMin*nums[i]);  // Use old max
```

**Why needed:** `currMin` update depends on **old** `currMax`, but we're updating `currMax` first.

**Lesson:** When updating multiple variables that depend on each other, save intermediate values.

### 4. Test All Three Negative Cases

**Always test:**
1. **Zero negatives:** `[1,2,3,4]` → full product
2. **Even negatives:** `[-2,-3]` → full product
3. **Odd negatives:** `[-2,-3,-4]` → exclude one

**These three cases cover all segment logic.**

### 5. Compare Multiple Implementations

Keep all three approaches in the code (like we did):
```java
maxProduct()                    // Main solution (DP)
maxProductKadaneVariant()      // Alternative (two-pass)
maxProductComplexApproach()    // Educational (segment logic)
```

**Benefits:**
- Can compare performance
- Reference for similar problems
- Educational value for reviewing later

---

## Pattern Recognition

### Pattern: "Track Extremes for Sign Flips"

**When to use:**
- Operations that can flip signs (multiplication by negative, division)
- Need to maximize/minimize result after flip

**Implementation:**
```java
currMax = max(element, currMax*element, currMin*element)
currMin = min(element, currMax*element, currMin*element)
```

**Related problems:**
- Maximum Product Subarray ✓
- Best Time to Buy and Sell Stock III (profit can go negative)
- Maximum Alternating Subarray Sum

### Pattern: "Bidirectional Scanning"

**When to use:**
- Direction matters (first vs last)
- Answer is found in one direction or the other

**Implementation:**
```java
// Left-to-right scan
for i = 0 to n-1: process(i)

// Right-to-left scan
for i = n-1 to 0: process(i)
```

**Related problems:**
- Maximum Product Subarray ✓
- Trapping Rain Water (can use two-pass)
- Product of Array Except Self (left and right products)

### Pattern: "Boundary Resets"

**When to use:**
- Elements that break continuity (zeros, delimiters, separators)
- Each segment can be processed independently

**Implementation:**
```java
for each element:
    if (boundary):
        process current segment
        reset state
    else:
        accumulate into current segment
```

**Related problems:**
- Maximum Product Subarray (zeros) ✓
- Number of Substrings Containing All Three Characters
- Longest Substring Without Repeating Characters

---

## Mistakes to Avoid

### 1. ❌ Forgetting to Track Minimum

**Wrong approach:**
```java
currMax = max(nums[i], currMax * nums[i])  // Missing currMin!
```

**Why it fails:** `[2, -5, -3]`
- After `-5`: currMax = 2 (ignores -10)
- After `-3`: currMax = max(-3, 2×-3) = -3
- **Wrong answer: -3** (correct is 30 from -5 × -3)

**Lesson:** Always track both max and min when sign flips are possible.

### 2. ❌ Ignoring Zero Edge Cases

**Wrong approach:**
```java
// Continuing product through zeros
currMax = currMax * nums[i]  // If nums[i]=0, currMax becomes 0 forever
```

**Why it fails:** `[-2, 0, 3, 4]`
- After `0`: currMax = 0
- After `3`: currMax = 0 (should be 3)
- **Wrong answer: 0** (correct is 12 from [3,4])

**Lesson:** Explicitly reset state when encountering zeros.

### 3. ❌ Not Handling All-Negative Arrays

**Wrong approach:**
```java
int answer = 0;  // Wrong initialization
```

**Why it fails:** `[-5, -3, -2]`
- Best product is -2 (single element)
- **Wrong answer: 0** (no product is > 0, but we still need the least negative)

**Lesson:** Initialize with `Integer.MIN_VALUE` and ensure single elements are considered.

### 4. ❌ Assuming Kadane's Directly Applies

**Wrong approach:**
```java
// Using Kadane's sum logic
currMax = max(nums[i], currMax + nums[i])  // Sum, not product!
```

**Why it fails:** Products have different properties than sums:
- Negative × negative = positive (no equivalent in sums)
- Zero is a boundary (in sums, zero is just another element)

**Lesson:** Understand the problem's unique properties before applying known patterns.

### 5. ❌ Not Using Temp Variable for Simultaneous Updates

**Wrong approach:**
```java
currMax = max(nums[i], currMax*nums[i], currMin*nums[i]);
currMin = min(nums[i], currMax*nums[i], currMin*nums[i]);  // Uses NEW currMax!
```

**Why it fails:** `currMin` calculation uses the already-updated `currMax`, not the old value.

**Lesson:** Save old values before updating when there are dependencies.

---

## Summary

### Core Takeaways

1. **Products ≠ Sums:** Sign flips require tracking both max and min
2. **Multiple valid approaches:** DP max/min (optimal), two-pass (intuitive), segment logic (clear)
3. **Zeros are boundaries:** No optimal subarray spans a zero
4. **Even/odd negative parity:** Determines whether to include full segment or exclude one negative
5. **Direction matters:** Left-to-right and right-to-left passes together cover all candidates

### Algorithm Selection Guide

**Choose DP Max/Min when:**
- Standard interview setting
- Need O(1) space
- Single pass is important

**Choose Two-Pass Kadane when:**
- Want most intuitive solution
- Explaining to beginners
- Time interview and want easy explanation

**Choose Segment Logic when:**
- Want explicit control
- Educational context
- Space is not a constraint

### Complexity Summary

| Approach | Time | Space | Best For |
|----------|------|-------|----------|
| DP Max/Min | O(n) | O(1) | Interviews |
| Two-Pass | O(n) | O(1) | Clarity |
| Segment Logic | O(n) | O(n) | Education |

---

**Problem solved with three different approaches, all O(n) time, demonstrating deep understanding of dynamic programming, bidirectional scanning, and segment-based logic.**
