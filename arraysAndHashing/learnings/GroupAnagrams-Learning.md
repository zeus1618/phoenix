# Group Anagrams - Deep Learning Guide

**Problem:** LeetCode #49 - Medium  
**Category:** Arrays & Hashing  
**Date Solved:** February 13, 2026

---

## 📖 Problem Evolution Journey

### **Challenge**
Given an array of strings, group all anagrams together efficiently.

**Key Constraint:** n ≤ 10⁴ strings, each length k ≤ 100

---

## 🔄 Solution Evolution (3 Attempts)

### **Attempt 1: Nested Loop - O(n² × k) ❌**

```java
for(int i=0; i<strs.length; i++) {
    for(int j=i+1; j<strs.length; j++) {
        if(isAnagram(curr, strs[j])) {
            // Group them, mark as null
        }
    }
}
```

**Time Complexity:** O(n² × k)
- Outer loop: n iterations
- Inner loop: n iterations per outer
- isAnagram check: k operations
- **Total: n × n × k = 10⁴ × 10⁴ × 10² = 10¹⁰ operations!**

**Result:** ❌ Time Limit Exceeded

**What was learned:**
- ✅ isAnagram() reuse from previous problem
- ✅ Edge case handling (nulls, boundaries)
- ❌ Nested loops don't scale for n > 1,000
- ❌ Destructive (mutates input with nulls)

**Key Lesson:** When you find yourself checking "all pairs," ask: "Can I use a HashMap instead?"

---

### **Attempt 2: Frequency String Key - O(n × k) ⚠️**

```java
// Build frequency
int[] freq = new int[26];
for(char c : s.toCharArray()) freq[c-'a']++;

// Convert to string key
StringBuilder sb = new StringBuilder();
for(int f : freq) {
    sb.append(f + ",");  // ❌ Creates temp String objects!
}
String key = sb.toString();  // "1,0,0,0,1,0,..."
```

**Time Complexity:** O(n × k) - Theoretically optimal!

**Runtime:** 33ms

**Problems identified:**
1. **String concatenation:** `f + ","` creates 26 temporary String objects per word
2. **StringBuffer overhead:** Thread-safe synchronization (should use StringBuilder)
3. **Oversized keys:** 52+ characters vs 3-10 for sorted
4. **Constant factor 26:** Always processes all 26 letters, even for 1-char strings
5. **HashMap slower:** Longer keys = slower hash computation and equality checks

**Hidden cost calculation:**
- Per word: Build freq (k ops) + String concat (26 × 30 ops) + HashMap (50 ops)
- For "eat": 3 + 780 + 50 = **833 operations**

**Key Lesson:** O(n × k) doesn't guarantee fast execution - constant factors dominate!

---

### **Attempt 3: Sorted Key - O(n × k log k) ✅**

```java
for (String str : strs) {
    char[] chars = str.toCharArray();
    Arrays.sort(chars);
    String key = new String(chars);
    
    map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
}
```

**Time Complexity:** O(n × k log k) - Theoretically worse than frequency!

**Runtime:** 6ms ⚡ **5.5x faster!**

**Why it's faster:**
1. **JVM intrinsic:** Arrays.sort() is highly optimized (dual-pivot quicksort)
2. **No string building:** Direct conversion of sorted chars
3. **Shorter keys:** "aet" (3 bytes) vs "1,0,0,..." (52 bytes)
4. **Single allocation:** Just the key string
5. **Scales with input:** Sorts k chars, not always 26

**Cost calculation:**
- Per word: toCharArray (10) + sort (k log k × 5) + new String (10) + HashMap (20)
- For "eat": 10 + 24 + 10 + 20 = **64 operations**

**Speedup:** 833 / 64 = **13x faster in real operations!**

---

## 🧪 Alternative Approaches Explored

### **Approach A: Prime Number Multiplication**

```java
int primes[] = {2, 3, 5, 7, 11, ..., 103};
long product = 1;
for(char c : str.toCharArray()) {
    product *= primes[c - 'a'];
}
// Use product as key
```

**Mathematical Basis:** Fundamental Theorem of Arithmetic
- Every integer has unique prime factorization
- "eat" = 11 × 2 × 19 = 418
- "tea" = 19 × 11 × 2 = 418 (same!)

**Performance:**
- **Runtime:** ~4ms (fastest!)
- **Time Complexity:** O(n × k)
- **Operations:** Just k multiplications per word

**Why it's the fastest:**
- Primitive multiplication (~1 CPU cycle)
- Long key (8 bytes, O(1) hashing)
- No string allocations
- No sorting overhead

**Fatal Flaw: Integer Overflow**
```
String with 14 'z's: 103^14 > Long.MAX_VALUE
Overflow causes wraparound
Different strings might produce same overflow value
❌ WRONG RESULTS!
```

**Problem constraint:** k ≤ 100
**Prime approach works for:** k ≤ ~13

**Verdict:** ❌ Clever but doesn't meet constraints. Not production-safe.

---

### **Approach B: Arrays.hashCode() as Key**

```java
int[] freq = new int[26];
// Build frequency...
int hashKey = Arrays.hashCode(freq);
map.put(hashKey, list);  // Use hash as key!
```

**Clever optimization:** Skip string conversion, use hash directly!

**Performance:**
- **Runtime:** ~5ms
- **Integer key:** 4 bytes, fastest HashMap operations

**Fatal Flaw: Hash Collisions**
```java
// Arrays.hashCode() is NOT a unique identifier!
int hash1 = Arrays.hashCode([1, 2, 0, ...]);
int hash2 = Arrays.hashCode([2, 1, 0, ...]);
// hash1 might equal hash2! (Collision)

// HashMap needs equals() to handle collisions
// But we're using int as key - no equals() check!
// Result: Non-anagrams can be grouped together ❌
```

**Why it "works" on LeetCode:**
- Collision probability ~0.01% for typical test cases
- Gets lucky with small inputs
- Not all possible frequency combinations appear

**Verdict:** ❌ Buggy. Violates HashMap contract. Fails in production with enough data.

---

## 📊 Complete Performance Analysis

### **Runtime Comparison (Real LeetCode Data)**

| Solution | Theoretical | Real Ops/Word | Actual Runtime | Winner |
|----------|-------------|---------------|----------------|--------|
| Nested Loop | O(n²k) | n×k | ❌ TLE | |
| Frequency String | O(nk) | 833 | 33ms | |
| **Sorted Key** | **O(nk log k)** | **64** | **6ms** | ✅ |
| Prime Multiply | O(nk) | 24 | 4ms ⚠️ | |
| Arrays.hashCode | O(nk) | 34 | 5ms ❌ | |

---

### **Key Size Impact on HashMap Performance**

| Approach | Key Type | Key Size | Hash Computation | Equality Check |
|----------|----------|----------|------------------|----------------|
| Frequency String | String | 52+ chars | O(52) | O(52) |
| Sorted Key | String | 3-100 chars | O(k) | O(k) |
| Prime | Long | 8 bytes | O(1) | O(1) |
| hashCode | Integer | 4 bytes | O(1) | O(1) |

**Insight:** Primitive keys (Long, Integer) are 2-3x faster than String keys, but correctness trumps speed!

---

## 🎯 Why Sorted Key Wins

### **1. JVM Optimization**
```java
Arrays.sort(chars);  // JVM intrinsic function
// Compiles to highly optimized machine code
// Dual-pivot quicksort for primitives
// CPU-level optimizations
```

vs

```java
for(int f : freq) {
    sb.append(f + ",");  // Method calls, allocations
}
```

**JVM intrinsics beat custom code!**

---

### **2. Cache Locality**
```java
// Sorted approach: Sequential memory access
Arrays.sort(chars);  // Contiguous char array
// CPU prefetcher predicts next access
// L1 cache hits

// Frequency approach: Scattered writes
freq[s.charAt(i) - 'a']++;  // Random array indices
sb.append(...);              // String buffer operations
// Cache misses
```

---

### **3. No Hidden Allocations**
```java
// Sorted: One allocation
String key = new String(chars);

// Frequency: 27 allocations
sb.append(f + ",");  // 26 temp String objects + 1 final toString()
```

**Garbage collection impact:**
- Frequency: 26 objects per word = 260 objects for 10 words
- Sorted: 1 object per word = 10 objects for 10 words
- **26x more GC pressure!**

---

### **4. Key Length Efficiency**

**Example for "eat":**
- Sorted key: "aet" (3 characters)
- Frequency key: "1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0," (52 chars)

**HashMap operations:**
- Hash computation: O(key length)
- Equality check: O(key length)
- Memory: key length bytes

**17x longer key = 17x slower HashMap operations!**

---

## 🎓 Major Lessons Learned

### **1. O(n²) → O(n) Transformation with HashMap**

**Pattern:**
```
Problem: Group items by property
Naive: Check all pairs - O(n²)
Optimal: HashMap with property as key - O(n)
```

**Applied here:**
- Don't check if each string is anagram of every other string
- Create a unique key for each anagram group
- Use HashMap to group by key

**Impact:** 10 billion ops → 1 million ops = **10,000x faster!**

---

### **2. Big-O Hides Critical Details**

**Theory says:** O(n × k) beats O(n × k log k)

**Practice shows:** O(n × k log k) sorted is 5.5x faster than O(n × k) frequency!

**Why?**
- O(n × k) has hidden constant of **~780 operations** per word
- O(n × k log k) has constant of **~64 operations** per word
- **12x constant factor difference overwhelms the log k term!**

**For k=3:** log k = 1.58, so k log k ≈ 4.7
- But constant factor is 780 vs 64!
- Conclusion: **Constants matter more than logarithms for small k**

---

### **3. String Operations Are Expensive**

**Cost hierarchy (operations per call):**
```
Primitive multiplication:        ~1 unit
Array access:                    ~1 unit
JVM intrinsic (Arrays.sort):    ~5 units per comparison
StringBuilder.append(int):      ~10 units
String concatenation (f + ","): ~30 units (creates temp object!)
HashMap with String key:        ~20 units
HashMap with primitive key:     ~5 units
```

**Lesson:** Avoid string operations in hot paths. Use primitives when possible.

---

### **4. The HashMap Contract**

**For correct HashMap usage, key objects need:**
```java
@Override
public int hashCode() { ... }      // Bucket selection

@Override  
public boolean equals(Object o) { ... }  // Collision handling
```

**Without equals():**
- HashMap can't distinguish collisions
- Different objects with same hash treated as same key
- **Wrong results!**

**Example:**
```java
// ❌ WRONG - Using hash directly as key
int hash = Arrays.hashCode(freq);
map.put(hash, list);  // Collisions not handled!

// ✅ CORRECT - String has proper equals()
String key = sortedString;
map.put(key, list);   // Collisions handled correctly
```

---

### **5. Clever Math Tricks Have Limits**

**Prime multiplication approach:**
- Elegant: Uses unique prime factorization
- Fast: Just k multiplications
- **But:** Overflows for k > 13

**Reality check:**
```
Problem constraint: k ≤ 100
Solution works for: k ≤ 13
❌ Doesn't meet requirements!
```

**Lesson:** Mathematical elegance must respect practical constraints!

---

### **6. JVM Intrinsics vs Custom Code**

**JVM Intrinsics** (super optimized):
- `Arrays.sort()` - Dual-pivot quicksort, CPU-optimized
- Math operations on primitives
- Direct memory operations

**Custom implementations:**
- StringBuffer/Builder - Method overhead
- Manual loops - Less optimized
- Object allocations - GC overhead

**Rule of thumb:** If JVM provides it, use it! (Arrays.sort, System.arraycopy, etc.)

---

### **7. Theory vs Practice - When O(n) Loses to O(n log n)**

**Case Study: This Problem**

| Approach | Big-O | Hidden Cost | Real Operations | Runtime |
|----------|-------|-------------|-----------------|---------|
| Frequency | O(nk) | String building | 833/word | 33ms |
| Sorted | O(nk log k) | JVM intrinsic | 64/word | **6ms** |

**For k=3:** log k = 1.58
- Frequency: k operations + 780 overhead = 783 total
- Sorted: k log k = 4.7 operations + 60 overhead = 64 total

**Logarithm is negligible compared to constant factors!**

**General lesson:** For small k (< 1000), O(n log n) can beat O(n) if:
- O(n) has high constant factor
- O(n log n) uses JVM intrinsics
- Constant difference > log factor

---

## 🔑 Critical Insights

### **Insight 1: HashMap Grouping Pattern**

**Generic pattern for grouping problems:**
```java
Map<KeyType, List<ItemType>> groups = new HashMap<>();

for (Item item : items) {
    KeyType key = calculateGroupKey(item);
    groups.computeIfAbsent(key, k -> new ArrayList<>()).add(item);
}

return new ArrayList<>(groups.values());
```

**Key requirements:**
- Same key for all items in a group
- Different key for items in different groups
- Fast to compute
- Unique (or properly handles collisions)

---

### **Insight 2: Anagram Key Strategies**

**Three valid approaches:**

| Strategy | Key Generation | Pros | Cons |
|----------|---------------|------|------|
| **Sort** | `Arrays.sort(chars)` | Simple, fast, correct | O(k log k) |
| **Frequency** | Count → String | Theoretically O(k) | Slow in practice |
| **Custom Object** | Frequency + equals() | Correct, flexible | Complex |

**Winner: Sort** - Simplicity + JVM optimization = practical winner

---

### **Insight 3: Why Sorted Beats Frequency in Practice**

**Five factors:**

1. **String Building Overhead (26 operations)**
   ```java
   for(int f : freq) {
       sb.append(f + ",");  // 26 × 30 = 780 ops!
   }
   ```

2. **Key Size**
   - Frequency: 52+ characters
   - Sorted: 3-10 characters (typical)
   - Impact: 5-17x longer hash computation

3. **Allocations**
   - Frequency: 27 objects (26 temp + 1 final)
   - Sorted: 1 object
   - Impact: GC pressure

4. **JVM Intrinsic vs Loop**
   - Arrays.sort(): Compiler-optimized intrinsic
   - Manual frequency loop: Standard bytecode
   - Impact: CPU-level optimization

5. **Constant Factor**
   - Frequency: Always processes 26 letters
   - Sorted: Only processes k actual characters
   - For short strings: Huge difference!

---

### **Insight 4: Buggy "Fast" Solutions**

**Two approaches that are fast but incorrect:**

#### **A. Arrays.hashCode() as Key**
```java
int hash = Arrays.hashCode(freqArray);
map.put(hash, list);  // ❌ Wrong!
```

**Problem:** Hash collisions not handled
- Different frequency arrays can have same hash
- HashMap contract violated (no equals())
- **Works 99.9% by luck, fails 0.1%**

**LeetCode accepts it:** Test cases don't hit collisions
**Production:** Would fail with enough data

---

#### **B. Prime Multiplication**
```java
long product = primes['a'] × primes['b'] × ...
```

**Problem:** Integer overflow
- Works for k ≤ 13
- **Fails for k ≤ 100** (problem constraint)
- Overflow causes wrong results

**LeetCode accepts it:** Test cases use short strings
**Production:** Would fail on "zzzzzzzzzzzzzz"

---

### **Insight 5: Performance Reality Check**

**What determines real performance:**

1. **Algorithm complexity:** O(n), O(n log n), O(n²)
2. **Constant factors:** Hidden multiplicative costs
3. **JVM optimizations:** Intrinsics, JIT compilation
4. **Memory patterns:** Cache locality, allocations
5. **Data structures:** Primitive vs Object keys
6. **Operation costs:** String ops vs array ops vs math

**All matter!** Big-O is just the starting point.

---

## 🏆 Best Practices Summary

### **1. Use Sorted Key for Anagram Grouping**
```java
char[] chars = str.toCharArray();
Arrays.sort(chars);
String key = new String(chars);
```

**Why:**
- ✅ Simple (4 lines)
- ✅ Fast (6ms)
- ✅ Correct (100% reliable)
- ✅ Meets all constraints
- ✅ Interview-safe
- ✅ Production-ready

---

### **2. Avoid String Building in Loops**
```java
// ❌ BAD
sb.append(f + ",");  // Creates temp String

// ✅ GOOD
sb.append(f).append(',');  // Direct append
```

---

### **3. Use StringBuilder, Not StringBuffer**
```java
// ❌ Slower (thread-safe overhead)
StringBuffer sb = new StringBuffer();

// ✅ Faster (single-threaded)
StringBuilder sb = new StringBuilder();
```

---

### **4. HashMap Keys Must Be Unique**
```java
// ❌ WRONG
int hash = object.hashCode();
map.put(hash, value);  // Hash codes aren't unique!

// ✅ CORRECT
map.put(object, value);  // Object has equals() + hashCode()
```

---

### **5. Validate Against Constraints**
```java
// Problem: k ≤ 100
// Solution: Works for k ≤ 13
// ❌ DOESN'T MEET REQUIREMENTS!
```

Always verify your solution handles max constraints!

---

## 📈 Complexity Analysis Deep Dive

### **Time Complexity Breakdown**

| Solution | Build Key | HashMap Ops | Total |
|----------|-----------|-------------|-------|
| Sorted | O(k log k) | O(k) | **O(nk log k)** |
| Frequency | O(k + 26) | O(52) | **O(nk)** |
| Prime | O(k) | O(1) | **O(nk)** |

**But real performance:**
- Sorted: 6ms (JVM intrinsic + short keys)
- Frequency: 33ms (string overhead + long keys)
- Prime: 4ms (but incorrect for constraints)

**Lesson:** Constants and operation costs matter more than Big-O differences!

---

### **Space Complexity**

All O(n × k) for output:
- Must store all strings grouped
- No way to reduce below O(nk)
- Auxiliary space negligible compared to output

---

## 🎯 Interview Strategy

### **How to Present:**

1. **Start with brute force:** "We could check all pairs O(n²)..."
2. **Identify optimization:** "But we can use HashMap to group by key!"
3. **Discuss key options:** "For anagrams, we can use sorted string or frequency"
4. **Choose sorted:** "Sorted is simpler and faster in practice"
5. **Analyze complexity:** "O(n × k log k) time, O(n × k) space"
6. **Mention alternatives:** "Could use frequency but it's more complex"

**Interviewers want:**
- ✅ Optimal approach (sorted key)
- ✅ Clear explanation
- ✅ Awareness of alternatives
- ✅ Complexity analysis

---

## 💡 What Makes This Problem "Medium"

### **Compared to "Easy" problems:**

**Contains Duplicate (Easy):**
- Direct HashMap application
- One element → one check

**Valid Anagram (Easy):**
- Compare two strings
- Single frequency comparison

**Group Anagrams (Medium):**
- **Multiple groupings** - Need to track many groups
- **Key generation** - Must create consistent keys
- **Return complex structure** - List of Lists
- **Performance sensitive** - n=10⁴, can't use O(n²)

**The difficulty is in:**
1. Recognizing HashMap grouping pattern
2. Choosing the right key strategy
3. Avoiding O(n²) trap
4. Handling complex data structure (List<List<String>>)

---

## 🔄 Pattern Recognition

**This problem teaches the "Group By Property" pattern:**

```
Problem Type: Group items that share a property
Solution Template:
  1. Calculate unique key representing the property
  2. Use HashMap<Key, List<Items>>
  3. Group items by key in single pass
  4. Return grouped results

Complexity: O(n) pass + O(key generation)
```

**Other problems using this pattern:**
- Group anagrams (this problem)
- Group people by age/country
- Group transactions by date
- Partition array by condition

---

## 📝 Mistakes to Avoid

### **1. Using hashCode() as Unique Key**
```java
int key = object.hashCode();  // ❌ Not unique!
```

### **2. Nested Loops for Grouping**
```java
// ❌ O(n²) - Don't compare all pairs!
for (i...) for (j...) if(similar(i,j))
```

### **3. Mutating Input**
```java
strs[j] = null;  // ❌ Destroys caller's data
```

### **4. Ignoring Constraints**
```java
// Problem: k ≤ 100
// Your solution: Only works for k ≤ 13
// ❌ FAIL!
```

### **5. Optimizing Prematurely**
- Frequency string was "optimized" to O(k)
- But became 5.5x slower due to constants
- **Simple sorted approach was actually faster!**

---

## 🏅 Final Recommendations

### **For Production:**
```java
// Use sorted key approach - 100% reliable
char[] chars = str.toCharArray();
Arrays.sort(chars);
String key = new String(chars);
```

### **For Interviews:**
1. Explain the HashMap grouping strategy
2. Present sorted key as primary solution
3. Mention frequency approach as alternative
4. Analyze O(n × k log k) complexity
5. Note that it works for all constraints

### **For Learning:**
- Study why Big-O can be misleading
- Understand constant factors and JVM optimizations
- Know when simple solutions beat complex ones
- Always validate against problem constraints

---

## 🎯 Summary Takeaways

1. **HashMap grouping** transforms O(n²) to O(n)
2. **Sorted key** is the practical winner despite higher Big-O
3. **String operations** are expensive - avoid in hot paths
4. **JVM intrinsics** beat manual implementations
5. **Hash codes ≠ unique IDs** - need proper equals()
6. **Clever tricks** often have hidden limitations
7. **Constant factors** can dominate asymptotic complexity
8. **Simple + correct** beats complex + fast-but-buggy

---

**This problem taught more about real-world performance than any "Easy" problem!** 🚀

The gap between theory and practice is where true understanding lives. 🎓
