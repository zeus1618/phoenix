# Encode and Decode Strings - Detailed Learning Guide

## Problem Statement

**LeetCode #271 (Medium) / NeetCode String Encode and Decode**

Design an algorithm to encode a list of strings to a single string, and decode it back to the original list. The encoded string is sent over the network.

**The Critical Constraint:**
- Strings can contain **ANY of 256 ASCII characters** (0-255)
- This means NO character can be reserved as a delimiter
- Must handle empty strings, special characters, newlines, etc.

---

## The Fundamental Challenge

### Why Simple Delimiters Fail

**Naive Approach 1: Comma Delimiter**
```
Input:  ["Hello", "World"]
Output: "Hello,World"
```

**Problem:** What if input contains comma?
```
Input:  ["Hello,World", "Test"]
Output: "Hello,World,Test"
Decode: ["Hello", "World", "Test"] ❌ WRONG!
```

**Naive Approach 2: Special Character Delimiter**
```
Use '#' as delimiter: "Hello#World#Test"
```

**Problem:** Input can contain '#' since any ASCII character is valid!
```
Input:  ["A#B", "C"]
Output: "A#B#C"
Decode: ["A", "B", "C"] ❌ WRONG!
```

**Naive Approach 3: Escape Sequences**
```
Escape delimiter: "A\#B#C" (backslash escapes #)
```

**Problem:** What if input contains backslash? Recursive escaping nightmare!
```
Input:  ["A\\#B"]
Output: "A\\\\\\#B"  // Escaping gets complex
```

### The Solution: Length-Prefixed Encoding

**Key Insight:** Don't look for delimiters - tell decoder exactly how many bytes to read!

**Format:** Store the length of each string before the string itself.

```
Input:  ["Hello", "World"]
Output: "5Hello5World"
        ↑      ↑
        length length
```

**Why It Works:**
- Decoder reads: "5" → read next 5 characters → "Hello"
- Then reads: "5" → read next 5 characters → "World"
- No ambiguity! Even if string contains "5", we know it's data, not length.

---

## Solution Evolution

### Approach 1: Fixed-Width Length Encoding (Implemented)

**Format:** `[listSize][len1][str1][len2][str2]...`

**Example:**
```
Input:  ["Hello", "World"]
Encode: "00200205Hello005World"
         │  │  │      │  │
         │  │  └──────┘  │
         │  │             └── String 2 length (3 digits)
         │  └── String 1 length (3 digits)  
         └── List size (3 digits)
```

**Implementation:**
```java
public String encode(List<String> strs) {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("%03d", strs.size()));  // List size
    for (String str : strs) {
        sb.append(String.format("%03d", str.length()));  // String length
        sb.append(str);                                   // String data
    }
    return sb.toString();
}

public List<String> decode(String s) {
    if (s == null || s.length() < 3) return new ArrayList<>();
    
    int listSize = Integer.parseInt(s.substring(0, 3));
    List<String> result = new ArrayList<>(listSize);
    
    int pos = 3;
    for (int i = 0; i < listSize; i++) {
        int length = Integer.parseInt(s.substring(pos, pos + 3));
        pos += 3;
        result.add(s.substring(pos, pos + length));
        pos += length;
    }
    return result;
}
```

**Complexity:**
- **Time:** O(m) where m = sum of all string lengths (optimal)
- **Space:** O(m) for output (optimal)

**Pros:**
- ✅ Predictable parsing - know exact positions
- ✅ O(1) to extract length (fixed 3 characters)
- ✅ No scanning required
- ✅ Easy to debug (clear structure)

**Cons:**
- ⚠️ Fixed overhead: 3 bytes per string (even for length 5 = "005")
- ⚠️ Wastes space for small numbers

---

### Approach 2: Length + Delimiter Encoding (Alternative)

**Format:** `len1#str1len2#str2...`

**Example:**
```
Input:  ["Hello", "World"]
Encode: "5#Hello5#World"
         ↑ ↑     ↑ ↑
         │ │     │ └── String 2
         │ │     └── String 2 length
         │ └── Delimiter (metadata, not data)
         └── String 1 length
```

**Why '#' is Safe Here:**
The `#` is **metadata** (outside string data), not data itself.

**Example with '#' in data:**
```
Input:  ["A#B#C"]
Encode: "5#A#B#C"
         ↑ ↑^^^^
         │ └── Data (exactly 5 characters)
         └── Length tells us to read 5 chars
```

Decoder logic: "Read '5', then read next 5 characters" → correctly gets "A#B#C"

**Implementation:**
```java
public String encodeV2(List<String> strs) {
    StringBuilder sb = new StringBuilder();
    for (String str : strs) {
        sb.append(str.length());  // Variable-width length
        sb.append('#');            // Delimiter
        sb.append(str);            // String data
    }
    return sb.toString();
}

public List<String> decodeV2(String s) {
    List<String> result = new ArrayList<>();
    if (s == null || s.isEmpty()) return result;
    
    int i = 0;
    while (i < s.length()) {
        // Find delimiter
        int delimPos = s.indexOf('#', i);
        if (delimPos == -1) break;
        
        // Extract length
        int length = Integer.parseInt(s.substring(i, delimPos));
        
        // Extract string
        int start = delimPos + 1;
        result.add(s.substring(start, start + length));
        
        // Move to next string
        i = start + length;
    }
    return result;
}
```

**Complexity:**
- **Time:** O(m) - indexOf scans but overall still linear
- **Space:** O(m) for output

**Pros:**
- ✅ More space-efficient (1-3 bytes overhead vs always 3)
- ✅ Human-readable format
- ✅ Common in network protocols

**Cons:**
- ⚠️ Must scan for delimiter (indexOf is O(n) per call)
- ⚠️ Variable-width parsing slightly more complex

---

## Space Efficiency Comparison

### Test Cases

| Input | Fixed-Width | Length+Delim | Savings |
|-------|-------------|--------------|---------|
| `["Hi","A"]` | `"00200202Hi001A"` (15) | `"2#Hi1#A"` (8) | **7 bytes (47%)** |
| `["","",""]` | `"003000000000"` (12) | `"0#0#0#"` (6) | **6 bytes (50%)** |
| `["SingleString"]` | `"00100012SingleString"` (23) | `"12#SingleString"` (16) | **7 bytes (30%)** |
| `["A"×100, "B"×150]` | `"002003100A...003150B..."` (259) | `"100#A...150#B..."` (254) | **5 bytes (2%)** |

### Analysis

**For Short Strings:**
- Fixed-Width: Always 3 bytes overhead
- Length+Delim: 1-2 bytes (length) + 1 byte (#) = 2-3 bytes
- **Winner:** Length+Delim saves 0-1 bytes per string

**For Long Strings (>100 chars):**
- Fixed-Width: 3 bytes overhead (e.g., "150")
- Length+Delim: 4 bytes overhead (e.g., "150#")
- **Winner:** Fixed-Width saves 1 byte per string

**For Many Strings:**
- Fixed-Width: Also stores list size (3 bytes upfront)
- Length+Delim: No list size needed
- **Winner:** Length+Delim saves 3 bytes total

**Conclusion:** Length+Delim is generally more space-efficient except for very long strings (>99 chars).

---

## When to Use Each Approach

### Use Fixed-Width When:

1. **Parsing Speed is Critical**
   - O(1) position calculation
   - No scanning for delimiters
   - Predictable memory access patterns

2. **Binary Protocols**
   - Fixed positions matter
   - Protocol specifications require it
   - Binary data (not human-readable anyway)

3. **Debugging Priority**
   - Easier to see structure: `"002005Hello003Yes"`
   - Can visually parse the format

4. **Implementation Simplicity**
   - No delimiter scanning logic
   - Single position pointer, linear traversal

### Use Length + Delimiter When:

1. **Space Efficiency Matters**
   - Network bandwidth limited
   - Storage costs significant
   - Many short strings

2. **Human Readability Important**
   - Text protocols (HTTP-like)
   - Logs and debugging output
   - Configuration files

3. **Variable-Length Numbers Expected**
   - String counts vary widely
   - Don't want to overallocate

4. **Standard Protocol Patterns**
   - Following existing conventions
   - Compatibility with other systems

---

## Real-World Applications

### 1. HTTP Protocol

HTTP uses **both patterns**:

```http
Content-Length: 156
[156 bytes of data]
```

This is length-prefixed! Server reads "156" → knows to read exactly 156 bytes.

### 2. Protocol Buffers (Protobuf)

Uses **variable-length encoding**:
```
[field-tag][length][data][field-tag][length][data]...
```

Similar to length + delimiter approach.

### 3. TCP/IP

**Length field in TCP header** (16-bit):
```
[Total Length: 1500 bytes][1500 bytes of data]
```

Fixed-width length at known position.

### 4. JSON Streaming

APIs sometimes use **newline-delimited JSON**:
```json
{"data":"value1"}
{"data":"value2"}
```

But this fails if JSON contains newlines! Better: length-prefix each JSON object.

---

## Common Mistakes and Edge Cases

### Edge Case 1: Empty Strings

**Problem:** Zero-length strings must still be encoded

**Solution:**
- Fixed-Width: `"000"` → empty string
- Length+Delim: `"0#"` → empty string

### Edge Case 2: Empty List

**Problem:** No strings to encode

**Solution:**
- Fixed-Width: `"000"` → list size is zero
- Length+Delim: `""` → empty output string

### Edge Case 3: Strings Containing Numbers

**Problem:** Input `["123", "456"]` could be confused with lengths

**Solution:** Length-prefixing eliminates ambiguity
```
Fixed:     "002003123003456"
            │  │  └─┘ │  └─┘
            │  │  data│  data
            │  └──────┘
            └─ list size
```

We read: "List has 2 strings. First string is 3 chars: '123'. Second string is 3 chars: '456'."

### Edge Case 4: All ASCII Characters (0-255)

**Test:**
```java
StringBuilder allAscii = new StringBuilder();
for (int i = 0; i < 256; i++) {
    allAscii.append((char) i);
}
List<String> input = Arrays.asList(allAscii.toString());
```

**Result:** Both approaches handle it correctly because:
- No character is reserved
- Length tells us exactly what to read
- Null bytes, control characters, all fine

---

## Performance Analysis

### Time Complexity Deep Dive

**Encoding - Both Approaches: O(m)**
- Must iterate through all characters once
- StringBuilder.append() is O(1) amortized
- String.format() or Integer.toString() is O(1) for small numbers

**Decoding - Fixed-Width: O(m)**
- Single pass through encoded string
- substring() is O(k) for string of length k
- Total: sum of all substring operations = O(m)

**Decoding - Length+Delim: O(m)**
- indexOf() is O(n) per call, called once per string
- Worst case: O(n × k) where k = number of strings
- But n decreases each iteration, so still O(m) total
- Slightly slower in practice due to scanning overhead

### Space Complexity

**Both Approaches: O(m)**
- Output string size ≈ m + overhead
- Overhead: Fixed-Width ≈ 3n bytes, Length+Delim ≈ 2n bytes
- Asymptotically O(m) since m >> n for typical inputs

### Constant Factor Comparison

**Fixed-Width Operations per String:**
1. Parse 3-digit length: ~3 character reads
2. Extract substring: ~length character copies
3. Advance position: ~1 arithmetic operation

**Length+Delim Operations per String:**
1. Scan for '#': ~length/2 character reads (average)
2. Parse variable-length: ~1-3 character reads
3. Extract substring: ~length character copies

**Verdict:** Fixed-Width is ~10-20% faster due to no scanning overhead.

---

## Key Takeaways

### 1. **Length-Prefixed Encoding is Fundamental**

This isn't just a LeetCode trick - it's how computers communicate:
- Network protocols (HTTP, TCP, TLS)
- File formats (PNG, ZIP, Protobuf)
- Serialization (Java serialization, Python pickle)
- Database storage (variable-length fields)

### 2. **Fixed vs Variable-Width Trade-off**

| Aspect | Fixed-Width | Variable-Width |
|--------|-------------|----------------|
| **Parsing Speed** | Faster (O(1) positions) | Slower (scanning) |
| **Space Efficiency** | Fixed overhead | Variable overhead |
| **Simplicity** | Simpler (no scanning) | Slightly complex |
| **Readability** | Less readable | More readable |

### 3. **Why Delimiters Fail Without Length**

- Any delimiter could appear in data
- Escaping is complex and error-prone
- Length-prefixing sidesteps the entire problem

### 4. **StringBuilder is Essential**

```java
// O(n²) - creates new string each time
String result = "";
for (String s : strs) {
    result += s;  // BAD!
}

// O(n) - amortized constant time append
StringBuilder sb = new StringBuilder();
for (String s : strs) {
    sb.append(s);  // GOOD!
}
```

### 5. **String.format() for Clean Padding**

```java
// Manual (verbose)
if (size < 10) sb.append("00");
else if (size < 100) sb.append("0");
sb.append(size);

// Clean (one-liner)
String formatted = String.format("%03d", size);
```

### 6. **Input Validation Matters**

```java
// Without validation - crashes on empty input
int size = Integer.parseInt(s.substring(0, 3));  // StringIndexOutOfBoundsException!

// With validation - handles edge cases
if (s == null || s.length() < 3) return new ArrayList<>();
```

---

## Interview Tips

### What Interviewers Look For

1. **Recognition of the core problem:** "Any ASCII character can appear"
2. **Why delimiters fail:** Explain conflict between data and metadata
3. **Length-prefixing insight:** This is THE solution pattern
4. **Trade-off discussion:** Fixed vs variable-width
5. **Edge case handling:** Empty strings, empty list, large numbers

### Strong Answer Structure

1. "The key challenge is that any character could appear in input strings, so we can't use a delimiter."
2. "Length-prefixed encoding solves this - we tell the decoder exactly how many bytes to read."
3. "I'll use fixed-width encoding for simplicity and predictable parsing."
4. "Time complexity is O(m) where m is the total length of all strings - optimal since we must process every character."
5. "An alternative is variable-width with a delimiter like '#', which is more space-efficient but requires scanning."

### Follow-Up Questions to Expect

**Q: "What if we used a really rare character like Unicode symbol?"**
A: "Problem says any ASCII character is valid. Even rare characters could theoretically appear in data."

**Q: "Could we use escape sequences?"**
A: "Yes, but it's complex - we'd need to escape the escape character recursively. Length-prefixing is simpler and more efficient."

**Q: "How does this relate to real-world systems?"**
A: "This is exactly how HTTP Content-Length works, and how TCP frames data. It's a fundamental protocol design pattern."

**Q: "Which approach would you use in production?"**
A: "Depends on requirements. If space is critical and strings are short, length+delimiter. If parsing speed matters or it's a binary protocol, fixed-width."

---

## Related Problems

1. **LeetCode 443 - String Compression**
   - Similar length encoding pattern
   - Count consecutive characters

2. **LeetCode 604 - Design Compressed String Iterator**
   - Length + character encoding
   - Related compression concepts

3. **LeetCode 820 - Short Encoding of Words**
   - Optimal encoding of word list
   - Suffix tree approach

4. **System Design: Design TinyURL**
   - Encoding/decoding mappings
   - Similar bijection concept

---

## Conclusion

**Encode and Decode Strings** teaches a fundamental computer science pattern: **length-prefixed encoding**. This isn't just a coding challenge - it's how real systems solve the "where does data end?" problem when no character can be reserved.

**Key Insight:** When you can't use delimiters (because any character might appear in data), tell the decoder exactly how many bytes to read. This eliminates ambiguity and avoids complex escaping logic.

**Real-World Impact:** Understanding this pattern helps you:
- Design network protocols
- Parse binary file formats
- Debug serialization issues
- Optimize data transfer
- Make informed trade-offs between speed and space

The difference between junior and senior engineers often shows in recognizing these fundamental patterns and understanding *why* they exist in production systems.
