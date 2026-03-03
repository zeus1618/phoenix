package arraysAndHashing;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * 973. K Closest Points to Origin
 * 
 * Difficulty: Medium
 * Topics: Array, Math, Divide and Conquer, Geometry, Sorting, Heap (Priority Queue), Quickselect
 * 
 * Problem Description:
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane 
 * and an integer k, return the k closest points to the origin (0, 0).
 * 
 * The distance between two points on the X-Y plane is the Euclidean distance 
 * (i.e., √(x1 - x2)² + (y1 - y2)²).
 * 
 * You may return the answer in any order. The answer is guaranteed to be unique 
 * (except for the order that it is in).
 * 
 * Constraints:
 * - -10^4 <= xi, yi <= 10^4
 * - 1 <= k <= points.length <= 10^4
 * 
 * Examples:
 * 
 * Example 1:
 * Input: points = [[1,3],[-2,2]], k = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
 * 
 * Example 2:
 * Input: points = [[3,3],[5,-1],[-2,4]], k = 2
 * Output: [[3,3],[-2,4]]
 * Explanation: The answer [[-2,4],[3,3]] would also be accepted.
 * 
 * Approach:
 * Full sort with custom comparator. Create Pair objects for each point containing coordinates
 * and squared distance (x² + y²). Sort all points by distance using Integer.compare() to avoid
 * overflow. Extract first k points from sorted list. Trading extra memory (n Pair objects) for
 * code clarity and readability.
 * 
 * Time Complexity: O(n log n)
 * - Creating Pair objects: O(n)
 * - Sorting n points: O(n log n) - Java's TimSort
 * - Extracting k points: O(k)
 * - Overall: O(n log n) dominates
 * 
 * Space Complexity: O(n)
 * - ArrayList and n Pair objects: O(n)
 * - Output array: O(k) - doesn't count toward space complexity
 * - Overall: O(n)
 * 
 * Alternative Approaches:
 * 1. Max Heap (Priority Queue) - O(n log k) time, O(k) space
 *    Better when k << n (e.g., k=10, n=10,000)
 *    Maintains heap of size k, evicting furthest point when size exceeds k
 * 
 * 2. Quickselect - O(n) average time, O(1) space
 *    Partition-based selection, optimal average case but O(n²) worst case
 *    More complex to implement, less predictable performance
 * 
 * 3. In-place Sort - O(n log n) time, O(1) space
 *    Sort points array directly with lambda comparator
 *    More memory efficient but same time complexity
 * 
 * Key Learnings:
 * - Sqrt optimization: Use x² + y² instead of √(x² + y²) for comparisons
 *   Since √a < √b ⟺ a < b for non-negative numbers, saves expensive sqrt operations
 * - Integer.compare() prevents overflow: Subtraction (a-b) can overflow, always use Integer.compare()
 * - Full sort is acceptable: O(n log n) is perfectly fine when k ≈ n or simplicity is priority
 * - Theory vs Practice: Modern sorting is highly optimized; don't over-optimize prematurely
 * - OOP encapsulation: Pair class makes code readable at cost of extra memory
 * 
 * @see <a href="https://leetcode.com/problems/k-closest-points-to-origin/">LeetCode Problem #973</a>
 */
public class KClosestPointsToOrigin {

    /**
     * Returns the k closest points to the origin (0, 0).
     * 
     * @param points array of points where points[i] = [xi, yi]
     * @param k number of closest points to return
     * @return array of k closest points to origin
     */
    public int[][] kClosest(int[][] points, int k) {
        List<Pair> coordinates = new ArrayList<>();
        for(int i=0; i<points.length; i++) {
            Pair p = new Pair(points[i][0], points[i][1]);
            coordinates.add(p);
        }
        coordinates.sort((a,b) -> {
            return Integer.compare(a.distance(), b.distance());
        });
        int[][] kClose = new int[k][2];
        for(int i=0; i<k; i++) {
            kClose[i][0] = coordinates.get(i).x;
            kClose[i][1] = coordinates.get(i).y;
        }
        // System.out.println(coordinates.toString());
        return kClose;
    }

    public class Pair {
        public int x,y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int distance(){
            return x*x + y*y;
        }
        @Override
        public String toString() {
            return x + ":" + y;
        }
    }

    /**
     * Helper method to run a single test case and display results.
     */
    private static void runTest(int testNumber, int[][] points, int k, int[][] expected, int[][] actual) {
        boolean passed = areArraysEquivalent(expected, actual);
        
        System.out.println("Test " + testNumber + ": " + (passed ? "PASS ✓" : "FAIL ✗"));
        System.out.println("  Input: points = " + Arrays.deepToString(points) + ", k = " + k);
        System.out.println("  Expected: " + Arrays.deepToString(expected));
        System.out.println("  Actual:   " + Arrays.deepToString(actual));
        
        if (!passed) {
            System.out.println("  Note: Order of points doesn't matter; checking if sets are equivalent");
        }
        System.out.println();
    }

    /**
     * Helper method to check if two 2D arrays contain the same points (order doesn't matter).
     */
    private static boolean areArraysEquivalent(int[][] arr1, int[][] arr2) {
        if (arr1.length != arr2.length) return false;
        
        // Convert to comparable format and sort for comparison
        String[] sorted1 = Arrays.stream(arr1)
            .map(Arrays::toString)
            .sorted()
            .toArray(String[]::new);
        
        String[] sorted2 = Arrays.stream(arr2)
            .map(Arrays::toString)
            .sorted()
            .toArray(String[]::new);
        
        return Arrays.equals(sorted1, sorted2);
    }

    /**
     * Prints test summary with overall statistics.
     */
    private static void printSummary(int passed, int total) {
        System.out.println("═══════════════════════════════════════════════");
        System.out.println("TEST SUMMARY");
        System.out.println("═══════════════════════════════════════════════");
        System.out.println("Total Tests: " + total);
        System.out.println("Passed: " + passed + " ✓");
        System.out.println("Failed: " + (total - passed) + " ✗");
        System.out.println("Success Rate: " + String.format("%.1f", (passed * 100.0 / total)) + "%");
        System.out.println("═══════════════════════════════════════════════");
    }

    public static void main(String[] args) {
        KClosestPointsToOrigin solution = new KClosestPointsToOrigin();
        int passedTests = 0;
        int totalTests = 0;

        System.out.println("═══════════════════════════════════════════════");
        System.out.println("Running K Closest Points to Origin Tests");
        System.out.println("═══════════════════════════════════════════════\n");

        // Example 1: Single closest point
        int[][] points1 = {{1, 3}, {-2, 2}};
        int k1 = 1;
        int[][] expected1 = {{-2, 2}};
        int[][] actual1 = solution.kClosest(points1, k1);
        runTest(++totalTests, points1, k1, expected1, actual1);
        if (areArraysEquivalent(expected1, actual1)) passedTests++;

        // Example 2: Two closest points from three
        int[][] points2 = {{3, 3}, {5, -1}, {-2, 4}};
        int k2 = 2;
        int[][] expected2 = {{3, 3}, {-2, 4}};
        int[][] actual2 = solution.kClosest(points2, k2);
        runTest(++totalTests, points2, k2, expected2, actual2);
        if (areArraysEquivalent(expected2, actual2)) passedTests++;

        // Test 3: All points on same distance circle (edge case)
        int[][] points3 = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int k3 = 2;
        // Any 2 points are acceptable since all have same distance = 1
        int[][] actual3 = solution.kClosest(points3, k3);
        runTest(++totalTests, points3, k3, new int[][]{{1, 0}, {0, 1}}, actual3);
        // For this test, just check we got k points
        if (actual3.length == k3) passedTests++;

        // Test 4: k equals array length (return all points)
        int[][] points4 = {{0, 1}, {1, 0}};
        int k4 = 2;
        int[][] expected4 = {{0, 1}, {1, 0}};
        int[][] actual4 = solution.kClosest(points4, k4);
        runTest(++totalTests, points4, k4, expected4, actual4);
        if (areArraysEquivalent(expected4, actual4)) passedTests++;

        // Test 5: Points at origin
        int[][] points5 = {{0, 0}, {1, 1}, {-1, -1}};
        int k5 = 1;
        int[][] expected5 = {{0, 0}};
        int[][] actual5 = solution.kClosest(points5, k5);
        runTest(++totalTests, points5, k5, expected5, actual5);
        if (areArraysEquivalent(expected5, actual5)) passedTests++;

        // Test 6: Negative coordinates
        int[][] points6 = {{-5, -5}, {-2, -2}, {1, 1}};
        int k6 = 2;
        int[][] expected6 = {{-2, -2}, {1, 1}};
        int[][] actual6 = solution.kClosest(points6, k6);
        runTest(++totalTests, points6, k6, expected6, actual6);
        if (areArraysEquivalent(expected6, actual6)) passedTests++;

        // Test 7: Large coordinates (constraint boundary)
        int[][] points7 = {{10000, 10000}, {-10000, -10000}, {0, 1}};
        int k7 = 1;
        int[][] expected7 = {{0, 1}};
        int[][] actual7 = solution.kClosest(points7, k7);
        runTest(++totalTests, points7, k7, expected7, actual7);
        if (areArraysEquivalent(expected7, actual7)) passedTests++;

        // Test 8: Points along x-axis
        int[][] points8 = {{1, 0}, {2, 0}, {3, 0}, {4, 0}};
        int k8 = 2;
        int[][] expected8 = {{1, 0}, {2, 0}};
        int[][] actual8 = solution.kClosest(points8, k8);
        runTest(++totalTests, points8, k8, expected8, actual8);
        if (areArraysEquivalent(expected8, actual8)) passedTests++;

        // Test 9: Points along y-axis
        int[][] points9 = {{0, 1}, {0, 2}, {0, 3}, {0, -1}};
        int k9 = 3;
        int[][] expected9 = {{0, 1}, {0, -1}, {0, 2}};
        int[][] actual9 = solution.kClosest(points9, k9);
        runTest(++totalTests, points9, k9, expected9, actual9);
        if (areArraysEquivalent(expected9, actual9)) passedTests++;

        // Test 10: Mixed quadrants
        int[][] points10 = {{1, 2}, {-1, 2}, {1, -2}, {-1, -2}, {0, 0}};
        int k10 = 3;
        int[][] expected10 = {{0, 0}, {1, 2}, {-1, 2}};
        int[][] actual10 = solution.kClosest(points10, k10);
        runTest(++totalTests, points10, k10, expected10, actual10);
        if (areArraysEquivalent(expected10, actual10)) passedTests++;

        // Print final summary
        printSummary(passedTests, totalTests);
    }
}
