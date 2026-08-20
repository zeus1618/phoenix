package arraysAndHashing;

/**
 * LeetCode Problem #605: Can Place Flowers
 * Difficulty: Easy
 *
 * Problem Description:
 * You have a long flowerbed in which some of the plots are planted, and
 * some are not. However, flowers cannot be planted in adjacent plots.
 *
 * Given an integer array flowerbed containing 0's and 1's, where 0 means
 * empty and 1 means not empty, and an integer n, return true if n new
 * flowers can be planted in the flowerbed without violating the
 * no-adjacent-flowers rule and false otherwise.
 *
 * Constraints:
 * - 1 <= flowerbed.length <= 2 * 10^4
 * - flowerbed[i] is 0 or 1
 * - There are no two adjacent flowers in flowerbed
 * - 0 <= n <= flowerbed.length
 *
 * Example 1:
 * Input: flowerbed = [1,0,0,0,1], n = 1
 * Output: true
 *
 * Example 2:
 * Input: flowerbed = [1,0,0,0,1], n = 2
 * Output: false
 *
 * Approach:
 * [To be documented after implementation]
 *
 * Time Complexity: [To be analyzed]
 * Space Complexity: [To be analyzed]
 *
 * @see <a href="https://leetcode.com/problems/can-place-flowers/description/">LeetCode Problem #605</a>
 */
public class CanPlaceFlowers {

    /**
     * Determines whether n new flowers can be planted in the flowerbed
     * without any two flowers ending up in adjacent plots.
     *
     * @param flowerbed array of 0's (empty) and 1's (planted) plots
     * @param n the number of new flowers to plant
     * @return true if all n flowers can be planted validly, false otherwise
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for(int i=0; i<flowerbed.length && n>0; i++) {
            int candidate = i;
            if(flowerbed[candidate] == 1 
                || (candidate>0 && flowerbed[candidate-1]==1)
                || (candidate<flowerbed.length-1 && flowerbed[candidate+1]==1)
            ){
                continue;
            } else {
                flowerbed[candidate] = 1;
                n--;
            }
        }
        return n<=0;
    }

    /**
     * Helper method to run a single test case.
     *
     * @param testNum test case number
     * @param flowerbed input flowerbed array
     * @param n input number of flowers to plant
     * @param expected expected result
     * @param actual actual result from solution
     * @param description test case description
     */
    private void runTest(int testNum, int[] flowerbed, int n, boolean expected, boolean actual, String description) {
        boolean passed = expected == actual;
        String status = passed ? "✓ PASS" : "✗ FAIL";
        String bedStr = arrayToString(flowerbed);

        System.out.printf("Test %d: %s - %s%n", testNum, description, status);
        System.out.printf("  Input:    flowerbed=%s, n=%d%n", bedStr, n);
        System.out.printf("  Expected: %b%n", expected);
        System.out.printf("  Actual:   %b%n", actual);
        System.out.println();
    }

    /**
     * Helper method to print test summary statistics.
     *
     * @param results array of boolean results for each test
     */
    private void printSummary(boolean[] results) {
        int passed = 0;
        int total = results.length;

        for (boolean result : results) {
            if (result) passed++;
        }

        System.out.println("=".repeat(60));
        System.out.printf("TEST SUMMARY: %d/%d passed (%.1f%%)%n",
                         passed, total, (passed * 100.0 / total));
        System.out.println("=".repeat(60));
    }

    /**
     * Converts an array to a formatted string for display, truncating
     * long arrays.
     *
     * @param arr input array
     * @return formatted string representation
     */
    private String arrayToString(int[] arr) {
        if (arr == null) return "null";
        if (arr.length == 0) return "[]";
        if (arr.length > 20) {
            return "[" + arr[0] + ", " + arr[1] + ", ..., " +
                   arr[arr.length - 2] + ", " + arr[arr.length - 1] +
                   "] (length=" + arr.length + ")";
        }

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Main method with comprehensive test cases.
     */
    public static void main(String[] args) {
        CanPlaceFlowers solution = new CanPlaceFlowers();
        boolean[] results = new boolean[10];
        int testNum = 0;

        System.out.println("Testing Can Place Flowers");
        System.out.println("=".repeat(60));
        System.out.println();

        // Test 1: Example 1 - exactly enough room
        {
            int[] flowerbed = {1, 0, 0, 0, 1};
            int n = 1;
            boolean expected = true;
            boolean actual = solution.canPlaceFlowers(flowerbed, n);
            solution.runTest(++testNum, flowerbed, n, expected, actual,
                           "Example 1 - exactly enough room");
            results[testNum - 1] = expected == actual;
        }

        // Test 2: Example 2 - not enough room
        {
            int[] flowerbed = {1, 0, 0, 0, 1};
            int n = 2;
            boolean expected = false;
            boolean actual = solution.canPlaceFlowers(flowerbed, n);
            solution.runTest(++testNum, flowerbed, n, expected, actual,
                           "Example 2 - not enough room");
            results[testNum - 1] = expected == actual;
        }

        // Test 3: n = 0 - always true regardless of flowerbed
        {
            int[] flowerbed = {1, 0, 0, 0, 1};
            int n = 0;
            boolean expected = true;
            boolean actual = solution.canPlaceFlowers(flowerbed, n);
            solution.runTest(++testNum, flowerbed, n, expected, actual,
                           "n = 0 - always true regardless of flowerbed");
            results[testNum - 1] = expected == actual;
        }

        // Test 4: Minimum constraint - single empty plot
        {
            int[] flowerbed = {0};
            int n = 1;
            boolean expected = true;
            boolean actual = solution.canPlaceFlowers(flowerbed, n);
            solution.runTest(++testNum, flowerbed, n, expected, actual,
                           "Minimum constraint - single empty plot");
            results[testNum - 1] = expected == actual;
        }

        // Test 5: Minimum constraint - single already-planted plot
        {
            int[] flowerbed = {1};
            int n = 1;
            boolean expected = false;
            boolean actual = solution.canPlaceFlowers(flowerbed, n);
            solution.runTest(++testNum, flowerbed, n, expected, actual,
                           "Minimum constraint - single already-planted plot");
            results[testNum - 1] = expected == actual;
        }

        // Test 6: All empty plots - maximum capacity
        {
            int[] flowerbed = {0, 0, 0, 0, 0};
            int n = 3;
            boolean expected = true;
            boolean actual = solution.canPlaceFlowers(flowerbed, n);
            solution.runTest(++testNum, flowerbed, n, expected, actual,
                           "All empty plots - maximum capacity (3 flowers in 5 plots)");
            results[testNum - 1] = expected == actual;
        }

        // Test 7: All empty plots - one more than maximum capacity
        {
            int[] flowerbed = {0, 0, 0, 0, 0};
            int n = 4;
            boolean expected = false;
            boolean actual = solution.canPlaceFlowers(flowerbed, n);
            solution.runTest(++testNum, flowerbed, n, expected, actual,
                           "All empty plots - one more than maximum capacity");
            results[testNum - 1] = expected == actual;
        }

        // Test 8: Already fully alternating - no room left
        {
            int[] flowerbed = {1, 0, 1, 0, 1, 0, 1};
            int n = 1;
            boolean expected = false;
            boolean actual = solution.canPlaceFlowers(flowerbed, n);
            solution.runTest(++testNum, flowerbed, n, expected, actual,
                           "Already fully alternating - no room left");
            results[testNum - 1] = expected == actual;
        }

        // Test 9: Single planted plot at the very start
        {
            int[] flowerbed = {1, 0, 0, 0, 0, 0, 1};
            int n = 2;
            boolean expected = true;
            boolean actual = solution.canPlaceFlowers(flowerbed, n);
            solution.runTest(++testNum, flowerbed, n, expected, actual,
                           "Planted plots at both ends, room for two in between");
            results[testNum - 1] = expected == actual;
        }

        // Test 10: Maximum constraint - large all-empty flowerbed
        {
            int[] flowerbed = new int[20000];
            int n = 10000;
            boolean expected = true;
            boolean actual = solution.canPlaceFlowers(flowerbed, n);
            solution.runTest(++testNum, flowerbed, n, expected, actual,
                           "Maximum constraint - large all-empty flowerbed");
            results[testNum - 1] = expected == actual;
        }

        solution.printSummary(results);
    }
}
