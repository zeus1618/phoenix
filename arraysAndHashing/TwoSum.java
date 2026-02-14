package arraysAndHashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode #1: Two Sum
 * Difficulty: Easy
 * 
 * Problem Description:
 * Given an array of integers nums and an integer target, return indices of the 
 * two numbers such that they add up to target.
 * 
 * You may assume that each input would have exactly one solution, and you may 
 * not use the same element twice.
 * 
 * You can return the answer in any order.
 * 
 * Constraints:
 * - 2 <= nums.length <= 10^4
 * - -10^9 <= nums[i] <= 10^9
 * - -10^9 <= target <= 10^9
 * - Only one valid answer exists
 * 
 * Follow-up:
 * Can you come up with an algorithm that is less than O(n²) time complexity?
 * 
 * Solution Approach:
 * Used HashMap to store complement pairs. For each element, calculate its complement
 * (target - current) and check if it exists in the map. If found, return the indices
 * immediately. Otherwise, store current element with its index for future lookups.
 * This achieves O(n) time complexity with single pass through the array.
 * 
 * Time Complexity: O(n) - Single pass through array with O(1) HashMap operations
 * Space Complexity: O(n) - HashMap storage for up to n elements
 * 
 * Key Learnings:
 * 1. HashMap Pattern: For "find pair with sum" problems, use complement = target - current
 * 2. Theory vs Practice: O(n) HashMap can be slower than O(n²) for small arrays (n<100)
 *    due to constant factors (hash computation, memory allocation, cache misses)
 * 3. Two-Pointer Limitation: Two-pointer technique requires SORTED arrays. On unsorted
 *    arrays, it degrades to O(n²) brute force
 * 4. LeetCode Metrics: Runtime comparisons can be misleading due to test case bias
 *    (small sizes, early answers) - always optimize for worst-case and scalability
 * 5. Early Termination: Gap-based O(n²) solutions can beat O(n) on LeetCode's small
 *    test cases but fail on large inputs - production needs guaranteed O(n)
 * 
 * Alternative Approaches:
 * 1. Brute Force - O(n²) time, O(1) space - Check all pairs
 * 2. Sort + Two Pointer - O(n log n) time, O(n) space - Must track original indices
 * 3. Gap-Based - O(n²) time, O(1) space - Fast on small arrays with early answers
 * 
 * Production Recommendation:
 * HashMap solution is optimal for scalability and worst-case guarantees, despite
 * potentially showing slower on LeetCode benchmarks for small test cases.
 */
class TwoSum {
    
    /**
     * Finds two numbers in the array that add up to target
     * @param nums - array of integers
     * @param target - target sum
     * @return array of two indices [i, j] where nums[i] + nums[j] = target
     */

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>(2*nums.length);
        for(int i=0; i<nums.length ; i++) {
            int complement = target - nums[i];
            if(numMap.containsKey(complement)) {
                return new int[]{i, numMap.get(complement)};
            }
            numMap.put(nums[i], i);
        }
        return new int[]{};
    }

    public int[] twoSumForNeetCode(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>(2*nums.length);
        for(int i=0; i<nums.length ; i++) {
            int complement = target - nums[i];
            if(numMap.containsKey(complement)) {
                int complementIndex = numMap.get(complement);
                if(i<complementIndex)
                    return new int[]{i, complementIndex};
                else
                    return new int[]{complementIndex, i};
            }
            numMap.put(nums[i], i);
        }
        return new int[]{};
    }

    public int[] twoSumBestSolutionFromLeetCode(int[] nums, int target) {
        int n=nums.length;
        for(int i=1;i<n;i++){
            for(int j=i;j<n;j++){
                if(nums[j-i]+nums[j]==target){
                    return new int[] {j-i,j};
                }
            }
        }
        return null;
    }

    public int[] twoSumFirstAttempt(int[] nums, int target) {
        for(int i=0 ; i<nums.length ; i++) {
            int b = target - nums[i];
            for(int j=i+1, k=nums.length-1; j<=k ; j++,k--) {
                // System.out.println("i : " + i + "     j : " + j + "     k : " + k);
                if(nums[j] == b)
                    return new int[] {i,j};
                else if(nums[k] == b)
                    return new int[] {i,k};
            }
        }
        return new int[]{};
    }
    
    /**
     * Helper method to check if two arrays are equal
     */
    private static boolean arraysEqual(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) return false;
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        return Arrays.equals(arr1, arr2);
    }
    
    /**
     * Main method to test the solution with various test cases
     */
    public static void main(String[] args) {
        TwoSum solution = new TwoSum();
        
        int totalTests = 10;
        int[][] results = new int[totalTests][];
        int[][] expected = new int[totalTests][];
        String[] testResults = new String[totalTests];
        String[] testDescriptions = new String[totalTests];
        
        // Test Case 1: Example 1 from problem
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        results[0] = solution.twoSum(nums1, target1);
        expected[0] = new int[]{0, 1};
        testResults[0] = arraysEqual(results[0].clone(), expected[0].clone()) ? "PASS ✓" : "FAIL ✗";
        testDescriptions[0] = "Example 1: [2,7,11,15], target=9";
        System.out.println("Test Case 1:");
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", target = " + target1);
        System.out.println("Output: " + Arrays.toString(results[0]));
        System.out.println("Expected: " + Arrays.toString(expected[0]));
        System.out.println("Status: " + testResults[0]);
        System.out.println();
        
        // Test Case 2: Example 2 from problem
        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        results[1] = solution.twoSum(nums2, target2);
        expected[1] = new int[]{1, 2};
        testResults[1] = arraysEqual(results[1].clone(), expected[1].clone()) ? "PASS ✓" : "FAIL ✗";
        testDescriptions[1] = "Example 2: [3,2,4], target=6";
        System.out.println("Test Case 2:");
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", target = " + target2);
        System.out.println("Output: " + Arrays.toString(results[1]));
        System.out.println("Expected: " + Arrays.toString(expected[1]));
        System.out.println("Status: " + testResults[1]);
        System.out.println();
        
        // Test Case 3: Example 3 from problem - duplicates
        int[] nums3 = {3, 3};
        int target3 = 6;
        results[2] = solution.twoSum(nums3, target3);
        expected[2] = new int[]{0, 1};
        testResults[2] = arraysEqual(results[2].clone(), expected[2].clone()) ? "PASS ✓" : "FAIL ✗";
        testDescriptions[2] = "Duplicates: [3,3], target=6";
        System.out.println("Test Case 3:");
        System.out.println("Input: nums = " + Arrays.toString(nums3) + ", target = " + target3);
        System.out.println("Output: " + Arrays.toString(results[2]));
        System.out.println("Expected: " + Arrays.toString(expected[2]));
        System.out.println("Status: " + testResults[2]);
        System.out.println();
        
        // Test Case 4: Negative numbers
        int[] nums4 = {-1, -2, -3, -4, -5};
        int target4 = -8;
        results[3] = solution.twoSum(nums4, target4);
        expected[3] = new int[]{2, 4};
        testResults[3] = arraysEqual(results[3].clone(), expected[3].clone()) ? "PASS ✓" : "FAIL ✗";
        testDescriptions[3] = "Negative numbers: [-1,-2,-3,-4,-5], target=-8";
        System.out.println("Test Case 4:");
        System.out.println("Input: nums = " + Arrays.toString(nums4) + ", target = " + target4);
        System.out.println("Output: " + Arrays.toString(results[3]));
        System.out.println("Expected: " + Arrays.toString(expected[3]));
        System.out.println("Status: " + testResults[3]);
        System.out.println();
        
        // Test Case 5: Mix of positive and negative
        int[] nums5 = {-3, 4, 3, 90};
        int target5 = 0;
        results[4] = solution.twoSum(nums5, target5);
        expected[4] = new int[]{0, 2};
        testResults[4] = arraysEqual(results[4].clone(), expected[4].clone()) ? "PASS ✓" : "FAIL ✗";
        testDescriptions[4] = "Mixed signs, target=0";
        System.out.println("Test Case 5:");
        System.out.println("Input: nums = " + Arrays.toString(nums5) + ", target = " + target5);
        System.out.println("Output: " + Arrays.toString(results[4]));
        System.out.println("Expected: " + Arrays.toString(expected[4]));
        System.out.println("Status: " + testResults[4]);
        System.out.println();
        
        // Test Case 6: Minimum array size
        int[] nums6 = {1, 2};
        int target6 = 3;
        results[5] = solution.twoSum(nums6, target6);
        expected[5] = new int[]{0, 1};
        testResults[5] = arraysEqual(results[5].clone(), expected[5].clone()) ? "PASS ✓" : "FAIL ✗";
        testDescriptions[5] = "Minimum size: [1,2], target=3";
        System.out.println("Test Case 6:");
        System.out.println("Input: nums = " + Arrays.toString(nums6) + ", target = " + target6);
        System.out.println("Output: " + Arrays.toString(results[5]));
        System.out.println("Expected: " + Arrays.toString(expected[5]));
        System.out.println("Status: " + testResults[5]);
        System.out.println();
        
        // Test Case 7: Large numbers
        int[] nums7 = {1000000000, 1000000, 500000000};
        int target7 = 1001000000;
        results[6] = solution.twoSum(nums7, target7);
        expected[6] = new int[]{0, 1};
        testResults[6] = arraysEqual(results[6].clone(), expected[6].clone()) ? "PASS ✓" : "FAIL ✗";
        testDescriptions[6] = "Large numbers";
        System.out.println("Test Case 7:");
        System.out.println("Input: nums = " + Arrays.toString(nums7) + ", target = " + target7);
        System.out.println("Output: " + Arrays.toString(results[6]));
        System.out.println("Expected: " + Arrays.toString(expected[6]));
        System.out.println("Status: " + testResults[6]);
        System.out.println();
        
        // Test Case 8: Answer at the end of array
        int[] nums8 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int target8 = 17;
        results[7] = solution.twoSum(nums8, target8);
        expected[7] = new int[]{7, 8};
        testResults[7] = arraysEqual(results[7].clone(), expected[7].clone()) ? "PASS ✓" : "FAIL ✗";
        testDescriptions[7] = "End of array: target=17";
        System.out.println("Test Case 8:");
        System.out.println("Input: nums = " + Arrays.toString(nums8) + ", target = " + target8);
        System.out.println("Output: " + Arrays.toString(results[7]));
        System.out.println("Expected: " + Arrays.toString(expected[7]));
        System.out.println("Status: " + testResults[7]);
        System.out.println();
        
        // Test Case 9: Zeros in array
        int[] nums9 = {0, 4, 3, 0};
        int target9 = 0;
        results[8] = solution.twoSum(nums9, target9);
        expected[8] = new int[]{0, 3};
        testResults[8] = arraysEqual(results[8].clone(), expected[8].clone()) ? "PASS ✓" : "FAIL ✗";
        testDescriptions[8] = "Zeros: [0,4,3,0], target=0";
        System.out.println("Test Case 9:");
        System.out.println("Input: nums = " + Arrays.toString(nums9) + ", target = " + target9);
        System.out.println("Output: " + Arrays.toString(results[8]));
        System.out.println("Expected: " + Arrays.toString(expected[8]));
        System.out.println("Status: " + testResults[8]);
        System.out.println();
        
        // Test Case 10: First and last element
        int[] nums10 = {5, 75, 25};
        int target10 = 30;
        results[9] = solution.twoSum(nums10, target10);
        expected[9] = new int[]{0, 2};
        testResults[9] = arraysEqual(results[9].clone(), expected[9].clone()) ? "PASS ✓" : "FAIL ✗";
        testDescriptions[9] = "First and last: [5,75,25], target=30";
        System.out.println("Test Case 10:");
        System.out.println("Input: nums = " + Arrays.toString(nums10) + ", target = " + target10);
        System.out.println("Output: " + Arrays.toString(results[9]));
        System.out.println("Expected: " + Arrays.toString(expected[9]));
        System.out.println("Status: " + testResults[9]);
        System.out.println();
        
        // Calculate summary statistics
        int passed = 0;
        int failed = 0;
        for (int i = 0; i < totalTests; i++) {
            if (testResults[i].contains("PASS")) {
                passed++;
            } else {
                failed++;
            }
        }
        
        // Print Summary
        System.out.println("=".repeat(60));
        System.out.println("TEST SUMMARY");
        System.out.println("=".repeat(60));
        System.out.println();
        
        // Detailed results table
        System.out.println("Test Case Results:");
        System.out.println("-".repeat(60));
        for (int i = 0; i < totalTests; i++) {
            System.out.printf("Test %2d: %-40s %s%n", 
                (i + 1), testDescriptions[i], testResults[i]);
        }
        System.out.println("-".repeat(60));
        System.out.println();
        
        // Statistics
        System.out.println("Statistics:");
        System.out.println("  Total Tests:  " + totalTests);
        System.out.println("  Passed:       " + passed + " ✓");
        System.out.println("  Failed:       " + failed + (failed > 0 ? " ✗" : ""));
        System.out.println("  Success Rate: " + String.format("%.1f", (passed * 100.0 / totalTests)) + "%");
        System.out.println();
        
        // Overall result
        if (failed == 0) {
            System.out.println("🎉 ALL TESTS PASSED! 🎉");
        } else {
            System.out.println("⚠️  SOME TESTS FAILED - Review failed cases above");
        }
        
        System.out.println("=".repeat(60));
    }
}
