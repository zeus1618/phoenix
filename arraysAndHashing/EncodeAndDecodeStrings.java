package arraysAndHashing;

import java.util.*;

/**
 * 271. Encode and Decode Strings (Medium)
 * 
 * Problem Link: https://neetcode.io/problems/string-encode-and-decode
 * 
 * Problem Description:
 * Design an algorithm to encode a list of strings to a string. The encoded string 
 * is then sent over the network and is decoded back to the original list of strings.
 * 
 * Machine 1 (sender) has the encode function:
 *   String encode(List<String> strs)
 * 
 * Machine 2 (receiver) has the decode function:
 *   List<String> decode(String s)
 * 
 * Implement the encode and decode methods.
 * 
 * Example 1:
 * Input: dummy_input = ["Hello","World"]
 * Output: ["Hello","World"]
 * Explanation:
 * Machine 1: String msg = encode(strs);
 * Machine 1 ---msg---> Machine 2
 * Machine 2: List<String> strs = decode(msg);
 * 
 * Example 2:
 * Input: dummy_input = [""]
 * Output: [""]
 * 
 * Constraints:
 * - 0 <= strs.length < 100
 * - 0 <= strs[i].length < 200
 * - strs[i] contains any possible characters out of 256 valid ASCII characters
 * 
 * Follow-up:
 * Could you write a generalized algorithm to work on any possible set of characters?
 * 
 * Approach:
 * Fixed-width length encoding. Encode format: [listSize][len1][str1][len2][str2]...
 * where each length is a 3-digit zero-padded integer. Decode parses lengths at 
 * fixed positions (0, 3, len1+3, ...) and extracts strings of specified lengths.
 * This approach eliminates delimiter conflicts since any ASCII character can appear
 * in the input strings. The length prefix tells decoder exactly how many bytes to read.
 * 
 * Time Complexity: O(m) where m = sum of all string lengths
 * Space Complexity: O(m) for the encoded string output
 * 
 * Key Learnings:
 * - Length-prefixed encoding is the standard solution when no character can be reserved
 * - Fixed-width vs variable-width: predictability vs space efficiency trade-off
 * - This is a fundamental protocol design pattern (HTTP, TCP, serialization formats)
 * - StringBuilder is essential for efficient string concatenation (O(n) vs O(n²))
 * - String.format("%03d") provides clean zero-padding
 * 
 * Alternative Approaches:
 * - Length + Delimiter (implemented as encodeV2/decodeV2): More space-efficient
 * - Escape sequences: Complex and error-prone for all ASCII characters
 * 
 * Detailed Learning Guide: See arraysAndHashing/learnings/EncodeAndDecodeStrings-Learning.md
 */
public class EncodeAndDecodeStrings {

    /**
     * Encodes a list of strings to a single string.
     * 
     * @param strs list of strings to encode
     * @return encoded string
     */
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        sb.append(get3DigitLength(strs.size()));
        for(int i=0; i<strs.size(); i++) {
            sb.append(get3DigitLength(strs.get(i).length()));
            sb.append(strs.get(i));
        }
        return sb.toString();
    }

    public String get3DigitLength(int size) {
        return String.format("%03d", size);
    }

    /**
     * Decodes a single string to a list of strings.
     * 
     * @param s encoded string
     * @return decoded list of strings
     */
    public List<String> decode(String s) {
        if(s==null || s.length()<3){
            return new ArrayList<>();
        }
        int arrSize = Integer.parseInt(s.substring(0, 3));
        List<String> strs = new ArrayList<>(arrSize);
        int pos=3;
        for(int i=0; i<arrSize; i++){
            int length = Integer.parseInt(s.substring(pos, pos + 3));
            pos += 3;
            strs.add(s.substring(pos, pos + length));
            pos += length;
        }
        return strs;
    }

    // ====================== Alternative Solution: Length + Delimiter ======================

    /**
     * Alternative Approach: Length + Delimiter Encoding
     * 
     * Format: length#string for each string
     * Example: ["Hello","World"] → "5#Hello5#World"
     * 
     * Time Complexity: O(m) where m = sum of all string lengths
     * Space Complexity: O(m) for the output string
     * 
     * Pros:
     * - More space-efficient than fixed-width (no padding)
     * - Human-readable format
     * - Common pattern in protocols
     * 
     * Cons:
     * - Must scan for delimiter (slightly slower than fixed-width)
     * - Delimiter character '#' is arbitrary but works for all ASCII
     * - Variable-width length parsing
     */
    public String encodeV2(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str.length());
            sb.append('#');
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     * Decodes a string encoded with length + delimiter format.
     * 
     * @param s encoded string
     * @return decoded list of strings
     */
    public List<String> decodeV2(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.isEmpty()) {
            return result;
        }
        
        int i = 0;
        while (i < s.length()) {
            // Find the delimiter '#'
            int delimiterPos = s.indexOf('#', i);
            if (delimiterPos == -1) break;
            
            // Extract length
            int length = Integer.parseInt(s.substring(i, delimiterPos));
            
            // Move past delimiter
            int start = delimiterPos + 1;
            
            // Extract string of given length
            result.add(s.substring(start, start + length));
            
            // Move to next string
            i = start + length;
        }
        
        return result;
    }

    // ====================== Test Infrastructure ======================

    /**
     * Helper method to run a single test case.
     * 
     * @param testNum test case number
     * @param input input list of strings
     * @param description test case description
     * @return true if test passed, false otherwise
     */
    private boolean runTest(int testNum, List<String> input, String description) {
        // Encode the input
        String encoded = encode(input);
        
        // Decode the encoded string
        List<String> decoded = decode(encoded);
        
        // Check if decoded equals original input
        boolean passed = input.equals(decoded);
        
        String status = passed ? "✓ PASS" : "✗ FAIL";
        System.out.printf("Test %d: %s - %s%n", testNum, status, description);
        
        if (!passed) {
            System.out.println("  Input:    " + input);
            System.out.println("  Encoded:  " + encoded);
            System.out.println("  Decoded:  " + decoded);
            System.out.println("  Expected: " + input);
        }
        
        return passed;
    }

    /**
     * Helper method to run a test case for the alternative solution.
     * 
     * @param testNum test case number
     * @param input input list of strings
     * @param description test case description
     * @return true if test passed, false otherwise
     */
    private boolean runTestV2(int testNum, List<String> input, String description) {
        // Encode the input
        String encoded = encodeV2(input);
        
        // Decode the encoded string
        List<String> decoded = decodeV2(encoded);
        
        // Check if decoded equals original input
        boolean passed = input.equals(decoded);
        
        String status = passed ? "✓ PASS" : "✗ FAIL";
        System.out.printf("Test %d: %s - %s%n", testNum, status, description);
        
        if (!passed) {
            System.out.println("  Input:    " + input);
            System.out.println("  Encoded:  " + encoded);
            System.out.println("  Decoded:  " + decoded);
            System.out.println("  Expected: " + input);
        }
        
        return passed;
    }

    /**
     * Compare both encoding approaches and show space efficiency.
     */
    private void compareEncodings(List<String> input, String description) {
        String encoded1 = encode(input);
        String encoded2 = encodeV2(input);
        
        System.out.println("\n" + "-".repeat(60));
        System.out.println("Encoding Comparison: " + description);
        System.out.println("-".repeat(60));
        System.out.println("Input:           " + input);
        System.out.println("Fixed-Width:     " + encoded1 + " (" + encoded1.length() + " chars)");
        System.out.println("Length+Delim:    " + encoded2 + " (" + encoded2.length() + " chars)");
        System.out.println("Space Savings:   " + (encoded1.length() - encoded2.length()) + " chars");
    }

    /**
     * Prints the final test results summary.
     * 
     * @param results array of test results
     */
    private void printSummary(boolean[] results) {
        int passed = 0;
        for (boolean result : results) {
            if (result) passed++;
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.printf("Test Results: %d/%d passed (%.1f%%)%n", 
                         passed, results.length, (passed * 100.0 / results.length));
        System.out.println("=".repeat(60));
    }

    // ====================== Main Method ======================

    public static void main(String[] args) {
        EncodeAndDecodeStrings solution = new EncodeAndDecodeStrings();
        
        System.out.println("=".repeat(60));
        System.out.println("Solution 1: Fixed-Width Length Encoding");
        System.out.println("=".repeat(60) + "\n");

        boolean[] results = new boolean[10];
        int testIndex = 0;

        // Example 1: Basic case with two strings
        results[testIndex++] = solution.runTest(1, 
            Arrays.asList("Hello", "World"),
            "Two simple strings");

        // Example 2: Empty string
        results[testIndex++] = solution.runTest(2,
            Arrays.asList(""),
            "Single empty string");

        // Test 3: Empty list
        results[testIndex++] = solution.runTest(3,
            new ArrayList<>(),
            "Empty list");

        // Test 4: Single string
        results[testIndex++] = solution.runTest(4,
            Arrays.asList("SingleString"),
            "Single string");

        // Test 5: Strings with special characters
        results[testIndex++] = solution.runTest(5,
            Arrays.asList("Hello#World", "Test:Data", "A#B#C"),
            "Special characters (# : etc)");

        // Test 6: Strings with numbers that could be confused with length
        results[testIndex++] = solution.runTest(6,
            Arrays.asList("123", "456#789", "10#20"),
            "Numeric strings with delimiters");

        // Test 7: Multiple empty strings
        results[testIndex++] = solution.runTest(7,
            Arrays.asList("", "", ""),
            "Multiple empty strings");

        // Test 8: Very long string
        results[testIndex++] = solution.runTest(8,
            Arrays.asList("A".repeat(100), "B".repeat(150)),
            "Very long strings (100 and 150 chars)");

        // Test 9: Strings with spaces and newlines
        results[testIndex++] = solution.runTest(9,
            Arrays.asList("Hello World", "Line1\nLine2", "Tab\there"),
            "Strings with spaces, newlines, tabs");

        // Test 10: All ASCII characters
        StringBuilder allAscii = new StringBuilder();
        for (int i = 0; i < 256; i++) {
            allAscii.append((char) i);
        }
        results[testIndex++] = solution.runTest(10,
            Arrays.asList(allAscii.toString()),
            "All 256 ASCII characters");

        solution.printSummary(results);

        // ====================== Alternative Solution Tests ======================
        
        System.out.println("\n\n" + "=".repeat(60));
        System.out.println("Solution 2: Length + Delimiter Encoding");
        System.out.println("=".repeat(60) + "\n");

        boolean[] resultsV2 = new boolean[10];
        testIndex = 0;

        resultsV2[testIndex++] = solution.runTestV2(1, 
            Arrays.asList("Hello", "World"),
            "Two simple strings");

        resultsV2[testIndex++] = solution.runTestV2(2,
            Arrays.asList(""),
            "Single empty string");

        resultsV2[testIndex++] = solution.runTestV2(3,
            new ArrayList<>(),
            "Empty list");

        resultsV2[testIndex++] = solution.runTestV2(4,
            Arrays.asList("SingleString"),
            "Single string");

        resultsV2[testIndex++] = solution.runTestV2(5,
            Arrays.asList("Hello#World", "Test:Data", "A#B#C"),
            "Special characters (# : etc)");

        resultsV2[testIndex++] = solution.runTestV2(6,
            Arrays.asList("123", "456#789", "10#20"),
            "Numeric strings with delimiters");

        resultsV2[testIndex++] = solution.runTestV2(7,
            Arrays.asList("", "", ""),
            "Multiple empty strings");

        resultsV2[testIndex++] = solution.runTestV2(8,
            Arrays.asList("A".repeat(100), "B".repeat(150)),
            "Very long strings (100 and 150 chars)");

        resultsV2[testIndex++] = solution.runTestV2(9,
            Arrays.asList("Hello World", "Line1\nLine2", "Tab\there"),
            "Strings with spaces, newlines, tabs");

        resultsV2[testIndex++] = solution.runTestV2(10,
            Arrays.asList(allAscii.toString()),
            "All 256 ASCII characters");

        solution.printSummary(resultsV2);

        // ====================== Encoding Comparison ======================
        
        System.out.println("\n\n" + "=".repeat(60));
        System.out.println("ENCODING APPROACH COMPARISON");
        System.out.println("=".repeat(60));

        solution.compareEncodings(
            Arrays.asList("Hello", "World"),
            "Short strings"
        );

        solution.compareEncodings(
            Arrays.asList("A".repeat(100), "B".repeat(150)),
            "Long strings (100 and 150 chars)"
        );

        solution.compareEncodings(
            Arrays.asList("", "", ""),
            "Multiple empty strings"
        );

        solution.compareEncodings(
            Arrays.asList("Hi", "A", "OK", "Yes", "No", "Go", "Up"),
            "Many short strings"
        );

        System.out.println("\n" + "=".repeat(60));
        System.out.println("SPACE EFFICIENCY ANALYSIS");
        System.out.println("=".repeat(60));
        System.out.println("Fixed-Width Overhead:  3 bytes per string (always)");
        System.out.println("Length+Delim Overhead: 1-3 bytes per string (variable)");
        System.out.println("                       1-2 bytes (length) + 1 byte (#)");
        System.out.println();
        System.out.println("Fixed-Width is better when: Predictable parsing is priority");
        System.out.println("Length+Delim is better when: Space efficiency matters");
        System.out.println("=".repeat(60));
    }
}
