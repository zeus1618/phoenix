package arraysAndHashing;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * LeetCode #242: Valid Anagram
 * Difficulty: Easy
 * 
 * Problem Description:
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word 
 * or phrase, typically using all the original letters exactly once.
 * 
 * Constraints:
 * - 1 <= s.length, t.length <= 5 * 10^4
 * - s and t consist of lowercase English letters
 * 
 * Follow-up:
 * What if the inputs contain Unicode characters? How would you adapt your solution?
 * 
 * Solution Approach:
 * Used frequency counter array (size 26 for lowercase English letters).
 * Single loop to increment count for characters in string s and decrement for characters 
 * in string t. If all counts are zero after processing, the strings are anagrams.
 * 
 * Time Complexity: O(n) where n is the length of the strings
 * Space Complexity: O(1) - constant space (array of 26 elements)
 * 
 * Key Learning:
 * Method calls like charAt() have overhead compared to direct array access. Converting 
 * strings to char arrays first can provide ~2.5x performance improvement in tight loops.
 * This demonstrates that algorithmic complexity (Big-O) doesn't tell the full story - 
 * the cost of individual operations matters significantly in practice.
 * 
 * Alternative approaches considered:
 * 1. Two HashMaps - Works but uses O(2n) space and has more overhead
 * 2. Sorting both strings - O(n log n) time complexity, simpler but slower
 * 3. Single HashMap with increment/decrement - O(n) space, better than two maps
 * 4. toCharArray() approach - Slightly better performance but uses O(n) extra space
 */
class ValidAnagram {
    
    /**
     * Determines if two strings are anagrams of each other
     * @param s - first string
     * @param t - second string
     * @return true if t is an anagram of s, false otherwise
     */
    public boolean isAnagram(String s, String t) {
        if(s.length() == t.length()) {
            int[] freq = new int[26];
            for(int i=0; i<s.length(); i++){
                ++freq[s.charAt(i) - 'a'];
                --freq[t.charAt(i) - 'a'];
            }
            for(int v : freq){
                if(v != 0){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean isAnagramSolutionFromLeetCode(String s, String t) {
        int n=s.length();
        int m=t.length();
        if(n!=m) return false;
        char ch1[]=s.toCharArray();
        char ch2[]=t.toCharArray();
        int freq1[]=new int[26];
        int freq2[]=new int[26];
        for(int i=0;i<n;i++){
          freq1[ch1[i]-'a']++;
        }
        for(int i=0;i<n;i++){
         freq2[ch2[i]-'a']++;
        }
        for(int i=0;i<26;i++){
         if(freq1[i]!=freq2[i]){
             return false;
         }
        }
         return true;
     }

    /**
     * First attempt using two HashMaps (less optimal approach)
     * Kept for reference and learning purposes
     */
    public boolean isAnagramFirstAttempt(String s, String t) {
        if(s.length() == t.length()){
            Map<Character, Integer> sMap = new HashMap<>();
            Map<Character, Integer> tMap = new HashMap<>();
            for (int i=0; i<s.length(); i++){
                if(sMap.get(s.charAt(i)) == null) {
                    sMap.put(s.charAt(i), 1);
                } else {
                    sMap.put(s.charAt(i), sMap.get(s.charAt(i))+1);
                }
                if(tMap.get(t.charAt(i)) == null) {
                    tMap.put(t.charAt(i), 1);
                } else {
                    tMap.put(t.charAt(i), tMap.get(t.charAt(i))+1);
                }
            }
            for(Entry<Character, Integer> entry: sMap.entrySet()) {
                if(entry.getValue().equals(tMap.get(entry.getKey()))){
                    tMap.remove(entry.getKey());
                } else {
                    return false;
                }
            }
            if(tMap.size() >0){
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Main method to test the solution with various test cases
     */
    public static void main(String[] args) {
        ValidAnagram solution = new ValidAnagram();
        
        int totalTests = 11;
        boolean[] results = new boolean[totalTests];
        boolean[] expected = new boolean[totalTests];
        String[] testResults = new String[totalTests];
        String[] testDescriptions = new String[totalTests];
        
        // Test Case 1: Valid anagram - Example 1 from problem
        String s1 = "anagram";
        String t1 = "nagaram";
        results[0] = solution.isAnagram(s1, t1);
        expected[0] = true;
        testResults[0] = (results[0] == expected[0] ? "PASS ✓" : "FAIL ✗");
        testDescriptions[0] = "Valid anagram (Example 1)";
        System.out.println("Test Case 1:");
        System.out.println("Input: s = \"" + s1 + "\", t = \"" + t1 + "\"");
        System.out.println("Output: " + results[0]);
        System.out.println("Expected: " + expected[0]);
        System.out.println("Status: " + testResults[0]);
        System.out.println();
        
        // Test Case 2: Not an anagram - Example 2 from problem
        String s2 = "rat";
        String t2 = "car";
        results[1] = solution.isAnagram(s2, t2);
        expected[1] = false;
        testResults[1] = (results[1] == expected[1] ? "PASS ✓" : "FAIL ✗");
        testDescriptions[1] = "Not an anagram (Example 2)";
        System.out.println("Test Case 2:");
        System.out.println("Input: s = \"" + s2 + "\", t = \"" + t2 + "\"");
        System.out.println("Output: " + results[1]);
        System.out.println("Expected: " + expected[1]);
        System.out.println("Status: " + testResults[1]);
        System.out.println();
        
        // Test Case 3: Different lengths - cannot be anagram
        String s3 = "abc";
        String t3 = "abcd";
        results[2] = solution.isAnagram(s3, t3);
        expected[2] = false;
        testResults[2] = (results[2] == expected[2] ? "PASS ✓" : "FAIL ✗");
        testDescriptions[2] = "Different lengths";
        System.out.println("Test Case 3:");
        System.out.println("Input: s = \"" + s3 + "\", t = \"" + t3 + "\"");
        System.out.println("Output: " + results[2]);
        System.out.println("Expected: " + expected[2]);
        System.out.println("Status: " + testResults[2]);
        System.out.println();
        
        // Test Case 4: Single character strings - same
        String s4 = "a";
        String t4 = "a";
        results[3] = solution.isAnagram(s4, t4);
        expected[3] = true;
        testResults[3] = (results[3] == expected[3] ? "PASS ✓" : "FAIL ✗");
        testDescriptions[3] = "Single character - same";
        System.out.println("Test Case 4:");
        System.out.println("Input: s = \"" + s4 + "\", t = \"" + t4 + "\"");
        System.out.println("Output: " + results[3]);
        System.out.println("Expected: " + expected[3]);
        System.out.println("Status: " + testResults[3]);
        System.out.println();
        
        // Test Case 5: Single character strings - different
        String s5 = "a";
        String t5 = "b";
        results[4] = solution.isAnagram(s5, t5);
        expected[4] = false;
        testResults[4] = (results[4] == expected[4] ? "PASS ✓" : "FAIL ✗");
        testDescriptions[4] = "Single character - different";
        System.out.println("Test Case 5:");
        System.out.println("Input: s = \"" + s5 + "\", t = \"" + t5 + "\"");
        System.out.println("Output: " + results[4]);
        System.out.println("Expected: " + expected[4]);
        System.out.println("Status: " + testResults[4]);
        System.out.println();
        
        // Test Case 6: Minimum length strings
        String s6 = "a";
        String t6 = "a";
        results[5] = solution.isAnagram(s6, t6);
        expected[5] = true;
        testResults[5] = (results[5] == expected[5] ? "PASS ✓" : "FAIL ✗");
        testDescriptions[5] = "Minimum length edge case";
        System.out.println("Test Case 6:");
        System.out.println("Input: s = \"" + s6 + "\", t = \"" + t6 + "\"");
        System.out.println("Output: " + results[5]);
        System.out.println("Expected: " + expected[5]);
        System.out.println("Status: " + testResults[5]);
        System.out.println();
        
        // Test Case 7: Repeated characters
        String s7 = "aabbcc";
        String t7 = "abcabc";
        results[6] = solution.isAnagram(s7, t7);
        expected[6] = true;
        testResults[6] = (results[6] == expected[6] ? "PASS ✓" : "FAIL ✗");
        testDescriptions[6] = "Repeated characters";
        System.out.println("Test Case 7:");
        System.out.println("Input: s = \"" + s7 + "\", t = \"" + t7 + "\"");
        System.out.println("Output: " + results[6]);
        System.out.println("Expected: " + expected[6]);
        System.out.println("Status: " + testResults[6]);
        System.out.println();
        
        // Test Case 8: Not anagram but same length
        String s8 = "aacc";
        String t8 = "ccac";
        results[7] = solution.isAnagram(s8, t8);
        expected[7] = false;
        testResults[7] = (results[7] == expected[7] ? "PASS ✓" : "FAIL ✗");
        testDescriptions[7] = "Same length, not anagram";
        System.out.println("Test Case 8:");
        System.out.println("Input: s = \"" + s8 + "\", t = \"" + t8 + "\"");
        System.out.println("Output: " + results[7]);
        System.out.println("Expected: " + expected[7]);
        System.out.println("Status: " + testResults[7]);
        System.out.println();
        
        // Test Case 9: All same characters
        String s9 = "aaaa";
        String t9 = "aaaa";
        results[8] = solution.isAnagram(s9, t9);
        expected[8] = true;
        testResults[8] = (results[8] == expected[8] ? "PASS ✓" : "FAIL ✗");
        testDescriptions[8] = "All same characters";
        System.out.println("Test Case 9:");
        System.out.println("Input: s = \"" + s9 + "\", t = \"" + t9 + "\"");
        System.out.println("Output: " + results[8]);
        System.out.println("Expected: " + expected[8]);
        System.out.println("Status: " + testResults[8]);
        System.out.println();
        
        // Test Case 10: Longer strings
        String s10 = "listen";
        String t10 = "silent";
        results[9] = solution.isAnagram(s10, t10);
        expected[9] = true;
        testResults[9] = (results[9] == expected[9] ? "PASS ✓" : "FAIL ✗");
        testDescriptions[9] = "Classic anagram (listen/silent)";
        System.out.println("Test Case 10:");
        System.out.println("Input: s = \"" + s10 + "\", t = \"" + t10 + "\"");
        System.out.println("Output: " + results[9]);
        System.out.println("Expected: " + expected[9]);
        System.out.println("Status: " + testResults[9]);
        System.out.println();
        
        // Test Case 11: Different frequency
        String s11 = "aacc";
        String t11 = "ccac";
        results[10] = solution.isAnagram(s11, t11);
        expected[10] = false;
        testResults[10] = (results[10] == expected[10] ? "PASS ✓" : "FAIL ✗");
        testDescriptions[10] = "Different character frequency";
        System.out.println("Test Case 11:");
        System.out.println("Input: s = \"" + s11 + "\", t = \"" + t11 + "\"");
        System.out.println("Output: " + results[10]);
        System.out.println("Expected: " + expected[10]);
        System.out.println("Status: " + testResults[10]);
        System.out.println();
        
        // Calculate summary statistics
        int passed = 0;
        int failed = 0;
        for (int i = 0; i < totalTests; i++) {
            if (results[i] == expected[i]) {
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
            System.out.printf("Test %2d: %-35s %s%n", 
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
