package twoPointers;

import java.util.*;

/**
 * LeetCode Problem #11: Container With Most Water
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/container-with-most-water/
 *
 * <p>Problem Description:
 * You are given an integer array height of length n. There are n vertical
 * lines drawn such that the two endpoints of the ith line are (i, 0) and
 * (i, height[i]).
 *
 * <p>Find two lines that together with the x-axis form a container, such
 * that the container contains the most water.
 *
 * <p>Return the maximum amount of water a container can store.
 *
 * <p>Notice that you may not slant the container.
 *
 * <p>Constraints:
 * - n == height.length
 * - 2 <= n <= 10^5
 * - 0 <= height[i] <= 10^4
 *
 * <p>Examples:
 * Example 1:
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The vertical lines are represented by the array
 * [1,8,6,2,5,4,8,3,7]. In this case, the max area of water the container
 * can contain is 49 (between index 1 and index 8, min(8,7) * (8-1) = 49).
 *
 * Example 2:
 * Input: height = [1,1]
 * Output: 1
 *
 * <p>Approach:
 * Converging two pointers. Start with {@code l} at the left end and
 * {@code r} at the right end, and track the best area seen so far. On
 * each iteration, compute the area for the current pair, then advance
 * past whichever wall(s) are less than or equal to the current
 * {@code minHeight} — the shorter wall is always the bottleneck, so any
 * position at or below it can never beat the area already recorded and is
 * safe to skip, even when this advances both pointers past several
 * positions (or both sides at once, on a tie) in a single iteration.
 *
 * <p>An earlier attempt ({@link #maxAreaOld}) used brute force with a
 * pruning heuristic based on the running best area; it was correct but
 * degraded to O(n^2) on adversarial inputs (e.g. strictly decreasing
 * heights). It's kept here for comparison but is no longer exercised by
 * the tests below.
 *
 * <p>Time Complexity: O(n) — each pointer only ever advances toward the
 * other, so total pointer movement across the whole run is bounded by n.
 * <p>Space Complexity: O(1) — two pointers and a running max, no
 * auxiliary data structures.
 */
public class ContainerWithMostWater {

    /**
     * Finds the maximum amount of water a container formed by two lines
     * from the height array (and the x-axis) can hold.
     *
     * @param height array where height[i] is the height of the ith line
     * @return the maximum container area
     */
    public int maxArea(int[] height) {
        int mArea = 0;
        int l = 0, r=height.length-1;
        int minHeight=0;
        while (l<r) {
            minHeight = Math.min(height[l], height[r]);
            mArea = Math.max(mArea, minHeight * (r-l));

            while (l<r && minHeight>=height[l]) {
                l++;
            }
            while (l<r && minHeight>=height[r]) {
                r--;
            }
        }

        return mArea;
    }

    /**
     * Earlier attempt: brute force with a pruning heuristic based on the
     * running best area. Correct, but O(n^2) worst case (e.g. strictly
     * decreasing heights) — superseded by {@link #maxArea}. Kept for
     * comparison; not called by the tests below.
     */
    public int maxAreaOld(int[] height) {
        int mArea = 0;
        for(int i=0; i<height.length; i++){
            if(height[i]==0){
                continue;
            }
            int j = i + mArea/height[i]+1;
            for(; j<height.length; j++){
                mArea = Math.max(mArea, getArea(height[i], height[j], j-i));
            }
        }
        return mArea;
    }

    public int getArea(int h1, int h2, int distance){
        return distance * (Math.min(h1, h2));
    }

    // ======================== Test Helper Methods ========================

    /**
     * Runs a single test case and prints the result.
     *
     * @param testNum the test case number
     * @param height the input array
     * @param expected the expected result
     * @param actual the actual result from the solution
     * @param description brief description of the test case
     */
    private static void runTest(
            int testNum,
            int[] height,
            int expected,
            int actual,
            String description) {

        boolean passed = expected == actual;
        String status = passed ? "✓ PASS" : "✗ FAIL";

        System.out.println("\n" + status + " - Test " + testNum + ": " + description);
        System.out.println("  Input:    height = " + Arrays.toString(height));
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
        System.out.println("Testing LeetCode #11: Container With Most Water");
        System.out.println("=".repeat(70));

        ContainerWithMostWater solution = new ContainerWithMostWater();

        // Test 1: Example 1
        {
            int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
            int expected = 49;
            int actual = solution.maxArea(height);
            runTest(++totalTests, height, expected, actual,
                    "Example 1 - classic case");
            if (expected == actual) passedTests++;
        }

        // Test 2: Example 2 - minimum constraint, both height 1
        {
            int[] height = {1, 1};
            int expected = 1;
            int actual = solution.maxArea(height);
            runTest(++totalTests, height, expected, actual,
                    "Example 2 - minimum length, equal small heights");
            if (expected == actual) passedTests++;
        }

        // Test 3: Two elements, different heights
        {
            int[] height = {4, 9};
            int expected = 4;
            int actual = solution.maxArea(height);
            runTest(++totalTests, height, expected, actual,
                    "Two elements - different heights, width 1");
            if (expected == actual) passedTests++;
        }

        // Test 4: All same height - widest pair wins
        {
            int[] height = {5, 5, 5, 5, 5};
            int expected = 20;
            int actual = solution.maxArea(height);
            runTest(++totalTests, height, expected, actual,
                    "All same height - widest pair is optimal");
            if (expected == actual) passedTests++;
        }

        // Test 5: Strictly increasing heights
        {
            int[] height = {1, 2, 3, 4, 5};
            int expected = 6;
            int actual = solution.maxArea(height);
            runTest(++totalTests, height, expected, actual,
                    "Strictly increasing heights");
            if (expected == actual) passedTests++;
        }

        // Test 6: Strictly decreasing heights
        {
            int[] height = {5, 4, 3, 2, 1};
            int expected = 6;
            int actual = solution.maxArea(height);
            runTest(++totalTests, height, expected, actual,
                    "Strictly decreasing heights");
            if (expected == actual) passedTests++;
        }

        // Test 7: Tallest lines at both ends
        {
            int[] height = {9, 1, 1, 1, 1, 9};
            int expected = 45;
            int actual = solution.maxArea(height);
            runTest(++totalTests, height, expected, actual,
                    "Tallest lines at both ends - full width wins");
            if (expected == actual) passedTests++;
        }

        // Test 8: Tallest lines in the middle
        {
            int[] height = {1, 9, 9, 1};
            int expected = 9;
            int actual = solution.maxArea(height);
            runTest(++totalTests, height, expected, actual,
                    "Tallest lines adjacent in the middle");
            if (expected == actual) passedTests++;
        }

        // Test 9: Contains zero-height lines
        {
            int[] height = {0, 2, 0, 4, 0};
            int expected = 4;
            int actual = solution.maxArea(height);
            runTest(++totalTests, height, expected, actual,
                    "Array contains zero-height lines");
            if (expected == actual) passedTests++;
        }

        // Test 10: All zero heights - no water can be held
        {
            int[] height = {0, 0, 0, 0};
            int expected = 0;
            int actual = solution.maxArea(height);
            runTest(++totalTests, height, expected, actual,
                    "All zero heights - zero area");
            if (expected == actual) passedTests++;
        }

        // Test 11: One very tall line among short ones
        {
            int[] height = {1, 1, 1, 1, 10000};
            int expected = 4;
            int actual = solution.maxArea(height);
            runTest(++totalTests, height, expected, actual,
                    "One very tall line - limited by the short partner");
            if (expected == actual) passedTests++;
        }

        // Test 12: Boundary max height values
        {
            int[] height = {10000, 10000};
            int expected = 10000;
            int actual = solution.maxArea(height);
            runTest(++totalTests, height, expected, actual,
                    "Boundary - max height constraint on both lines");
            if (expected == actual) passedTests++;
        }

        // Test 13: Symmetric V-shape
        {
            int[] height = {6, 4, 2, 0, 2, 4, 6};
            int expected = 36;
            int actual = solution.maxArea(height);
            runTest(++totalTests, height, expected, actual,
                    "Symmetric V-shape - ends form the widest container");
            if (expected == actual) passedTests++;
        }

        printSummary(totalTests, passedTests);
    }
}
