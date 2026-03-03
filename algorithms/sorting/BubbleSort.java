package algorithms.sorting;

import java.util.Arrays;

/**
 * Bubble Sort Algorithm Implementation
 *
 * <p>Description:
 * Bubble Sort is a simple comparison-based sorting algorithm that repeatedly steps through
 * the list, compares adjacent elements, and swaps them if they are in the wrong order.
 * The pass through the list is repeated until the list is sorted.
 *
 * <p>Algorithm:
 * 1. Compare adjacent elements in the array
 * 2. Swap them if the left element is greater than the right element
 * 3. After each complete pass, the largest unsorted element "bubbles up" to its correct position
 * 4. Repeat until no swaps are needed (array is sorted)
 *
 * <p>Characteristics:
 * - Simple and easy to understand
 * - Stable sort (maintains relative order of equal elements)
 * - In-place sorting (requires O(1) extra space)
 * - Adaptive: can be optimized to detect already sorted arrays
 *
 * <p>Time Complexity:
 * - Best Case: O(n) - when array is already sorted (with optimization)
 * - Average Case: O(n²) - random order
 * - Worst Case: O(n²) - when array is reverse sorted
 *
 * <p>Space Complexity: O(1) - only uses a constant amount of extra space
 *
 * <p>When to Use:
 * - Small datasets (< 10-20 elements)
 * - Educational purposes to understand sorting basics
 * - When simplicity is more important than performance
 * - When you need a stable sort and space is extremely limited
 *
 * <p>When NOT to Use:
 * - Large datasets (very inefficient compared to O(n log n) algorithms)
 * - Performance-critical applications
 * - Use Quick Sort, Merge Sort, or Heap Sort instead for large data
 *
 * <p>Variations:
 * - Optimized Bubble Sort: stops early if no swaps occur in a pass
 * - Cocktail Shaker Sort: bidirectional bubble sort
 *
 * <p>Key Learnings:
 * [To be documented after implementation]
 */
public class BubbleSort {

    /**
     * Sorts the given array using Bubble Sort algorithm (basic version).
     * This version always performs n-1 passes regardless of array state.
     *
     * @param arr the array to sort (modified in-place)
     */
    public static void sort(int[] arr) {
        int temp = 0;
        int executions = 0;
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr.length-i-1; j++) {
                executions++;
                if(arr[j] > arr[j+1]){
                    temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println("Executions :" + executions);
    }

    /**
     * Sorts the given array using Optimized Bubble Sort algorithm.
     * This version stops early if the array becomes sorted before all passes.
     *
     * @param arr the array to sort (modified in-place)
     */
    public static void sortOptimized(int[] arr) {
        int temp = 0;
        int executions = 0;
        boolean anySwap = true;
        for(int i=0; i<arr.length && anySwap; i++){
            anySwap = false;
            for(int j=0; j<arr.length-i-1; j++) {
                executions++;
                if(arr[j] > arr[j+1]){
                    anySwap = true;
                    temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println("Executions :" + executions);
    }

    /**
     * Demonstrates the sorting process step-by-step (for learning purposes).
     * Prints the array state after each pass.
     *
     * @param arr the array to sort
     */
    public static void sortWithVisualization(int[] arr) {
        // TODO: Implement bubble sort with step-by-step printing
        // This helps visualize how elements "bubble up" to their positions
        int temp = 0;
        int executions = 0;
        int counter = 0;
        int swaps = 0;
        System.out.println("Start : " + Arrays.toString(arr));
        for(int i=0; i<arr.length; i++){
            swaps=0;
            for(int j=0; j<arr.length-i-1; j++) {
                executions++;
                if(arr[j] > arr[j+1]){
                    swaps++;
                    temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
            counter++;
            System.out.println("Counter-" + counter + " : swaps-" + swaps +
            "\t" + Arrays.toString(arr));
        }
        System.out.println("Finish : " + Arrays.toString(arr));
        System.out.println("Executions :" + executions);
    }

    /**
     * Main method with comprehensive test cases using the common test suite.
     */
    public static void main(String[] args) {
        // Test basic implementation
        System.out.println("Testing Basic Bubble Sort:");
        System.out.println("─────────────────────────────────────────────────────────────\n");
        
        // TODO: Uncomment when implementation is ready
        SortingTestSuite.runAllTests("Bubble Sort (Basic)", BubbleSort::sort);
        
        System.out.println("\n" + "─".repeat(61) + "\n");
        
        // Test optimized implementation
        System.out.println("Testing Optimized Bubble Sort:");
        System.out.println("─────────────────────────────────────────────────────────────\n");
        
        // TODO: Uncomment when implementation is ready
        SortingTestSuite.runAllTests("Bubble Sort (Optimized)", BubbleSort::sortOptimized);
        
        // Visualization example
        System.out.println("\n" + "─".repeat(61) + "\n");
        System.out.println("Visualization Example:");
        System.out.println("─────────────────────────────────────────────────────────────\n");
        
        int[] visualDemo = {64, 34, 25, 12, 22};
        System.out.println("Original array: [64, 34, 25, 12, 22]");
        System.out.println();
        
        // TODO: Uncomment when implementation is ready
        sortWithVisualization(visualDemo);
    }

}
