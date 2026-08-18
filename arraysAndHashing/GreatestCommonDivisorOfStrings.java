package arraysAndHashing;

/**
 * LeetCode Problem #1071: Greatest Common Divisor of Strings
 * Difficulty: Easy
 *
 * Problem Description:
 * For two strings s and t, we say "t divides s" if and only if
 * s = t + t + ... + t (t concatenated with itself one or more times).
 *
 * Given two strings str1 and str2, return the largest string x such that x
 * divides both str1 and str2.
 *
 * Constraints:
 * - 1 <= str1.length, str2.length <= 1000
 * - str1 and str2 consist of English uppercase letters
 *
 * Example 1:
 * Input: str1 = "ABCABC", str2 = "ABC"
 * Output: "ABC"
 *
 * Example 2:
 * Input: str1 = "ABABAB", str2 = "ABAB"
 * Output: "AB"
 *
 * Example 3:
 * Input: str1 = "LEET", str2 = "CODE"
 * Output: ""
 *
 * Approach:
 * Concatenation-commutativity check + Euclidean GCD. A common divisor string
 * x can only exist if str1 and str2 commute under concatenation, i.e.
 * str1 + str2 equals str2 + str1 — this is the necessary and sufficient
 * condition (a consequence of the string periodicity / Fine-Wilf theorem).
 * When that holds, the length of the *greatest* such x is always exactly
 * gcd(str1.length(), str2.length()) — no candidate search is needed, the
 * answer is simply str1.substring(0, gcd).
 *
 * Time Complexity: O(n + m) — dominated by building and comparing the two
 * concatenated strings; the recursive Euclidean gcd() call is only
 * O(log(min(n, m))).
 * Space Complexity: O(n + m) — the two concatenated strings, plus
 * O(log(min(n, m))) recursion stack for gcd(), plus O(gcd) for the output.
 *
 * Key Learnings:
 * - The concatenation check is both necessary AND sufficient — no need to
 *   verify candidate lengths individually once it passes.
 * - When a common divisor exists, its length is uniquely gcd(n, m); there
 *   is never more than one valid answer length to search among.
 *
 * Detailed Learning Guide: See arraysAndHashing/learnings/GreatestCommonDivisorOfStrings-Learning.md
 *
 * @see <a href="https://leetcode.com/problems/greatest-common-divisor-of-strings/description/">LeetCode Problem #1071</a>
 */
public class GreatestCommonDivisorOfStrings {

    /**
     * Finds the largest string that divides both str1 and str2.
     *
     * @param str1 the first string
     * @param str2 the second string
     * @return the largest string x such that x divides both str1 and str2,
     *         or an empty string if no such x exists
     */

    public String gcdOfStrings(String str1, String str2) {
        // Check if concatenated strings are equal or not, if not return ""
        if (!(str1 + str2).equals(str2 + str1))
            return "";
        // If strings are equal than return the substring from 0 to gcd of size(str1), size(str2)
        int gcd = gcd(str1.length(), str2.length());
        return str1.substring(0, gcd);
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public String gcdOfStringOld(String str1, String str2) {
        int index = Math.min(str1.length(), str2.length());
        String shorterString = str1.length() > str2.length() ? str2 : str1;
        String gcdCandidate = shorterString;
        while (true) {
            gcdCandidate = shorterString.substring(0, index);
            System.out.println("current gcdCandidate : " + gcdCandidate);

            if(str1.split(gcdCandidate).length == 0 && str2.split(gcdCandidate).length == 0){
                System.out.println("gcdFound : " + gcdCandidate);
                return gcdCandidate;
            } else {
                if(index % 2 != 0){
                    System.out.println("GCD can't exist");
                    break;
                } else {
                    index = index/2;
                    System.out.println("gcd Not Found dividing index : " + index);
                }
            }
        }
        return "";
    }

    public String lcdOfStrings(String str1, String str2) {
        int index = 0;
        String lcdCandidate = "";
        while (true) {
            if(index >= str1.length() || index >= str2.length()){
                System.out.println("Breaking due to index exceeded length, index : " + index);
                break;
            }
            if(str1.charAt(index) == str2.charAt(index)){
                lcdCandidate = str1.substring(0, index+1);
                System.out.println("current lcdCandidate : " + lcdCandidate);
                if(str1.split(lcdCandidate).length == 0 && str2.split(lcdCandidate).length == 0){
                    System.out.println("lcdFound : " + lcdCandidate);
                    return lcdCandidate;
                } else {
                    System.out.println("lcd Not Found increasing index : " + index);
                    index++;
                }
            } else {
                System.out.println("Character mismatched - No LCD");
                return "";
            }
        }
        return "";
    }

    /**
     * Helper method to run a single test case.
     *
     * @param testNum test case number
     * @param str1 first input string
     * @param str2 second input string
     * @param expected expected result
     * @param actual actual result from solution
     * @param description test case description
     */
    private void runTest(int testNum, String str1, String str2, String expected, String actual, String description) {
        boolean passed = expected.equals(actual);
        String status = passed ? "✓ PASS" : "✗ FAIL";

        System.out.printf("Test %d: %s - %s%n", testNum, description, status);
        System.out.printf("  Input:    str1=\"%s\", str2=\"%s\"%n", str1, str2);
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
        GreatestCommonDivisorOfStrings solution = new GreatestCommonDivisorOfStrings();
        boolean[] results = new boolean[10];
        int testNum = 0;

        System.out.println("Testing Greatest Common Divisor of Strings");
        System.out.println("=".repeat(60));
        System.out.println();

        // Test 1: Example 1 - str2 divides str1 exactly
        {
            String str1 = "ABCABC", str2 = "ABC";
            String expected = "ABC";
            String actual = solution.gcdOfStrings(str1, str2);
            solution.runTest(++testNum, str1, str2, expected, actual,
                           "Example 1 - str2 divides str1 exactly");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 2: Example 2 - common divisor shorter than either string
        {
            String str1 = "ABABAB", str2 = "ABAB";
            String expected = "AB";
            String actual = solution.gcdOfStrings(str1, str2);
            solution.runTest(++testNum, str1, str2, expected, actual,
                           "Example 2 - common divisor shorter than either string");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 3: Example 3 - no common divisor at all
        {
            String str1 = "LEET", str2 = "CODE";
            String expected = "";
            String actual = solution.gcdOfStrings(str1, str2);
            solution.runTest(++testNum, str1, str2, expected, actual,
                           "Example 3 - no common divisor");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 4: Example 4 - gcd(length) exists but strings don't actually divide
        {
            String str1 = "AAAAAB", str2 = "AAA";
            String expected = "";
            String actual = solution.gcdOfStrings(str1, str2);
            solution.runTest(++testNum, str1, str2, expected, actual,
                           "gcd(length) exists but strings don't divide evenly");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 5: Identical strings
        {
            String str1 = "ABC", str2 = "ABC";
            String expected = "ABC";
            String actual = solution.gcdOfStrings(str1, str2);
            solution.runTest(++testNum, str1, str2, expected, actual,
                           "Identical strings");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 6: Common divisor shorter than both strings
        {
            String str1 = "ABCDABCD", str2 = "ABCDABCDABCD";
            String expected = "ABCD";
            String actual = solution.gcdOfStrings(str1, str2);
            solution.runTest(++testNum, str1, str2, expected, actual,
                           "Common divisor shorter than both strings (gcd(8,12)=4)");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 7: Single character each, matching
        {
            String str1 = "A", str2 = "A";
            String expected = "A";
            String actual = solution.gcdOfStrings(str1, str2);
            solution.runTest(++testNum, str1, str2, expected, actual,
                           "Single character each, matching");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 8: Single character each, mismatched
        {
            String str1 = "A", str2 = "B";
            String expected = "";
            String actual = solution.gcdOfStrings(str1, str2);
            solution.runTest(++testNum, str1, str2, expected, actual,
                           "Single character each, mismatched");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 9: Same characters, different arrangement - not a valid divisor pair
        {
            String str1 = "AB", str2 = "BA";
            String expected = "";
            String actual = solution.gcdOfStrings(str1, str2);
            solution.runTest(++testNum, str1, str2, expected, actual,
                           "Same characters, different arrangement");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 10: Maximum constraint - long repeated strings
        {
            String str1 = "A".repeat(1000), str2 = "A".repeat(500);
            String expected = "A".repeat(500);
            String actual = solution.gcdOfStrings(str1, str2);
            solution.runTest(++testNum, "A*1000", "A*500", expected, actual,
                           "Maximum constraint - long repeated strings");
            results[testNum - 1] = expected.equals(actual);
        }

        solution.printSummary(results);
    }
}
