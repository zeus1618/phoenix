package twoPointers;

import java.util.List;
import java.util.Map;

/**
 * LeetCode Problem #392: Is Subsequence
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/is-subsequence/
 *
 * <p>Problem Description:
 * Given two strings s and t, return true if s is a subsequence of t, or
 * false otherwise.
 *
 * <p>A subsequence of a string is a new string that is formed from the
 * original string by deleting some (can be none) of the characters without
 * disturbing the relative positions of the remaining characters. (i.e.,
 * "ace" is a subsequence of "abcde" while "aec" is not).
 *
 * <p>Constraints:
 * - 0 <= s.length <= 100
 * - 0 <= t.length <= 10^4
 * - s and t consist only of lowercase English letters.
 *
 * <p>Follow-up:
 * Suppose there are lots of incoming s, say s1, s2, ..., sk where k >= 10^9,
 * and you want to check one by one to see if t has its subsequence. In this
 * scenario, how would you change your code?
 *
 * <p>Examples:
 * Example 1:
 * Input: s = "abc", t = "ahbgdc"
 * Output: true
 *
 * Example 2:
 * Input: s = "axc", t = "ahbgdc"
 * Output: false
 *
 * <p>Approach:
 * Two-pointer scan. {@code subI} and {@code supI} advance forward through
 * {@code s} and {@code t} respectively; {@code supI} only ever moves
 * forward and never resets, so across the whole run it visits each
 * position of {@code t} at most once — the nested-loop shape amortizes to
 * a single combined pass over both strings.
 *
 * <p>Time Complexity: O(n + m) — n = s.length(), m = t.length(); supI is
 * monotonic across the entire run despite the nested-loop structure.
 * <p>Space Complexity: O(n + m) auxiliary, from the two toCharArray()
 * copies; could be reduced to O(1) auxiliary by using charAt() instead.
 */
public class IsSubsequence {

    /**
     * Determines whether s is a subsequence of t.
     *
     * @param s the candidate subsequence
     * @param t the string to check against
     * @return true if s is a subsequence of t, false otherwise
     */
    public boolean isSubsequence(String s, String t) {
        if(t.length()<s.length()){
            return false;
        }
        char[] sub = s.toCharArray();
        char[] sup = t.toCharArray();
        int subI = 0, supI=0;

        for(; subI<sub.length && supI<sup.length;){
            for(; supI<sup.length;){
                if(sub[subI] == sup[supI]){
                    subI++;
                    supI++;
                    break;
                } else {
                    supI++;
                }
            }
        }
        return subI>=sub.length;
    }

    // ======================== Follow-Up: Many Queries Against Fixed t ========================

    /**
     * Follow-up (build phase): preprocesses t once so that many subsequent
     * isSubsequence queries (s1, s2, ..., sk with k possibly >= 10^9) can
     * each be answered without re-scanning all of t from the start.
     *
     * Intended approach: for each character, record the sorted list of
     * indices at which it occurs in t (e.g. Map&lt;Character, List&lt;Integer&gt;&gt;).
     * A query can then binary-search each character's index list for the
     * next occurrence at or after the current position.
     *
     * @param t the fixed string that many queries will be checked against
     * @return a preprocessed index structure to be passed to
     *         {@link #isSubsequenceWithIndex(String, Map)}
     */
    public Map<Character, List<Integer>> buildCharacterIndex(String t) {
        // TODO: implement solution
        return null;
    }

    /**
     * Follow-up (query phase): determines whether s is a subsequence of the
     * t that was preprocessed into charIndex, using binary search over each
     * character's occurrence list instead of a linear scan of t.
     *
     * @param s the candidate subsequence for this query
     * @param charIndex the index built by {@link #buildCharacterIndex(String)}
     * @return true if s is a subsequence of the original t, false otherwise
     */
    public boolean isSubsequenceWithIndex(String s, Map<Character, List<Integer>> charIndex) {
        // TODO: implement solution
        return false;
    }

    // ======================== Test Helper Methods ========================

    /**
     * Runs a single test case and prints the result.
     *
     * @param testNum the test case number
     * @param s the candidate subsequence input
     * @param t the string to check against
     * @param expected the expected result
     * @param actual the actual result from the solution
     * @param description brief description of the test case
     */
    private static void runTest(
            int testNum,
            String s,
            String t,
            boolean expected,
            boolean actual,
            String description) {

        boolean passed = expected == actual;
        String status = passed ? "✓ PASS" : "✗ FAIL";

        System.out.println("\n" + status + " - Test " + testNum + ": " + description);
        System.out.println("  Input:    s = \"" + s + "\", t = \"" + t + "\"");
        System.out.println("  Expected: " + expected);
        System.out.println("  Actual:   " + actual);

        if (!passed) {
            System.out.println("  ❌ Test failed!");
        }
    }

    /**
     * Prints a summary of all test results.
     *
     * @param totalTests total number of tests run
     * @param passedTests number of tests that passed
     */
    private static void printSummary(int totalTests, int passedTests) {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("TEST SUMMARY");
        System.out.println("=".repeat(70));
        System.out.println("Total Tests: " + totalTests);
        System.out.println("Passed:      " + passedTests + " ✓");
        System.out.println("Failed:      " + (totalTests - passedTests) + " ✗");
        System.out.println("Success Rate: " +
                String.format("%.1f%%", (passedTests * 100.0 / totalTests)));
        System.out.println("=".repeat(70));

        if (passedTests == totalTests) {
            System.out.println("🎉 All tests passed!");
        } else {
            System.out.println("⚠️  Some tests failed. Please review the output above.");
        }
    }

    // ======================== Main Test Function ========================

    public static void main(String[] args) {
        int totalTests = 0;
        int passedTests = 0;

        System.out.println("=".repeat(70));
        System.out.println("Testing LeetCode #392: Is Subsequence");
        System.out.println("=".repeat(70));

        IsSubsequence solution = new IsSubsequence();

        // Test 1: Example 1 - valid subsequence
        {
            String s = "abc", t = "ahbgdc";
            boolean expected = true;
            boolean actual = solution.isSubsequence(s, t);
            runTest(++totalTests, s, t, expected, actual,
                    "Example 1 - valid subsequence");
            if (expected == actual) passedTests++;
        }

        // Test 2: Example 2 - not a subsequence
        {
            String s = "axc", t = "ahbgdc";
            boolean expected = false;
            boolean actual = solution.isSubsequence(s, t);
            runTest(++totalTests, s, t, expected, actual,
                    "Example 2 - not a subsequence");
            if (expected == actual) passedTests++;
        }

        // Test 3: Empty s - vacuously true
        {
            String s = "", t = "ahbgdc";
            boolean expected = true;
            boolean actual = solution.isSubsequence(s, t);
            runTest(++totalTests, s, t, expected, actual,
                    "Empty s - vacuously true");
            if (expected == actual) passedTests++;
        }

        // Test 4: Empty t, non-empty s - impossible
        {
            String s = "a", t = "";
            boolean expected = false;
            boolean actual = solution.isSubsequence(s, t);
            runTest(++totalTests, s, t, expected, actual,
                    "Empty t with non-empty s - impossible");
            if (expected == actual) passedTests++;
        }

        // Test 5: Both empty
        {
            String s = "", t = "";
            boolean expected = true;
            boolean actual = solution.isSubsequence(s, t);
            runTest(++totalTests, s, t, expected, actual,
                    "Both s and t empty");
            if (expected == actual) passedTests++;
        }

        // Test 6: s equals t
        {
            String s = "abc", t = "abc";
            boolean expected = true;
            boolean actual = solution.isSubsequence(s, t);
            runTest(++totalTests, s, t, expected, actual,
                    "s equals t exactly");
            if (expected == actual) passedTests++;
        }

        // Test 7: s longer than t - impossible
        {
            String s = "abcd", t = "abc";
            boolean expected = false;
            boolean actual = solution.isSubsequence(s, t);
            runTest(++totalTests, s, t, expected, actual,
                    "s longer than t - impossible");
            if (expected == actual) passedTests++;
        }

        // Test 8: Single character present in t
        {
            String s = "b", t = "abc";
            boolean expected = true;
            boolean actual = solution.isSubsequence(s, t);
            runTest(++totalTests, s, t, expected, actual,
                    "Single character present in t");
            if (expected == actual) passedTests++;
        }

        // Test 9: Single character absent from t
        {
            String s = "z", t = "abc";
            boolean expected = false;
            boolean actual = solution.isSubsequence(s, t);
            runTest(++totalTests, s, t, expected, actual,
                    "Single character absent from t");
            if (expected == actual) passedTests++;
        }

        // Test 10: Repeated characters, valid order
        {
            String s = "aab", t = "aaabc";
            boolean expected = true;
            boolean actual = solution.isSubsequence(s, t);
            runTest(++totalTests, s, t, expected, actual,
                    "Repeated characters - valid subsequence");
            if (expected == actual) passedTests++;
        }

        // Test 11: Repeated characters, insufficient occurrences
        {
            String s = "aaab", t = "aabc";
            boolean expected = false;
            boolean actual = solution.isSubsequence(s, t);
            runTest(++totalTests, s, t, expected, actual,
                    "Repeated characters - not enough occurrences in t");
            if (expected == actual) passedTests++;
        }

        // Test 12: Same characters, wrong order
        {
            String s = "cba", t = "abc";
            boolean expected = false;
            boolean actual = solution.isSubsequence(s, t);
            runTest(++totalTests, s, t, expected, actual,
                    "Same characters but wrong order");
            if (expected == actual) passedTests++;
        }

        // Test 13: Characters at the very end of t
        {
            String s = "xyz", t = "abcdefwxyz";
            boolean expected = true;
            boolean actual = solution.isSubsequence(s, t);
            runTest(++totalTests, s, t, expected, actual,
                    "Match confined to the tail end of t");
            if (expected == actual) passedTests++;
        }

        printSummary(totalTests, passedTests);

        // ======================== Follow-Up Tests: Many Queries Against Fixed t ========================

        System.out.println();
        System.out.println("=".repeat(70));
        System.out.println("Testing Follow-Up: Many Queries Against a Fixed t");
        System.out.println("=".repeat(70));

        int followUpTotal = 0;
        int followUpPassed = 0;
        String fixedT = "ahbgdc";
        Map<Character, List<Integer>> charIndex = solution.buildCharacterIndex(fixedT);

        String[] queries = {"abc", "axc", "", "ahbgdc", "abgdc", "z", "abcx"};
        boolean[] expectedResults = {true, false, true, true, true, false, false};

        for (int i = 0; i < queries.length; i++) {
            boolean actual = solution.isSubsequenceWithIndex(queries[i], charIndex);
            runTest(++followUpTotal, queries[i], fixedT, expectedResults[i], actual,
                    "Follow-up query " + (i + 1) + " against shared index of t");
            if (expectedResults[i] == actual) followUpPassed++;
        }

        printSummary(followUpTotal, followUpPassed);
    }
}
