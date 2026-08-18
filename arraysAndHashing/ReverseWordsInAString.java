package arraysAndHashing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * LeetCode Problem #151: Reverse Words in a String
 * Difficulty: Medium
 *
 * Problem Description:
 * Given an input string s, reverse the order of the words.
 *
 * A word is defined as a sequence of non-space characters. The words in s
 * will be separated by at least one space. Return a string of the words in
 * reverse order concatenated by a single space.
 *
 * Note that s may contain leading or trailing spaces or multiple spaces
 * between two words. The returned string should only have a single space
 * separating the words. Do not include any extra spaces.
 *
 * Constraints:
 * - 1 <= s.length <= 10^4
 * - s contains English letters (upper-case and lower-case), digits, and
 *   spaces ' '
 * - There is at least one word in s
 *
 * Example 1:
 * Input: s = "the sky is blue"
 * Output: "blue is sky the"
 *
 * Example 2:
 * Input: s = "  hello world  "
 * Output: "world hello"
 * Explanation: Your reversed string should not contain leading or trailing
 * spaces.
 *
 * Example 3:
 * Input: s = "a good   example"
 * Output: "example good a"
 * Explanation: You need to reduce multiple spaces between two words to a
 * single space in the reversed string.
 *
 * Follow-up:
 * If the string data type is mutable in your language, can you solve it
 * in-place with O(1) extra space?
 *
 * Approach:
 * [To be documented after implementation]
 *
 * Time Complexity: [To be analyzed]
 * Space Complexity: [To be analyzed]
 *
 * @see <a href="https://leetcode.com/problems/reverse-words-in-a-string/description/">LeetCode Problem #151</a>
 */
public class ReverseWordsInAString {

    /**
     * Reverses the order of words in the given string, collapsing any
     * leading, trailing, or multiple internal spaces to a single separator.
     *
     * @param s the input string
     * @return the words of s in reverse order, single-space separated
     */
    public String reverseWords(String s) {
        String[] split = s.split("\s+");
        StringBuilder sb = new StringBuilder();
        for(int i=split.length-1; i>=0; i--){
            sb.append(split[i]);
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    /**
     * Helper method to run a single test case.
     *
     * @param testNum test case number
     * @param input input string
     * @param expected expected result
     * @param actual actual result from solution
     * @param description test case description
     */
    private void runTest(int testNum, String input, String expected, String actual, String description) {
        boolean passed = expected.equals(actual);
        String status = passed ? "✓ PASS" : "✗ FAIL";

        System.out.printf("Test %d: %s - %s%n", testNum, description, status);
        System.out.printf("  Input:    \"%s\"%n", input);
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
        ReverseWordsInAString solution = new ReverseWordsInAString();
        boolean[] results = new boolean[10];
        int testNum = 0;

        System.out.println("Testing Reverse Words in a String");
        System.out.println("=".repeat(60));
        System.out.println();

        // Test 1: Example 1 - single spaces between words
        {
            String input = "the sky is blue";
            String expected = "blue is sky the";
            String actual = solution.reverseWords(input);
            solution.runTest(++testNum, input, expected, actual,
                           "Example 1 - single spaces between words");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 2: Example 2 - leading and trailing spaces
        {
            String input = "  hello world  ";
            String expected = "world hello";
            String actual = solution.reverseWords(input);
            solution.runTest(++testNum, input, expected, actual,
                           "Example 2 - leading and trailing spaces");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 3: Example 3 - multiple spaces between words
        {
            String input = "a good   example";
            String expected = "example good a";
            String actual = solution.reverseWords(input);
            solution.runTest(++testNum, input, expected, actual,
                           "Example 3 - multiple internal spaces collapsed");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 4: Single word, no spaces
        {
            String input = "hello";
            String expected = "hello";
            String actual = solution.reverseWords(input);
            solution.runTest(++testNum, input, expected, actual,
                           "Single word, no spaces");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 5: Single word surrounded by spaces
        {
            String input = "   hello   ";
            String expected = "hello";
            String actual = solution.reverseWords(input);
            solution.runTest(++testNum, input, expected, actual,
                           "Single word surrounded by spaces");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 6: Many words with irregular spacing
        {
            String input = "  this   is  a bunch of words  ";
            String expected = "words of bunch a is this";
            String actual = solution.reverseWords(input);
            solution.runTest(++testNum, input, expected, actual,
                           "Many words with irregular spacing");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 7: Two words
        {
            String input = "hi there";
            String expected = "there hi";
            String actual = solution.reverseWords(input);
            solution.runTest(++testNum, input, expected, actual,
                           "Two words");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 8: Words containing digits
        {
            String input = "a1 b2 c3";
            String expected = "c3 b2 a1";
            String actual = solution.reverseWords(input);
            solution.runTest(++testNum, input, expected, actual,
                           "Words containing digits");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 9: Minimum constraint - single character
        {
            String input = "a";
            String expected = "a";
            String actual = solution.reverseWords(input);
            solution.runTest(++testNum, input, expected, actual,
                           "Minimum constraint - single character");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 10: Large input with many words and irregular spacing
        {
            List<String> words = new ArrayList<>();
            for (int i = 0; i < 500; i++) {
                words.add("word" + i);
            }
            String input = "  " + String.join("   ", words) + "  ";
            List<String> reversedWords = new ArrayList<>(words);
            Collections.reverse(reversedWords);
            String expected = String.join(" ", reversedWords);
            String actual = solution.reverseWords(input);
            solution.runTest(++testNum, "500 words with irregular spacing (truncated)",
                           expected.substring(0, Math.min(50, expected.length())) + "...",
                           actual == null ? "null" : actual.substring(0, Math.min(50, actual.length())) + "...",
                           "Large input with many words and irregular spacing");
            results[testNum - 1] = expected.equals(actual);
        }

        solution.printSummary(results);
    }
}
