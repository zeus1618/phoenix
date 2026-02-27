package arraysAndHashing;

import java.util.*;

/**
 * LeetCode Problem 189: Rotate Array
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/rotate-array/
 *
 * <p>Problem Description:
 * Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
 *
 * <p>Constraints:
 * - 1 <= nums.length <= 10^5
 * - -2^31 <= nums[i] <= 2^31 - 1
 * - 0 <= k <= 10^5
 *
 * <p>Examples:
 * Example 1:
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * - rotate 1 step to the right: [7,1,2,3,4,5,6]
 * - rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * - rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * 
 * 
 * Example 1:
 * Input: nums = [1,2,3,4,5,6,7,8,9], k = 3
 * Output: [7,8,9,1,2,3,4,5,6]
 * tmp = [k-1]
 * [n-1] -> [k-1]
 * 
 * 
 * Explanation:
 * - rotate 1 step to the right: [9,1,2,3,4,5,6,7,8]
 * - rotate 2 steps to the right: [8,9,1,2,3,4,5,6,7]
 * - rotate 3 steps to the right: [7,8,9,1,2,3,4,5,6]
 * 
 * 
 * Example 2:
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * - rotate 1 step to the right: [99,-1,-100,3]
 * - rotate 2 steps to the right: [3,99,-1,-100]
 *
 * <p>Follow-up:
 * - Try to come up with at least three different ways to solve this problem.
 * - Could you do it in-place with O(1) extra space?
 *
 * <p>Approach:
 * Extra array approach. First, normalize k using modulo to handle k > array length. Then, convert
 * right rotation by k to left rotation by (n-k). Copy elements to a temporary array starting from
 * the calculated position, wrapping around using manual index reset. Finally, copy back to original
 * array. Two-pass solution with auxiliary space.
 *
 * <p>Time Complexity: O(n) - Two passes through array (copy to temp, copy back)
 * <p>Space Complexity: O(n) - Extra array of same size as input
 *
 * <p>Key Learnings:
 * - Current solution is correct but uses O(n) space, does not meet O(1) space follow-up requirement
 * - Reversal algorithm is optimal: reverse entire array, then reverse first k, then reverse last n-k
 * - Reversal approach achieves O(n) time and O(1) space - the gold standard for this problem
 * - Always check if follow-up requirements are met, especially space constraints
 * - Array reversal pattern is powerful for in-place transformations and rotations
 * - Modulo operation essential for handling k > array length in rotation problems
 *
 * <p>Alternative Approaches:
 * 1. Reversal Algorithm - O(n) time, O(1) space [OPTIMAL - meets follow-up]
 *    Three reversals: reverse all → reverse first k → reverse last n-k
 * 2. Cyclic Replacements - O(n) time, O(1) space [Complex but meets follow-up]
 *    Place each element directly in final position, follow cycles
 * 3. Extra Array (current) - O(n) time, O(n) space [Correct but not space-optimal]
 * 4. Brute Force (rotate one by one) - O(n×k) time, O(1) space [Too slow for large k]
 */
public class RotateArray {

    /**
     * Rotates the array to the right by k steps in-place.
     *
     * @param nums the input array to rotate
     * @param k the number of steps to rotate right
     */
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, nums.length-1);
        System.out.println(Arrays.toString(nums));
    }

    public void reverse(int[] num, int start, int end) {
        int temp = 0;
        while(start < end){
            temp = num[end];
            num[end] = num[start];
            num[start] = temp;
            start++;
            end--;
        }
    }

    public void rotateSimpleApproach_BetterSize_ButNot_O1(int[] nums, int k) {
        k = k % nums.length;
        int[] kArr = new int[k];
        for(int i=nums.length-1, j = kArr.length-1; i>nums.length-1-k; i--, j--) {
            kArr[j] = nums[i];
        }
        // System.out.println(Arrays.toString(kArr));
        for(int i=nums.length-1-k, j=nums.length-1; i>=0; i--,j--) {
            nums[j] = nums[i];
        }
        // System.out.println(Arrays.toString(nums));
        for(int i=0; i<k; i++){
            nums[i] = kArr[i];
        }
        // System.out.println(Arrays.toString(nums)); 
    }

    public void rotateFirstAttemp(int[] nums, int k) {
        k = k % nums.length;
        k = nums.length - k;
        int[] rotatedNums = new int[nums.length];
        for(int j=0; j<nums.length; j++) {
            if(k == nums.length){
                k=0;
            }
            rotatedNums[j] = nums[k];
            k++;
        }
        for(int i=0; i<nums.length; i++) {
            nums[i] = rotatedNums[i];
        }
        System.out.println(Arrays.toString(nums));
    }

    // ======================== Test Helper Methods ========================

    /**
     * Runs a single test case and prints the result.
     *
     * @param testNum the test case number
     * @param nums the input array (will be modified)
     * @param k the rotation steps
     * @param expected the expected output array
     * @param description brief description of the test case
     */
    private static void runTest(
            int testNum,
            int[] nums,
            int k,
            int[] expected,
            String description) {

        int[] original = Arrays.copyOf(nums, nums.length);
        RotateArray solution = new RotateArray();
        solution.rotate(nums, k);

        boolean passed = Arrays.equals(expected, nums);
        String status = passed ? "✓ PASS" : "✗ FAIL";

        System.out.println("\n" + status + " - Test " + testNum + ": " + description);
        System.out.println("  Input:    nums = " + Arrays.toString(original) + ", k = " + k);
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
        System.out.println("Testing LeetCode #189: Rotate Array");
        System.out.println("=".repeat(70));

        // Test 1: Example 1
        {
            int[] nums = {1, 2, 3, 4, 5, 6, 7};
            int k = 3;
            int[] expected = {5, 6, 7, 1, 2, 3, 4};
            runTest(++totalTests, nums, k, expected,
                    "Example 1 - rotate [1,2,3,4,5,6,7] by 3");
            if (Arrays.equals(expected, nums)) passedTests++;
        }

        // Test 2: Example 2
        {
            int[] nums = {-1, -100, 3, 99};
            int k = 2;
            int[] expected = {3, 99, -1, -100};
            runTest(++totalTests, nums, k, expected,
                    "Example 2 - rotate [-1,-100,3,99] by 2");
            if (Arrays.equals(expected, nums)) passedTests++;
        }

        // Test 3: Rotate by 0 (no change)
        {
            int[] nums = {1, 2, 3, 4};
            int k = 0;
            int[] expected = {1, 2, 3, 4};
            runTest(++totalTests, nums, k, expected,
                    "k = 0, no rotation");
            if (Arrays.equals(expected, nums)) passedTests++;
        }

        // Test 4: Rotate by array length (full cycle, no change)
        {
            int[] nums = {1, 2, 3, 4};
            int k = 4;
            int[] expected = {1, 2, 3, 4};
            runTest(++totalTests, nums, k, expected,
                    "k = array length, full cycle");
            if (Arrays.equals(expected, nums)) passedTests++;
        }

        // Test 5: k > array length (wraps around)
        {
            int[] nums = {1, 2, 3};
            int k = 4;  // 4 % 3 = 1, effectively rotate by 1
            int[] expected = {3, 1, 2};
            runTest(++totalTests, nums, k, expected,
                    "k > length, wraps around (k=4, len=3)");
            if (Arrays.equals(expected, nums)) passedTests++;
        }

        // Test 6: Single element
        {
            int[] nums = {1};
            int k = 3;
            int[] expected = {1};
            runTest(++totalTests, nums, k, expected,
                    "Single element - no visible change");
            if (Arrays.equals(expected, nums)) passedTests++;
        }

        // Test 7: Two elements, rotate by 1
        {
            int[] nums = {1, 2};
            int k = 1;
            int[] expected = {2, 1};
            runTest(++totalTests, nums, k, expected,
                    "Two elements - swap positions");
            if (Arrays.equals(expected, nums)) passedTests++;
        }

        // Test 8: Rotate by 1
        {
            int[] nums = {1, 2, 3, 4, 5};
            int k = 1;
            int[] expected = {5, 1, 2, 3, 4};
            runTest(++totalTests, nums, k, expected,
                    "Rotate by 1");
            if (Arrays.equals(expected, nums)) passedTests++;
        }

        // Test 9: Rotate by length - 1 (almost full cycle)
        {
            int[] nums = {1, 2, 3, 4};
            int k = 3;
            int[] expected = {2, 3, 4, 1};
            runTest(++totalTests, nums, k, expected,
                    "Rotate by length-1");
            if (Arrays.equals(expected, nums)) passedTests++;
        }

        // Test 10: Large k value (multiple full cycles)
        {
            int[] nums = {1, 2, 3};
            int k = 10;  // 10 % 3 = 1
            int[] expected = {3, 1, 2};
            runTest(++totalTests, nums, k, expected,
                    "Large k (k=10, len=3, effective k=1)");
            if (Arrays.equals(expected, nums)) passedTests++;
        }

        // Test 11: All same elements
        {
            int[] nums = {5, 5, 5, 5};
            int k = 2;
            int[] expected = {5, 5, 5, 5};
            runTest(++totalTests, nums, k, expected,
                    "All same elements - no visible change");
            if (Arrays.equals(expected, nums)) passedTests++;
        }

        // Test 12: Negative numbers
        {
            int[] nums = {-1, -2, -3, -4, -5};
            int k = 2;
            int[] expected = {-4, -5, -1, -2, -3};
            runTest(++totalTests, nums, k, expected,
                    "Negative numbers");
            if (Arrays.equals(expected, nums)) passedTests++;
        }

        // Test 13: Mixed positive and negative
        {
            int[] nums = {-1, 2, -3, 4};
            int k = 3;
            int[] expected = {2, -3, 4, -1};
            runTest(++totalTests, nums, k, expected,
                    "Mixed positive and negative");
            if (Arrays.equals(expected, nums)) passedTests++;
        }

        // Test 14: Rotate entire right half to front
        {
            int[] nums = {1, 2, 3, 4, 5, 6};
            int k = 3;
            int[] expected = {4, 5, 6, 1, 2, 3};
            runTest(++totalTests, nums, k, expected,
                    "Rotate half - right half moves to front");
            if (Arrays.equals(expected, nums)) passedTests++;
        }

        // Test 15: Boundary values
        {
            int[] nums = {Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
            int k = 1;
            int[] expected = {0, Integer.MAX_VALUE, Integer.MIN_VALUE};
            runTest(++totalTests, nums, k, expected,
                    "Boundary values (MAX, MIN, 0)");
            if (Arrays.equals(expected, nums)) passedTests++;
        }

        printSummary(totalTests, passedTests);
    }
}
