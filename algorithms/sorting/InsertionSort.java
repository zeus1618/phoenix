package algorithms.sorting;

import java.util.Arrays;

/**
 * Insertion Sort Algorithm Implementation
 *
 * <p>Description:
 * Insertion Sort builds the final sorted array one element at a time. It iterates through
 * the input, consuming one element each iteration, and grows a sorted output list. At each
 * iteration, it removes one element from the input, finds the location it belongs within
 * the sorted list, and inserts it there. It repeats until no input elements remain.
 *
 * <p>Algorithm:
 * 1. Start with second element (assume first element is already "sorted")
 * 2. Compare current element (key) with elements in the sorted portion (left side)
 * 3. Shift all larger elements one position to the right
 * 4. Insert the key at the correct position
 * 5. Repeat for all elements
 *
 * <p>Analogy:
 * Like sorting playing cards in your hand - you pick up cards one by one and insert
 * each into its correct position among the cards you're already holding.
 *
 * <p>Characteristics:
 * - Simple and intuitive (mimics how humans naturally sort)
 * - Stable sort (maintains relative order of equal elements)
 * - In-place sorting (requires O(1) extra space)
 * - Adaptive: efficient for nearly sorted data or small arrays
 * - Online algorithm: can sort a list as it receives it
 *
 * <p>Time Complexity:
 * - Best Case: O(n) - when array is already sorted (only n-1 comparisons)
 * - Average Case: O(n²) - random order
 * - Worst Case: O(n²) - when array is reverse sorted
 * - Number of Shifts: O(n²) worst case
 *
 * <p>Space Complexity: O(1) - only uses a constant amount of extra space
 *
 * <p>When to Use:
 * - Small datasets (< 50 elements) - often faster than complex algorithms due to low overhead
 * - Nearly sorted data (performs very well, approaching O(n))
 * - Online sorting - can sort data as it arrives
 * - When simplicity and stability are important
 * - As the recursive base case in advanced algorithms (Quick Sort, Merge Sort for small subarrays)
 * - Simple implementation in embedded systems
 *
 * <p>When NOT to Use:
 * - Large completely random datasets (O(n²) becomes very slow)
 * - When you need guaranteed O(n log n) performance
 * - Use Quick Sort, Merge Sort, or Heap Sort instead for large data
 *
 * <p>Comparison with Other O(n²) Sorts:
 * 
 * <pre>
 * ┌─────────────────┬──────────────┬───────────────┬──────────────┐
 * │ Algorithm       │ Best Case    │ Nearly Sorted │ Stable       │
 * ├─────────────────┼──────────────┼───────────────┼──────────────┤
 * │ Bubble Sort     │ O(n)*        │ O(n)*         │ Yes          │
 * │ Selection Sort  │ O(n²)        │ O(n²)         │ No           │
 * │ Insertion Sort  │ O(n)         │ O(n)          │ Yes          │
 * └─────────────────┴──────────────┴───────────────┴──────────────┘
 * * = with optimization
 * 
 * Insertion Sort is THE BEST choice for nearly sorted data among O(n²) algorithms
 * </pre>
 *
 * <p>Real-World Usage:
 * - Used by Java's Arrays.sort() for small subarrays (< 47 elements) in combination with Quick Sort
 * - Used in TimSort (Python's default sort) for small runs
 * - Many production sorting algorithms switch to Insertion Sort for small inputs
 *
 * <p>Variations:
 * - Binary Insertion Sort: uses binary search to find insertion position (reduces comparisons)
 * - Shell Sort: generalized Insertion Sort with gap sequences (O(n log n) variants)
 *
 * <p>Key Learnings:
 * [To be documented after implementation]
 */
public class InsertionSort {

    /**
     * Sorts the given array using Insertion Sort algorithm (basic version).
     * Builds sorted portion by inserting each element at its correct position.
     *
     * @param arr the array to sort (modified in-place)
     */
    public static boolean debug = false;

    public static void sort(int[] arr) {
        // TODO: Implement basic insertion sort
        // Hint:
        // - Outer loop: iterate from index 1 to n-1 (first element is already "sorted")
        // - Store current element as 'key'
        // - Inner loop: compare key with sorted portion (elements to the left)
        // - Shift larger elements one position to the right
        // - Insert key at the correct position
        int executions = 0;
        if(debug) System.out.println("\nStart : " + Arrays.toString(arr));
        for (int i = 1; i < arr.length; i++) {
            int j=i-1;
            int key = arr[i];
            for(; j>=0 && arr[j]>key; j--){ //earlier didn't had the condition arr[j]>key, so loop was going till 0 making time complexity = O(n*n)
                executions++;
                arr[j+1] = arr[j];
                //swapping is incorrect, simply shift right elements one by one and once you find the place to put the key override the location as that location is already sifted right.
                // if(arr[j-1]>arr[j]) {
                //     int temp = arr[j-1];
                //     arr[j-1] = arr[j];
                //     arr[j] = temp;
                // }
                if(debug) System.out.println("\nstep : " + i + "," + j + "\t" + Arrays.toString(arr));
            }
            arr[j+1] = key;
        }
        if(debug) System.out.println("\nFinish : " + Arrays.toString(arr));
        if(debug) System.out.println("\nExecutions :" + executions + "\n\n");
    }

    /**
     * Sorts the given array using Binary Insertion Sort.
     * Uses binary search to find the correct insertion position, reducing comparisons.
     * Note: Still requires O(n²) shifts, so time complexity remains O(n²).
     *
     * @param arr the array to sort (modified in-place)
     */
    public static void sortBinaryInsertion(int[] arr) {
        // TODO: Implement binary insertion sort
        // Hint:
        // - Same as basic insertion sort, but use binary search to find position
        // - Binary search reduces comparisons from O(n) to O(log n) per element
        // - However, shifting elements still takes O(n) time
        // - Overall complexity: O(n²) for shifts, O(n log n) for comparisons
        if(debug) {
            System.out.println("\n" + "*".repeat(65) + "\n");
            System.out.println("For Array : " + Arrays.toString(arr));
            System.out.println("\n" + "*".repeat(65) + "\n");
        }
        for(int i=1; i<arr.length; i++){
            int index = binarySearch(arr, arr[i], 0, i-1);
            if(index != i){
                shiftRight(arr, index , i);
            }
        }

    }

    /**
     * Demonstrates the sorting process step-by-step (for learning purposes).
     * Shows how each element is inserted into the sorted portion.
     * Visualizes the sorted vs unsorted regions and the insertion process.
     *
     * @param arr the array to sort
     */
    public static void sortWithVisualization(int[] arr) {
        // TODO: Implement insertion sort with step-by-step printing
        // Show:
        // - Current key being inserted
        // - Sorted portion (left side)
        // - Unsorted portion (right side)
        // - Elements being shifted
        // - Final position of inserted element
        int executions = 0;
        {
            System.out.println("\n" + "*".repeat(30) + "\n");
            System.out.println("For Array : " + Arrays.toString(arr));
            System.out.println("\n" + "*".repeat(30) + "\n");
        }
        for(int i=0; i<arr.length-1; i++){
            System.out.println(String.format("\n-----Sorted portion : %d, %d", 0, i) + "\t\t" + Arrays.toString(Arrays.copyOfRange(arr, 0,i+1)));
                if(i<arr.length)
                    System.out.println(String.format("-----Unsorted portion : %d, %d", i+1, arr.length) + "\t\t" + Arrays.toString(Arrays.copyOfRange(arr, i+1,arr.length)));
            for(int j=i+1; j>0;j--){
                System.out.println("\n\ncurrent key : " + arr[j]);
                executions++;
                if(arr[j] < arr[j-1]){
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
                System.out.println(String.format("For Execution-%d \ti,j=%d,%d\t\tmodified array: ",executions, i, j) + Arrays.toString(arr));
            }
        }
        System.out.println("Sorted array "+Arrays.toString(arr));
    }

    /**
     * Helper method to perform binary search to find insertion position.
     * Returns the index where the key should be inserted to maintain sorted order.
     *
     * @param arr the array (sorted portion to search)
     * @param key the element to insert
     * @param start starting index of sorted portion
     * @param end ending index of sorted portion
     * @return the index where key should be inserted
     */
    private static int binarySearch(int[] arr, int key, int start, int end) {
        // TODO: Implement binary search to find insertion position
        // Find the position where key should be inserted to keep array sorted
        // Use standard binary search, but return insertion point
        int pos=-1;
        int executions = 0;
        if(debug){
            System.out.println("\n" + "~".repeat(65) + "\n");
            System.out.println("Binary Search : " + Arrays.toString(arr) + "\n" + String.format("For key:%d\tstart:%d\tend:%d", key, start, end));
        }
        while (start<=end) {
            executions++;
            pos = (end+start)/2;
            if(debug) System.out.println(String.format("\n\nFor Execution:%d\tstart:%d\tend:%d\tpos:%d",executions, start, end, pos));
            if(arr[pos]<=key){
                start=pos+1;
                if(debug) System.out.println(String.format("Updated start:%d", start));
            } else {
                end = pos-1;
                if(debug) System.out.println(String.format("Updated end:%d", end));
                
            }
        }
        if(debug) {
            System.out.println("Executions : " + executions);
            System.out.println(String.format("In array put %d at pos %d", key, start));
            System.out.println("\n" + "+".repeat(65) + "\n");
        }
        return start;
    }

    /**
     * Helper method to shift elements to the right by one position.
     * Used after finding insertion position to make room for the key.
     *
     * @param arr the array
     * @param from starting index to shift from
     * @param to ending index to shift to
     */
    private static void shiftRight(int[] arr, int from, int to) {
        // TODO: Shift all elements from 'from' to 'to' one position to the right
        // Example: shiftRight([1,2,3,4,5], 1, 3) -> [1,_,2,3,4,5]
        //          Shifts elements at indices 1,2,3 to indices 2,3,4
        if(debug){
            System.out.println("\n" + "%".repeat(65) + "\n");
            System.out.println("SHIFTING RIGHT : " + Arrays.toString(arr) + "\n" + String.format("from:%d\tto:%d", from, to));
        }
        int temp = arr[to];
        for(int i=to; i>from; i--){
            arr[i] = arr[i-1];
            if(debug) System.out.println(Arrays.toString(arr));
        }
        if(debug) System.out.println("\n" + "%".repeat(65) + "\n");
        arr[from] = temp;
    }

    /**
     * Helper method to print array with visual markers showing sorted/unsorted regions.
     * Useful for visualization.
     *
     * @param arr the array to print
     * @param sortedBoundary index marking the end of sorted region
     * @param keyIndex current element being inserted
     * @param insertPos position where key will be inserted
     */
    private static void printArrayWithMarkers(int[] arr, int sortedBoundary, 
                                             int keyIndex, int insertPos) {
        System.out.print("  ");
        for (int i = 0; i < arr.length; i++) {
            if (i == keyIndex) {
                System.out.print("[" + arr[i] + "]"); // Current key
            } else if (i == insertPos) {
                System.out.print("↓" + arr[i] + "↓"); // Insertion point
            } else if (i <= sortedBoundary) {
                System.out.print("(" + arr[i] + ")"); // Sorted region
            } else {
                System.out.print(" " + arr[i] + " "); // Unsorted region
            }
            if (i < arr.length - 1) System.out.print(" ");
        }
        System.out.println();
        System.out.println("  Legend: (x)=sorted, [x]=key, ↓x↓=insert here");
    }

    /**
     * Counts the number of comparisons made during sorting (for analysis).
     *
     * @param arr the array to sort
     * @return the number of comparisons made
     */
    public static int sortAndCountComparisons(int[] arr) {
        // TODO: Implement sort with comparison counter
        // Useful for analyzing best/average/worst case performance
        return 0; // Placeholder
    }

    /**
     * Counts the number of shifts made during sorting (for analysis).
     *
     * @param arr the array to sort
     * @return the number of element shifts made
     */
    public static int sortAndCountShifts(int[] arr) {
        // TODO: Implement sort with shift counter
        // Shows how Insertion Sort performs on nearly sorted data
        return 0; // Placeholder
    }

    /**
     * Main method with comprehensive test cases using the common test suite.
     */
    public static void main(String[] args) {
        // Test basic implementation
        System.out.println("Testing Basic Insertion Sort:");
        System.out.println("─────────────────────────────────────────────────────────────\n");
        
        // TODO: Uncomment when implementation is ready
        SortingTestSuite.runAllTests("Insertion Sort (Basic)", InsertionSort::sort);
        
        System.out.println("\n" + "─".repeat(65) + "\n");

         // Test binary search implementation
        System.out.println("Testing Binary Search :");
        System.out.println("─────────────────────────────────────────────────────────────\n");
        int[] bsArrayOdd = {1, 4, 7, 11, 17, 20};
        int[] bsArrayEven = {1, 4, 7, 11, 17, 20, 25};
        int key = 90;
        System.out.println("Odd array: " + Arrays.toString(bsArrayOdd));
        System.out.println("Even array: " + Arrays.toString(bsArrayEven));
        System.out.println(String.format("In OddArray put %d at pos %d", key, binarySearch(bsArrayOdd, key, 0, 3)));
        System.out.println(String.format("In EvenArray put %d at pos %d", key, binarySearch(bsArrayEven, key, 0, bsArrayEven.length-1)));
        // SortingTestSuite.runAllTests("Insertion Sort (Binary)", InsertionSort::sortBinaryInsertion);
        System.out.println("\n" + "─".repeat(65) + "\n\n");

        // Test binary insertion implementation
        System.out.println("Testing Binary Insertion Sort:");
        System.out.println("─────────────────────────────────────────────────────────────\n");
        
        // TODO: Uncomment when implementation is ready
        SortingTestSuite.runAllTests("Insertion Sort (Binary)", InsertionSort::sortBinaryInsertion);
        
        // Visualization example
        System.out.println("\n" + "─".repeat(65) + "\n");
        System.out.println("Visualization Example:");
        System.out.println("─────────────────────────────────────────────────────────────\n");
        
        // int[] visualDemo = {1,-1,0,5,2,-99,0,4,0,0,90,1};
        int[] visualDemo = {5, 2, 4, 6, 1, 3};
        System.out.println("Original array: [5, 2, 4, 6, 1, 3]");
        System.out.println("Watch how each element is inserted into sorted portion\n");
        
        // TODO: Uncomment when implementation is ready
        sortWithVisualization(visualDemo);
        
        // Performance analysis example
        System.out.println("\n" + "─".repeat(65) + "\n");
        System.out.println("Performance Analysis:");
        System.out.println("─────────────────────────────────────────────────────────────\n");
        
        // Nearly sorted array - Insertion Sort's best scenario
        int[] nearlySorted = {1, 2, 3, 4, 5, 7, 6, 8, 9, 10};
        System.out.println("Nearly sorted: " + Arrays.toString(nearlySorted));
        
        // TODO: Uncomment when implementation is ready
        // int comparisons = sortAndCountComparisons(nearlySorted.clone());
        // int shifts = sortAndCountShifts(nearlySorted.clone());
        // System.out.println("Comparisons: " + comparisons + ", Shifts: " + shifts);
        
        // Reverse sorted array - Insertion Sort's worst scenario
        int[] reverseSorted = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        System.out.println("\nReverse sorted: " + Arrays.toString(reverseSorted));
        
        // TODO: Uncomment when implementation is ready
        // comparisons = sortAndCountComparisons(reverseSorted.clone());
        // shifts = sortAndCountShifts(reverseSorted.clone());
        // System.out.println("Comparisons: " + comparisons + ", Shifts: " + shifts);
    }
}
