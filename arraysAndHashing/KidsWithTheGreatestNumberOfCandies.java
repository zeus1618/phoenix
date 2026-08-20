package arraysAndHashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * LeetCode Problem #1431: Kids With the Greatest Number of Candies
 * Difficulty: Easy
 *
 * Problem Description:
 * There are n kids with candies. You are given an integer array candies,
 * where each candies[i] represents the number of candies the ith kid has,
 * and an integer extraCandies, denoting the number of extra candies that
 * you have.
 *
 * Return a boolean array result of length n, where result[i] is true if,
 * after giving the ith kid all the extraCandies, they will have the
 * greatest number of candies among all the kids, or false otherwise.
 *
 * Note that multiple kids can have the greatest number of candies.
 *
 * Constraints:
 * - 2 <= candies.length <= 100
 * - 1 <= candies[i] <= 100
 * - 1 <= extraCandies <= 50
 *
 * Example 1:
 * Input: candies = [2,3,5,1,3], extraCandies = 3
 * Output: [true,true,true,false,true]
 *
 * Example 2:
 * Input: candies = [4,2,1,1,2], extraCandies = 1
 * Output: [true,false,false,false,false]
 *
 * Example 3:
 * Input: candies = [12,1,12], extraCandies = 10
 * Output: [true,false,true]
 *
 * Approach:
 * [To be documented after implementation]
 *
 * Time Complexity: [To be analyzed]
 * Space Complexity: [To be analyzed]
 *
 * @see <a href="https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/description/">LeetCode Problem #1431</a>
 */
public class KidsWithTheGreatestNumberOfCandies {

    /**
     * Determines for each kid whether giving them all the extra candies
     * would make their total the greatest (or tied for greatest) among
     * all kids.
     *
     * @param candies the number of candies each kid currently has
     * @param extraCandies the number of extra candies available to give
     * @return a list where result.get(i) is true if kid i could have
     *         the greatest number of candies after receiving extraCandies
     */
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int maxCandies = 0;
        List<Boolean> kidsWithGreatestNumberOfCandies = new ArrayList<>(candies.length);
        for (int kidCandies : candies) {
            maxCandies = Math.max(kidCandies, maxCandies);
        }
        for(int i=0; i<candies.length; i++) {
            kidsWithGreatestNumberOfCandies.add(candies[i] + extraCandies >= maxCandies);
        }
        return kidsWithGreatestNumberOfCandies;
    }

    /**
     * Helper method to run a single test case.
     *
     * @param testNum test case number
     * @param candies input candies array
     * @param extraCandies input extra candies count
     * @param expected expected result
     * @param actual actual result from solution
     * @param description test case description
     */
    private void runTest(int testNum, int[] candies, int extraCandies, List<Boolean> expected, List<Boolean> actual, String description) {
        boolean passed = expected.equals(actual);
        String status = passed ? "✓ PASS" : "✗ FAIL";

        System.out.printf("Test %d: %s - %s%n", testNum, description, status);
        System.out.printf("  Input:    candies=%s, extraCandies=%d%n", Arrays.toString(candies), extraCandies);
        System.out.printf("  Expected: %s%n", expected);
        System.out.printf("  Actual:   %s%n", actual);
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
        KidsWithTheGreatestNumberOfCandies solution = new KidsWithTheGreatestNumberOfCandies();
        boolean[] results = new boolean[10];
        int testNum = 0;

        System.out.println("Testing Kids With the Greatest Number of Candies");
        System.out.println("=".repeat(60));
        System.out.println();

        // Test 1: Example 1
        {
            int[] candies = {2, 3, 5, 1, 3};
            int extraCandies = 3;
            List<Boolean> expected = Arrays.asList(true, true, true, false, true);
            List<Boolean> actual = solution.kidsWithCandies(candies, extraCandies);
            solution.runTest(++testNum, candies, extraCandies, expected, actual,
                           "Example 1");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 2: Example 2
        {
            int[] candies = {4, 2, 1, 1, 2};
            int extraCandies = 1;
            List<Boolean> expected = Arrays.asList(true, false, false, false, false);
            List<Boolean> actual = solution.kidsWithCandies(candies, extraCandies);
            solution.runTest(++testNum, candies, extraCandies, expected, actual,
                           "Example 2");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 3: Example 3
        {
            int[] candies = {12, 1, 12};
            int extraCandies = 10;
            List<Boolean> expected = Arrays.asList(true, false, true);
            List<Boolean> actual = solution.kidsWithCandies(candies, extraCandies);
            solution.runTest(++testNum, candies, extraCandies, expected, actual,
                           "Example 3");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 4: All kids already tied for max
        {
            int[] candies = {5, 5, 5};
            int extraCandies = 1;
            List<Boolean> expected = Arrays.asList(true, true, true);
            List<Boolean> actual = solution.kidsWithCandies(candies, extraCandies);
            solution.runTest(++testNum, candies, extraCandies, expected, actual,
                           "All kids already tied for max");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 5: Minimum constraint - two kids
        {
            int[] candies = {1, 5};
            int extraCandies = 1;
            List<Boolean> expected = Arrays.asList(false, true);
            List<Boolean> actual = solution.kidsWithCandies(candies, extraCandies);
            solution.runTest(++testNum, candies, extraCandies, expected, actual,
                           "Minimum constraint - two kids");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 6: Only the current max stays true, extra insufficient for the rest
        {
            int[] candies = {10, 1, 1, 1};
            int extraCandies = 1;
            List<Boolean> expected = Arrays.asList(true, false, false, false);
            List<Boolean> actual = solution.kidsWithCandies(candies, extraCandies);
            solution.runTest(++testNum, candies, extraCandies, expected, actual,
                           "Only the current max stays true");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 7: Maximum extraCandies makes every kid true
        {
            int[] candies = {1, 2, 3};
            int extraCandies = 50;
            List<Boolean> expected = Arrays.asList(true, true, true);
            List<Boolean> actual = solution.kidsWithCandies(candies, extraCandies);
            solution.runTest(++testNum, candies, extraCandies, expected, actual,
                           "Maximum extraCandies makes every kid true");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 8: Boundary - extra exactly ties the max (still counts as true)
        {
            int[] candies = {5, 3};
            int extraCandies = 2;
            List<Boolean> expected = Arrays.asList(true, true);
            List<Boolean> actual = solution.kidsWithCandies(candies, extraCandies);
            solution.runTest(++testNum, candies, extraCandies, expected, actual,
                           "Boundary - extra exactly ties the max");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 9: Boundary - extra one short of tying the max
        {
            int[] candies = {5, 3};
            int extraCandies = 1;
            List<Boolean> expected = Arrays.asList(true, false);
            List<Boolean> actual = solution.kidsWithCandies(candies, extraCandies);
            solution.runTest(++testNum, candies, extraCandies, expected, actual,
                           "Boundary - extra one short of tying the max");
            results[testNum - 1] = expected.equals(actual);
        }

        // Test 10: Maximum constraint - 100 kids, uniform candy count
        {
            int[] candies = new int[100];
            Arrays.fill(candies, 50);
            int extraCandies = 1;
            List<Boolean> expected = Collections.nCopies(100, true);
            List<Boolean> actual = solution.kidsWithCandies(candies, extraCandies);
            solution.runTest(++testNum, candies, extraCandies, expected, actual,
                           "Maximum constraint - 100 kids, uniform candy count");
            results[testNum - 1] = expected.equals(actual);
        }

        solution.printSummary(results);
    }
}
