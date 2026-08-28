package twoPointers;

import java.util.*;

/**
 * LeetCode Problem #283: Move Zeroes
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/move-zeroes/
 *
 * <p>Problem Description:
 * Given an integer array nums, move all 0's to the end of it while maintaining
 * the relative order of the non-zero elements.
 *
 * <p>Note that you must do this in-place without making a copy of the array.
 *
 * <p>Constraints:
 * - 1 <= nums.length <= 10^4
 * - -2^31 <= nums[i] <= 2^31 - 1
 *
 * <p>Follow-up:
 * Could you minimize the total number of operations done?
 *
 * <p>Examples:
 * Example 1:
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 *
 * Example 2:
 * Input: nums = [0]
 * Output: [0]
 *
 * <p>Approach:
 * In-place read/write two-pointer swap. {@code noZeroIndex} trails the scan
 * pointer {@code i} and marks the next slot to place a non-zero value.
 * Whenever {@code nums[i]} is non-zero, it's swapped into
 * {@code nums[noZeroIndex]} (a no-op when the two indices coincide) and
 * {@code noZeroIndex} advances; zeroes are never written explicitly — they
 * end up trailing because every non-zero value gets pulled in front of them.
 *
 * <p>Time Complexity: O(n) — single pass over nums.
 * <p>Space Complexity: O(1) — in-place, no auxiliary array.
 */
public class MoveZeroes {

    /**
     * Moves all zeroes in the array to the end while preserving the relative
     * order of the non-zero elements, in-place.
     *
     * @param nums the input array to modify in-place
     */
    public void moveZeroes(int[] nums) {
        int noZeroIndex = 0;
        int temp=0;
        for(int i=0; i<nums.length; i++) {
            if(nums[i] != 0){
                temp = nums[i];
                nums[i] = nums[noZeroIndex];
                nums[noZeroIndex] = temp;
                noZeroIndex++;
            }
        }
    }

    // ======================== Test Helper Methods ========================

    /**
     * Runs a single test case and prints the result.
     *
     * @param testNum the test case number
     * @param nums the input array (will be modified)
     * @param expected the expected output array
     * @param description brief description of the test case
     */
    private static void runTest(
            int testNum,
            int[] nums,
            int[] expected,
            String description) {

        int[] original = Arrays.copyOf(nums, nums.length);
        MoveZeroes solution = new MoveZeroes();
        solution.moveZeroes(nums);

        boolean passed = Arrays.equals(expected, nums);
        String status = passed ? "✓ PASS" : "✗ FAIL";

        System.out.println("\n" + status + " - Test " + testNum + ": " + description);
        System.out.println("  Input:    nums = " + Arrays.toString(original));
        System.out.println("  Expected: " + Arrays.toString(expected));
        System.out.println("  Actual:   " + Arrays.toString(nums));

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
        System.out.println("Testing LeetCode #283: Move Zeroes");
        System.out.println("=".repeat(70));

        // Test 1: Example 1
        {
            int[] nums = {0, 1, 0, 3, 12};
            int[] expected = {1, 3, 12, 0, 0};
            runTest(++totalTests, nums, expected,
                    "Example 1 - zeroes scattered throughout");
            if (Arrays.equals(expected, nums)) passedTests++;
        }

        // Test 2: Example 2 - single zero
        {
            int[] nums = {0};
            int[] expected = {0};
            runTest(++totalTests, nums, expected,
                    "Example 2 - single element, is zero");
            if (Arrays.equals(expected, nums)) passedTests++;
        }

        // Test 3: Single non-zero element
        {
            int[] nums = {7};
            int[] expected = {7};
            runTest(++totalTests, nums, expected,
                    "Minimum constraint - single non-zero element");
            if (Arrays.equals(expected, nums)) passedTests++;
        }

        // Test 4: No zeroes at all - order unchanged
        {
            int[] nums = {4, 2, 9, 1, 5};
            int[] expected = {4, 2, 9, 1, 5};
            runTest(++totalTests, nums, expected,
                    "No zeroes at all - array unchanged");
            if (Arrays.equals(expected, nums)) passedTests++;
        }

        // Test 5: All zeroes
        {
            int[] nums = {0, 0, 0, 0};
            int[] expected = {0, 0, 0, 0};
            runTest(++totalTests, nums, expected,
                    "All zeroes");
            if (Arrays.equals(expected, nums)) passedTests++;
        }

        // Test 6: Zeroes already at the end
        {
            int[] nums = {1, 2, 3, 0, 0};
            int[] expected = {1, 2, 3, 0, 0};
            runTest(++totalTests, nums, expected,
                    "Zeroes already at the end");
            if (Arrays.equals(expected, nums)) passedTests++;
        }

        // Test 7: Zeroes at the beginning
        {
            int[] nums = {0, 0, 1, 2, 3};
            int[] expected = {1, 2, 3, 0, 0};
            runTest(++totalTests, nums, expected,
                    "Zeroes at the beginning");
            if (Arrays.equals(expected, nums)) passedTests++;
        }

        // Test 8: Alternating zero and non-zero
        {
            int[] nums = {0, 1, 0, 2, 0, 3};
            int[] expected = {1, 2, 3, 0, 0, 0};
            runTest(++totalTests, nums, expected,
                    "Alternating zero and non-zero");
            if (Arrays.equals(expected, nums)) passedTests++;
        }

        // Test 9: Two elements, zero then non-zero
        {
            int[] nums = {0, 1};
            int[] expected = {1, 0};
            runTest(++totalTests, nums, expected,
                    "Two elements - zero then non-zero");
            if (Arrays.equals(expected, nums)) passedTests++;
        }

        // Test 10: Negative numbers mixed with zero
        {
            int[] nums = {-1, 0, -2, 0, -3};
            int[] expected = {-1, -2, -3, 0, 0};
            runTest(++totalTests, nums, expected,
                    "Negative numbers mixed with zero");
            if (Arrays.equals(expected, nums)) passedTests++;
        }

        // Test 11: Consecutive zeroes in the middle
        {
            int[] nums = {1, 0, 0, 0, 2, 3};
            int[] expected = {1, 2, 3, 0, 0, 0};
            runTest(++totalTests, nums, expected,
                    "Consecutive zeroes in the middle");
            if (Arrays.equals(expected, nums)) passedTests++;
        }

        // Test 12: Boundary values with zero
        {
            int[] nums = {Integer.MAX_VALUE, 0, Integer.MIN_VALUE, 0};
            int[] expected = {Integer.MAX_VALUE, Integer.MIN_VALUE, 0, 0};
            runTest(++totalTests, nums, expected,
                    "Boundary values (MAX, MIN) with zero");
            if (Arrays.equals(expected, nums)) passedTests++;
        }

        // Test 13: Single zero surrounded by same non-zero value
        {
            int[] nums = {5, 5, 0, 5, 5};
            int[] expected = {5, 5, 5, 5, 0};
            runTest(++totalTests, nums, expected,
                    "Duplicate non-zero values around a single zero");
            if (Arrays.equals(expected, nums)) passedTests++;
        }

        printSummary(totalTests, passedTests);
    }
}
