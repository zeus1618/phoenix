package algorithms.sorting;

import java.util.Arrays;

/**
 * Selection Sort Algorithm Implementation
 *
 * <p>Description:
 * Selection Sort is a simple comparison-based sorting algorithm that divides the input
 * into a sorted and unsorted region. It repeatedly selects the smallest (or largest)
 * element from the unsorted region and moves it to the end of the sorted region.
 *
 * <p>Algorithm:
 * 1. Start with the first element as the current position
 * 2. Find the minimum element in the remaining unsorted portion
 * 3. Swap the minimum element with the element at the current position
 * 4. Move the boundary between sorted and unsorted regions one position forward
 * 5. Repeat until the entire array is sorted
 *
 * <p>Characteristics:
 * - Simple and intuitive to understand
 * - NOT stable (relative order of equal elements may change)
 * - In-place sorting (requires O(1) extra space)
 * - Makes minimum number of swaps: O(n) swaps in worst case
 * - Performance does NOT improve on nearly sorted data
 *
 * <p>Time Complexity:
 * - Best Case: O(n²) - always performs same number of comparisons
 * - Average Case: O(n²) - quadratic time
 * - Worst Case: O(n²) - same as best/average case
 * - Number of Swaps: O(n) - at most n swaps (better than Bubble Sort's O(n²))
 *
 * <p>Space Complexity: O(1) - only uses a constant amount of extra space
 *
 * <p>When to Use:
 * - Small datasets (< 20 elements)
 * - When memory write (swap) is costly - minimizes number of swaps
 * - When you need in-place sorting with minimal extra memory
 * - When simplicity is more important than performance
 * - Systems where writes are expensive (e.g., flash memory, EEPROM)
 *
 * <p>When NOT to Use:
 * - Large datasets (inefficient O(n²) complexity)
 * - When stability is required (use Insertion Sort or Merge Sort)
 * - Performance-critical applications (use Quick Sort, Merge Sort, or Heap Sort)
 * - Nearly sorted data (Insertion Sort would be better)
 *
 * <p>Comparison with Bubble Sort:
 * - Same O(n²) time complexity
 * - Selection Sort: O(n) swaps vs Bubble Sort: O(n²) swaps
 * - Bubble Sort is stable, Selection Sort is NOT
 * - Bubble Sort can be optimized for sorted arrays, Selection Sort cannot
 *
 * <p>Variations:
 * - Bidirectional Selection Sort: finds both min and max in each pass
 * - Stable Selection Sort: modified version that maintains stability
 * - Heap Sort: advanced version using heap data structure (O(n log n))
 *
 * <p>Key Learnings:
 * [To be documented after implementation]
 */
public class SelectionSort {

    /**
     * Sorts the given array using Selection Sort algorithm (basic version).
     * Finds the minimum element in unsorted portion and swaps it to the front.
     *
     * @param arr the array to sort (modified in-place)
     */
    public static void sort(int[] arr) {
        // TODO: Implement basic selection sort
        // Hint: 
        // - Outer loop: iterate through each position (0 to n-1)
        // - Inner loop: find index of minimum element in remaining unsorted portion
        // - Swap minimum element with current position

        int currentMinPos = 0;
        int executions = 0;
        for(int i=0; i<arr.length; i++){
            currentMinPos = i;
            for(int j=i; j<arr.length; j++){
                executions++;
                if(arr[j] < arr[currentMinPos]){
                    currentMinPos = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[currentMinPos];
            arr[currentMinPos] = temp;
        }
        System.out.println("\nExecutions :" + executions + "\n\n");
    }

    /**
     * Sorts the given array using Bidirectional Selection Sort.
     * Finds both minimum and maximum in each pass, placing them at both ends.
     * This reduces the number of passes by half.
     *
     * @param arr the array to sort (modified in-place)
     */
    public static void sortBidirectional(int[] arr) {
        // TODO: Implement bidirectional selection sort
        // Hint:
        // - Process array from both ends simultaneously
        // - Find both min and max in each pass
        // - Place min at left boundary, max at right boundary
        // - Move both boundaries inward

        int currentMinPos = 0;
        int currentMaxPos = 0;
        int startIndex = 0;
        int executions = 0;
        int endIndex = arr.length-1;
        for(int i=0; i<(arr.length/2); i++){
            startIndex = i;
            currentMinPos = startIndex;
            endIndex = arr.length-i-1;
            currentMaxPos = endIndex;
            for(int j=i; j<=endIndex; j++){
                executions++;
                if(arr[j] < arr[currentMinPos])
                    currentMinPos = j;
                if(arr[j] > arr[currentMaxPos])
                    currentMaxPos = j;
            }
            int temp = arr[startIndex];
            arr[startIndex] = arr[currentMinPos];
            arr[currentMinPos] = temp;

            //incase max is at beginning and it already got swapped
            if(currentMaxPos == startIndex){
                temp = arr[endIndex];
                arr[endIndex] = arr[currentMinPos];
                arr[currentMinPos] = temp;
            } else {
                temp = arr[endIndex];
                arr[endIndex] = arr[currentMaxPos];
                arr[currentMaxPos] = temp;
            }
        }
        System.out.println("\nExecutions :" + executions + "\n\n");
    }

    /**
     * Demonstrates the sorting process step-by-step (for learning purposes).
     * Shows the selection of minimum element and swapping in each pass.
     * Highlights the sorted vs unsorted regions.
     *
     * @param arr the array to sort
     */
    public static void sortWithVisualization(int[] arr) {
        // TODO: Implement selection sort with step-by-step printing
        // Show:
        // - Current position being filled
        // - Which element is selected as minimum
        // - Array state after each swap
        // - Visual distinction between sorted and unsorted portions

        int currentMinPos = 0;
        int currentMaxPos = 0;
        int startIndex = 0;
        int executions = 0;
        int endIndex = arr.length-1;
        System.out.println("\nStart : " + Arrays.toString(arr));
        for(int i=0; i<(arr.length/2); i++){
            startIndex = i;
            currentMinPos = startIndex;
            endIndex = arr.length-i-1;
            currentMaxPos = endIndex;
            for(int j=i; j<=endIndex; j++){
                executions++;
                if(arr[j] < arr[currentMinPos])
                    currentMinPos = j;
                if(arr[j] > arr[currentMaxPos])
                    currentMaxPos = j;
            }
            System.out.println(String.format("startIndex : %d\tendIndex : %d\t currentMinPos : %d\t currentMaxPos : %d\t ", startIndex, endIndex, currentMinPos, currentMaxPos));
            int temp = arr[startIndex];
            arr[startIndex] = arr[currentMinPos];
            arr[currentMinPos] = temp;

            //incase max is at beginning and it already got swapped
            if(currentMaxPos == startIndex){
                temp = arr[endIndex];
                arr[endIndex] = arr[currentMinPos];
                arr[currentMinPos] = temp;
            } else {
                temp = arr[endIndex];
                arr[endIndex] = arr[currentMaxPos];
                arr[currentMaxPos] = temp;
            }
            System.out.println("\n\nstep-" + i +"\t" + Arrays.toString(arr));

        }
        System.out.println("\nFinish : " + Arrays.toString(arr));
        System.out.println("\nExecutions :" + executions + "\n\n");
    }

    /**
     * Main method with comprehensive test cases using the common test suite.
     */
    public static void main(String[] args) {
        // Test basic implementation
        System.out.println("Testing Basic Selection Sort:");
        System.out.println("─────────────────────────────────────────────────────────────\n");
        
        // TODO: Uncomment when implementation is ready
        SortingTestSuite.runAllTests("Selection Sort (Basic)", SelectionSort::sort);
        
        System.out.println("\n" + "─".repeat(65) + "\n");
        
        // Test bidirectional implementation
        System.out.println("Testing Bidirectional Selection Sort:");
        System.out.println("─────────────────────────────────────────────────────────────\n");
        
        // TODO: Uncomment when implementation is ready
        SortingTestSuite.runAllTests("Selection Sort (Bidirectional)", SelectionSort::sortBidirectional);
        
        // Visualization example
        System.out.println("\n" + "─".repeat(65) + "\n");
        System.out.println("Visualization Example:");
        System.out.println("─────────────────────────────────────────────────────────────\n");
        
        int[] visualDemo = {64, 25, 12, 22, 11};
        System.out.println("Original array: [64, 25, 12, 22, 11]");
        System.out.println("Goal: Show how minimum element is selected and swapped\n");
        
        // TODO: Uncomment when implementation is ready
        sortWithVisualization(visualDemo);
    }

    /**
     * Helper method to find the index of the minimum element in a range.
     *
     * @param arr the array to search
     * @param startIndex the starting index of the range (inclusive)
     * @param endIndex the ending index of the range (inclusive)
     * @return the index of the minimum element in the specified range
     */
    private static int findMinIndex(int[] arr, int startIndex, int endIndex) {
        // TODO: Implement helper to find minimum element index
        // This makes the main sort logic more readable
        return -1; // Placeholder
    }

    /**
     * Helper method to swap two elements in an array.
     *
     * @param arr the array containing elements to swap
     * @param i index of first element
     * @param j index of second element
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Helper method to print array with visual markers (for visualization).
     * Shows sorted region, current position, and unsorted region.
     *
     * @param arr the array to print
     * @param sortedBoundary index marking the end of sorted region
     * @param currentPos current position being processed
     * @param minIndex index of minimum element found
     */
    private static void printArrayWithMarkers(int[] arr, int sortedBoundary, 
                                             int currentPos, int minIndex) {
        System.out.print("  Array: ");
        for (int i = 0; i < arr.length; i++) {
            if (i == currentPos) {
                System.out.print("[" + arr[i] + "] "); // Current position
            } else if (i == minIndex) {
                System.out.print("*" + arr[i] + "* "); // Minimum found
            } else if (i < sortedBoundary) {
                System.out.print("(" + arr[i] + ") "); // Sorted region
            } else {
                System.out.print(arr[i] + " "); // Unsorted region
            }
        }
        System.out.println();
        System.out.println("         Legend: (x)=sorted, [x]=current, *x*=min");
    }
}
