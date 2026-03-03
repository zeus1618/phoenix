package algorithms.sorting;

import java.util.Arrays;

/**
 * Common Test Suite for Sorting Algorithms
 *
 * <p>This utility class provides reusable test cases and helper methods for testing
 * various sorting algorithm implementations. It includes comprehensive test scenarios
 * covering edge cases, normal cases, and performance benchmarks.
 *
 * <p>Usage:
 * 1. Call runAllTests() with your sorting method reference
 * 2. Use individual test methods for specific scenarios
 * 3. Use printTestResult() for consistent output formatting
 *
 * <p>Test Categories:
 * - Edge Cases: empty, single element, two elements, duplicates
 * - Normal Cases: random arrays, sorted arrays, reverse sorted
 * - Special Cases: all same elements, negative numbers, large arrays
 */
public class SortingTestSuite {

    /**
     * Functional interface for sorting algorithms.
     * Allows passing different sorting implementations as method references.
     */
    @FunctionalInterface
public interface SortingAlgorithm {
        void sort(int[] arr);
    }

    private static int testsPassed = 0;
    private static int testsFailed = 0;
    private static int totalTests = 0;

    /**
     * Runs all test cases for a given sorting algorithm.
     *
     * @param algorithmName the name of the sorting algorithm being tested
     * @param sortingAlgorithm the sorting algorithm implementation
     */
    public static void runAllTests(String algorithmName, SortingAlgorithm sortingAlgorithm) {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("Testing: " + algorithmName);
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        resetCounters();

        // Edge Cases
        testEmptyArray(sortingAlgorithm);
        testSingleElement(sortingAlgorithm);
        testTwoElements(sortingAlgorithm);
        testAllDuplicates(sortingAlgorithm);

        // Normal Cases
        testSmallArray(sortingAlgorithm);
        testAlreadySorted(sortingAlgorithm);
        testReverseSorted(sortingAlgorithm);
        testRandomArray(sortingAlgorithm);

        // Special Cases
        testNegativeNumbers(sortingAlgorithm);
        testMixedPositiveNegative(sortingAlgorithm);
        testLargeArray(sortingAlgorithm);

        // Print Summary
        printSummary();
    }

    /**
     * Test: Empty Array
     */
    private static void testEmptyArray(SortingAlgorithm algorithm) {
        int[] arr = {};
        int[] expected = {};
        runTest("Empty Array", arr, expected, algorithm);
    }

    /**
     * Test: Single Element
     */
    private static void testSingleElement(SortingAlgorithm algorithm) {
        int[] arr = {5};
        int[] expected = {5};
        runTest("Single Element", arr, expected, algorithm);
    }

    /**
     * Test: Two Elements (unsorted)
     */
    private static void testTwoElements(SortingAlgorithm algorithm) {
        int[] arr = {2, 1};
        int[] expected = {1, 2};
        runTest("Two Elements", arr, expected, algorithm);
    }

    /**
     * Test: All Duplicate Elements
     */
    private static void testAllDuplicates(SortingAlgorithm algorithm) {
        int[] arr = {5, 5, 5, 5, 5};
        int[] expected = {5, 5, 5, 5, 5};
        runTest("All Duplicates", arr, expected, algorithm);
    }

    /**
     * Test: Small Array with Random Order
     */
    private static void testSmallArray(SortingAlgorithm algorithm) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        int[] expected = {11, 12, 22, 25, 34, 64, 90};
        runTest("Small Array (Random)", arr, expected, algorithm);
    }

    /**
     * Test: Already Sorted Array
     */
    private static void testAlreadySorted(SortingAlgorithm algorithm) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        runTest("Already Sorted", arr, expected, algorithm);
    }

    /**
     * Test: Reverse Sorted Array
     */
    private static void testReverseSorted(SortingAlgorithm algorithm) {
        int[] arr = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        runTest("Reverse Sorted", arr, expected, algorithm);
    }

    /**
     * Test: Random Array with Duplicates
     */
    private static void testRandomArray(SortingAlgorithm algorithm) {
        int[] arr = {3, 7, 1, 4, 7, 2, 9, 3, 5};
        int[] expected = {1, 2, 3, 3, 4, 5, 7, 7, 9};
        runTest("Random with Duplicates", arr, expected, algorithm);
    }

    /**
     * Test: All Negative Numbers
     */
    private static void testNegativeNumbers(SortingAlgorithm algorithm) {
        int[] arr = {-5, -2, -10, -1, -8};
        int[] expected = {-10, -8, -5, -2, -1};
        runTest("All Negative Numbers", arr, expected, algorithm);
    }

    /**
     * Test: Mixed Positive and Negative Numbers
     */
    private static void testMixedPositiveNegative(SortingAlgorithm algorithm) {
        int[] arr = {-3, 5, -1, 0, 8, -9, 2};
        int[] expected = {-9, -3, -1, 0, 2, 5, 8};
        runTest("Mixed Pos/Neg", arr, expected, algorithm);
    }

    /**
     * Test: Large Array
     */
    private static void testLargeArray(SortingAlgorithm algorithm) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = 100 - i;
        }
        int[] expected = new int[100];
        for (int i = 0; i < 100; i++) {
            expected[i] = i + 1;
        }
        runTest("Large Array (100 elements)", arr, expected, algorithm);
    }

    /**
     * Core test execution method.
     * Runs a single test case and validates the result.
     *
     * @param testName the name/description of the test
     * @param input the input array to sort
     * @param expected the expected sorted result
     * @param algorithm the sorting algorithm to test
     */
    private static void runTest(String testName, int[] input, int[] expected, SortingAlgorithm algorithm) {
        totalTests++;
        int[] inputCopy = Arrays.copyOf(input, input.length);

        try {
            algorithm.sort(input);
            boolean passed = Arrays.equals(input, expected);

            if (passed) {
                testsPassed++;
                printTestResult(testName, inputCopy, input, expected, true, "");
            } else {
                testsFailed++;
                printTestResult(testName, inputCopy, input, expected, false, "Array mismatch");
            }
        } catch (Exception e) {
            testsFailed++;
            printTestResult(testName, inputCopy, input, expected, false, e.getMessage());
        }
    }

    /**
     * Prints formatted test result with input, output, and status.
     */
    private static void printTestResult(String testName, int[] input, int[] actual, 
                                       int[] expected, boolean passed, String error) {
        String status = passed ? "✅ PASS" : "❌ FAIL";
        System.out.printf("Test #%d: %-30s %s%n", totalTests, testName, status);
        
        // Only show arrays for small inputs or failures
        if (input.length <= 10 || !passed) {
            System.out.printf("  Input:    %s%n", arrayToString(input));
            System.out.printf("  Expected: %s%n", arrayToString(expected));
            System.out.printf("  Actual:   %s%n", arrayToString(actual));
        } else {
            System.out.printf("  Array size: %d elements%n", input.length);
        }
        
        if (!passed && !error.isEmpty()) {
            System.out.printf("  Error: %s%n", error);
        }
        System.out.println();
    }

    /**
     * Converts array to readable string format.
     */
    private static String arrayToString(int[] arr) {
        if (arr.length == 0) return "[]";
        if (arr.length <= 10) return Arrays.toString(arr);
        return String.format("[%d, %d, %d, ... %d, %d, %d]", 
                           arr[0], arr[1], arr[2], 
                           arr[arr.length-3], arr[arr.length-2], arr[arr.length-1]);
    }

    /**
     * Prints test summary with pass/fail statistics.
     */
    private static void printSummary() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("TEST SUMMARY");
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.printf("Total Tests:  %d%n", totalTests);
        System.out.printf("✅ Passed:    %d%n", testsPassed);
        System.out.printf("❌ Failed:    %d%n", testsFailed);
        System.out.printf("Success Rate: %.1f%%%n", (testsPassed * 100.0 / totalTests));
        System.out.println("═══════════════════════════════════════════════════════════════\n");
    }

    /**
     * Resets test counters for a new test run.
     */
    private static void resetCounters() {
        testsPassed = 0;
        testsFailed = 0;
        totalTests = 0;
    }

    /**
     * Utility method for manual testing of a single case.
     */
    public static void testSingleCase(String testName, int[] input, int[] expected, 
                                     SortingAlgorithm algorithm) {
        resetCounters();
        runTest(testName, input, expected, algorithm);
        printSummary();
    }
}
