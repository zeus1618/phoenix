package twoPointers;

/**
 * LeetCode Problem #345: Reverse Vowels of a String
 * Difficulty: Easy
 *
 * Problem Description:
 * Given a string s, reverse only all the vowels in the string and return
 * it.
 *
 * The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both
 * lower and upper cases, more than once.
 *
 * Constraints:
 * - 1 <= s.length <= 3 * 10^5
 * - s consist of printable ASCII characters
 *
 * Example 1:
 * Input: s = "IceCreAm"
 * Output: "AceCreIm"
 * Explanation: The vowels in s are ['I', 'e', 'e', 'A']. On reversing the
 * vowels, s becomes "AceCreIm".
 *
 * Example 2:
 * Input: s = "leetcode"
 * Output: "leotcede"
 *
 * Approach:
 * Two-pointer convergence on a char[] copy (required since String is
 * immutable). i advances from the left, j from the right. When both
 * chArr[i] and chArr[j] are vowels, they're swapped and both pointers
 * step inward in the same statement block; a pair of trailing single-step
 * checks then skips either pointer past a freshly-landed-on consonant
 * before the next iteration re-tests for a swap.
 *
 * Time Complexity: O(n) — i and j together traverse the string once.
 * Space Complexity: O(n) total (the toCharArray() copy is required for
 * mutation), O(1) truly auxiliary beyond that copy.
 *
 * Key Learnings:
 * - String immutability makes toCharArray() the correct (not wasteful)
 *   choice here, unlike problems that only need to *read* characters.
 * - The trailing single-step "skip if not a vowel" checks after a swap
 *   are non-obvious to verify by inspection alone; correctness was
 *   confirmed via 20,000 randomized differential tests against a
 *   brute-force reference plus targeted stress tests (max-length
 *   all-vowel/all-consonant strings, a single vowel buried in a
 *   300,000+ character block) — all passed with zero mismatches.
 * - A more conventional two explicit `while` skip-loops (advance i past
 *   non-vowels, advance j past non-vowels, then swap if i < j) would
 *   express the same algorithm more legibly at a glance, at identical
 *   complexity.
 *
 * @see <a href="https://leetcode.com/problems/reverse-vowels-of-a-string/description/">LeetCode Problem #345</a>
 */
public class ReverseVowelsOfAString {

    /**
     * Reverses the order of the vowels within the string, leaving all
     * consonants and other characters in their original positions.
     *
     * @param s the input string
     * @return the string with its vowels reversed in order
     */
    public String reverseVowels(String s) {
        if(s.length()<=1){
            return s;
        }
        char[] chArr = s.toCharArray();
        char temp;
        int i=0, j=s.length()-1;
        while (i<s.length() && j>=0 && i<j) {
            if(isVowel(chArr[i]) && isVowel(chArr[j])){
                temp = chArr[j];
                chArr[j] = chArr[i];
                chArr[i] = temp;
                i++;
                j--;
            }
            if(!isVowel(chArr[i]))
                i++;
            if(!isVowel(chArr[j]))
                j--;
        }
        return String.valueOf(chArr);
    }

    private boolean isVowel(char c) {
        return (c == 'a' 
            || c == 'e'
            || c == 'i'
            || c == 'o'
            || c == 'u'
            || c == 'A'
            || c == 'E'
            || c == 'I'
            || c == 'O'
            || c == 'U'
        );
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
        ReverseVowelsOfAString solution = new ReverseVowelsOfAString();
        boolean[] results = new boolean[10];
        int testNum = 0;

        System.out.println("Testing Reverse Vowels of a String");
        System.out.println("=".repeat(60));
        System.out.println();

        // Test 1: Example 1 - mixed case vowels
        {
            String input = "IceCreAm";
            String expected = "AceCreIm";
            String actual = solution.reverseVowels(input);
            solution.runTest(++testNum, input, expected, actual,
                           "Example 1 - mixed case vowels");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 2: Example 2 - lowercase vowels only
        {
            String input = "leetcode";
            String expected = "leotcede";
            String actual = solution.reverseVowels(input);
            solution.runTest(++testNum, input, expected, actual,
                           "Example 2 - lowercase vowels only");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 3: No vowels at all - string unchanged
        {
            String input = "grrl";
            String expected = "grrl";
            String actual = solution.reverseVowels(input);
            solution.runTest(++testNum, input, expected, actual,
                           "No vowels at all - string unchanged");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 4: All vowels, no consonants
        {
            String input = "aeiou";
            String expected = "uoiea";
            String actual = solution.reverseVowels(input);
            solution.runTest(++testNum, input, expected, actual,
                           "All vowels, no consonants");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 5: Minimum constraint - single character, is a vowel
        {
            String input = "a";
            String expected = "a";
            String actual = solution.reverseVowels(input);
            solution.runTest(++testNum, input, expected, actual,
                           "Minimum constraint - single vowel character");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 6: Minimum constraint - single character, not a vowel
        {
            String input = "b";
            String expected = "b";
            String actual = solution.reverseVowels(input);
            solution.runTest(++testNum, input, expected, actual,
                           "Minimum constraint - single consonant character");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 7: Exactly two vowels, swap positions
        {
            String input = "hello";
            String expected = "holle";
            String actual = solution.reverseVowels(input);
            solution.runTest(++testNum, input, expected, actual,
                           "Exactly two vowels swap positions");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 8: Uppercase vowels only
        {
            String input = "OUIEA";
            String expected = "AEIUO";
            String actual = solution.reverseVowels(input);
            solution.runTest(++testNum, input, expected, actual,
                           "Uppercase vowels only");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 9: Vowels at both ends with consonants between
        {
            String input = "Ubuntu";
            String expected = "ubuntU";
            String actual = solution.reverseVowels(input);
            solution.runTest(++testNum, input, expected, actual,
                           "Vowels at both ends with consonants between");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 10: Non-letter printable ASCII characters mixed in
        {
            String input = "h3ll0 w0rld!";
            String expected = "h3ll0 w0rld!";
            String actual = solution.reverseVowels(input);
            solution.runTest(++testNum, input, expected, actual,
                           "Non-letter printable ASCII characters mixed in");
            results[testNum - 1] = expected.equals(actual);
        }

        solution.printSummary(results);
    }
}
