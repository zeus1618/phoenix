package arraysAndHashing;

import java.util.Arrays;

/**
 * Sort the Tuples
 *
 * Difficulty: Easy
 * Topics: Array, Sorting, Comparator, Stability
 *
 * Problem Description:
 * You are given an array of tuples 'ARR' of length 'N'. All the tuples are of
 * length 'L'. Sort the tuples in non-decreasing order by the last element of
 * the tuples. If the last elements of two tuples are equal, the tuple with the
 * smaller original index should be placed first.
 *
 * Note: The length of all the tuples will be the same.
 *
 * Constraints:
 * - Not specified in the given problem statement.
 *
 * Examples:
 *
 * Example 1:
 * Input: N = 3, L = 2, ARR = [(1, 1), (5, 3), (8, 2)]
 * Output: [(1, 1), (8, 2), (5, 3)]
 * Explanation: The last values of each tuple are (1, 3, 2). Sorting them in
 * non-decreasing order gives (1, 2, 3), so the final result is
 * [(1, 1), (8, 2), (5, 3)].
 *
 * Approach: [To be documented after implementation]
 *
 * Time Complexity: [To be analyzed]
 * Space Complexity: [To be analyzed]
 */
public class SortTheTuples {

	/**
	 * Sorts arr in place by the last element of each tuple.
	 *
	 * @param arr the input array of tuples (each an int[] of length L)
	 */
	public static void sortTuples(int[][] arr) {
		Arrays.sort(arr, (o1, o2) ->
			Integer.compare(o1[o1.length - 1], o2[o2.length - 1])
		);
	}

	// ======================== Test Helper Methods ========================

	/**
	 * Returns a deep copy of a 2D int array, used to preserve the original
	 * input for display since sortTuples mutates the array in place.
	 */
	private static int[][] deepCopy(int[][] arr) {
		int[][] copy = new int[arr.length][];
		for (int i = 0; i < arr.length; i++) {
			copy[i] = Arrays.copyOf(arr[i], arr[i].length);
		}
		return copy;
	}

	/**
	 * Runs a single test case and prints the result.
	 *
	 * @param testNum the test case number
	 * @param original a copy of the input array before sorting (for display)
	 * @param expected the expected sorted output
	 * @param actual the actual output from the solution
	 * @param description brief description of the test case
	 */
	private static void runTest(
			int testNum,
			int[][] original,
			int[][] expected,
			int[][] actual,
			String description) {

		boolean passed = Arrays.deepEquals(expected, actual);
		String status = passed ? "✓ PASS" : "✗ FAIL";

		System.out.println("\n" + status + " - Test " + testNum + ": " + description);
		System.out.println("  Input:    " + Arrays.deepToString(original));
		System.out.println("  Expected: " + Arrays.deepToString(expected));
		System.out.println("  Actual:   " + Arrays.deepToString(actual));

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
		int totalTests = 0;
		int passedTests = 0;

		System.out.println("=".repeat(70));
		System.out.println("Testing: Sort the Tuples");
		System.out.println("=".repeat(70));

		// Test 1: Example from problem statement
		{
			int[][] arr = {{1, 1}, {5, 3}, {8, 2}};
			int[][] original = deepCopy(arr);
			int[][] expected = {{1, 1}, {8, 2}, {5, 3}};
			sortTuples(arr);
			runTest(++totalTests, original, expected, arr, "Example from problem statement");
			if (Arrays.deepEquals(expected, arr)) passedTests++;
		}

		// Test 2: Tie on last element - smaller original index comes first
		{
			int[][] arr = {{1, 5}, {2, 5}, {3, 1}};
			int[][] original = deepCopy(arr);
			int[][] expected = {{3, 1}, {1, 5}, {2, 5}};
			sortTuples(arr);
			runTest(++totalTests, original, expected, arr, "Tie on last element - stable order");
			if (Arrays.deepEquals(expected, arr)) passedTests++;
		}

		// Test 3: Already sorted by last element
		{
			int[][] arr = {{1, 1}, {2, 2}, {3, 3}};
			int[][] original = deepCopy(arr);
			int[][] expected = {{1, 1}, {2, 2}, {3, 3}};
			sortTuples(arr);
			runTest(++totalTests, original, expected, arr, "Already sorted by last element");
			if (Arrays.deepEquals(expected, arr)) passedTests++;
		}

		// Test 4: Reverse sorted by last element
		{
			int[][] arr = {{1, 3}, {2, 2}, {3, 1}};
			int[][] original = deepCopy(arr);
			int[][] expected = {{3, 1}, {2, 2}, {1, 3}};
			sortTuples(arr);
			runTest(++totalTests, original, expected, arr, "Reverse sorted by last element");
			if (Arrays.deepEquals(expected, arr)) passedTests++;
		}

		// Test 5: Edge case - single tuple
		{
			int[][] arr = {{5, 10}};
			int[][] original = deepCopy(arr);
			int[][] expected = {{5, 10}};
			sortTuples(arr);
			runTest(++totalTests, original, expected, arr, "Edge case - single tuple");
			if (Arrays.deepEquals(expected, arr)) passedTests++;
		}

		// Test 6: All last elements equal - original order fully preserved
		{
			int[][] arr = {{1, 7}, {2, 7}, {3, 7}, {4, 7}};
			int[][] original = deepCopy(arr);
			int[][] expected = {{1, 7}, {2, 7}, {3, 7}, {4, 7}};
			sortTuples(arr);
			runTest(++totalTests, original, expected, arr, "All last elements equal");
			if (Arrays.deepEquals(expected, arr)) passedTests++;
		}

		// Test 7: Longer tuples (L = 3)
		{
			int[][] arr = {{1, 2, 9}, {3, 4, 1}, {5, 6, 5}};
			int[][] original = deepCopy(arr);
			int[][] expected = {{3, 4, 1}, {5, 6, 5}, {1, 2, 9}};
			sortTuples(arr);
			runTest(++totalTests, original, expected, arr, "Longer tuples (L = 3)");
			if (Arrays.deepEquals(expected, arr)) passedTests++;
		}

		// Test 8: Negative last elements
		{
			int[][] arr = {{1, -5}, {2, 3}, {3, -1}};
			int[][] original = deepCopy(arr);
			int[][] expected = {{1, -5}, {3, -1}, {2, 3}};
			sortTuples(arr);
			runTest(++totalTests, original, expected, arr, "Negative last elements");
			if (Arrays.deepEquals(expected, arr)) passedTests++;
		}

		// Test 9: Duplicate tuples (identical first and last elements)
		{
			int[][] arr = {{5, 2}, {5, 2}, {1, 2}};
			int[][] original = deepCopy(arr);
			int[][] expected = {{5, 2}, {5, 2}, {1, 2}};
			sortTuples(arr);
			runTest(++totalTests, original, expected, arr, "Duplicate tuples, all tied on last element");
			if (Arrays.deepEquals(expected, arr)) passedTests++;
		}

		// Test 10: Longer tuples (L = 4)
		{
			int[][] arr = {{1, 1, 1, 3}, {2, 2, 2, 1}, {3, 3, 3, 2}};
			int[][] original = deepCopy(arr);
			int[][] expected = {{2, 2, 2, 1}, {3, 3, 3, 2}, {1, 1, 1, 3}};
			sortTuples(arr);
			runTest(++totalTests, original, expected, arr, "Longer tuples (L = 4)");
			if (Arrays.deepEquals(expected, arr)) passedTests++;
		}

		// Test 11: Edge case - empty array
		{
			int[][] arr = {};
			int[][] original = deepCopy(arr);
			int[][] expected = {};
			sortTuples(arr);
			runTest(++totalTests, original, expected, arr, "Edge case - empty array");
			if (Arrays.deepEquals(expected, arr)) passedTests++;
		}

		printSummary(totalTests, passedTests);
	}
}
