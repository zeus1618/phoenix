package arraysAndHashing;

import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

/**
 * LeetCode #217: Contains Duplicate
 * Difficulty: Easy
 * 
 * Problem Description:
 * Given an integer array nums, return true if any value appears at least twice 
 * in the array, and return false if every element is distinct.
 * 
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - -10^9 <= nums[i] <= 10^9
 * 
 * Approach:
 * Use a HashSet to track seen elements. If we encounter an element that's already
 * in the set, we have found a duplicate and return true. If we traverse the entire
 * array without finding duplicates, return false.
 * 
 * Time Complexity: O(n) where n is the length of the array
 * Space Complexity: O(n) for the HashSet storage
 */
class ContainsDuplicate {
    
    /**
     * Checks if the array contains any duplicate elements
     * @param nums - input integer array
     * @return true if duplicates exist, false otherwise
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for(int num:nums) {
            if(!numSet.add(num)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Main method to test the solution with various test cases
     */
    public static void main(String[] args) {
        ContainsDuplicate solution = new ContainsDuplicate();
        
        // Test Case 1: Array with duplicates at different positions
        int[] test1 = {1, 2, 3, 1};
        boolean result1 = solution.containsDuplicate(test1);
        System.out.println("Test Case 1:");
        System.out.println("Input: " + Arrays.toString(test1));
        System.out.println("Output: " + result1);
        System.out.println("Expected: true");
        System.out.println("Status: " + (result1 == true ? "PASS ✓" : "FAIL ✗"));
        System.out.println();
        
        // Test Case 2: Array with all distinct elements
        int[] test2 = {1, 2, 3, 4};
        boolean result2 = solution.containsDuplicate(test2);
        System.out.println("Test Case 2:");
        System.out.println("Input: " + Arrays.toString(test2));
        System.out.println("Output: " + result2);
        System.out.println("Expected: false");
        System.out.println("Status: " + (result2 == false ? "PASS ✓" : "FAIL ✗"));
        System.out.println();
        
        // Test Case 3: Array with multiple duplicates
        int[] test3 = {1, 1, 1, 3, 3, 4, 3, 2, 4, 2};
        boolean result3 = solution.containsDuplicate(test3);
        System.out.println("Test Case 3:");
        System.out.println("Input: " + Arrays.toString(test3));
        System.out.println("Output: " + result3);
        System.out.println("Expected: true");
        System.out.println("Status: " + (result3 == true ? "PASS ✓" : "FAIL ✗"));
        System.out.println();
        
        // Test Case 4: Single element array (no duplicates possible)
        int[] test4 = {1};
        boolean result4 = solution.containsDuplicate(test4);
        System.out.println("Test Case 4:");
        System.out.println("Input: " + Arrays.toString(test4));
        System.out.println("Output: " + result4);
        System.out.println("Expected: false");
        System.out.println("Status: " + (result4 == false ? "PASS ✓" : "FAIL ✗"));
        System.out.println();
        
        // Test Case 5: Two elements with duplicate
        int[] test5 = {5, 5};
        boolean result5 = solution.containsDuplicate(test5);
        System.out.println("Test Case 5:");
        System.out.println("Input: " + Arrays.toString(test5));
        System.out.println("Output: " + result5);
        System.out.println("Expected: true");
        System.out.println("Status: " + (result5 == true ? "PASS ✓" : "FAIL ✗"));
        System.out.println();
        
        // Test Case 6: Array with negative numbers
        int[] test6 = {-1, -2, -3, -1};
        boolean result6 = solution.containsDuplicate(test6);
        System.out.println("Test Case 6:");
        System.out.println("Input: " + Arrays.toString(test6));
        System.out.println("Output: " + result6);
        System.out.println("Expected: true");
        System.out.println("Status: " + (result6 == true ? "PASS ✓" : "FAIL ✗"));
        System.out.println();
        
        // Test Case 7: Array with zeros
        int[] test7 = {0, 1, 2, 3, 0};
        boolean result7 = solution.containsDuplicate(test7);
        System.out.println("Test Case 7:");
        System.out.println("Input: " + Arrays.toString(test7));
        System.out.println("Output: " + result7);
        System.out.println("Expected: true");
        System.out.println("Status: " + (result7 == true ? "PASS ✓" : "FAIL ✗"));
        System.out.println();
        
        // Test Case 8: Large array with no duplicates
        int[] test8 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        boolean result8 = solution.containsDuplicate(test8);
        System.out.println("Test Case 8:");
        System.out.println("Input: " + Arrays.toString(test8));
        System.out.println("Output: " + result8);
        System.out.println("Expected: false");
        System.out.println("Status: " + (result8 == false ? "PASS ✓" : "FAIL ✗"));
        System.out.println();
        
        // Summary
        System.out.println("=".repeat(50));
        System.out.println("All test cases completed!");
        System.out.println("=".repeat(50));
    }
}