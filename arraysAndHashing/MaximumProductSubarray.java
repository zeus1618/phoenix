package arraysAndHashing;

import java.util.*;

/**
 * LeetCode Problem 152: Maximum Product Subarray
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/maximum-product-subarray/
 *
 * <p>Problem Description:
 * Given an integer array nums, find a subarray that has the largest product,
 * and return the product.
 *
 * <p>The test cases are generated so that the answer will fit in a 32-bit integer.
 * Note that the product of an array with a single element is the value of that element.
 *
 * <p>Constraints:
 * - 1 <= nums.length <= 2 * 10^4
 * - -10 <= nums[i] <= 10
 * - The product of any subarray of nums is guaranteed to fit in a 32-bit integer
 *
 * <p>Examples:
 * Example 1:
 * Input: nums = [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 *
 * Example 2:
 * Input: nums = [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 *
 * <p>Approach: Dynamic Programming with Max/Min Tracking
 * Track both maximum and minimum products ending at each position. At each element:
 * 1. currMax = max(element itself, currMax × element, currMin × element)
 * 2. currMin = min(element itself, currMax × element, currMin × element)
 * 3. Update global answer with currMax
 * 
 * Key insight: Unlike maximum sum subarray, products can flip signs. A negative number
 * can turn a large negative product into a large positive one, so we must track both
 * extremes (max and min). When encountering zero, reset both to 1 (neutral identity).
 * 
 * Pre-scan: Initialize answer with max single element to handle all-negative arrays.
 *
 * <p>Time Complexity: O(n) — one pre-scan + one main pass
 * <p>Space Complexity: O(1) — only a few integer variables
 *
 * <p>Key Learnings:
 * - Unlike maximum sum (Kadane's), products require tracking BOTH max and min due to sign flips
 * - Negative × negative = positive: min can become max when multiplied by negative
 * - Zeros break continuity: reset to 1 (product identity) to start fresh
 * - Even/odd negative parity determines optimal subarray in zero-free segments
 * - Multiple valid O(n) approaches exist: DP max/min, two-pass, segment-based
 *
 * <p>Alternative Approaches:
 * 1. Two-pass Kadane variant (left-to-right + right-to-left): O(n) time, O(1) space
 *    - Intuition: Try both directions; one excludes first neg, other excludes last neg
 * 2. Split-by-zero + segment logic: O(n) time, O(n) space
 *    - Intuition: Process zero-free segments; handle even/odd negatives explicitly
 * 3. Brute force: O(n²) time, O(1) space — try all subarrays
 * 
 * <p>Detailed Learning Guide: See arraysAndHashing/learnings/MaximumProductSubarray-Learning.md
 */
public class MaximumProductSubarray {

    /**
     * Finds the contiguous subarray with the largest product.
     *
     * @param nums the input array of integers
     * @return the maximum product of any contiguous subarray
     */
    public int maxProduct(int[] nums) {
        int answer = Integer.MIN_VALUE;
        for(int n : nums) {
            answer = Math.max(answer, n);
        }
        int currMax = 1;
        int currMin = 1;
        int temp = 0;
        for(int i=0; i< nums.length; i++) {
            if(nums[i] == 0) {
                currMax = 1;
                currMin = 1;
                continue;
            }
            temp = currMax*nums[i];
            currMax = Math.max(nums[i], Math.max(currMax*nums[i], currMin*nums[i]));
            currMin = Math.min(nums[i], Math.min(temp, currMin*nums[i]));
            answer = Math.max(currMax, answer);
        }
        return answer;
    }

    /**
     * Kadane's variant: Two-pass approach (left-to-right and right-to-left).
     * 
     * Key insight: The maximum product is found in either the left-to-right pass
     * or the right-to-left pass. This handles odd negative counts elegantly:
     * - Left-to-right pass: Finds best product excluding the last negative
     * - Right-to-left pass: Finds best product excluding the first negative
     * 
     * With even negatives or all positives, either pass captures the full product.
     * Zeros naturally reset the running product in both directions.
     * 
     * Time: O(n) - two passes
     * Space: O(1) - only two variables
     * 
     * @param nums the input array of integers
     * @return the maximum product of any contiguous subarray
     */
    public int maxProductKadaneVariant(int[] nums) {
        int maxProduct = Integer.MIN_VALUE;
        int product = 1;
        
        // Left-to-right pass
        for (int i = 0; i < nums.length; i++) {
            product *= nums[i];
            maxProduct = Math.max(maxProduct, product);
            if (product == 0) {
                product = 1;  // Reset after zero
            }
        }
        
        // Right-to-left pass
        product = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            product *= nums[i];
            maxProduct = Math.max(maxProduct, product);
            if (product == 0) {
                product = 1;  // Reset after zero
            }
        }
        
        return maxProduct;
    }

    public int maxProductComplexApproach(int[] nums) {
        List<List<Integer>> splitList = new ArrayList<>();
        List<Integer> listA = new ArrayList<>();
        boolean containsZero = false;
        for(int n : nums) {
            if(n == 0) {
                containsZero = true;
                if(!listA.isEmpty()) {
                    splitList.add(listA);
                }
                listA = new ArrayList<>();
            } else {
                listA.add(n);
            }
        }
        if(!listA.isEmpty()) {
            splitList.add(listA);
        }

        Integer maxProduct = Integer.MIN_VALUE;
        if(containsZero) {
            maxProduct = 0;
        }
        for (int i=0; i< splitList.size(); i++) {
            if(!splitList.get(i).isEmpty()) {
                List<Integer> listB = splitList.get(i);
                if(listB.size() == 1) {
                    if(maxProduct < listB.get(0)) {
                        maxProduct = listB.get(0);
                    }
                    continue;
                }
                boolean oddNeg = false;
                int firstNegIndex = -1;
                int lastNegIndex = -1;
                for(int j=0; j<listB.size(); j++) {
                    if(listB.get(j)<0) {
                        if(firstNegIndex<0) {
                            firstNegIndex = j;
                            lastNegIndex = j;
                        }
                        if(lastNegIndex<j) {
                            lastNegIndex = j;
                        }
                        oddNeg = !oddNeg;
                    }
                }
                int currProd = 1;
                if(oddNeg == false) {
                    for(int v : listB) {
                        currProd = currProd*v;
                    }
                    if(maxProduct < currProd) {
                        maxProduct = currProd;
                    }
                } else {
                    //left product
                    
                    int leftProduct = 1;
                    int rightProduct = 1;
                    int centerProduct = 1;
                    if(firstNegIndex == lastNegIndex) {
                        for(int k = 0; k<firstNegIndex; k++) {
                            leftProduct *= listB.get(k);
                        }
                        for(int k=firstNegIndex+1; k<listB.size(); k++) {
                            rightProduct *= listB.get(k);
                        }
                        if(maxProduct < leftProduct){
                            maxProduct = leftProduct;
                        }
                        if(maxProduct < rightProduct) {
                            maxProduct = rightProduct;
                        }
                    } else {
                        for(int k = 0; k<=firstNegIndex; k++) {
                            leftProduct = leftProduct*listB.get(k);
                        }
                        for(int k=firstNegIndex+1; k<lastNegIndex; k++) {
                            centerProduct = centerProduct*listB.get(k);
                        }
                        for(int k=lastNegIndex; k<listB.size(); k++) {
                            rightProduct = rightProduct * listB.get(k);
                        }
                        currProd = Math.max(leftProduct*centerProduct, centerProduct*rightProduct);
                        if(maxProduct < currProd) {
                            maxProduct = currProd;
                        }
                    }
                }
            }
        }

        System.out.println(splitList);
        return maxProduct;
    }

    public int maxProductNotWorking(int[] nums) {
        int currProd = nums[0];
        int maxProd = nums[0];
        int negMaxProd = maxProd;
        for(int i=1; i<nums.length; i++) {
            if(currProd ==0) {
                if(nums[i] == 0) {
                    continue;
                } else {
                    currProd = nums[i];
                }
                continue;
            }
            currProd = currProd*nums[i];
            if(currProd > 0) {
                maxProd = Math.max(maxProd, currProd);
            } else if(currProd < 0) {
                negMaxProd = Math.min(negMaxProd, currProd);
            }
        }
        return maxProd;
    }

    // ======================== Test Helper Methods ========================

    /**
     * Runs a single test case and prints the result.
     *
     * @param testNum the test case number
     * @param nums the input array
     * @param expected the expected maximum product
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
        MaximumProductSubarray solution = new MaximumProductSubarray();
        int totalTests = 0;
        int passedTests = 0;

        System.out.println("=".repeat(70));
        System.out.println("Testing LeetCode #152: Maximum Product Subarray");
        System.out.println("=".repeat(70));

        // Test 1: Example 1 - Subarray [2,3]
        {
            int[] nums = {2, 3, -2, 4};
            int expected = 6;
            int actual = solution.maxProduct(nums);
            runTest(++totalTests, nums, expected, actual,
                    "Example 1 - [2,3] has largest product 6");
            if (expected == actual) passedTests++;
        }

        // Test 2: Example 2 - Zero and negatives
        {
            int[] nums = {-2, 0, -1};
            int expected = 0;
            int actual = solution.maxProduct(nums);
            runTest(++totalTests, nums, expected, actual,
                    "Example 2 - Zero and negatives, [-2,-1] not contiguous");
            if (expected == actual) passedTests++;
        }

        // Test 3: Single element
        {
            int[] nums = {5};
            int expected = 5;
            int actual = solution.maxProduct(nums);
            runTest(++totalTests, nums, expected, actual,
                    "Single element - product is the element");
            if (expected == actual) passedTests++;
        }

        // Test 4: Two positive elements
        {
            int[] nums = {2, 3};
            int expected = 6;
            int actual = solution.maxProduct(nums);
            runTest(++totalTests, nums, expected, actual,
                    "Two positives - full array product");
            if (expected == actual) passedTests++;
        }

        // Test 5: Two elements, one negative - take single positive
        {
            int[] nums = {-2, 3};
            int expected = 3;
            int actual = solution.maxProduct(nums);
            runTest(++totalTests, nums, expected, actual,
                    "Negative and positive - max is 3");
            if (expected == actual) passedTests++;
        }

        // Test 6: Negative and zero - max product 0
        {
            int[] nums = {-2, 0};
            int expected = 0;
            int actual = solution.maxProduct(nums);
            runTest(++totalTests, nums, expected, actual,
                    "Negative and zero - max 0 from [0] or [-2,0]");
            if (expected == actual) passedTests++;
        }

        // Test 7: Two negatives - product positive
        {
            int[] nums = {-2, -3};
            int expected = 6;
            int actual = solution.maxProduct(nums);
            runTest(++totalTests, nums, expected, actual,
                    "Two negatives - product 6");
            if (expected == actual) passedTests++;
        }

        // Test 8: All negative odd length - best is pair or single
        {
            int[] nums = {-2, -3, -4};
            int expected = 12;  // [-3,-4] or [-2,-3] = 6, [-3,-4] = 12
            int actual = solution.maxProduct(nums);
            runTest(++totalTests, nums, expected, actual,
                    "Three negatives - max product 12 from [-3,-4]");
            if (expected == actual) passedTests++;
        }

        // Test 9: Contains zero
        {
            int[] nums = {0, 2, 3};
            int expected = 6;
            int actual = solution.maxProduct(nums);
            runTest(++totalTests, nums, expected, actual,
                    "Zero then positives - max 6 from [2,3]");
            if (expected == actual) passedTests++;
        }

        // Test 10: All zeros
        {
            int[] nums = {0, 0, 0};
            int expected = 0;
            int actual = solution.maxProduct(nums);
            runTest(++totalTests, nums, expected, actual,
                    "All zeros");
            if (expected == actual) passedTests++;
        }

        // Test 11: Two negative stretches - even negatives give positive product
        {
            int[] nums = {2, -2, 3, -3, 4};
            int expected = 144;  // [2,-2,3,-3,4] = 144
            int actual = solution.maxProduct(nums);
            runTest(++totalTests, nums, expected, actual,
                    "Alternating - full array product 144");
            if (expected == actual) passedTests++;
        }

        // Test 12: Single negative
        {
            int[] nums = {-3};
            int expected = -3;
            int actual = solution.maxProduct(nums);
            runTest(++totalTests, nums, expected, actual,
                    "Single negative - product is -3");
            if (expected == actual) passedTests++;
        }

        // Test 12: Negative in middle
        {
            int[] nums = {2, 3, -1, 4};
            int expected = 6;  // [2,3]
            int actual = solution.maxProduct(nums);
            runTest(++totalTests, nums, expected, actual,
                    "Negative in middle - best [2,3] = 6");
            if (expected == actual) passedTests++;
        }

        // Test 14: All positive
        {
            int[] nums = {1, 2, 3, 4};
            int expected = 24;
            int actual = solution.maxProduct(nums);
            runTest(++totalTests, nums, expected, actual,
                    "All positive - full product 24");
            if (expected == actual) passedTests++;
        }

        // Test 15: Zero resets product
        {
            int[] nums = {-2, 0, 3, 4};
            int expected = 12;
            int actual = solution.maxProduct(nums);
            runTest(++totalTests, nums, expected, actual,
                    "Zero resets - max 12 from [3,4]");
            if (expected == actual) passedTests++;
        }

        // Test 16: Constraint boundary - single element at boundary
        {
            int[] nums = {-10};
            int expected = -10;
            int actual = solution.maxProduct(nums);
            runTest(++totalTests, nums, expected, actual,
                    "Single element at value boundary");
            if (expected == actual) passedTests++;
        }

        printSummary(totalTests, passedTests);
    }
}
