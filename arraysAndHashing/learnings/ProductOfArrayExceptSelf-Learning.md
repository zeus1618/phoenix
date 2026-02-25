# Product of Array Except Self - Deep Learning Guide

**Problem:** [LeetCode #238 - Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/)  
**Difficulty:** Medium  
**Category:** Arrays & Hashing

---

## Table of Contents

1. [Problem Evolution Journey](#problem-evolution-journey)
2. [Solution Comparison](#solution-comparison)
3. [Prefix/Suffix Deep Dive](#prefixsuffix-deep-dive)
4. [Key Insights](#key-insights)
5. [Major Lessons](#major-lessons)
6. [Best Practices](#best-practices)
7. [Pattern Recognition](#pattern-recognition)
8. [Mistakes to Avoid](#mistakes-to-avoid)

---

## Problem Evolution Journey

### Initial Understanding

**Given:** An integer array `nums`  
**Return:** Array `answer` where `answer[i]` = product of all elements except `nums[i]`  
**Constraints:**
- Must run in **O(n) time**
- **Cannot use division operation**
- 2 ≤ nums.length ≤ 10^5
- -30 ≤ nums[i] ≤ 30

**Follow-up:** Solve with O(1) extra space (output array doesn't count)

**What makes this challenging:**
- **No division:** Can't just compute total product and divide
- **O(n) requirement:** Rules out O(n²) brute force
- **Zeros complicate things:** Must handle carefully

---

### Attempt 1: Total Product + Division (Incorrect but Instructive)

**Initial intuition:** "If I can't include element i, I'll compute the product of everything else and divide by i."

**Algorithm:**
```java
public int[] productExceptSelf(int[] nums) {
    int[] prodArray = new int[nums.length];
    int zeroCount = 0;
    int wholeProduct = 1;
    
    // Compute product of all non-zero elements
    for (int n : nums) {
        if (n != 0) {
            wholeProduct *= n;
        } else {
            ++zeroCount;
        }
    }
    
    // Fill result based on zero count
    Arrays.fill(prodArray, 0);
    
    if (zeroCount == 1) {
        // Only the zero position gets non-zero product
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                prodArray[i] = wholeProduct;
            }
        }
    } else if (zeroCount == 0) {
        // Use division (VIOLATES CONSTRAINT!)
        for (int i = 0; i < nums.length; i++) {
            prodArray[i] = wholeProduct / nums[i];
        }
    }
    // else: 2+ zeros → all results stay 0
    
    return prodArray;
}
```

**Zero handling logic:**

| Zero Count | Strategy | Example |
|------------|----------|---------|
| 0 | Divide total by each element | `[1,2,3]` → product=6 → `[6/1, 6/2, 6/3]` = `[6,3,2]` |
| 1 | Only zero position = product of others, rest = 0 | `[2,0,3]` → `[0, 6, 0]` |
| 2+ | All positions = 0 | `[0,0,2]` → `[0,0,0]` |

**Why this is clever:**
- Correct logic for zero handling ✓
- O(n) time, O(1) space ✓
- Handles negatives, boundaries ✓

**Why this fails:**
- ❌ **Uses division** → violates explicit problem constraint
- ❌ Doesn't demonstrate the prefix/suffix pattern
- ❌ Would be rejected by LeetCode

**Key lesson:** Sometimes "clever" solutions miss the point. The problem **bans division** specifically to force you to discover the prefix/suffix pattern.

---

### Attempt 2: Prefix + Suffix Products (Correct)

**Key insight:** Instead of "product of everything ÷ this element," think "product of everything to the left × product of everything to the right."

**For each position i:**
```
answer[i] = (product of nums[0..i-1]) × (product of nums[i+1..n-1])
          = prefix[i] × suffix[i]
```

**Approach 2a: Two separate arrays (O(n) space)**

```java
public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] prefix = new int[n];
    int[] suffix = new int[n];
    int[] answer = new int[n];
    
    // Build prefix: prefix[i] = product of nums[0..i-1]
    prefix[0] = 1;  // nothing to the left of index 0
    for (int i = 1; i < n; i++) {
        prefix[i] = prefix[i-1] * nums[i-1];
    }
    
    // Build suffix: suffix[i] = product of nums[i+1..n-1]
    suffix[n-1] = 1;  // nothing to the right of index n-1
    for (int i = n-2; i >= 0; i--) {
        suffix[i] = suffix[i+1] * nums[i+1];
    }
    
    // Combine
    for (int i = 0; i < n; i++) {
        answer[i] = prefix[i] * suffix[i];
    }
    
    return answer;
}
```

**Example trace: `[1, 2, 3, 4]`**

**Step 1: Build prefix**
```
prefix[0] = 1           (nothing to left)
prefix[1] = 1 × 1 = 1   (product of [1])
prefix[2] = 1 × 2 = 2   (product of [1,2])
prefix[3] = 2 × 3 = 6   (product of [1,2,3])
Result: prefix = [1, 1, 2, 6]
```

**Step 2: Build suffix**
```
suffix[3] = 1           (nothing to right)
suffix[2] = 1 × 4 = 4   (product of [4])
suffix[1] = 4 × 3 = 12  (product of [3,4])
suffix[0] = 12 × 2 = 24 (product of [2,3,4])
Result: suffix = [24, 12, 4, 1]
```

**Step 3: Combine**
```
answer[0] = 1 × 24 = 24
answer[1] = 1 × 12 = 12
answer[2] = 2 × 4 = 8
answer[3] = 6 × 1 = 6
Result: [24, 12, 8, 6] ✓
```

**Complexity:**
- **Time:** O(n) — three passes (prefix, suffix, combine)
- **Space:** O(n) — two extra arrays

**Verdict:** Correct, but uses O(n) extra space. Can we do better?

---

**Approach 2b: Single array optimization (O(1) space) — Final Solution**

**Key optimization:** We don't need separate prefix and suffix arrays. Store prefix in the output array, then multiply suffix on-the-fly.

```java
public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] answer = new int[n];
    
    // Pass 1: Fill answer with prefix products
    answer[0] = 1;
    for (int i = 1; i < n; i++) {
        answer[i] = answer[i-1] * nums[i-1];
    }
    
    // Pass 2: Multiply suffix products into answer
    int suffix = 1;
    for (int i = n-1; i >= 0; i--) {
        answer[i] *= suffix;      // multiply existing prefix by suffix
        suffix *= nums[i];        // update suffix for next iteration
    }
    
    return answer;
}
```

**Example trace: `[1, 2, 3, 4]`**

**After Pass 1 (prefix):**
```
answer = [1, 1, 2, 6]  (same as before)
```

**Pass 2 (suffix, right-to-left):**
```
i=3, suffix=1:
  answer[3] = 6 × 1 = 6
  suffix = 1 × 4 = 4

i=2, suffix=4:
  answer[2] = 2 × 4 = 8
  suffix = 4 × 3 = 12

i=1, suffix=12:
  answer[1] = 1 × 12 = 12
  suffix = 12 × 2 = 24

i=0, suffix=24:
  answer[0] = 1 × 24 = 24
  suffix = 24 × 1 = 24

Result: [24, 12, 8, 6] ✓
```

**Complexity:**
- **Time:** O(n) — two passes
- **Space:** O(1) — only output array + one suffix variable

**Verdict:** Optimal! Meets all constraints including the follow-up.

---

## Solution Comparison

| Approach | Time | Space (extra) | Division? | Meets Constraints | Notes |
|----------|------|---------------|-----------|-------------------|-------|
| **Brute force** | O(n²) | O(1) | No | ❌ Too slow | Try all positions, multiply others |
| **Total product ÷ element** | O(n) | O(1) | **Yes** | ❌ Violates constraint | Clever zero handling but uses division |
| **Prefix/Suffix (two arrays)** | O(n) | O(n) | No | ✅ Yes | Correct but extra space |
| **Prefix/Suffix (one array)** | O(n) | O(1) | No | ✅ **Optimal** | Reuses output array |

---

## Prefix/Suffix Deep Dive

### Why Prefix/Suffix Works

**Problem:** For each position, compute product of "everything except me."

**Decomposition:**
```
"Everything except i" = "Everything before i" × "Everything after i"
                      = prefix[i] × suffix[i]
```

**Visual: `[a, b, c, d, e]`**

```
For index 2 (element c):
  prefix[2] = a × b
  suffix[2] = d × e
  answer[2] = (a × b) × (d × e) = product of all except c
```

**Key insight:** By separating left and right products, we avoid the need for division.

### Prefix Array Construction

**Definition:** `prefix[i]` = product of all elements **before** index i

**Recurrence:**
```
prefix[0] = 1              (base case: nothing before first element)
prefix[i] = prefix[i-1] × nums[i-1]   (for i > 0)
```

**Why prefix[0] = 1?**  
Because multiplying by 1 is the identity operation. It means "no elements contribute to the left product at index 0."

**Example: `[2, 3, 4, 5]`**
```
prefix[0] = 1
prefix[1] = 1 × 2 = 2
prefix[2] = 2 × 3 = 6
prefix[3] = 6 × 4 = 24
```

### Suffix Array Construction

**Definition:** `suffix[i]` = product of all elements **after** index i

**Recurrence:**
```
suffix[n-1] = 1                  (base case: nothing after last element)
suffix[i] = suffix[i+1] × nums[i+1]   (for i < n-1)
```

**Why suffix[n-1] = 1?**  
Same reason: identity for multiplication.

**Example: `[2, 3, 4, 5]`**
```
suffix[3] = 1
suffix[2] = 1 × 5 = 5
suffix[1] = 5 × 4 = 20
suffix[0] = 20 × 3 = 60
```

### Space Optimization: Reusing Output Array

**Why it works:**
1. **Pass 1:** Store prefix in output → `answer[i]` = prefix[i]
2. **Pass 2:** Multiply suffix on-the-fly → `answer[i] *= suffix`

**Pass 2 detail:** When processing position i right-to-left:
- `answer[i]` already contains `prefix[i]`
- We multiply by current `suffix` value → `answer[i] = prefix[i] × suffix`
- Then update `suffix` for next iteration → `suffix *= nums[i]`

**Order matters in Pass 2:**
```java
answer[i] *= suffix;    // MUST come first (use old suffix)
suffix *= nums[i];      // Then update suffix
```

If reversed, we'd use the wrong suffix value.

---

## Key Insights

### 1. Constraint-Driven Design

**Problem bans division for a reason:**
- Not just a random restriction
- Guides you toward the prefix/suffix pattern
- Tests your ability to decompose problems

**Lesson:** When a problem has unusual constraints, they're hints about the intended approach.

### 2. Prefix/Suffix Decomposition

**Core idea:** To compute "all except me," split into "before me" and "after me."

**Why it's powerful:**
- Avoids division entirely
- Each part computed independently
- O(n) time with simple logic

**Applications:**
- Product of array except self ✓
- Max/min to left and right
- Sum ranges
- Water trapping

### 3. Space Optimization via Output Array Reuse

**Technique:** Use output array as workspace for intermediate results.

**Steps:**
1. Store first component (prefix) in output
2. Compute second component (suffix) on-the-fly
3. Combine by multiplying into output

**Why it works:**
- Output array must exist anyway (doesn't count toward space)
- Can overwrite values as we go
- Reduces O(n) space to O(1)

### 4. Identity Element Initialization

**Both prefix[0] and suffix[n-1] initialize to 1:**
- 1 is the multiplicative identity: `x × 1 = x`
- Means "no elements contribute yet"
- Handles edge positions cleanly

**Lesson:** Choose identity elements carefully:
- Multiplication: 1
- Addition: 0
- Max: Integer.MIN_VALUE or -∞
- Min: Integer.MAX_VALUE or +∞

### 5. Two-Pass Patterns

**Many problems benefit from two passes:**
- First pass: accumulate info in one direction
- Second pass: accumulate info in opposite direction
- Combine results

**Related problems:**
- Product of Array Except Self ✓
- Trapping Rain Water
- Candy Distribution
- Best Time to Buy and Sell Stock III

---

## Major Lessons

### 1. Read Constraints Carefully

**Mistake:** Implementing division approach without noticing "without using division."

**Lesson:** Constraints aren't arbitrary. They guide the solution approach.

**Practice:**
- Highlight unusual constraints
- Ask "Why is this constraint here?"
- Let constraints shape your approach

### 2. Clever ≠ Correct

**Your division approach was:**
- ✅ Logically sound
- ✅ Handled zeros elegantly
- ✅ O(n) time, O(1) space
- ❌ **Violated explicit constraint**

**Lesson:** In interviews, meeting **all** requirements matters more than being clever. A "simple" solution that meets constraints beats a "clever" solution that doesn't.

### 3. Prefix/Suffix is a Pattern, Not a Trick

**Recognition signals:**
- "All elements except current"
- "Max/min/sum to left and right"
- "Cannot use division"
- Two-directional dependencies

**Template:**
```java
// Pass 1: left-to-right (prefix)
result[0] = identity;
for (int i = 1; i < n; i++) {
    result[i] = result[i-1] OPERATION nums[i-1];
}

// Pass 2: right-to-left (suffix)
accumulator = identity;
for (int i = n-1; i >= 0; i--) {
    result[i] COMBINE_WITH accumulator;
    accumulator OPERATION nums[i];
}
```

### 4. Output Array is Free Real Estate

**Follow-up asks for O(1) space:**
- Output array **doesn't count** toward space complexity
- Can use it as workspace for intermediate results
- Eliminates need for auxiliary arrays

**Lesson:** When space-optimizing, check if output storage can serve double duty.

### 5. Edge Cases Drive Initialization

**Why prefix[0] = 1 and suffix[n-1] = 1:**
- Handles first/last elements cleanly
- No special-case logic needed
- Identity element makes math work out

**Lesson:** Good initialization eliminates edge-case branches.

---

## Best Practices

### 1. Start with Clarity, Optimize Later

**Process:**
1. **First:** Two-array approach (prefix + suffix)
2. **Second:** Realize you can reuse output array
3. **Third:** Implement optimized version

Don't jump to the optimized solution immediately. Build understanding incrementally.

### 2. Trace with Small Examples

**Always trace with n=3 or n=4:**
- Small enough to trace by hand
- Large enough to show the pattern
- Catches off-by-one errors

**Example: `[2, 3, 4]`**
```
Prefix: [1, 2, 6]
Suffix: [12, 4, 1]
Result: [12, 8, 6] ✓
```

### 3. Initialize with Identity Elements

```java
answer[0] = 1;          // multiplicative identity
int suffix = 1;         // start with identity
```

Never use 0 or arbitrary values when identity elements exist.

### 4. Mind the Order in Updates

**In Pass 2:**
```java
// CORRECT order:
answer[i] *= suffix;    // use current suffix
suffix *= nums[i];      // then update

// WRONG order:
suffix *= nums[i];      // updates suffix first!
answer[i] *= suffix;    // uses wrong value
```

When updating multiple dependent variables, sketch out which values you need before vs after updates.

### 5. Test Zero Handling

**Always test:**
- No zeros: `[1,2,3,4]`
- One zero: `[2,0,3]`
- Multiple zeros: `[0,0,2]`
- All zeros: `[0,0,0]`

Prefix/suffix handles zeros naturally (no special logic needed), but verify with tests.

---

## Pattern Recognition

### Pattern: "Prefix/Suffix Product/Sum"

**When to use:**
- Need to compute something "for all except current element"
- Two-directional dependencies
- Cannot use division
- Need cumulative information from both directions

**Implementation:**
1. Build prefix array/value (left-to-right)
2. Build suffix array/value (right-to-left)
3. Combine: `result[i] = f(prefix[i], suffix[i])`

**Space optimization:** Reuse output array for prefix, compute suffix on-the-fly.

**Related problems:**

| Problem | Prefix | Suffix | Combine |
|---------|--------|--------|---------|
| **Product Except Self** | Left products | Right products | Multiply |
| **Trapping Rain Water** | Max height left | Max height right | `min(left, right) - height` |
| **Candy Distribution** | Increasing left | Decreasing right | `max(left, right)` |
| **Max Subarray Sum (prefix)** | Sum left | - | Track max |

### Pattern: "Two-Pass Accumulation"

**Structure:**
```
Pass 1 (left-to-right): Build something
Pass 2 (right-to-left): Refine/combine
```

**Signals:**
- "All elements except"
- "Left and right"
- "Two directions"

### Pattern: "Output Array as Workspace"

**Technique:** Use output array to store intermediate results.

**When applicable:**
- Space complexity asks for O(1) extra
- Output array size equals input size
- Can overwrite values after using them

**Example:** Store prefix in output, then multiply suffix in-place.

---

## Mistakes to Avoid

### 1. ❌ Using Division

**Wrong:**
```java
int totalProduct = computeProduct(nums);
for (int i = 0; i < n; i++) {
    answer[i] = totalProduct / nums[i];  // FORBIDDEN
}
```

**Why it's tempting:** Simple, handles math directly.  
**Why it's wrong:** Explicit constraint violation.

### 2. ❌ Forgetting Identity Initialization

**Wrong:**
```java
answer[0] = 0;  // Wrong! Should be 1
```

**Why it fails:** Multiplying by 0 makes everything 0.

**Correct:**
```java
answer[0] = 1;  // Identity for multiplication
```

### 3. ❌ Wrong Order in Pass 2

**Wrong:**
```java
suffix *= nums[i];      // Updates suffix first
answer[i] *= suffix;    // Uses NEW suffix (wrong)
```

**Why it fails:** You need the suffix from **after** position i, not including i.

**Correct:**
```java
answer[i] *= suffix;    // Use current suffix
suffix *= nums[i];      // Then update for next iteration
```

### 4. ❌ Off-by-One in Prefix/Suffix

**Wrong prefix:**
```java
prefix[i] = prefix[i-1] * nums[i];  // Includes nums[i]! Wrong
```

**Correct:**
```java
prefix[i] = prefix[i-1] * nums[i-1];  // Excludes nums[i]
```

**Rule:** `prefix[i]` should be product **before** index i, not including i.

### 5. ❌ Not Testing Edge Cases

**Common oversight:** Only test normal cases like `[1,2,3,4]`.

**Must test:**
- Zeros: `[0,2,3]`, `[2,0,3]`, `[2,3,0]`
- Multiple zeros: `[0,0,2]`
- Negatives: `[-2, 3, -4]`
- Minimum length: `[a, b]`

### 6. ❌ Creating Unnecessary Arrays

**Wasteful:**
```java
int[] prefix = new int[n];
int[] suffix = new int[n];
int[] answer = new int[n];
// ... populate all three ...
```

**Optimized:**
```java
int[] answer = new int[n];  // Only one array
int suffix = 1;             // Single variable
```

**Lesson:** Always check if you can reuse the output array.

---

## Summary

### Core Takeaways

1. **Prefix/suffix decomposition:** Split "all except me" into "before me" × "after me"
2. **No division needed:** Prefix × suffix achieves the same result without division
3. **Space optimization:** Reuse output array for prefix, compute suffix on-the-fly
4. **Identity initialization:** Use 1 for multiplication (identity element)
5. **Two-pass pattern:** Left-to-right for prefix, right-to-left for suffix

### Algorithm Selection

**Choose prefix/suffix when:**
- Problem mentions "all except current"
- Division is forbidden or problematic
- Need cumulative info from both directions

**Implementation checklist:**
- ✅ Initialize with identity element (1 for product)
- ✅ Build prefix left-to-right
- ✅ Build/apply suffix right-to-left
- ✅ Mind the order: use suffix before updating it
- ✅ Test with zeros, negatives, edge cases

### Complexity Summary

| Metric | Two-Array Approach | Optimized (One-Array) |
|--------|--------------------|-----------------------|
| **Time** | O(n) | O(n) |
| **Space** | O(n) extra | O(1) extra |
| **Passes** | 3 (prefix, suffix, combine) | 2 (prefix, suffix combined) |
| **Interview** | Good first solution | Optimal follow-up |

---

**Problem solved with prefix/suffix pattern, demonstrating space optimization and two-pass accumulation technique.**
