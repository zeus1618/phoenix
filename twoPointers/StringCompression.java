package twoPointers;

import java.util.Arrays;

/**
 * LeetCode Problem #443: String Compression
 * Difficulty: Medium
 *
 * Problem Description:
 * Given an array of characters chars, compress it using the following
 * algorithm:
 *
 * Begin with an empty string s. For each group of consecutive repeating
 * characters in chars:
 * - If the group's length is 1, append the character to s.
 * - Otherwise, append the character followed by the group's length.
 *
 * The compressed string s should not be returned separately, but instead
 * be stored in the input character array chars. Note that group lengths
 * 10 or longer will be split into multiple characters in chars.
 *
 * After you are done modifying the input array, return the new length of
 * the array.
 *
 * You must write an algorithm that uses only constant extra space.
 *
 * Constraints:
 * - 1 <= chars.length <= 2000
 * - chars[i] is a lowercase English letter, uppercase English letter,
 *   digit, or symbol
 *
 * Example 1:
 * Input: chars = ["a","a","b","b","c","c","c"]
 * Output: Return 6, and the first 6 characters of the input array should
 * be: ["a","2","b","2","c","3"]
 *
 * Example 2:
 * Input: chars = ["a"]
 * Output: Return 1, and the first character of the input array should be:
 * ["a"]
 *
 * Example 3:
 * Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 * Output: Return 4, and the first 4 characters of the input array should
 * be: ["a","b","1","2"]
 * Explanation: Since the character "b" occurs 12 times, group lengths of
 * 10 or longer are split into multiple characters, resulting in "b12" -> "b","1","2".
 *
 * Approach:
 * In-place read/write two-pointer. i tracks the compacted write position,
 * j scans ahead counting the length of the current run. When a run's
 * length is 1, its single character is copied forward from j to the
 * compacted position at i (needed whenever a prior run has already
 * shifted i behind the original scan position). When a run's length is
 * greater than 1, the run's character stays at i and the digit characters
 * of its length are written immediately after; the first character of the
 * next run is then copied back from j to the new i, "healing" the gap
 * created by compaction so the next iteration's comparison starts aligned.
 *
 * Time Complexity: O(n) — i and j each advance strictly forward and never
 * revisit a position, so total work across the scan is linear despite the
 * nested while loop.
 * Space Complexity: O(1) auxiliary — a few int/String locals only; the
 * digit string per run is bounded (at most 4 characters, since
 * chars.length <= 2000), and all writes happen in the input array itself.
 *
 * Key Learnings:
 * - The write-back copy (chars[i] = chars[j]) must happen for EVERY run,
 *   not just multi-length ones — an earlier version only performed it for
 *   the l > 1 branch (via a shared trailing step) while the l == 1 branch
 *   used `continue` to skip it, leaving stale pre-compaction data at i
 *   whenever a singleton run followed an already-compacted run.
 * - This class of bug is dangerous because the RETURNED LENGTH stays
 *   correct even when the array CONTENTS are wrong — a test that only
 *   checks length would pass while silently returning garbage data.
 *   Catching it requires a test where i has already fallen behind j
 *   (i.e., a run of length >= 2 occurred earlier) AND is immediately
 *   followed by a run of length 1.
 *
 * @see <a href="https://leetcode.com/problems/string-compression/description/">LeetCode Problem #443</a>
 */
public class StringCompression {

    /**
     * Compresses chars in place using run-length encoding and returns the
     * new logical length of the array.
     *
     * @param chars the input character array, modified in place
     * @return the number of characters in the compressed result
     */
    public int compress(char[] chars) {
        if(chars.length == 1){
            return 1;
        }
        String len;
        int i=0, j=1;
        for(; j<=chars.length;){
            int l = 1;
            //increment length and increment j
            while(j<chars.length && chars[i] == chars[j]){
                l++;
                j++;
            }
            //if length is 1 then move both i and j ahead to find next char length
            if(l==1){
                i++;
                if(i<chars.length && j<chars.length)
                    chars[i] = chars[j];
                j++;
                continue;
            } 
            //else if length is greater than 1 then add characters of length 
            //and keep incrementing pointer i 
            //finally new character replaces char at i and j checks the character ahead 
            //that is same positioning as the beginning
            else {
                int k=0;
                i++;
                len = String.valueOf(l);
                for(; k<len.length();k++){
                    chars[i] = len.charAt(k);
                    i++;
                }
                
            }
            if(j<chars.length){
                chars[i] = chars[j];
                j++;
            } else {
                break;
            }
        }
        return i;
    }

    /**
     * Helper method to run a single test case.
     *
     * @param testNum test case number
     * @param original a copy of the input array before compression (for display)
     * @param expectedLength expected returned length
     * @param expectedChars expected compressed characters (length == expectedLength)
     * @param actualLength actual returned length
     * @param actualChars the array after compress() was called (only the first
     *                     actualLength entries are meaningful)
     * @param description test case description
     */
    private void runTest(int testNum, char[] original, int expectedLength, char[] expectedChars,
                          int actualLength, char[] actualChars, String description) {
        char[] actualCompressed = actualLength >= 0 && actualLength <= actualChars.length
                ? Arrays.copyOf(actualChars, actualLength)
                : actualChars;
        boolean passed = expectedLength == actualLength && Arrays.equals(expectedChars, actualCompressed);
        String status = passed ? "✓ PASS" : "✗ FAIL";

        System.out.printf("Test %d: %s - %s%n", testNum, description, status);
        System.out.printf("  Input:    %s%n", charArrayToString(original));
        System.out.printf("  Expected: length=%d, %s%n", expectedLength, charArrayToString(expectedChars));
        System.out.printf("  Actual:   length=%d, %s%n", actualLength, charArrayToString(actualCompressed));
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
     * Converts a char array to a formatted string for display, truncating
     * long arrays.
     *
     * @param chars input array
     * @return formatted string representation
     */
    private String charArrayToString(char[] chars) {
        if (chars == null) return "null";
        if (chars.length > 20) {
            return "[" + chars[0] + ", " + chars[1] + ", ..., " +
                   chars[chars.length - 2] + ", " + chars[chars.length - 1] +
                   "] (length=" + chars.length + ")";
        }
        return Arrays.toString(chars);
    }

    /**
     * Main method with comprehensive test cases.
     */
    public static void main(String[] args) {
        StringCompression solution = new StringCompression();
        boolean[] results = new boolean[10];
        int testNum = 0;

        System.out.println("Testing String Compression");
        System.out.println("=".repeat(60));
        System.out.println();

        // Test 1: Example 1 - multiple groups, all length >= 2
        {
            char[] chars = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
            char[] original = Arrays.copyOf(chars, chars.length);
            int expectedLength = 6;
            char[] expectedChars = {'a', '2', 'b', '2', 'c', '3'};
            int actualLength = solution.compress(chars);
            solution.runTest(++testNum, original, expectedLength, expectedChars, actualLength, chars,
                           "Example 1 - multiple groups, all length >= 2");
            results[testNum - 1] = expectedLength == actualLength
                    && Arrays.equals(expectedChars, Arrays.copyOf(chars, Math.max(actualLength, 0)));
        }

        // Test 2: Example 2 - single character
        {
            char[] chars = {'a'};
            char[] original = Arrays.copyOf(chars, chars.length);
            int expectedLength = 1;
            char[] expectedChars = {'a'};
            int actualLength = solution.compress(chars);
            solution.runTest(++testNum, original, expectedLength, expectedChars, actualLength, chars,
                           "Example 2 - single character");
            results[testNum - 1] = expectedLength == actualLength
                    && Arrays.equals(expectedChars, Arrays.copyOf(chars, Math.max(actualLength, 0)));
        }

        // Test 3: Example 3 - group length rolls over into two digits (12 -> "1","2")
        {
            char[] chars = {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
            char[] original = Arrays.copyOf(chars, chars.length);
            int expectedLength = 4;
            char[] expectedChars = {'a', 'b', '1', '2'};
            int actualLength = solution.compress(chars);
            solution.runTest(++testNum, original, expectedLength, expectedChars, actualLength, chars,
                           "Example 3 - group length rolls over into two digits");
            results[testNum - 1] = expectedLength == actualLength
                    && Arrays.equals(expectedChars, Arrays.copyOf(chars, Math.max(actualLength, 0)));
        }

        // Test 4: All distinct characters - no compression possible
        {
            char[] chars = {'a', 'b', 'c', 'd'};
            char[] original = Arrays.copyOf(chars, chars.length);
            int expectedLength = 4;
            char[] expectedChars = {'a', 'b', 'c', 'd'};
            int actualLength = solution.compress(chars);
            solution.runTest(++testNum, original, expectedLength, expectedChars, actualLength, chars,
                           "All distinct characters - no compression possible");
            results[testNum - 1] = expectedLength == actualLength
                    && Arrays.equals(expectedChars, Arrays.copyOf(chars, Math.max(actualLength, 0)));
        }

        // Test 5: Exactly two distinct single-character groups
        {
            char[] chars = {'a', 'b'};
            char[] original = Arrays.copyOf(chars, chars.length);
            int expectedLength = 2;
            char[] expectedChars = {'a', 'b'};
            int actualLength = solution.compress(chars);
            solution.runTest(++testNum, original, expectedLength, expectedChars, actualLength, chars,
                           "Exactly two distinct single-character groups");
            results[testNum - 1] = expectedLength == actualLength
                    && Arrays.equals(expectedChars, Arrays.copyOf(chars, Math.max(actualLength, 0)));
        }

        // Test 6: Group length exactly 9 - stays single digit
        {
            char[] chars = new char[9];
            Arrays.fill(chars, 'a');
            char[] original = Arrays.copyOf(chars, chars.length);
            int expectedLength = 2;
            char[] expectedChars = {'a', '9'};
            int actualLength = solution.compress(chars);
            solution.runTest(++testNum, original, expectedLength, expectedChars, actualLength, chars,
                           "Group length exactly 9 - stays single digit");
            results[testNum - 1] = expectedLength == actualLength
                    && Arrays.equals(expectedChars, Arrays.copyOf(chars, Math.max(actualLength, 0)));
        }

        // Test 7: Group length exactly 10 - rolls over to two digits
        {
            char[] chars = new char[10];
            Arrays.fill(chars, 'a');
            char[] original = Arrays.copyOf(chars, chars.length);
            int expectedLength = 3;
            char[] expectedChars = {'a', '1', '0'};
            int actualLength = solution.compress(chars);
            solution.runTest(++testNum, original, expectedLength, expectedChars, actualLength, chars,
                           "Group length exactly 10 - rolls over to two digits");
            results[testNum - 1] = expectedLength == actualLength
                    && Arrays.equals(expectedChars, Arrays.copyOf(chars, Math.max(actualLength, 0)));
        }

        // Test 8: Digits and symbols as the characters themselves
        {
            char[] chars = {'1', '1', '2', '2', '2', '#', '#'};
            char[] original = Arrays.copyOf(chars, chars.length);
            int expectedLength = 6;
            char[] expectedChars = {'1', '2', '2', '3', '#', '2'};
            int actualLength = solution.compress(chars);
            solution.runTest(++testNum, original, expectedLength, expectedChars, actualLength, chars,
                           "Digits and symbols as the characters themselves");
            results[testNum - 1] = expectedLength == actualLength
                    && Arrays.equals(expectedChars, Arrays.copyOf(chars, Math.max(actualLength, 0)));
        }

        // Test 9: Mixed uppercase and lowercase treated as distinct characters
        {
            char[] chars = {'A', 'a', 'a', 'A', 'A'};
            char[] original = Arrays.copyOf(chars, chars.length);
            int expectedLength = 5;
            char[] expectedChars = {'A', 'a', '2', 'A', '2'};
            int actualLength = solution.compress(chars);
            solution.runTest(++testNum, original, expectedLength, expectedChars, actualLength, chars,
                           "Mixed uppercase and lowercase treated as distinct characters");
            results[testNum - 1] = expectedLength == actualLength
                    && Arrays.equals(expectedChars, Arrays.copyOf(chars, Math.max(actualLength, 0)));
        }

        // Test 10: Maximum constraint - single group of 2000 identical characters
        {
            char[] chars = new char[2000];
            Arrays.fill(chars, 'z');
            char[] original = Arrays.copyOf(chars, chars.length);
            int expectedLength = 5;
            char[] expectedChars = {'z', '2', '0', '0', '0'};
            int actualLength = solution.compress(chars);
            solution.runTest(++testNum, original, expectedLength, expectedChars, actualLength, chars,
                           "Maximum constraint - single group of 2000 identical characters");
            results[testNum - 1] = expectedLength == actualLength
                    && Arrays.equals(expectedChars, Arrays.copyOf(chars, Math.max(actualLength, 0)));
        }

        solution.printSummary(results);
    }
}
