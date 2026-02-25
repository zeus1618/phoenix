package arraysAndHashing;

import java.util.*;

/**
 * LeetCode Problem 238: Product of Array Except Self
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/product-of-array-except-self/
 *
 * <p>Problem Description:
 * Given an integer array nums, return an array answer such that answer[i] is equal to 
 * the product of all elements of nums except nums[i].
 *
 * <p>The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 * <p>You must write an algorithm that runs in O(n) time and without using the division operation.
 *
 * <p>Constraints:
 * - 2 <= nums.length <= 10^5
 * - -30 <= nums[i] <= 30
 * - The product of any prefix or suffix is guaranteed to fit in a 32-bit integer
 *
 * <p>Examples:
 * Example 1:
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * Explanation: 
 * - answer[0] = 2*3*4 = 24
 * - answer[1] = 1*3*4 = 12
 * - answer[2] = 1*2*4 = 8
 * - answer[3] = 1*2*3 = 6
 *
 * Example 2:
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 * Explanation:
 * - answer[0] = 1*0*(-3)*3 = 0
 * - answer[1] = (-1)*0*(-3)*3 = 0
 * - answer[2] = (-1)*1*(-3)*3 = 9
 * - answer[3] = (-1)*1*0*3 = 0
 * - answer[4] = (-1)*1*0*(-3) = 0
 *
 * <p>Follow-up:
 * Can you solve the problem in O(1) extra space complexity? 
 * (The output array does not count as extra space for space complexity analysis.)
 *
 * <p>Approach: Prefix and Suffix Products (O(1) Extra Space)
 * Build prefix products in the output array (left-to-right pass), then multiply suffix
 * products on-the-fly (right-to-left pass). For each position i:
 * - answer[i] = (product of all elements before i) × (product of all elements after i)
 * - Pass 1: answer[i] = prefix product = nums[0] × ... × nums[i-1]
 * - Pass 2: answer[i] *= suffix product = nums[i+1] × ... × nums[n-1]
 * 
 * Key insight: Decompose "product except self" into left and right products, avoiding division.
 * Initialize answer[0]=1 (no elements to left) and suffix=1 (multiplicative identity).
 * 
 * Why no division: Problem explicitly forbids it to guide toward prefix/suffix pattern.
 *
 * <p>Time Complexity: O(n) — two passes (prefix left-to-right, suffix right-to-left)
 * <p>Space Complexity: O(1) — only output array (doesn't count) + one suffix variable
 *
 * <p>Key Learnings:
 * - Prefix/suffix decomposition: split "all except me" into "before me" × "after me"
 * - Division is forbidden for a reason: forces discovery of prefix/suffix pattern
 * - Space optimization: reuse output array for prefix, compute suffix on-the-fly
 * - Identity initialization: answer[0]=1, suffix=1 (multiplicative identity)
 * - Order matters in Pass 2: multiply suffix before updating it
 * - Handles zeros naturally: no special logic needed (zeros propagate through products)
 *
 * <p>Alternative Approaches:
 * 1. Total product ÷ element: O(n) time, O(1) space — VIOLATES "no division" constraint
 * 2. Prefix/suffix with two arrays: O(n) time, O(n) space — correct but not space-optimal
 * 3. Brute force: O(n²) time, O(1) space — too slow
 * 
 * <p>Detailed Learning Guide: See arraysAndHashing/learnings/ProductOfArrayExceptSelf-Learning.md
 */
public class ProductOfArrayExceptSelf {

    /**
     * Returns an array where each element is the product of all elements except itself.
     *
     * @param nums the input array of integers
     * @return array where answer[i] = product of all nums except nums[i]
     */
    public int[] productExceptSelf(int[] nums) {
        int[] solution = new int[nums.length];
        solution[0] = 1;
        for(int i=1; i<nums.length; i++) {
            solution[i] = solution[i-1]*nums[i-1];
        }
        int suffix = 1;
        for(int i=nums.length-1; i>=0; i--) {
            solution[i] = solution[i]*suffix;
            suffix *= nums[i];
        }
        return solution;
    }

    public int[] productExceptSelfIncorrectUsedDivision(int[] nums) {
        int[] prodArray = new int[nums.length];
        int zeroCount = 0;
        int wholeProduct = 1;
        for(int n : nums) {
            if(n != 0){
                wholeProduct*=n;
            } else {
                ++zeroCount;
            }
        }
        Arrays.fill(prodArray, 0);
        if(zeroCount==1){
            for(int i=0; i<nums.length; i++) {
                if(nums[i] == 0) {
                    prodArray[i] = wholeProduct;
                }
            }
        } else if(zeroCount == 0) {
            for(int i=0 ; i<nums.length; i++) {
                prodArray[i] = wholeProduct/nums[i];
            }
        }
        return prodArray;
    }

    // ======================== Test Helper Methods ========================

    /**
     * Runs a single test case and prints the result.
     *
     * @param testNum the test case number
     * @param nums the input array
     * @param expected the expected output array
     * @param actual the actual result from the solution
     * @param description brief description of the test case
     */
    private static void runTest(
            int testNum,
            int[] nums,
            int[] expected,
            int[] actual,
            String description) {

        boolean passed = Arrays.equals(expected, actual);
        String status = passed ? "✓ PASS" : "✗ FAIL";

        System.out.println("\n" + status + " - Test " + testNum + ": " + description);
        System.out.println("  Input:    nums = " + Arrays.toString(nums));
        System.out.println("  Expected: " + Arrays.toString(expected));
        System.out.println("  Actual:   " + Arrays.toString(actual));

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
        ProductOfArrayExceptSelf solution = new ProductOfArrayExceptSelf();
        int totalTests = 0;
        int passedTests = 0;

        System.out.println("=".repeat(70));
        System.out.println("Testing LeetCode #238: Product of Array Except Self");
        System.out.println("=".repeat(70));

        // Test 1: Example 1 - Basic case
        {
            int[] nums = {1, 2, 3, 4};
            int[] expected = {24, 12, 8, 6};
            int[] actual = solution.productExceptSelf(nums);
            runTest(++totalTests, nums, expected, actual,
                    "Example 1 - Basic [1,2,3,4]");
            if (Arrays.equals(expected, actual)) passedTests++;
        }

        // Test 2: Example 2 - Contains zero
        {
            int[] nums = {-1, 1, 0, -3, 3};
            int[] expected = {0, 0, 9, 0, 0};
            int[] actual = solution.productExceptSelf(nums);
            runTest(++totalTests, nums, expected, actual,
                    "Example 2 - Contains zero");
            if (Arrays.equals(expected, actual)) passedTests++;
        }

        // Test 3: Two elements
        {
            int[] nums = {2, 3};
            int[] expected = {3, 2};
            int[] actual = solution.productExceptSelf(nums);
            runTest(++totalTests, nums, expected, actual,
                    "Minimum length - two elements");
            if (Arrays.equals(expected, actual)) passedTests++;
        }

        // Test 4: All ones
        {
            int[] nums = {1, 1, 1, 1};
            int[] expected = {1, 1, 1, 1};
            int[] actual = solution.productExceptSelf(nums);
            runTest(++totalTests, nums, expected, actual,
                    "All ones");
            if (Arrays.equals(expected, actual)) passedTests++;
        }

        // Test 5: Contains negative numbers
        {
            int[] nums = {-2, -3, 4, 5};
            int[] expected = {-60, -40, 30, 24};
            int[] actual = solution.productExceptSelf(nums);
            runTest(++totalTests, nums, expected, actual,
                    "Negative numbers");
            if (Arrays.equals(expected, actual)) passedTests++;
        }

        // Test 6: Single zero at beginning
        {
            int[] nums = {0, 2, 3, 4};
            int[] expected = {24, 0, 0, 0};
            int[] actual = solution.productExceptSelf(nums);
            runTest(++totalTests, nums, expected, actual,
                    "Zero at beginning");
            if (Arrays.equals(expected, actual)) passedTests++;
        }

        // Test 7: Single zero at end
        {
            int[] nums = {2, 3, 4, 0};
            int[] expected = {0, 0, 0, 24};
            int[] actual = solution.productExceptSelf(nums);
            runTest(++totalTests, nums, expected, actual,
                    "Zero at end");
            if (Arrays.equals(expected, actual)) passedTests++;
        }

        // Test 8: Single zero in middle
        {
            int[] nums = {2, 0, 3};
            int[] expected = {0, 6, 0};
            int[] actual = solution.productExceptSelf(nums);
            runTest(++totalTests, nums, expected, actual,
                    "Zero in middle");
            if (Arrays.equals(expected, actual)) passedTests++;
        }

        // Test 9: Multiple zeros
        {
            int[] nums = {0, 0, 2, 3};
            int[] expected = {0, 0, 0, 0};
            int[] actual = solution.productExceptSelf(nums);
            runTest(++totalTests, nums, expected, actual,
                    "Multiple zeros - all results zero");
            if (Arrays.equals(expected, actual)) passedTests++;
        }

        // Test 10: All zeros
        {
            int[] nums = {0, 0, 0};
            int[] expected = {0, 0, 0};
            int[] actual = solution.productExceptSelf(nums);
            runTest(++totalTests, nums, expected, actual,
                    "All zeros");
            if (Arrays.equals(expected, actual)) passedTests++;
        }

        // Test 11: Mix of positive and negative
        {
            int[] nums = {-2, 3, -4, 5};
            int[] expected = {-60, 40, -30, 24};
            int[] actual = solution.productExceptSelf(nums);
            runTest(++totalTests, nums, expected, actual,
                    "Mix positive and negative");
            if (Arrays.equals(expected, actual)) passedTests++;
        }

        // Test 12: Large numbers (boundary)
        {
            int[] nums = {30, 30, 30};
            int[] expected = {900, 900, 900};  // 30*30 = 900
            int[] actual = solution.productExceptSelf(nums);
            runTest(++totalTests, nums, expected, actual,
                    "Boundary values (30, 30, 30)");
            if (Arrays.equals(expected, actual)) passedTests++;
        }

        // Test 13: Negative boundary
        {
            int[] nums = {-30, -30, -30};
            int[] expected = {900, 900, 900};  // (-30)*(-30) = 900
            int[] actual = solution.productExceptSelf(nums);
            runTest(++totalTests, nums, expected, actual,
                    "Negative boundary (-30, -30, -30)");
            if (Arrays.equals(expected, actual)) passedTests++;
        }

        // Test 14: Identity element (1) dominates
        {
            int[] nums = {1, 2, 1, 3, 1};
            int[] expected = {6, 3, 6, 2, 6};
            int[] actual = solution.productExceptSelf(nums);
            runTest(++totalTests, nums, expected, actual,
                    "Multiple 1s (identity)");
            if (Arrays.equals(expected, actual)) passedTests++;
        }

        // Test 15: Longer array
        {
            int[] nums = {1, 2, 3, 4, 5, 6};
            int[] expected = {720, 360, 240, 180, 144, 120};
            int[] actual = solution.productExceptSelf(nums);
            runTest(++totalTests, nums, expected, actual,
                    "Longer array [1,2,3,4,5,6]");
            if (Arrays.equals(expected, actual)) passedTests++;
        }

        // Test 16: Alternating positive/negative
        {
            int[] nums = {2, -3, 4, -5};
            int[] expected = {60, -40, 30, -24};
            int[] actual = solution.productExceptSelf(nums);
            runTest(++totalTests, nums, expected, actual,
                    "Alternating signs");
            if (Arrays.equals(expected, actual)) passedTests++;
        }

        printSummary(totalTests, passedTests);
    }
}
