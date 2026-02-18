package arraysAndHashing;

import java.util.*;

/**
 * LeetCode Problem 53: Maximum Subarray
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/maximum-subarray/
 *
 * <p>Problem Description:
 * Given an integer array nums, find the contiguous subarray with the largest sum, 
 * and return its sum.
 *
 * <p>A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * <p>Constraints:
 * - 1 <= nums.length <= 10^5
 * - -10^4 <= nums[i] <= 10^4
 *
 * <p>Follow-up:
 * If you have figured out the O(n) solution, try coding another solution using the 
 * divide and conquer approach, which is more subtle.
 *
 * <p>Examples:
 * Example 1:
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 *
 * Example 2:
 * Input: nums = [1]
 * Output: 1
 * Explanation: The subarray [1] has the largest sum 1.
 *
 * Example 3:
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 * Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 *
 * <p>Approach: Kadane's Algorithm (Optimal)
 * Uses a greedy + dynamic programming approach to find the maximum subarray sum in linear time.
 * The algorithm maintains two values:
 * 1. currentSum: Running sum of the current subarray
 * 2. maxSum: Maximum sum seen so far (global maximum)
 *
 * Key insight: At each position, we decide whether to extend the current subarray or start fresh.
 * When currentSum becomes negative, it's better to abandon it and start a new subarray, because
 * a negative sum will only reduce any future positive numbers.
 *
 * Algorithm steps:
 * 1. Add current element to running sum
 * 2. Update global maximum if current sum is larger
 * 3. Reset running sum to 0 if it becomes negative (start fresh)
 *
 * The order of operations is critical: add → check → reset ensures every element gets
 * a chance to be the maximum (handles all-negative arrays correctly).
 *
 * <p>Time Complexity: O(n) - Single pass through the array, each element visited exactly once
 * <p>Space Complexity: O(1) - Only two integer variables regardless of input size
 *
 * <p>Key Learnings:
 * - Kadane's Algorithm is the optimal solution combining greedy choice with dynamic programming
 * - The "reset when negative" strategy is the key insight: negative sums hurt future elements
 * - Order of operations matters: add → update max → reset (ensures correctness for all cases)
 * - This pattern appears in many subarray problems (max product, circular subarray, stock problems)
 * - Greedy choice at each step: extend current subarray OR start fresh (whichever is better)
 * - Mathematical formulation: currentSum = max(num, currentSum + num)
 * - Initialize maxSum to Integer.MIN_VALUE to handle all-negative arrays correctly
 *
 * <p>Alternative Approaches:
 * 1. Dynamic Programming (Explicit Array): O(n) time, O(n) space
 *    - dp[i] = max sum ending at position i
 *    - dp[i] = max(nums[i], dp[i-1] + nums[i])
 *    - Kadane's is the space-optimized version of this
 *
 * 2. Divide and Conquer: O(n log n) time, O(log n) space
 *    - Divide array into left and right halves
 *    - Max subarray is either: in left half, in right half, or crosses the middle
 *    - Recursively solve and combine results
 *    - Slower than Kadane's but demonstrates divide & conquer technique
 *
 * 3. Brute Force: O(n²) time, O(1) space
 *    - Try all possible subarrays using nested loops
 *    - Track maximum sum found
 *    - Correct but inefficient, 100x slower for large inputs
 */
public class MaximumSubarray {

    /**
     * Finds the contiguous subarray with the largest sum.
     *
     * @param nums the input array of integers
     * @return the maximum sum of any contiguous subarray
     */
    public int maxSubArray(int[] nums) {
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;
        for(int num : nums) {
            sum += num;
            if(sum > maxSum) {
                maxSum = sum;
            }
            if(sum < 0){
                sum = 0;
            }
        }
        return maxSum;
    }

    // ======================== Test Helper Methods ========================

    /**
     * Runs a single test case and prints the result.
     *
     * @param testNum the test case number
     * @param nums the input array
     * @param expected the expected maximum subarray sum
     * @param actual the actual result from the solution
     * @param description brief description of the test case
     */
    private static void runTest(
            int testNum,
            int[] nums,
            int expected,
            int actual,
            String description) {
        
        boolean passed = (expected == actual);
        String status = passed ? "✓ PASS" : "✗ FAIL";
        
        System.out.println("\n" + status + " - Test " + testNum + ": " + description);
        System.out.println("  Input:    nums = " + Arrays.toString(nums));
        System.out.println("  Expected: " + expected);
        System.out.println("  Actual:   " + actual);
        
        if (!passed) {
            System.out.println("  ❌ Test failed! Difference: " + (actual - expected));
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
        MaximumSubarray solution = new MaximumSubarray();
        int totalTests = 0;
        int passedTests = 0;

        System.out.println("=".repeat(70));
        System.out.println("Testing LeetCode #53: Maximum Subarray");
        System.out.println("=".repeat(70));

        // Test 1: Example 1 - Mixed positive and negative
        {
            int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
            int expected = 6;  // [4,-1,2,1]
            int actual = solution.maxSubArray(nums);
            runTest(++totalTests, nums, expected, actual, 
                "Example 1 - Mixed positive/negative");
            if (expected == actual) passedTests++;
        }

        // Test 2: Example 2 - Single element
        {
            int[] nums = {1};
            int expected = 1;
            int actual = solution.maxSubArray(nums);
            runTest(++totalTests, nums, expected, actual, 
                "Example 2 - Single positive element");
            if (expected == actual) passedTests++;
        }

        // Test 3: Example 3 - All positive with one negative
        {
            int[] nums = {5, 4, -1, 7, 8};
            int expected = 23;  // Entire array
            int actual = solution.maxSubArray(nums);
            runTest(++totalTests, nums, expected, actual, 
                "Example 3 - Mostly positive");
            if (expected == actual) passedTests++;
        }

        // Test 4: All negative numbers
        {
            int[] nums = {-3, -2, -5, -1, -4};
            int expected = -1;  // Best is single element -1
            int actual = solution.maxSubArray(nums);
            runTest(++totalTests, nums, expected, actual, 
                "All negative numbers");
            if (expected == actual) passedTests++;
        }

        // Test 5: All positive numbers
        {
            int[] nums = {1, 2, 3, 4, 5};
            int expected = 15;  // Sum of entire array
            int actual = solution.maxSubArray(nums);
            runTest(++totalTests, nums, expected, actual, 
                "All positive numbers");
            if (expected == actual) passedTests++;
        }

        // Test 6: Single negative element
        {
            int[] nums = {-1};
            int expected = -1;
            int actual = solution.maxSubArray(nums);
            runTest(++totalTests, nums, expected, actual, 
                "Single negative element");
            if (expected == actual) passedTests++;
        }

        // Test 7: Two elements - both positive
        {
            int[] nums = {1, 2};
            int expected = 3;
            int actual = solution.maxSubArray(nums);
            runTest(++totalTests, nums, expected, actual, 
                "Two positive elements");
            if (expected == actual) passedTests++;
        }

        // Test 8: Two elements - one positive, one negative
        {
            int[] nums = {-1, 2};
            int expected = 2;
            int actual = solution.maxSubArray(nums);
            runTest(++totalTests, nums, expected, actual, 
                "Negative then positive");
            if (expected == actual) passedTests++;
        }

        // Test 9: Alternating positive and negative
        {
            int[] nums = {1, -1, 1, -1, 1};
            int expected = 1;
            int actual = solution.maxSubArray(nums);
            runTest(++totalTests, nums, expected, actual, 
                "Alternating +1 and -1");
            if (expected == actual) passedTests++;
        }

        // Test 10: Large positive followed by small negative
        {
            int[] nums = {5, -3, 5};
            int expected = 7;  // [5, -3, 5]
            int actual = solution.maxSubArray(nums);
            runTest(++totalTests, nums, expected, actual, 
                "Worth including negative");
            if (expected == actual) passedTests++;
        }

        // Test 11: Maximum at beginning
        {
            int[] nums = {5, 4, -10, -2, -3};
            int expected = 9;  // [5, 4]
            int actual = solution.maxSubArray(nums);
            runTest(++totalTests, nums, expected, actual, 
                "Maximum subarray at start");
            if (expected == actual) passedTests++;
        }

        // Test 12: Maximum at end
        {
            int[] nums = {-5, -2, -3, 4, 5};
            int expected = 9;  // [4, 5]
            int actual = solution.maxSubArray(nums);
            runTest(++totalTests, nums, expected, actual, 
                "Maximum subarray at end");
            if (expected == actual) passedTests++;
        }

        // Test 13: Maximum in middle
        {
            int[] nums = {-1, -2, 5, -1, -2};
            int expected = 5;  // [5]
            int actual = solution.maxSubArray(nums);
            runTest(++totalTests, nums, expected, actual, 
                "Maximum subarray in middle");
            if (expected == actual) passedTests++;
        }

        // Test 14: Zero included
        {
            int[] nums = {0, -3, 1, 1};
            int expected = 2;  // [1, 1]
            int actual = solution.maxSubArray(nums);
            runTest(++totalTests, nums, expected, actual, 
                "Array with zero");
            if (expected == actual) passedTests++;
        }

        // Test 15: Large array with clear maximum
        {
            int[] nums = {-2, -3, 4, -1, -2, 1, 5, -3};
            int expected = 7;  // [4, -1, -2, 1, 5]
            int actual = solution.maxSubArray(nums);
            runTest(++totalTests, nums, expected, actual, 
                "Larger array with subarray");
            if (expected == actual) passedTests++;
        }

        // Test 16: Performance test - Large array
        {
            int[] nums = new int[1000];
            for (int i = 0; i < 1000; i++) {
                nums[i] = (i % 2 == 0) ? 1 : -1;
            }
            nums[500] = 100;  // Peak in middle
            int expected = 100;
            
            long startTime = System.nanoTime();
            int actual = solution.maxSubArray(nums);
            long endTime = System.nanoTime();
            double elapsedMs = (endTime - startTime) / 1_000_000.0;
            
            runTest(++totalTests, new int[]{/* abbreviated for display */}, expected, actual, 
                String.format("Performance test (n=1000, %.2f ms)", elapsedMs));
            if (expected == actual) passedTests++;
        }

        // Test 17: Edge case - Very large positive sum
        {
            int[] nums = {1000, 1000, 1000, 1000, 1000};
            int expected = 5000;
            int actual = solution.maxSubArray(nums);
            runTest(++totalTests, nums, expected, actual, 
                "Large positive values");
            if (expected == actual) passedTests++;
        }

        // Test 18: Edge case - Very large negative value
        {
            int[] nums = {-10000, -1, -2};
            int expected = -1;
            int actual = solution.maxSubArray(nums);
            runTest(++totalTests, nums, expected, actual, 
                "Large negative values");
            if (expected == actual) passedTests++;
        }

        printSummary(totalTests, passedTests);
    }
}
