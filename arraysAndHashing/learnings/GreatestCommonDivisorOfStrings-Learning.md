# Greatest Common Divisor of Strings (#1071) — Deep Dive

> LeetCode #1071 · Easy · Arrays & Hashing
> Solved: 2026-08-18

---

## Problem Evolution Journey

Three attempts exist in `GreatestCommonDivisorOfStrings.java`, each still present in the file (only the last is wired into `main`):

### Attempt 1: `lcdOfStrings` — Incremental Single-Character Scan

```java
int index = 0;
String lcdCandidate = "";
while (true) {
    if (index >= str1.length() || index >= str2.length()) break;
    if (str1.charAt(index) == str2.charAt(index)) {
        lcdCandidate = str1.substring(0, index + 1);
        if (str1.split(lcdCandidate).length == 0 && str2.split(lcdCandidate).length == 0) {
            return lcdCandidate;   // <-- returns on the FIRST hit
        }
        index++;
    } else {
        return "";
    }
}
```

**Idea:** grow a candidate prefix one character at a time, and use the quirk that
`"AAAA".split("AA")` returns an empty array (all pieces are empty, and Java's
`split()` strips *all* trailing empty strings — including every element, if
every element is empty) to test "does this candidate evenly tile both strings."

**The bug:** it returns on the *first* length that passes the tiling check,
not the *largest*. This is silently wrong whenever a string has more than one
valid divisor length — which is exactly what happens with repeated-character
strings.

**What caught it:** a test case with `str1 = "A"*1000, str2 = "A"*500`. Every
length that divides both 1000 and 500 (1, 2, 4, 5, 10, ..., 500) passes the
tiling check, so the scan-from-length-1 approach stops at `"A"` (length 1)
instead of continuing to length 500. The bug had passed 9/9 other handwritten
tests — none of them had more than one valid divisor length, so the "return
early" flaw never triggered.

### Attempt 2: `gcdOfStringOld` — Binary-Halving Search

```java
int index = Math.min(str1.length(), str2.length());
String shorterString = str1.length() > str2.length() ? str2 : str1;
while (true) {
    String gcdCandidate = shorterString.substring(0, index);
    if (str1.split(gcdCandidate).length == 0 && str2.split(gcdCandidate).length == 0) {
        return gcdCandidate;
    } else if (index % 2 != 0) {
        break;   // "GCD can't exist"
    } else {
        index = index / 2;
    }
}
```

**Idea:** start from the shorter string's full length and repeatedly halve
the candidate length until a tiling match is found, or the length becomes odd
(treated as "no GCD").

**The bug:** halving only visits a small subset of a number's divisors — the
ones reachable by repeated division by 2 (e.g. from 12: 12, 6, 3 — stop,
since 3 is odd). It never tries 4, which *does* divide 12. So this approach
can both miss the true GCD length and incorrectly conclude "no GCD exists"
when an odd-but-valid length is skipped over. It's a search strategy that
happens to work on some inputs (like the ABCD example) purely by luck of
which divisors are power-of-2 multiples of each other.

### Attempt 3 (Final): `gcdOfStrings` — Concatenation Check + Euclidean GCD

```java
public String gcdOfStrings(String str1, String str2) {
    if (!(str1 + str2).equals(str2 + str1))
        return "";
    int gcd = gcd(str1.length(), str2.length());
    return str1.substring(0, gcd);
}

private int gcd(int a, int b) {
    return b == 0 ? a : gcd(b, a % b);
}
```

No search at all. See below for why this works.

---

## Solution Comparison

| Approach | Time | Space | Correctness |
|---|---|---|---|
| Concatenation check + Euclidean GCD (final) | O(n+m) | O(n+m) | ✅ Always correct |
| Incremental single-character scan | O(n·m) worst case | O(n+m) | ❌ Returns first valid length, not largest |
| Binary-halving search | O(n log n) | O(n+m) | ❌ Misses divisor lengths not reachable by halving |

---

## Performance Deep Dive: Why the Concatenation Check Works

This is the part worth internalizing, since it isn't obvious from the problem
statement alone.

**Claim:** A non-empty string `x` divides both `str1` and `str2` if and only
if `str1 + str2 == str2 + str1`.

**Why "only if" (necessity) is intuitive:** if `str1 = x^p` (x repeated p
times) and `str2 = x^q`, then `str1 + str2 = x^(p+q) = str2 + str1`. Both
sides are just `x` repeated `p+q` times, so they're trivially equal.

**Why "if" (sufficiency) is the non-obvious direction:** if `str1 + str2 ==
str2 + str1`, it's a classical result (related to the Fine–Wilf theorem on
string periodicity) that `str1` and `str2` must both be powers of some common
shorter string. It's not something you'd derive from scratch during an
interview — it's worth just knowing as a fact: **two strings commute under
concatenation if and only if they're both built from the same repeating
unit.**

**Why the answer length is exactly `gcd(len1, len2)`:** once you know str1
and str2 are both powers of some base string `b` (say `str1 = b^p`, `str2 =
b^q`), the *largest* string dividing both is `b` repeated `gcd(p, q)` times
— which has length `gcd(p, q) * len(b) = gcd(p*len(b), q*len(b)) =
gcd(len1, len2)`. So you never need to search for the answer's length; it's
determined directly by the two input lengths.

This is why the final solution has no loop over candidates at all — the
"search" that both earlier attempts were doing is mathematically unnecessary
once you know this theorem.

---

## Key Insights

- **A O(n·m)-shaped search collapses into an O(n+m) check.** Both buggy
  attempts were implicitly searching for the right candidate length. The
  concatenation trick sidesteps the search by proving the length in advance.
- **Weak test coverage lets greedy-first-match bugs survive.** The
  incremental scan's "return on first match" bug is invisible unless a test
  case has *multiple* valid divisor lengths. Repeated-character strings
  (`"AAAA"`, `"AAAAAA"`) are the canonical adversarial input for this shape
  of bug — worth remembering as a go-to stress case for any "find the
  largest X satisfying property P" problem where a naive scan might stop at
  the first P-satisfying candidate instead of the largest.
- **`String.split()`'s trailing-empty-strip behavior is a genuine (if
  obscure) way to test "does A evenly tile B."** `"AAAA".split("AA")`
  returns `[]` because every resulting piece is empty and Java's default
  `split()` strips *all* trailing empty strings, including every element
  when all of them are empty. This is a real technique, just applied inside
  the wrong search strategy in both earlier attempts.

## Major Lessons

- **Recognizing a closed-form beats searching, even when the search is
  "obviously" going to work eventually.** The instinct to scan for the
  right answer is natural, but for problems built on a clean mathematical
  property (periodicity, GCD, modular arithmetic), there's often a direct
  formula that eliminates the search entirely — and it's usually both
  simpler *and* faster.
- **A partial/incomplete search strategy (like halving) can look correct on
  hand-picked examples while being wrong in general.** The halving approach
  passed the ABCD/ABCDABCDABCD example because 4 happens to be reachable
  from 8 by one halving step — that's a coincidence of the specific numbers
  chosen, not evidence the strategy generalizes.

## Best Practices

- When a problem involves "does X evenly divide/tile Y," check whether the
  problem has an underlying number-theoretic structure (GCD, LCM, modular
  properties) before reaching for a scan or search — these problems often
  have a formula-based answer.
- When testing a "find the largest/best candidate" solution, always include
  a case with **multiple valid candidates**, not just cases with zero or
  one. A single valid candidate can't distinguish "finds the best one" from
  "finds the first one."

## Pattern Recognition

**String periodicity / concatenation-check pattern**: for problems of the
form "does string x divide/tile both str1 and str2," or "is str2 a rotation
of str1," concatenation tricks (`str1+str2 == str2+str1` for divisibility,
or checking `str2` is a substring of `str1+str1` for rotation) often replace
what looks like it needs a search or simulation.

## Mistakes to Avoid

- Don't return on the first candidate that satisfies a property when the
  problem asks for the *largest* (or *best*) one — verify there isn't a
  larger valid candidate before short-circuiting, or better, prove
  mathematically which candidate must be the answer.
- Don't assume a search strategy that reduces the problem size by a fixed
  ratio (like halving) explores all divisors — divisor sets aren't
  power-of-2 structured in general.
