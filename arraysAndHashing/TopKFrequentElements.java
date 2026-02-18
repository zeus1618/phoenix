package arraysAndHashing;

import java.util.*;

/**
 * LeetCode Problem 347: Top K Frequent Elements
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/top-k-frequent-elements/
 *
 * <p>Problem Description:
 * Given an integer array nums and an integer k, return the k most frequent elements.
 * You may return the answer in any order.
 *
 * <p>Constraints:
 * - 1 <= nums.length <= 10^5
 * - -10^4 <= nums[i] <= 10^4
 * - k is in the range [1, the number of unique elements in the array]
 * - It is guaranteed that the answer is unique
 *
 * <p>Follow-up:
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 *
 * <p>Examples:
 * Example 1:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Explanation: 1 appears 3 times, 2 appears 2 times, 3 appears 1 time
 *
 * Example 2:
 * Input: nums = [1], k = 1
 * Output: [1]
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
 * [To be documented after analysis]
 */
public class TopKFrequentElements {

    /**
     * Returns the k most frequent elements from the given array.
     *
     * @param nums the input array of integers
     * @param k the number of most frequent elements to return
     * @return an array containing the k most frequent elements
     */
    public int[] topKFrequent(int[] nums, int k) {

        return new int[]{};
    }
    public int[] topKFrequentFromLeetCode(int[] nums, int k) {
        Map<Integer, Integer> mp = new HashMap<>();
        for(int num : nums) {
            mp.put(num, mp.getOrDefault(num, 0)+1);
        } 

        PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> y.v - x.v);

        for(Map.Entry<Integer, Integer> ent : mp.entrySet()) {
            pq.add(new Pair(ent.getKey(), ent.getValue()));
        }

        int[] ans = new int[k];
        for(int i = 0; i < k; i++) {
            ans[i] = pq.poll().k;
        }
        return ans;
    }

    class Pair {
        int k;
        int v;
        Pair(int k, int v) {
            this.k = k;
            this.v = v;
        }
    }

    // ======================== Test Helper Methods ========================

    /**
     * Runs a single test case and prints the result.
     *
     * @param testNum the test case number
     * @param nums the input array
     * @param k the number of most frequent elements
     * @param expected the expected output (can be in any order)
     * @param actual the actual output from the solution
     * @param description brief description of the test case
     */
    private static void runTest(
            int testNum,
            int[] nums,
            int k,
            int[] expected,
            int[] actual,
            String description) {
        
        // Sort both arrays for comparison since order doesn't matter
        Arrays.sort(expected);
        Arrays.sort(actual);
        
        boolean passed = Arrays.equals(expected, actual);
        String status = passed ? "✓ PASS" : "✗ FAIL";
        
        System.out.println("\n" + status + " - Test " + testNum + ": " + description);
        System.out.println("  Input:    nums = " + Arrays.toString(nums) + ", k = " + k);
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
        TopKFrequentElements solution = new TopKFrequentElements();
        int totalTests = 0;
        int passedTests = 0;

        System.out.println("=".repeat(70));
        System.out.println("Testing LeetCode #347: Top K Frequent Elements");
        System.out.println("=".repeat(70));

        // Test 1: Example 1 - Basic case with k=2
        {
            int[] nums = {1, 1, 1, 2, 2, 3};
            int k = 2;
            int[] expected = {1, 2};
            int[] actual = solution.topKFrequent(nums, k);
            runTest(++totalTests, nums, k, expected, actual, 
                "Example 1 - Basic case with k=2");
            if (Arrays.equals(sortArray(expected), sortArray(actual))) passedTests++;
        }

        // Test 2: Example 2 - Single element array
        {
            int[] nums = {1};
            int k = 1;
            int[] expected = {1};
            int[] actual = solution.topKFrequent(nums, k);
            runTest(++totalTests, nums, k, expected, actual, 
                "Example 2 - Single element");
            if (Arrays.equals(sortArray(expected), sortArray(actual))) passedTests++;
        }

        // Test 3: All elements have same frequency
        {
            int[] nums = {1, 2, 3};
            int k = 2;
            // Any 2 elements are valid since all have frequency 1
            int[] actual = solution.topKFrequent(nums, k);
            boolean passed = actual.length == k && isValidResult(nums, actual, k);
            totalTests++;
            if (passed) passedTests++;
            
            System.out.println("\n" + (passed ? "✓ PASS" : "✗ FAIL") + 
                " - Test " + totalTests + ": All elements same frequency");
            System.out.println("  Input:    nums = " + Arrays.toString(nums) + ", k = " + k);
            System.out.println("  Actual:   " + Arrays.toString(actual));
            System.out.println("  Note: Any " + k + " elements valid (all have same frequency)");
        }

        // Test 4: k equals number of unique elements
        {
            int[] nums = {1, 1, 2, 2, 3, 3};
            int k = 3;
            int[] expected = {1, 2, 3};
            int[] actual = solution.topKFrequent(nums, k);
            runTest(++totalTests, nums, k, expected, actual, 
                "k equals number of unique elements");
            if (Arrays.equals(sortArray(expected), sortArray(actual))) passedTests++;
        }

        // Test 5: Larger array with clear frequency differences
        {
            int[] nums = {4, 1, -1, 2, -1, 2, 3, 4, 4, 4};
            int k = 2;
            int[] expected = {4, -1};  // 4 appears 4 times, -1 appears 2 times
            int[] actual = solution.topKFrequent(nums, k);
            runTest(++totalTests, nums, k, expected, actual, 
                "Larger array with clear frequencies");
            if (Arrays.equals(sortArray(expected), sortArray(actual))) passedTests++;
        }

        // Test 6: Negative numbers
        {
            int[] nums = {-1, -1, -2, -2, -2, -3};
            int k = 2;
            int[] expected = {-2, -1};
            int[] actual = solution.topKFrequent(nums, k);
            runTest(++totalTests, nums, k, expected, actual, 
                "Array with negative numbers");
            if (Arrays.equals(sortArray(expected), sortArray(actual))) passedTests++;
        }

        // Test 7: Large frequency differences
        {
            int[] nums = {1, 1, 1, 1, 1, 2, 2, 3};
            int k = 1;
            int[] expected = {1};  // 1 appears 5 times (clearly most frequent)
            int[] actual = solution.topKFrequent(nums, k);
            runTest(++totalTests, nums, k, expected, actual, 
                "Large frequency differences");
            if (Arrays.equals(sortArray(expected), sortArray(actual))) passedTests++;
        }

        // Test 8: Multiple elements with same frequency
        {
            int[] nums = {1, 1, 2, 2, 3, 3, 4, 4};
            int k = 2;
            // Any 2 elements are valid since all have frequency 2
            int[] actual = solution.topKFrequent(nums, k);
            boolean passed = actual.length == k && isValidResult(nums, actual, k);
            totalTests++;
            if (passed) passedTests++;
            
            System.out.println("\n" + (passed ? "✓ PASS" : "✗ FAIL") + 
                " - Test " + totalTests + ": Multiple same frequencies");
            System.out.println("  Input:    nums = " + Arrays.toString(nums) + ", k = " + k);
            System.out.println("  Actual:   " + Arrays.toString(actual));
            System.out.println("  Note: Any " + k + " elements valid (all have frequency 2)");
        }

        // Test 9: Boundary - maximum array size (performance test)
        {
            int[] nums = new int[100];
            for (int i = 0; i < 50; i++) {
                nums[i] = 1;  // 1 appears 50 times
            }
            for (int i = 50; i < 75; i++) {
                nums[i] = 2;  // 2 appears 25 times
            }
            for (int i = 75; i < 100; i++) {
                nums[i] = 3;  // 3 appears 25 times
            }
            int k = 1;
            int[] expected = {1};
            
            long startTime = System.nanoTime();
            int[] actual = solution.topKFrequent(nums, k);
            long endTime = System.nanoTime();
            double elapsedMs = (endTime - startTime) / 1_000_000.0;
            
            runTest(++totalTests, nums, k, expected, actual, 
                String.format("Performance test (n=100, %.2f ms)", elapsedMs));
            if (Arrays.equals(sortArray(expected), sortArray(actual))) passedTests++;
        }

        // Test 10: Edge case - all same element
        {
            int[] nums = {5, 5, 5, 5, 5};
            int k = 1;
            int[] expected = {5};
            int[] actual = solution.topKFrequent(nums, k);
            runTest(++totalTests, nums, k, expected, actual, 
                "All same element");
            if (Arrays.equals(sortArray(expected), sortArray(actual))) passedTests++;
        }

        // Test 11: Mixed positive and negative with zero
        {
            int[] nums = {0, 0, 0, 1, 1, -1, -1, -1, -1};
            int k = 2;
            int[] expected = {-1, 0};  // -1 appears 4 times, 0 appears 3 times
            int[] actual = solution.topKFrequent(nums, k);
            runTest(++totalTests, nums, k, expected, actual, 
                "Mixed with zero");
            if (Arrays.equals(sortArray(expected), sortArray(actual))) passedTests++;
        }

        // Test 12: Two elements only
        {
            int[] nums = {1, 2};
            int k = 2;
            int[] expected = {1, 2};
            int[] actual = solution.topKFrequent(nums, k);
            runTest(++totalTests, nums, k, expected, actual, 
                "Two elements, k=2");
            if (Arrays.equals(sortArray(expected), sortArray(actual))) passedTests++;
        }

        printSummary(totalTests, passedTests);
    }

    /**
     * Helper method to sort an array for comparison.
     */
    private static int[] sortArray(int[] arr) {
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        return sorted;
    }

    /**
     * Validates that the result contains valid elements from the input array.
     */
    private static boolean isValidResult(int[] nums, int[] result, int k) {
        if (result.length != k) return false;
        
        // Build frequency map
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        
        // Check all result elements exist in input
        for (int num : result) {
            if (!freqMap.containsKey(num)) return false;
        }
        
        return true;
    }
}
