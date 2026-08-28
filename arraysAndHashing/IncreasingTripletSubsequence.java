package arraysAndHashing;

/**
 * LeetCode Problem #334: Increasing Triplet Subsequence
 * Difficulty: Medium
 *
 * Problem Description:
 * Given an integer array nums, return true if there exists a triple of
 * indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k].
 * If no such indices exists, return false.
 *
 * Constraints:
 * - 1 <= nums.length <= 5 * 10^5
 * - -2^31 <= nums[i] <= 2^31 - 1
 *
 * Example 1:
 * Input: nums = [1,2,3,4,5]
 * Output: true
 * Explanation: Any triplet where i < j < k is valid.
 *
 * Example 2:
 * Input: nums = [5,4,3,2,1]
 * Output: false
 * Explanation: No triplet exists.
 *
 * Example 3:
 * Input: nums = [2,1,5,0,4,6]
 * Output: true
 * Explanation: The triplet (3, 4, 5) is valid because nums[3] == 0 < nums[4] == 4 < nums[5] == 6.
 *
 * Follow-up:
 * Could you implement a solution that runs in O(n) time complexity and
 * O(1) space complexity?
 *
 * Approach:
 * Greedy two-variable tracking. Maintain {@code first} (the smallest value
 * seen so far) and {@code second} (the smallest value seen so far that is
 * strictly greater than some earlier {@code first}) while scanning left to
 * right. Any later value greater than {@code second} completes a valid
 * increasing triplet — the three values don't need to be tracked at fixed
 * indices, only the best-so-far low and low-mid candidates matter.
 *
 * <p>An earlier attempt ({@link #increasingTripletOld}) searched ahead for
 * the third element with a nested lookahead instead of tracking running
 * minimums; it's kept here for comparison but is no longer exercised by
 * the tests below.
 *
 * Time Complexity: O(n) — single pass over nums.
 * Space Complexity: O(1) — two int variables regardless of input size.
 *
 * @see <a href="https://leetcode.com/problems/increasing-triplet-subsequence/description/">LeetCode Problem #334</a>
 */
public class IncreasingTripletSubsequence {

    /**
     * Determines whether an increasing triplet subsequence exists in nums.
     *
     * @param nums the input array
     * @return true if indices i < j < k exist with nums[i] < nums[j] < nums[k]
     */

    public boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for(int third : nums){
            if(third > second){
                return true;
            } else if(third > first){
                second = third;
            } else {
                first = third;
            }
        }
        return false;
    }

    public boolean increasingTripletOld(int[] nums) {
        int i=0, j=-1, k=-1;
        int currPeak = Integer.MAX_VALUE;
        for(int x=1; x<nums.length; x++){
            if(nums[i] < nums[x]){
                j = x;
                if(nums[j]<currPeak){
                    k = findGreaterThan(nums, j);
                    if(k!=-1){
                        return true;
                    } else {
                        currPeak = nums[j];
                    }
                }
            }
            if(nums[x]<nums[x-1]){
                i = x;
            }
        } 
        return false;
    }

    private int findGreaterThan(int[] nums, int j){
        int k=-1;
        for(int i=j+1; i<nums.length; i++) {
            if(nums[i] > nums[j]){
                k=i;
                break;
            }
        }
        return k;
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
    private void runTest(int testNum, int[] nums, boolean expected, boolean actual, String description) {
        boolean passed = expected == actual;
        String status = passed ? "✓ PASS" : "✗ FAIL";
        String arrayStr = arrayToString(nums);

        System.out.printf("Test %d: %s - %s%n", testNum, description, status);
        System.out.printf("  Input:    %s%n", arrayStr);
        System.out.printf("  Expected: %b%n", expected);
        System.out.printf("  Actual:   %b%n", actual);
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
     * Converts an array to a formatted string for display, truncating
     * long arrays.
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
        IncreasingTripletSubsequence solution = new IncreasingTripletSubsequence();
        boolean[] results = new boolean[10];
        int testNum = 0;

        System.out.println("Testing Increasing Triplet Subsequence");
        System.out.println("=".repeat(60));
        System.out.println();

        // Test 1: Example 1 - strictly increasing array
        {
            int[] nums = {1, 2, 3, 4, 5};
            boolean expected = true;
            boolean actual = solution.increasingTriplet(nums);
            solution.runTest(++testNum, nums, expected, actual,
                           "Example 1 - strictly increasing array");
            results[testNum - 1] = expected == actual;
        }

        // Test 2: Example 2 - strictly decreasing array
        {
            int[] nums = {5, 4, 3, 2, 1};
            boolean expected = false;
            boolean actual = solution.increasingTriplet(nums);
            solution.runTest(++testNum, nums, expected, actual,
                           "Example 2 - strictly decreasing array");
            results[testNum - 1] = expected == actual;
        }

        // Test 3: Example 3 - triplet appears after a later dip
        {
            int[] nums = {2, 1, 5, 0, 4, 6};
            boolean expected = true;
            boolean actual = solution.increasingTriplet(nums);
            solution.runTest(++testNum, nums, expected, actual,
                           "Example 3 - triplet appears after a later dip");
            results[testNum - 1] = expected == actual;
        }

        // Test 4: Minimum constraint - single element
        {
            int[] nums = {5};
            boolean expected = false;
            boolean actual = solution.increasingTriplet(nums);
            solution.runTest(++testNum, nums, expected, actual,
                           "Minimum constraint - single element");
            results[testNum - 1] = expected == actual;
        }

        // Test 5: Two elements only - never enough for a triplet
        {
            int[] nums = {1, 2};
            boolean expected = false;
            boolean actual = solution.increasingTriplet(nums);
            solution.runTest(++testNum, nums, expected, actual,
                           "Two elements only - never enough for a triplet");
            results[testNum - 1] = expected == actual;
        }

        // Test 6: All equal elements - no strictly increasing triplet
        {
            int[] nums = {3, 3, 3, 3};
            boolean expected = false;
            boolean actual = solution.increasingTriplet(nums);
            solution.runTest(++testNum, nums, expected, actual,
                           "All equal elements - no strictly increasing triplet");
            results[testNum - 1] = expected == actual;
        }

        // Test 7: Classic greedy trap - low update after a valid mid is set
        {
            int[] nums = {20, 100, 10, 12, 5, 13};
            boolean expected = true;
            boolean actual = solution.increasingTriplet(nums);
            solution.runTest(++testNum, nums, expected, actual,
                           "Classic greedy trap - low updates after mid is set");
            results[testNum - 1] = expected == actual;
        }

        // Test 8: Triplet only at the very end
        {
            int[] nums = {5, 4, 3, 2, 1, 0, 1, 2};
            boolean expected = true;
            boolean actual = solution.increasingTriplet(nums);
            solution.runTest(++testNum, nums, expected, actual,
                           "Triplet only at the very end");
            results[testNum - 1] = expected == actual;
        }

        // Test 9: Negative and boundary integer values
        {
            int[] nums = {Integer.MIN_VALUE, 0, Integer.MAX_VALUE};
            boolean expected = true;
            boolean actual = solution.increasingTriplet(nums);
            solution.runTest(++testNum, nums, expected, actual,
                           "Negative and boundary integer values");
            results[testNum - 1] = expected == actual;
        }

        // Test 10: Maximum constraint - large strictly decreasing then one rise
        {
            int[] nums = new int[500000];
            for (int i = 0; i < 499998; i++) {
                nums[i] = 499998 - i;
            }
            nums[499998] = 500000;
            nums[499999] = 500001;
            boolean expected = true;
            boolean actual = solution.increasingTriplet(nums);
            solution.runTest(++testNum, nums, expected, actual,
                           "Maximum constraint - large decreasing run then a rising pair at the end");
            results[testNum - 1] = expected == actual;
        }

        solution.printSummary(results);
    }
}
