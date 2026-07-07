package arraysAndHashing;

import java.util.Arrays;
import java.util.Random;

/**
 * 215. Kth Largest Element in an Array
 *
 * Difficulty: Medium
 * Topics: Array, Divide and Conquer, Sorting, Heap (Priority Queue), Quickselect
 *
 * Problem Description:
 * Given an integer array nums and an integer k, return the kth largest element
 * in the array.
 *
 * Note that it is the kth largest element in the sorted order, not the kth
 * distinct element.
 *
 * Can you solve it without sorting?
 *
 * Constraints:
 * - 1 <= k <= nums.length <= 10^5
 * - -10^4 <= nums[i] <= 10^4
 *
 * Examples:
 *
 * Example 1:
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 *
 * Example 2:
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 *
 * Approach:
 * Iterative Quickselect. Converts "kth largest" into "index (n-k) in ascending
 * sorted order", then repeatedly partitions the array (Hoare-style, pivot =
 * nums[low]) and narrows the [low, high] search range based on where the
 * partition index lands relative to the target, until the target index is
 * itself the partition point. Runs in a while loop rather than recursively,
 * so there's no call-stack growth regardless of array size.
 *
 * Time Complexity: O(n) average case; O(n^2) worst case (pivot is always the
 * first element of the range, so already-sorted/reverse-sorted input causes
 * maximally unbalanced partitions every step).
 * Space Complexity: O(1) auxiliary — partitioning is in-place on the input array.
 *
 * Key Learnings:
 * - Quickselect's iterative form (narrowing low/high in a loop) avoids the
 *   stack-depth risk that a recursive version would have on large inputs.
 * - Fixed first-element pivot selection is the classic quickselect weak point:
 *   it degrades to O(n^2) on already-sorted or reverse-sorted arrays. A
 *   randomized pivot (swap nums[low] with a random index in [low, high]
 *   before reading the pivot) makes that worst case vanishingly unlikely
 *   without changing the algorithm's structure.
 * - A LeetCode solution using a HashMap frequency count + walking down from
 *   the max value is a disguised counting sort — its O(n + R) time is only
 *   cheap because this problem bounds values to a small range (R <= 20001).
 *   It would degrade badly (or need to scan an enormous range) if values
 *   weren't tightly bounded, whereas quickselect's cost is independent of
 *   value magnitude.
 * - Comparison-based in-place quickselect also tends to beat HashMap-based
 *   counting approaches in practice due to cache locality: primitive array
 *   swaps stay in contiguous memory, while HashMap operations pay for boxing,
 *   hashing, and pointer-chasing through bucket nodes.
 *
 * Alternative Approaches:
 * 1. Sort then index nums[n-k] — O(n log n) time, O(log n) space, simplest.
 * 2. Min-heap bounded to size k — O(n log k) time, O(k) space, best when k << n.
 * 3. Max-heap of all n elements — O(n log n) time, O(n) space, rarely better than sorting.
 * 4. Counting sort via frequency map (external solution) — O(n + R) time where
 *    R is the value range, O(min(n, R)) space; only cheap when R is small and bounded.
 *
 * Detailed Learning Guide: See arraysAndHashing/learnings/KthLargestElementInArray-Learning.md
 *
 * @see <a href="https://leetcode.com/problems/kth-largest-element-in-an-array/">LeetCode Problem #215</a>
 */
public class KthLargestElementInArray {

    /**
     * Returns the kth largest element in nums.
     *
     * @param nums the input array of integers
     * @param k the rank (1-indexed) of the largest element to find
     * @return the kth largest element in nums
     */
    public int findKthLargest(int[] nums, int k) {
        k=nums.length-k;
        if(k<0 || k>nums.length){
            System.out.println("OUT OF BOUNDS");
            return 0;
        }
        int low=0, high=nums.length-1;
        int pIndex = -1;
        System.out.println("array : " + Arrays.toString(nums) + "\tk : " + k);
        while(low<=high){
            pIndex = getPartitionIndex(nums, low, high);
            System.out.println("pIndex" + pIndex);
            if(pIndex == k){
                return nums[pIndex];
            } else if(pIndex < k){
                low = pIndex+1;
            } else {
                high = pIndex - 1;
            }
        }
        return -1;
    }

    public int findKthLargestEasyAndFast(int[] nums, int k){
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public int getPartitionIndex(int[] nums, int low, int high){
        if(high-low == 0){
            return low;
        }
        // int randomIndex = low + new Random().nextInt(high - low + 1);
        // swap(nums, low, randomIndex);   // randomize before using nums[low] as pivot
        int pivot = nums[low];
        int i=low, j=high;
        while(i<j){
            while(nums[i]<=pivot && i<high){
                ++i;
            }
            while(nums[j]>pivot && j>low){
                --j;
            }
            if(i<j){
                swap(nums, i, j);
            }
        }
        swap(nums, low, j);
        return j;
    }

    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // ======================== Test Helper Methods ========================

    /**
     * Runs a single test case and prints the result.
     *
     * @param testNum the test case number
     * @param nums the input array
     * @param k the rank of the largest element to find
     * @param expected the expected output
     * @param actual the actual output from the solution
     * @param description brief description of the test case
     */
    private static void runTest(
            int testNum,
            int[] nums,
            int k,
            int expected,
            int actual,
            String description) {

        boolean passed = expected == actual;
        String status = passed ? "✓ PASS" : "✗ FAIL";

        System.out.println("\n" + status + " - Test " + testNum + ": " + description);
        System.out.println("  Input:    nums = " + Arrays.toString(nums) + ", k = " + k);
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
        KthLargestElementInArray solution = new KthLargestElementInArray();
        int totalTests = 0;
        int passedTests = 0;

        System.out.println("=".repeat(70));
        System.out.println("Testing LeetCode #215: Kth Largest Element in an Array");
        System.out.println("=".repeat(70));

        // Test 1: Example 1
        {
            int[] nums = {3, 2, 1, 5, 6, 4};
            int k = 2;
            int expected = 5;
            int actual = solution.findKthLargest(nums, k);
            runTest(++totalTests, nums, k, expected, actual, "Example 1 - Basic case");
            if (expected == actual) passedTests++;
        }

        // Test 2: Example 2 - duplicates present
        {
            int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
            int k = 4;
            int expected = 4;
            int actual = solution.findKthLargest(nums, k);
            runTest(++totalTests, nums, k, expected, actual, "Example 2 - Duplicates present");
            if (expected == actual) passedTests++;
        }

        // Test 3: Edge case - single element, k = 1
        {
            int[] nums = {7};
            int k = 1;
            int expected = 7;
            int actual = solution.findKthLargest(nums, k);
            runTest(++totalTests, nums, k, expected, actual, "Edge case - single element");
            if (expected == actual) passedTests++;
        }

        // Test 4: k = 1 (largest element)
        {
            int[] nums = {9, 3, 2, 4, 8};
            int k = 1;
            int expected = 9;
            int actual = solution.findKthLargest(nums, k);
            runTest(++totalTests, nums, k, expected, actual, "k = 1 returns the maximum");
            if (expected == actual) passedTests++;
        }

        // Test 5: k = nums.length (smallest element)
        {
            int[] nums = {9, 3, 2, 4, 8};
            int k = 5;
            int expected = 2;
            int actual = solution.findKthLargest(nums, k);
            runTest(++totalTests, nums, k, expected, actual, "k = length returns the minimum");
            if (expected == actual) passedTests++;
        }

        // Test 6: All elements identical
        {
            int[] nums = {4, 4, 4, 4, 4};
            int k = 3;
            int expected = 4;
            int actual = solution.findKthLargest(nums, k);
            runTest(++totalTests, nums, k, expected, actual, "All elements identical");
            if (expected == actual) passedTests++;
        }

        // Test 7: Already sorted ascending
        {
            int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
            int k = 3;
            int expected = 6;
            int actual = solution.findKthLargest(nums, k);
            runTest(++totalTests, nums, k, expected, actual, "Already sorted ascending");
            if (expected == actual) passedTests++;
        }

        // Test 8: Already sorted descending
        {
            int[] nums = {8, 7, 6, 5, 4, 3, 2, 1};
            int k = 3;
            int expected = 6;
            int actual = solution.findKthLargest(nums, k);
            runTest(++totalTests, nums, k, expected, actual, "Already sorted descending");
            if (expected == actual) passedTests++;
        }

        // Test 9: Negative numbers
        {
            int[] nums = {-1, -5, -3, -2, -4};
            int k = 2;
            int expected = -2;
            int actual = solution.findKthLargest(nums, k);
            runTest(++totalTests, nums, k, expected, actual, "Negative numbers only");
            if (expected == actual) passedTests++;
        }

        // Test 10: Mixed positive and negative
        {
            int[] nums = {-10, 4, -2, 0, 7, -1, 3};
            int k = 3;
            int expected = 3;
            int actual = solution.findKthLargest(nums, k);
            runTest(++totalTests, nums, k, expected, actual, "Mixed positive and negative numbers");
            if (expected == actual) passedTests++;
        }

        // Test 11: Constraint boundary values
        {
            int[] nums = {10000, -10000, 0, 5000, -5000};
            int k = 1;
            int expected = 10000;
            int actual = solution.findKthLargest(nums, k);
            runTest(++totalTests, nums, k, expected, actual, "Constraint boundary values");
            if (expected == actual) passedTests++;
        }

        // Test 12: Two elements
        {
            int[] nums = {2, 1};
            int k = 2;
            int expected = 1;
            int actual = solution.findKthLargest(nums, k);
            runTest(++totalTests, nums, k, expected, actual, "Two elements, k = length");
            if (expected == actual) passedTests++;
        }

        printSummary(totalTests, passedTests);
    }
}
