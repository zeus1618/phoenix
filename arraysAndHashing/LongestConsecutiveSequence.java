package arraysAndHashing;

import java.util.*;

/**
 * LeetCode Problem 128: Longest Consecutive Sequence
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/longest-consecutive-sequence/
 *
 * <p>Problem Description:
 * Given an unsorted array of integers nums, return the length of the longest
 * consecutive elements sequence.
 *
 * <p>You must write an algorithm that runs in O(n) time.
 *
 * <p>Constraints:
 * - 0 <= nums.length <= 10^5
 * - -10^9 <= nums[i] <= 10^9
 *
 * <p>Examples:
 * Example 1:
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 *
 * Example 2:
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 *
 * Example 3:
 * Input: nums = [1,0,1,2]
 * Output: 3
 *
 * <p>Approach:
 * [To be documented after implementation]
 *
 * <p>Time Complexity: [To be analyzed]
 * <p>Space Complexity: [To be analyzed]
 *
 * <p>Key Learnings:
 * [To be documented after implementation]
 *
 * <p>Alternative Approaches:
 * [To be documented after implementation]
 */
public class LongestConsecutiveSequence {

    /**
     * Returns the length of the longest consecutive elements sequence in nums.
     *
     * @param nums the input array of integers
     * @return the length of the longest consecutive elements sequence
     */
    public int longestConsecutive(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        HashSet<Integer> s = new HashSet<>();
        for(int n : nums) {
            s.add(n);
        }
        int maxSeq = 1;
        int currVal, highOrder, lowOrder;
        boolean highStop = false, lowStop = false;
        int currSeq=0;
        System.out.println("Set : " + s);
        while (s.size()>0) {
            currVal = s.iterator().next();
            s.remove(currVal);
            highOrder = 0;
            lowOrder = 0;
            highStop = false; lowStop=false;
            System.out.println("Start For currVal : " + currVal);
            while (!highStop || !lowStop) {
                if(s.contains(currVal+(highOrder+1))){
                    s.remove(currVal+(highOrder+1));
                    ++highOrder;
                } else { 
                    highStop = true; 
                }
                if(s.contains(currVal-(lowOrder+1))){
                    s.remove(currVal-(lowOrder+1));
                    ++lowOrder;
                } else { 
                    lowStop = true; 
                }
            }
            System.out.println("Result For currVal : " + currVal);
            System.out.println(String.format("high : %d \t low : %d", highOrder, lowOrder));
            System.out.println("Set : " + s);
            currSeq = highOrder+lowOrder+1;
            System.out.println("currSeq : " + currSeq);
            maxSeq = Math.max(maxSeq, currSeq);
            System.out.println("maxSeq : " + maxSeq);
        }
        return maxSeq;
    }

    // ======================== Test Helper Methods ========================

    /**
     * Runs a single test case and prints the result.
     *
     * @param testNum the test case number
     * @param nums the input array
     * @param expected the expected output
     * @param actual the actual output from the solution
     * @param description brief description of the test case
     */
    private static void runTest(
            int testNum,
            int[] nums,
            int expected,
            int actual,
            String description) {

        boolean passed = expected == actual;
        String status = passed ? "✓ PASS" : "✗ FAIL";

        System.out.println("\n" + status + " - Test " + testNum + ": " + description);
        System.out.println("  Input:    nums = " + Arrays.toString(nums));
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
        LongestConsecutiveSequence solution = new LongestConsecutiveSequence();
        int totalTests = 0;
        int passedTests = 0;

        System.out.println("=".repeat(70));
        System.out.println("Testing LeetCode #128: Longest Consecutive Sequence");
        System.out.println("=".repeat(70));

        // Test manual
        // {
        //     int[] nums = {-100000000,-99999999,-99999997,-99999996,-99999994,-99999993,-99999991,-99999990,-99999988,-99999987,-99999985,-99999984,-99999982,-99999981,-99999979,-99999978,-99999976,-99999975,-99999973,-99999972,-99999970,-99999969,-99999967,-99999966,-99999964,-99999963,-99999961,-99999960,-99999958,-99999957,-99999955,-99999954,-99999952};
        //     int expected = 2;
        //     int actual = solution.longestConsecutive(nums);
        //     runTest(++totalTests, nums, expected, actual, "Manual test case");
        //     if (expected == actual) passedTests++;
        // }

        // Test 1: Example 1 - Basic case
        {
            int[] nums = {100, 4, 200, 1, 3, 2};
            int expected = 4;
            int actual = solution.longestConsecutive(nums);
            runTest(++totalTests, nums, expected, actual, "Example 1 - Basic case");
            if (expected == actual) passedTests++;
        }

        // Test 2: Example 2 - Larger sequence with duplicate
        {
            int[] nums = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
            int expected = 9;
            int actual = solution.longestConsecutive(nums);
            runTest(++totalTests, nums, expected, actual, "Example 2 - Larger sequence with duplicate");
            if (expected == actual) passedTests++;
        }

        // Test 3: Example 3 - Duplicates present
        {
            int[] nums = {1, 0, 1, 2};
            int expected = 3;
            int actual = solution.longestConsecutive(nums);
            runTest(++totalTests, nums, expected, actual, "Example 3 - Duplicates present");
            if (expected == actual) passedTests++;
        }

        // Test 4: Edge case - empty array
        {
            int[] nums = {};
            int expected = 0;
            int actual = solution.longestConsecutive(nums);
            runTest(++totalTests, nums, expected, actual, "Edge case - empty array");
            if (expected == actual) passedTests++;
        }

        // Test 5: Edge case - single element
        {
            int[] nums = {5};
            int expected = 1;
            int actual = solution.longestConsecutive(nums);
            runTest(++totalTests, nums, expected, actual, "Edge case - single element");
            if (expected == actual) passedTests++;
        }

        // Test 6: All elements identical
        {
            int[] nums = {7, 7, 7, 7};
            int expected = 1;
            int actual = solution.longestConsecutive(nums);
            runTest(++totalTests, nums, expected, actual, "All elements identical");
            if (expected == actual) passedTests++;
        }

        // Test 7: Already sorted consecutive sequence
        {
            int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
            int expected = 8;
            int actual = solution.longestConsecutive(nums);
            runTest(++totalTests, nums, expected, actual, "Already sorted consecutive sequence");
            if (expected == actual) passedTests++;
        }

        // Test 8: Reverse sorted consecutive sequence
        {
            int[] nums = {8, 7, 6, 5, 4, 3, 2, 1};
            int expected = 8;
            int actual = solution.longestConsecutive(nums);
            runTest(++totalTests, nums, expected, actual, "Reverse sorted consecutive sequence");
            if (expected == actual) passedTests++;
        }

        // Test 9: Negative numbers with a consecutive run
        {
            int[] nums = {-2, -1, 0, 1, -3, 5};
            int expected = 5;
            int actual = solution.longestConsecutive(nums);
            runTest(++totalTests, nums, expected, actual, "Negative numbers with a consecutive run");
            if (expected == actual) passedTests++;
        }

        // Test 10: Multiple disjoint sequences, longest not at the start
        {
            int[] nums = {10, 11, 9, 1, 2, 20, 3, 30, 21};
            int expected = 3; // [9,10,11] and [1,2,3] both length 3; [20,21] length 2; [30] length 1
            int actual = solution.longestConsecutive(nums);
            runTest(++totalTests, nums, expected, actual, "Multiple disjoint sequences, longest not at the start");
            if (expected == actual) passedTests++;
        }

        // Test 11: Extreme values near int boundaries
        {
            int[] nums = {Integer.MAX_VALUE, Integer.MAX_VALUE - 1, Integer.MIN_VALUE, Integer.MIN_VALUE + 1};
            int expected = 2;
            int actual = solution.longestConsecutive(nums);
            runTest(++totalTests, nums, expected, actual, "Extreme values near int boundaries");
            if (expected == actual) passedTests++;
        }

        // Test 12: No consecutive elements at all
        {
            int[] nums = {10, 100, 1000, 10000};
            int expected = 1;
            int actual = solution.longestConsecutive(nums);
            runTest(++totalTests, nums, expected, actual, "No consecutive elements at all");
            if (expected == actual) passedTests++;
        }

        printSummary(totalTests, passedTests);
    }
}
