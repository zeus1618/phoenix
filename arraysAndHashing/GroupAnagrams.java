package arraysAndHashing;

import java.util.*;

/**
 * LeetCode #49: Group Anagrams
 * Difficulty: Medium
 * 
 * Problem Description:
 * Given an array of strings strs, group the anagrams together. You can return 
 * the answer in any order.
 * 
 * An Anagram is a word or phrase formed by rearranging the letters of a different 
 * word or phrase, typically using all the original letters exactly once.
 * 
 * Constraints:
 * - 1 <= strs.length <= 10^4
 * - 0 <= strs[i].length <= 100
 * - strs[i] consists of lowercase English letters
 * 
 * Solution Approach:
 * Used HashMap with sorted string as key. For each string, convert to char array,
 * sort it, and use the sorted string as the HashMap key. All anagrams produce the
 * same sorted key, so they naturally group together. Single pass through input
 * array with O(k log k) key generation per string.
 * 
 * Time Complexity: O(n × k log k) where n = number of strings, k = max string length
 * Space Complexity: O(n × k) for HashMap storage and output
 * 
 * Key Learnings:
 * 1. HashMap Grouping Pattern: Transform O(n²) pair comparison to O(n) grouping by key
 * 2. Theory vs Practice: O(nk log k) sorted key beat O(nk) frequency string by 5.5x
 *    due to constant factors (string building overhead: 780 vs 64 ops per word)
 * 3. String Operations Expensive: sb.append(f + ",") creates 26 temp String objects
 * 4. JVM Intrinsics Win: Arrays.sort() highly optimized, beats custom implementations
 * 5. Key Size Matters: Short keys (3-10 chars) vs long keys (52 chars) = 17x faster
 * 6. HashMap Contract: Keys need proper equals() + hashCode(), using raw hash is buggy
 * 
 * Alternative Approaches Analyzed:
 * 1. Nested Loop - O(n²k) time - TLE on LeetCode, doesn't scale
 * 2. Frequency String - O(nk) time - 33ms, slow due to string building overhead
 * 3. Sorted Key - O(nk log k) time - 6ms, optimal in practice (IMPLEMENTED)
 * 4. Prime Multiplication - O(nk) time - 4ms but overflows for k>13, fails constraints
 * 5. Arrays.hashCode() - O(nk) time - 5ms but has hash collisions, incorrect
 * 
 * Production Recommendation:
 * Sorted key approach is the standard solution. Simple, fast enough (6ms), and
 * 100% correct for all constraints. Clever optimizations (prime, hashCode) sacrifice
 * correctness for marginal speed gains.
 * 
 * Detailed Learning Guide: See arraysAndHashing/learnings/GroupAnagrams-Learning.md
 */
class GroupAnagrams {
    
    /**
     * Groups strings that are anagrams of each other
     * @param strs - array of strings
     * @return list of groups, where each group contains anagram strings
     */

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> group = new HashMap<>();
        
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String normalizeKey = new String(chars);
            if (!group.containsKey(normalizeKey)) {
                group.put(normalizeKey, new ArrayList<>());
            }
            group.get(normalizeKey).add(str);
        }
        
        return new ArrayList<>(group.values());
    }

    public List<List<String>> groupAnagramsFrequencyKey(String[] strs) {
        Map<String, List<String>> mapper = new HashMap<>();
        for(String s : strs) {
            int[] freq = new int[26];
            char[] sChar = s.toCharArray();
            for(char c : sChar) {
                freq[c-'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for(int f : freq) {
                sb.append(f).append(',');
            }
            String key = sb.toString();
            // System.out.println(key);
            mapper.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
            // if (!mapper.containsKey(key)) {
            //     mapper.put(key, new ArrayList<>());
            // }
            // mapper.get(key).add(s);
        }
        return new ArrayList<>(mapper.values());
    }

    public List<List<String>> groupAnagramsFirstAttempt(String[] strs) {
        List<List<String>> result = new ArrayList<List<String>>();
        for(int i=0; i<strs.length; i++) {
            if(strs[i] == null) {
                continue;
            }
            List<String> anagramList = new ArrayList<>();
            String curr = strs[i];
            anagramList.add(curr);
            if(i+1 >= strs.length) {
                result.add(anagramList);
                continue;
            }
            for(int j=i+1; j<strs.length; j++) {
                if(strs[j]!= null && isAnagram(curr, strs[j])){
                    anagramList.add(strs[j]);
                    strs[j] = null;
                }
            }
            result.add(anagramList);
        }
        return result;
    }

    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) {
            return false;
        }
        int[] freq = new int[26];
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();

        for(char c : sChar) {
            freq[c-'a']++;
        }
        for(char c : tChar) {
            freq[c-'a']--;
        }
        for(int f : freq) {
            if(f != 0) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Helper method to compare two lists of lists (order-independent)
     * Sorts both inner and outer lists for comparison
     */
    private static boolean compareResults(List<List<String>> actual, List<List<String>> expected) {
        if (actual.size() != expected.size()) return false;
        
        // Sort each inner list and then sort the outer list
        List<List<String>> sortedActual = new ArrayList<>();
        for (List<String> group : actual) {
            List<String> sorted = new ArrayList<>(group);
            Collections.sort(sorted);
            sortedActual.add(sorted);
        }
        sortedActual.sort((a, b) -> {
            String aStr = String.join(",", a);
            String bStr = String.join(",", b);
            return aStr.compareTo(bStr);
        });
        
        List<List<String>> sortedExpected = new ArrayList<>();
        for (List<String> group : expected) {
            List<String> sorted = new ArrayList<>(group);
            Collections.sort(sorted);
            sortedExpected.add(sorted);
        }
        sortedExpected.sort((a, b) -> {
            String aStr = String.join(",", a);
            String bStr = String.join(",", b);
            return aStr.compareTo(bStr);
        });
        
        return sortedActual.equals(sortedExpected);
    }
    
    /**
     * Helper method to run a single test case
     */
    private static void runTest(int testNum, String[] input, List<List<String>> expected, 
                                 GroupAnagrams solution, String[] results, String[] descriptions) {
        System.out.println("Test Case " + testNum + ":");
        System.out.println("Input: strs = " + Arrays.toString(input));
        
        List<List<String>> actual = solution.groupAnagrams(input);
        
        System.out.println("Output: " + actual);
        System.out.println("Expected: " + expected);
        
        boolean pass = compareResults(actual, expected);
        results[testNum - 1] = pass ? "PASS ✓" : "FAIL ✗";
        
        System.out.println("Status: " + results[testNum - 1]);
        System.out.println();
    }
    
    /**
     * Helper method to print test summary
     */
    private static void printSummary(int totalTests, String[] results, String[] descriptions) {
        int passed = 0;
        int failed = 0;
        
        for (String result : results) {
            if (result.contains("PASS")) passed++;
            else failed++;
        }
        
        System.out.println("=".repeat(60));
        System.out.println("TEST SUMMARY");
        System.out.println("=".repeat(60));
        System.out.println();
        
        System.out.println("Test Case Results:");
        System.out.println("-".repeat(60));
        for (int i = 0; i < totalTests; i++) {
            System.out.printf("Test %2d: %-40s %s%n", 
                (i + 1), descriptions[i], results[i]);
        }
        System.out.println("-".repeat(60));
        System.out.println();
        
        System.out.println("Statistics:");
        System.out.println("  Total Tests:  " + totalTests);
        System.out.println("  Passed:       " + passed + " ✓");
        System.out.println("  Failed:       " + failed + (failed > 0 ? " ✗" : ""));
        System.out.println("  Success Rate: " + String.format("%.1f", (passed * 100.0 / totalTests)) + "%");
        System.out.println();
        
        if (failed == 0) {
            System.out.println("🎉 ALL TESTS PASSED! 🎉");
        } else {
            System.out.println("⚠️  SOME TESTS FAILED - Review failed cases above");
        }
        
        System.out.println("=".repeat(60));
    }
    
    /**
     * Main method to test the solution with various test cases
     */
    public static void main(String[] args) {
        GroupAnagrams solution = new GroupAnagrams();
        
        int totalTests = 9;
        String[] results = new String[totalTests];
        String[] descriptions = new String[totalTests];
        
        // Test Case 1: Example 1 - Multiple anagram groups
        descriptions[0] = "Example 1: Multiple groups";
        String[] strs1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> expected1 = Arrays.asList(
            Arrays.asList("bat"),
            Arrays.asList("nat", "tan"),
            Arrays.asList("ate", "eat", "tea")
        );
        runTest(1, strs1, expected1, solution, results, descriptions);
        
        // Test Case 2: Example 2 - Empty string
        descriptions[1] = "Example 2: Empty string";
        String[] strs2 = {""};
        List<List<String>> expected2 = Arrays.asList(
            Arrays.asList("")
        );
        runTest(2, strs2, expected2, solution, results, descriptions);
        
        // Test Case 3: Example 3 - Single character
        descriptions[2] = "Example 3: Single character";
        String[] strs3 = {"a"};
        List<List<String>> expected3 = Arrays.asList(
            Arrays.asList("a")
        );
        runTest(3, strs3, expected3, solution, results, descriptions);
        
        // Test Case 4: No anagrams - all unique
        descriptions[3] = "No anagrams - all unique";
        String[] strs4 = {"abc", "def", "ghi"};
        List<List<String>> expected4 = Arrays.asList(
            Arrays.asList("abc"),
            Arrays.asList("def"),
            Arrays.asList("ghi")
        );
        runTest(4, strs4, expected4, solution, results, descriptions);
        
        // Test Case 5: All same strings
        descriptions[4] = "All identical strings";
        String[] strs5 = {"abc", "abc", "abc"};
        List<List<String>> expected5 = Arrays.asList(
            Arrays.asList("abc", "abc", "abc")
        );
        runTest(5, strs5, expected5, solution, results, descriptions);
        
        // Test Case 6: Different lengths (not anagrams)
        descriptions[5] = "Different length strings";
        String[] strs6 = {"a", "ab", "abc"};
        List<List<String>> expected6 = Arrays.asList(
            Arrays.asList("a"),
            Arrays.asList("ab"),
            Arrays.asList("abc")
        );
        runTest(6, strs6, expected6, solution, results, descriptions);
        
        // Test Case 7: Large group of anagrams
        descriptions[6] = "Large anagram group";
        String[] strs7 = {"listen", "silent", "enlist", "hello", "llohe"};
        List<List<String>> expected7 = Arrays.asList(
            Arrays.asList("listen", "silent", "enlist"),
            Arrays.asList("hello", "llohe")
        );
        runTest(7, strs7, expected7, solution, results, descriptions);
        
        // Test Case 8: Mixed - some anagrams, some unique
        descriptions[7] = "Mixed anagrams and unique";
        String[] strs8 = {"cat", "dog", "tac", "god", "act", "bird"};
        List<List<String>> expected8 = Arrays.asList(
            Arrays.asList("cat", "tac", "act"),
            Arrays.asList("dog", "god"),
            Arrays.asList("bird")
        );
        runTest(8, strs8, expected8, solution, results, descriptions);
        
        // Test Case 9: Different length strings with many repeated chars
        descriptions[8] = "Long strings, not anagrams";
        String[] strs9 = {"bdddddddddd", "bbbbbbbbbbc"};
        List<List<String>> expected9 = Arrays.asList(
            Arrays.asList("bdddddddddd"),
            Arrays.asList("bbbbbbbbbbc")
        );
        runTest(9, strs9, expected9, solution, results, descriptions);
        
        // Print summary
        printSummary(totalTests, results, descriptions);
    }
}
