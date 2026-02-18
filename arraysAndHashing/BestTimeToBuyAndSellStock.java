package arraysAndHashing;

import java.util.*;

/**
 * LeetCode Problem 121: Best Time to Buy and Sell Stock
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 *
 * <p>Problem Description:
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a 
 * different day in the future to sell that stock.
 *
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any 
 * profit, return 0.
 *
 * <p>Constraints:
 * - 1 <= prices.length <= 10^5
 * - 0 <= prices[i] <= 10^4
 *
 * <p>Note:
 * - You must buy before you can sell (cannot sell on the same day or before buying)
 *
 * <p>Examples:
 * Example 1:
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6 - 1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before 
 * you sell.
 *
 * Example 2:
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transactions are done and the max profit = 0.
 *
 * <p>Approach:
 * Single pass: track the minimum price seen so far (best buy candidate) and at each day
 * compute profit if we sold today (price - minSoFar). Update max profit and then update
 * the minimum. Order matters: update profit first, then min, so we never use "buy today"
 * when considering "sell today."
 *
 * <p>Time Complexity: O(n) — one pass over the array.
 * <p>Space Complexity: O(1) — two variables only.
 *
 * <p>Key Learnings:
 * - "Minimum so far" + "max profit so far" gives optimal single-buy single-sell in one pass.
 * - Same pattern generalizes to other "best pair with ordering" problems.
 *
 * <p>Alternative Approaches:
 * - Brute force: try all (i, j) with i < j → O(n²) time, O(1) space.
 * - Suffix max: precompute max price to the right per index → O(n) time, O(n) space (unnecessary).
 */
public class BestTimeToBuyAndSellStock {

    /**
     * Finds the maximum profit from buying and selling a stock once.
     *
     * @param prices the array of stock prices, where prices[i] is the price on day i
     * @return the maximum profit achievable, or 0 if no profit is possible
     */
    public int maxProfit(int[] prices) {
        int buyAt = prices[0];
        int maxProfit = 0;
        for (int price : prices) {
            maxProfit = Math.max(maxProfit, price - buyAt);
            buyAt = Math.min(buyAt, price);
        }
        return maxProfit;
    }

    public int maxProfitFirstAttemptDetailed(int[] prices) {
        int buyAt = prices[0];
        int buyAtIndex = 0;
        int sellAt = prices[0];
        int sellAtIndex = 0;
        int maxProfit = 0;
        for (int i=1; i<prices.length; i++) {
            // maxProfit = Math.max(maxProfit, prices[i] - buyAt);
            if(maxProfit < prices[i] - buyAt) {
                maxProfit = prices[i] - buyAt;
                sellAt = prices[i];
                sellAtIndex = i;
            }
            // buyAt = Math.min(buyAt, prices[i]);
            if(prices[i] < buyAt) {
                buyAt = prices[i];
                buyAtIndex = i;
            }
        }
        System.out.println("\n\n\nbuyAt : " + buyAt + " ----- sellAt : " + sellAt);
        System.out.println("buyAtIndex : " + buyAtIndex + " ----- sellAtIndex : " + sellAtIndex);
        return maxProfit;
    }

    // ======================== Test Helper Methods ========================

    /**
     * Runs a single test case and prints the result.
     *
     * @param testNum the test case number
     * @param prices the input array of stock prices
     * @param expected the expected maximum profit
     * @param actual the actual result from the solution
     * @param description brief description of the test case
     */
    private static void runTest(
            int testNum,
            int[] prices,
            int expected,
            int actual,
            String description) {
        
        boolean passed = (expected == actual);
        String status = passed ? "✓ PASS" : "✗ FAIL";
        
        System.out.println("\n" + status + " - Test " + testNum + ": " + description);
        System.out.println("  Input:    prices = " + Arrays.toString(prices));
        System.out.println("  Expected: " + expected);
        System.out.println("  Actual:   " + actual);
        
        if (!passed) {
            System.out.println("  ❌ Test failed! Difference: " + (actual - expected));
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
        BestTimeToBuyAndSellStock solution = new BestTimeToBuyAndSellStock();
        int totalTests = 0;
        int passedTests = 0;

        System.out.println("=".repeat(70));
        System.out.println("Testing LeetCode #121: Best Time to Buy and Sell Stock");
        System.out.println("=".repeat(70));

        // Test 1: Example 1 - Normal case with profit
        {
            int[] prices = {7, 1, 5, 3, 6, 4};
            int expected = 5;  // Buy at 1, sell at 6
            int actual = solution.maxProfit(prices);
            runTest(++totalTests, prices, expected, actual, 
                "Example 1 - Buy low, sell high");
            if (expected == actual) passedTests++;
        }

        // Test 2: Example 2 - Decreasing prices (no profit)
        {
            int[] prices = {7, 6, 4, 3, 1};
            int expected = 0;  // No profitable transaction
            int actual = solution.maxProfit(prices);
            runTest(++totalTests, prices, expected, actual, 
                "Example 2 - Decreasing prices");
            if (expected == actual) passedTests++;
        }

        // Test 3: Single price (can't buy and sell)
        {
            int[] prices = {5};
            int expected = 0;
            int actual = solution.maxProfit(prices);
            runTest(++totalTests, prices, expected, actual, 
                "Single day - no transaction possible");
            if (expected == actual) passedTests++;
        }

        // Test 4: Two prices - profit possible
        {
            int[] prices = {1, 5};
            int expected = 4;  // Buy at 1, sell at 5
            int actual = solution.maxProfit(prices);
            runTest(++totalTests, prices, expected, actual, 
                "Two days - simple profit");
            if (expected == actual) passedTests++;
        }

        // Test 5: Two prices - no profit
        {
            int[] prices = {5, 1};
            int expected = 0;  // Price drops, no profit
            int actual = solution.maxProfit(prices);
            runTest(++totalTests, prices, expected, actual, 
                "Two days - price drops");
            if (expected == actual) passedTests++;
        }

        // Test 6: All same prices
        {
            int[] prices = {3, 3, 3, 3, 3};
            int expected = 0;  // No change in price
            int actual = solution.maxProfit(prices);
            runTest(++totalTests, prices, expected, actual, 
                "All same prices");
            if (expected == actual) passedTests++;
        }

        // Test 7: Profit at the end
        {
            int[] prices = {2, 4, 1, 7};
            int expected = 6;  // Buy at 1, sell at 7
            int actual = solution.maxProfit(prices);
            runTest(++totalTests, prices, expected, actual, 
                "Maximum profit at the end");
            if (expected == actual) passedTests++;
        }

        // Test 8: Profit at the beginning
        {
            int[] prices = {1, 7, 4, 2, 1};
            int expected = 6;  // Buy at 1, sell at 7
            int actual = solution.maxProfit(prices);
            runTest(++totalTests, prices, expected, actual, 
                "Maximum profit at beginning");
            if (expected == actual) passedTests++;
        }

        // Test 9: Multiple valleys and peaks
        {
            int[] prices = {3, 2, 6, 5, 0, 3};
            int expected = 4;  // Buy at 2, sell at 6 (or buy at 0, sell at 3)
            int actual = solution.maxProfit(prices);
            runTest(++totalTests, prices, expected, actual, 
                "Multiple valleys and peaks");
            if (expected == actual) passedTests++;
        }

        // Test 10: Increasing prices
        {
            int[] prices = {1, 2, 3, 4, 5};
            int expected = 4;  // Buy at 1, sell at 5
            int actual = solution.maxProfit(prices);
            runTest(++totalTests, prices, expected, actual, 
                "Continuously increasing");
            if (expected == actual) passedTests++;
        }

        // Test 11: Large price difference
        {
            int[] prices = {10000, 1, 10000};
            int expected = 9999;  // Buy at 1, sell at 10000
            int actual = solution.maxProfit(prices);
            runTest(++totalTests, prices, expected, actual, 
                "Large price difference");
            if (expected == actual) passedTests++;
        }

        // Test 12: Zero prices
        {
            int[] prices = {0, 0, 0, 0};
            int expected = 0;
            int actual = solution.maxProfit(prices);
            runTest(++totalTests, prices, expected, actual, 
                "All zeros");
            if (expected == actual) passedTests++;
        }

        // Test 13: Peak in middle, valley at end
        {
            int[] prices = {5, 10, 3, 1};
            int expected = 5;  // Buy at 5, sell at 10
            int actual = solution.maxProfit(prices);
            runTest(++totalTests, prices, expected, actual, 
                "Peak early, then decline");
            if (expected == actual) passedTests++;
        }

        // Test 14: Small oscillations
        {
            int[] prices = {2, 1, 2, 1, 2, 1, 2};
            int expected = 1;  // Multiple 1-profit opportunities
            int actual = solution.maxProfit(prices);
            runTest(++totalTests, prices, expected, actual, 
                "Small oscillations");
            if (expected == actual) passedTests++;
        }

        // Test 15: Performance test - Large array
        {
            int[] prices = new int[1000];
            for (int i = 0; i < 1000; i++) {
                prices[i] = 1000 - i;  // Decreasing from 1000 to 1
            }
            prices[999] = 2000;  // Big spike at the end
            int expected = 1998;  // Buy at 2 (min price at index 998), sell at 2000
            
            long startTime = System.nanoTime();
            int actual = solution.maxProfit(prices);
            long endTime = System.nanoTime();
            double elapsedMs = (endTime - startTime) / 1_000_000.0;
            
            runTest(++totalTests, new int[]{/* abbreviated */}, expected, actual, 
                String.format("Performance test (n=1000, %.2f ms)", elapsedMs));
            if (expected == actual) passedTests++;
        }

        // Test 16: Maximum constraint values
        {
            int[] prices = {10000, 0, 10000, 0, 10000};
            int expected = 10000;  // Buy at 0, sell at 10000
            int actual = solution.maxProfit(prices);
            runTest(++totalTests, prices, expected, actual, 
                "Max constraint values");
            if (expected == actual) passedTests++;
        }

        printSummary(totalTests, passedTests);
    }
}
