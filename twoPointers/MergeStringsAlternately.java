package twoPointers;

/**
 * LeetCode Problem #1768: Merge Strings Alternately
 * Difficulty: Easy
 *
 * Problem Description:
 * You are given two strings word1 and word2. Merge the strings by adding
 * letters in alternating order, starting with word1. If a string is longer
 * than the other, append the additional letters onto the end of the merged
 * string. Return the merged string.
 *
 * Constraints:
 * - 1 <= word1.length, word2.length <= 100
 * - word1 and word2 consist of lowercase English letters
 *
 * Example 1:
 * Input: word1 = "abc", word2 = "pqr"
 * Output: "apbqcr"
 * Explanation: The merged string will be merged as so:
 * word1:  a   b   c
 * word2:    p   q   r
 * merged: a p b q c r
 *
 * Example 2:
 * Input: word1 = "ab", word2 = "pqrs"
 * Output: "apbqrs"
 * Explanation: Notice that as word2 is longer, "rs" is appended to the end.
 * word1:  a   b
 * word2:    p   q   r   s
 * merged: a p b q   r   s
 *
 * Example 3:
 * Input: word1 = "abcd", word2 = "pq"
 * Output: "apbqcd"
 * Explanation: Notice that as word1 is longer, "cd" is appended to the end.
 * word1:  a   b   c   d
 * word2:    p   q
 * merged: a p b q c   d
 *
 * Approach:
 * Two-pointer interleaving over char array copies. Converts both strings to
 * char[] up front, then walks a single output index i while two independent
 * pointers (p1, p2) track position in each source array. Each loop iteration
 * writes from word1 if p1 hasn't run out, then from word2 if p2 hasn't run
 * out. Because the two writes are independent if-checks rather than a
 * paired step, once one string is exhausted its check simply stops firing
 * and the other keeps filling the output every iteration — the trailing
 * tail of the longer string falls out of the same loop with no special case.
 *
 * Time Complexity: O(n + m) — single pass, every character of both strings
 * is visited exactly once.
 * Space Complexity: O(n + m) total (dominated by the required output).
 * Auxiliary space (excluding output) is also O(n + m), because both inputs
 * are copied via toCharArray() before merging; reading characters directly
 * with charAt() instead would bring auxiliary space down to O(1).
 *
 * Key Learnings:
 * - Two independent if-checks in one loop (rather than a single combined
 *   step) is what makes the "append the leftover tail" behavior fall out
 *   automatically — no separate branch needed for unequal-length inputs.
 * - toCharArray() trades auxiliary space for direct indexed access; since
 *   String already supports O(1) charAt(), the copies aren't required here.
 *
 * Alternative Approaches:
 * 1. charAt() two-pointer (same structure, no toCharArray() copies) — O(n+m)
 *    time, O(1) auxiliary space (excl. output).
 * 2. StringBuilder + charAt() — O(n+m) time, O(1) auxiliary space; most
 *    idiomatic Java version of this pattern.
 *
 * @see <a href="https://leetcode.com/problems/merge-strings-alternately/description/">LeetCode Problem #1768</a>
 */
public class MergeStringsAlternately {

    /**
     * Merges two strings by alternating characters, appending any leftover
     * tail from the longer string.
     *
     * @param word1 the first string
     * @param word2 the second string
     * @return the merged string
     */
    public String mergeAlternately(String word1, String word2) {
        char[] ch1, ch2, totalCh;
        ch1 = word1.toCharArray();
        ch2 = word2.toCharArray();

        totalCh = new char[word1.length()+word2.length()];

        int p1=0, p2=0;
        for(int i=0; i<totalCh.length ; ){
            if(p1 < ch1.length){
                totalCh[i] = ch1[p1];
                p1++;
                i++;
            }
            if(p2 < ch2.length){
                totalCh[i] = ch2[p2];
                p2++;
                i++;
            }
        }
        return String.copyValueOf(totalCh);
    }

    /**
     * Helper method to run a single test case.
     *
     * @param testNum test case number
     * @param word1 first input string
     * @param word2 second input string
     * @param expected expected result
     * @param actual actual result from solution
     * @param description test case description
     */
    private void runTest(int testNum, String word1, String word2, String expected, String actual, String description) {
        boolean passed = expected.equals(actual);
        String status = passed ? "✓ PASS" : "✗ FAIL";

        System.out.printf("Test %d: %s - %s%n", testNum, description, status);
        System.out.printf("  Input:    word1=\"%s\", word2=\"%s\"%n", word1, word2);
        System.out.printf("  Expected: \"%s\"%n", expected);
        System.out.printf("  Actual:   \"%s\"%n", actual);
        System.out.println();
    }

    /**
     * Helper method to print test summary statistics.
     *
     * @param results array of boolean results for each test
     */
    private void printSummary(boolean[] results) {
        int passed = 0;
        int total = results.length;

        for (boolean result : results) {
            if (result) passed++;
        }

        System.out.println("=".repeat(60));
        System.out.printf("TEST SUMMARY: %d/%d passed (%.1f%%)%n",
                         passed, total, (passed * 100.0 / total));
        System.out.println("=".repeat(60));
    }

    /**
     * Main method with comprehensive test cases.
     */
    public static void main(String[] args) {
        MergeStringsAlternately solution = new MergeStringsAlternately();
        boolean[] results = new boolean[9];
        int testNum = 0;

        System.out.println("Testing Merge Strings Alternately");
        System.out.println("=".repeat(60));
        System.out.println();

        // Test 1: Example 1 - equal length strings
        {
            String word1 = "abc", word2 = "pqr";
            String expected = "apbqcr";
            String actual = solution.mergeAlternately(word1, word2);
            solution.runTest(++testNum, word1, word2, expected, actual,
                           "Example 1 - equal length strings");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 2: Example 2 - word2 longer
        {
            String word1 = "ab", word2 = "pqrs";
            String expected = "apbqrs";
            String actual = solution.mergeAlternately(word1, word2);
            solution.runTest(++testNum, word1, word2, expected, actual,
                           "Example 2 - word2 longer, tail appended");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 3: Example 3 - word1 longer
        {
            String word1 = "abcd", word2 = "pq";
            String expected = "apbqcd";
            String actual = solution.mergeAlternately(word1, word2);
            solution.runTest(++testNum, word1, word2, expected, actual,
                           "Example 3 - word1 longer, tail appended");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 4: Single character each
        {
            String word1 = "a", word2 = "b";
            String expected = "ab";
            String actual = solution.mergeAlternately(word1, word2);
            solution.runTest(++testNum, word1, word2, expected, actual,
                           "Single character each");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 5: word1 single char, word2 much longer
        {
            String word1 = "a", word2 = "bcdef";
            String expected = "abcdef";
            String actual = solution.mergeAlternately(word1, word2);
            solution.runTest(++testNum, word1, word2, expected, actual,
                           "word1 single char, word2 much longer");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 6: word2 single char, word1 much longer
        {
            String word1 = "bcdef", word2 = "a";
            String expected = "bacdef";
            String actual = solution.mergeAlternately(word1, word2);
            solution.runTest(++testNum, word1, word2, expected, actual,
                           "word2 single char, word1 much longer");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 7: Minimum constraint - both length 1
        {
            String word1 = "x", word2 = "y";
            String expected = "xy";
            String actual = solution.mergeAlternately(word1, word2);
            solution.runTest(++testNum, word1, word2, expected, actual,
                           "Minimum constraint - both length 1");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 8: Same character repeated
        {
            String word1 = "aaa", word2 = "bbb";
            String expected = "ababab";
            String actual = solution.mergeAlternately(word1, word2);
            solution.runTest(++testNum, word1, word2, expected, actual,
                           "Same character repeated in each string");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 9: Maximum constraint - length 100 each
        {
            String word1 = "a".repeat(100);
            String word2 = "b".repeat(100);
            StringBuilder expectedBuilder = new StringBuilder();
            for (int i = 0; i < 100; i++) {
                expectedBuilder.append('a').append('b');
            }
            String expected = expectedBuilder.toString();
            String actual = solution.mergeAlternately(word1, word2);
            solution.runTest(++testNum, "a*100", "b*100", expected, actual,
                           "Maximum constraint - length 100 each");
            results[testNum - 1] = expected.equals(actual);
        }

        solution.printSummary(results);
    }
}
