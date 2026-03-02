package arraysAndHashing;

/**
 * LeetCode Problem #485: Max Consecutive Ones
 * Difficulty: Easy
 * 
 * Problem Description:
 * Given a binary array nums, return the maximum number of consecutive 1s in the array.
 * 
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - nums[i] is either 0 or 1
 * 
 * Example 1:
 * Input: nums = [1,1,0,1,1,1]
 * Output: 3
 * Explanation: The first two digits or the last three digits are consecutive 1s.
 * The maximum number of consecutive 1s is 3.
 * 
 * Example 2:
 * Input: nums = [1,0,1,1,0,1]
 * Output: 2
 * 
 * Approach:
 * Single-pass streaming algorithm with two variables: max (global maximum) and currMax
 * (current streak). Iterate through array: when encountering 0, reset currMax to 0
 * (zeros act as boundaries); when encountering 1, increment currMax and update max.
 * This treats zeros as natural delimiters that break consecutive sequences.
 * 
 * Time Complexity: O(n) - Single pass through array, each element visited exactly once
 * Space Complexity: O(1) - Only two integer variables (max, currMax)
 * 
 * Key Learnings:
 * - Zeros as boundaries: Similar to Maximum Product Subarray, zeros segment the problem
 * - Single-pass sufficiency: Unlike prefix/suffix problems, one forward pass is enough
 * - Enhanced for-loop: When indices aren't needed, for(element : array) is cleaner
 * - Continuous max update: Updating max after each increment captures maximum anywhere
 * - Asymptotic optimality: O(n) is provably optimal - must examine every element
 * - Pattern: "Count until boundary" - track streak, reset on delimiter, maintain global max
 * 
 * Alternative Approaches:
 * 1. Conditional Increment (if n==1 then increment else reset) - slightly cleaner
 * 2. Ternary Operator (currMax = n==1 ? currMax+1 : 0) - most concise
 * 3. Kadane's Pattern (Math.max(0, currMax+n)) - overkill but demonstrates generalization
 * 
 * @see <a href="https://leetcode.com/problems/max-consecutive-ones/description/">LeetCode Problem #485</a>
 */
public class MaxConsecutiveOnes {

    /**
     * Finds the maximum number of consecutive 1s in the binary array.
     * 
     * @param nums binary array containing only 0s and 1s
     * @return the maximum number of consecutive 1s
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int currMax = 0;
        for (int n : nums) {
            currMax = (n == 1) ? currMax + 1 : 0;
            max = Math.max(max, currMax);
        }
        return max;
    }

    public int findMaxConsecutiveOnes_FirstAttempt(int[] nums) {
        int max = 0;
        int currMax = 0;
        for(int n:nums){
            if (n == 1) {
                currMax++;
                max = Math.max(max, currMax);
            } else {
                currMax = 0;
            }
        }
        return max;
    }

    /**
     * Helper method to run a single test case.
     * 
     * @param testNum test case number
     * @param nums input array
     * @param expected expected result
     * @param actual actual result from solution
     * @param description test case description
     */
    private void runTest(int testNum, int[] nums, int expected, int actual, String description) {
        boolean passed = expected == actual;
        String status = passed ? "✓ PASS" : "✗ FAIL";
        String arrayStr = arrayToString(nums);
        
        System.out.printf("Test %d: %s - %s%n", testNum, description, status);
        System.out.printf("  Input:    %s%n", arrayStr);
        System.out.printf("  Expected: %d%n", expected);
        System.out.printf("  Actual:   %d%n", actual);
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
     * Converts an array to a formatted string for display.
     * 
     * @param nums input array
     * @return formatted string representation
     */
    private String arrayToString(int[] nums) {
        if (nums == null) return "null";
        if (nums.length == 0) return "[]";
        if (nums.length > 20) {
            return "[" + nums[0] + ", " + nums[1] + ", ..., " + 
                   nums[nums.length - 2] + ", " + nums[nums.length - 1] + 
                   "] (length=" + nums.length + ")";
        }
        
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < nums.length; i++) {
            sb.append(nums[i]);
            if (i < nums.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Main method with comprehensive test cases.
     */
    public static void main(String[] args) {
        MaxConsecutiveOnes solution = new MaxConsecutiveOnes();
        boolean[] results = new boolean[10];
        int testNum = 0;

        System.out.println("Testing Max Consecutive Ones");
        System.out.println("=".repeat(60));
        System.out.println();

        // Test 1: Example 1 - Last three digits are consecutive 1s
        {
            int[] nums = {1, 1, 0, 1, 1, 1};
            int expected = 3;
            int actual = solution.findMaxConsecutiveOnes(nums);
            solution.runTest(++testNum, nums, expected, actual, 
                           "Example 1 - consecutive 1s at end");
            results[testNum - 1] = (expected == actual);
        }

        // Test 2: Example 2 - Multiple groups of consecutive 1s
        {
            int[] nums = {1, 0, 1, 1, 0, 1};
            int expected = 2;
            int actual = solution.findMaxConsecutiveOnes(nums);
            solution.runTest(++testNum, nums, expected, actual, 
                           "Example 2 - multiple groups");
            results[testNum - 1] = (expected == actual);
        }

        // Test 3: All 1s
        {
            int[] nums = {1, 1, 1, 1, 1};
            int expected = 5;
            int actual = solution.findMaxConsecutiveOnes(nums);
            solution.runTest(++testNum, nums, expected, actual, 
                           "All 1s - entire array");
            results[testNum - 1] = (expected == actual);
        }

        // Test 4: All 0s
        {
            int[] nums = {0, 0, 0, 0, 0};
            int expected = 0;
            int actual = solution.findMaxConsecutiveOnes(nums);
            solution.runTest(++testNum, nums, expected, actual, 
                           "All 0s - no consecutive 1s");
            results[testNum - 1] = (expected == actual);
        }

        // Test 5: Single element - 1
        {
            int[] nums = {1};
            int expected = 1;
            int actual = solution.findMaxConsecutiveOnes(nums);
            solution.runTest(++testNum, nums, expected, actual, 
                           "Single element - 1");
            results[testNum - 1] = (expected == actual);
        }

        // Test 6: Single element - 0
        {
            int[] nums = {0};
            int expected = 0;
            int actual = solution.findMaxConsecutiveOnes(nums);
            solution.runTest(++testNum, nums, expected, actual, 
                           "Single element - 0");
            results[testNum - 1] = (expected == actual);
        }

        // Test 7: Consecutive 1s at beginning
        {
            int[] nums = {1, 1, 1, 0, 0, 1};
            int expected = 3;
            int actual = solution.findMaxConsecutiveOnes(nums);
            solution.runTest(++testNum, nums, expected, actual, 
                           "Consecutive 1s at beginning");
            results[testNum - 1] = (expected == actual);
        }

        // Test 8: Alternating 0s and 1s
        {
            int[] nums = {0, 1, 0, 1, 0, 1, 0};
            int expected = 1;
            int actual = solution.findMaxConsecutiveOnes(nums);
            solution.runTest(++testNum, nums, expected, actual, 
                           "Alternating 0s and 1s");
            results[testNum - 1] = (expected == actual);
        }

        // Test 9: Large array - maximum constraint
        {
            int[] nums = new int[100000];
            for (int i = 0; i < 50000; i++) {
                nums[i] = 1;
            }
            for (int i = 50000; i < 100000; i++) {
                nums[i] = 0;
            }
            int expected = 50000;
            int actual = solution.findMaxConsecutiveOnes(nums);
            solution.runTest(++testNum, nums, expected, actual, 
                           "Large array - 50000 consecutive 1s");
            results[testNum - 1] = (expected == actual);
        }

        // Test 10: Multiple equal groups
        {
            int[] nums = {1, 1, 0, 1, 1, 0, 1, 1};
            int expected = 2;
            int actual = solution.findMaxConsecutiveOnes(nums);
            solution.runTest(++testNum, nums, expected, actual, 
                           "Multiple equal groups of 1s");
            results[testNum - 1] = (expected == actual);
        }

        solution.printSummary(results);
    }
}
