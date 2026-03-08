package algorithms.sorting;

import java.util.Arrays;

/**
 * Merge Sort Algorithm Implementation
 *
 * <p>Description:
 * Merge Sort is a divide-and-conquer algorithm that divides the input array into two halves,
 * recursively sorts them, and then merges the two sorted halves. It's one of the most efficient
 * sorting algorithms with guaranteed O(n log n) time complexity.
 *
 * <p>Algorithm:
 * 1. Divide: Split the array into two halves (left and right)
 * 2. Conquer: Recursively sort both halves
 * 3. Combine: Merge the two sorted halves into a single sorted array
 *
 * <p>The Merge Process:
 * - Create temporary arrays for left and right halves
 * - Compare elements from both arrays
 * - Place smaller element into the original array
 * - Copy any remaining elements
 *
 * <p>Analogy:
 * Like sorting two decks of cards - you split the deck in half, sort each half separately,
 * then merge them by repeatedly taking the smaller card from the top of either deck.
 *
 * <p>Characteristics:
 * - Divide-and-conquer algorithm (first O(n log n) sorting algorithm to learn)
 * - Stable sort (maintains relative order of equal elements)
 * - NOT in-place (requires O(n) extra space for temporary arrays)
 * - Predictable performance (always O(n log n), no worst-case degradation)
 * - Parallelizable (left and right halves can be sorted in parallel)
 * - Optimal for linked lists (no random access needed)
 *
 * <p>Time Complexity:
 * - Best Case: O(n log n) - even if already sorted
 * - Average Case: O(n log n)
 * - Worst Case: O(n log n) - consistent performance
 * - Recurrence: T(n) = 2T(n/2) + O(n)
 *   - 2T(n/2): Two recursive calls on half-sized arrays
 *   - O(n): Linear time to merge
 *
 * <p>Space Complexity: O(n)
 * - Requires temporary arrays for merging
 * - Recursion stack: O(log n) for call stack depth
 * - Total: O(n) auxiliary space
 *
 * <p>When to Use:
 * - Large datasets requiring guaranteed O(n log n) performance
 * - When stability is important (preserving order of equal elements)
 * - Sorting linked lists (better than Quick Sort for linked structures)
 * - External sorting (sorting data that doesn't fit in memory)
 * - When worst-case performance matters (unlike Quick Sort)
 * - Parallel processing (left/right halves can be sorted concurrently)
 *
 * <p>When NOT to Use:
 * - Memory-constrained environments (uses O(n) extra space)
 * - Small datasets (overhead of recursion and copying, use Insertion Sort)
 * - When in-place sorting is required (use Heap Sort or in-place Quick Sort)
 * - When average-case speed is critical and memory is available (Quick Sort is faster)
 *
 * <p>Comparison with Other O(n log n) Sorts:
 * 
 * <pre>
 * ┌─────────────┬──────────────┬──────────────┬───────┬────────┬─────────────┐
 * │ Algorithm   │ Best         │ Worst        │ Space │ Stable │ Notes       │
 * ├─────────────┼──────────────┼──────────────┼───────┼────────┼─────────────┤
 * │ Merge Sort  │ O(n log n)   │ O(n log n)   │ O(n)  │ ✅     │ Consistent  │
 * │ Quick Sort  │ O(n log n)   │ O(n²)*       │ O(log)│ ❌     │ Fast avg    │
 * │ Heap Sort   │ O(n log n)   │ O(n log n)   │ O(1)  │ ❌     │ In-place    │
 * └─────────────┴──────────────┴──────────────┴───────┴────────┴─────────────┘
 * * = with poor pivot selection; O(n log n) with good pivot strategies
 * 
 * Merge Sort: Best choice when stability and guaranteed performance are required
 * </pre>
 *
 * <p>Real-World Usage:
 * - Used in Java's Arrays.sort() for object arrays (stable sort requirement)
 * - Python's Timsort (hybrid of Merge Sort and Insertion Sort)
 * - External sorting algorithms (sorting files larger than RAM)
 * - Database systems for stable sorting operations
 * - Git uses Merge Sort for merging file histories
 *
 * <p>Optimizations:
 * - Switch to Insertion Sort for small subarrays (< 10-15 elements)
 * - Avoid copying if already sorted (check if left_max <= right_min)
 * - Use iterative bottom-up approach to reduce recursion overhead
 * - Natural Merge Sort: exploit existing runs of sorted data
 *
 * <p>Variations:
 * - Top-down Merge Sort (recursive, shown here)
 * - Bottom-up Merge Sort (iterative, no recursion stack)
 * - Natural Merge Sort (exploits naturally occurring sorted runs)
 * - Timsort (hybrid with Insertion Sort, used in Python/Java)
 * - 3-way Merge Sort (divides into 3 parts instead of 2)
 *
 * <p>Key Concepts to Understand:
 * - Divide-and-conquer paradigm
 * - Recursion tree (height = log n, work per level = n)
 * - Master Theorem for analyzing recurrence relations
 * - Stability vs in-place trade-offs
 * - Why O(n log n) is theoretically optimal for comparison-based sorting
 *
 * <p>Key Learnings:
 * [To be documented after implementation and analysis]
 */
public class MergeSort {
    public static boolean debug = false;

    /**
     * Sorts the given array using Merge Sort algorithm (recursive top-down approach).
     * Main entry point that delegates to the recursive helper method.
     *
     * @param arr the array to sort (modified in-place, but uses O(n) auxiliary space)
     */
    public static void sort(int[] arr) {
        // TODO: Implement merge sort
        // Hint:
        // - Handle base case: if array length <= 1, it's already sorted
        // - Call the recursive helper: mergeSort(arr, 0, arr.length - 1)
        // - The helper will divide, recursively sort, and merge
        if(arr.length>=2)
            mergeSort(arr, 0, arr.length-1);
    }

    /**
     * Recursive helper method that performs the divide-and-conquer merge sort.
     *
     * @param arr the array to sort
     * @param left the starting index of the subarray
     * @param right the ending index of the subarray (inclusive)
     */
    private static void mergeSort(int[] arr, int left, int right) {
        // TODO: Implement recursive merge sort
        // Base case: if left >= right, subarray has 0 or 1 element (already sorted)
        
        // Divide step:
        // - Calculate middle point: mid = left + (right - left) / 2
        //   (Use this formula to avoid integer overflow)
        
        // Conquer step:
        // - Recursively sort left half: mergeSort(arr, left, mid)
        // - Recursively sort right half: mergeSort(arr, mid + 1, right)
        
        // Combine step:
        // - Merge the two sorted halves: merge(arr, left, mid, right)

        if(left==right){
            return;
        }

        int mid = (right+left)/2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid+1, right);
        merge(arr, left, mid, right);

    }

    /**
     * Merges two sorted subarrays into a single sorted subarray.
     * Left subarray: arr[left...mid]
     * Right subarray: arr[mid+1...right]
     *
     * @param arr the array containing both subarrays
     * @param left the starting index of the left subarray
     * @param mid the ending index of the left subarray
     * @param right the ending index of the right subarray
     */
    private static void merge(int[] arr, int left, int mid, int right) {
        // TODO: Implement merge operation
        // Step 1: Create temporary arrays for left and right subarrays
        // - Calculate sizes: leftSize = mid - left + 1, rightSize = right - mid
        // - Create: int[] leftArr = new int[leftSize], rightArr = new int[rightSize]
        // - Copy data: use System.arraycopy() or manual loop
        
        // Step 2: Merge the temporary arrays back into arr[left...right]
        // - Use three pointers: i (left array), j (right array), k (merged array)
        // - While both arrays have elements:
        //   - Compare leftArr[i] and rightArr[j]
        //   - Place smaller element into arr[k]
        //   - Increment appropriate pointer
        
        // Step 3: Copy remaining elements (if any)
        // - Copy remaining from leftArr (if i < leftSize)
        // - Copy remaining from rightArr (if j < rightSize)

        // int[] leftSubArray = (mid+1<=arr.length) ?Arrays.copyOfRange(arr, left, mid+1) : new int[mid+1-left];
        // int[] rightSubArray = (mid+1<=arr.length && right+1<=arr.length) ?Arrays.copyOfRange(arr, mid+1, right+1);
        int[] leftSubArray = Arrays.copyOfRange(arr, left, mid+1);
        int[] rightSubArray = Arrays.copyOfRange(arr, mid+1, right+1);
        int leftPointer = 0;
        int rightPointer = 0;
        if(debug) System.out.println("Left Sub Array : " + Arrays.toString(leftSubArray));
        if(debug) System.out.println("Right Sub Array : " + Arrays.toString(rightSubArray));
        for(int i=left; i<=right; i++) {
            if(rightPointer >= rightSubArray.length || (leftPointer<leftSubArray.length && leftSubArray[leftPointer]<rightSubArray[rightPointer])){
                arr[i] = leftSubArray[leftPointer];
                leftPointer++;
            } else {
                arr[i] = rightSubArray[rightPointer];
                rightPointer++;
            }
        }
        if(debug) System.out.println("Merged array : " + Arrays.toString(arr));
    }

    /**
     * Sorts the array with visualization showing the divide and merge process.
     * Displays recursion depth and array state at each merge operation.
     *
     * @param arr the array to sort
     */
    public static void sortWithVisualization(int[] arr) {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("MERGE SORT VISUALIZATION - Divide and Conquer");
        System.out.println("=".repeat(70));
        System.out.println("Original Array: " + Arrays.toString(arr));
        System.out.println("Array Length: " + arr.length + " → Tree Height: ~" + 
                          (int)Math.ceil(Math.log(arr.length) / Math.log(2)) + " levels");
        System.out.println("=".repeat(70) + "\n");
        
        mergeSortVisualized(arr, 0, arr.length - 1, 0);
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("FINAL SORTED ARRAY: " + Arrays.toString(arr));
        System.out.println("=".repeat(70));
    }

    /**
     * Recursive helper with visualization showing divide and merge steps.
     *
     * @param arr the array to sort
     * @param left the starting index
     * @param right the ending index
     * @param depth the current recursion depth (for indentation)
     */
    private static void mergeSortVisualized(int[] arr, int left, int right, int depth) {
        String indent = "  ".repeat(depth);
        
        if (left >= right) {
            System.out.println(indent + "└─ Base case [" + left + "]: " + arr[left]);
            return;
        }
        
        // Show divide step
        int mid = left + (right - left) / 2;
        System.out.println(indent + "┌─ DIVIDE [" + left + "..." + right + "]: " + 
                          Arrays.toString(Arrays.copyOfRange(arr, left, right + 1)));
        System.out.println(indent + "│  → Left: [" + left + "..." + mid + "]");
        System.out.println(indent + "│  → Right: [" + (mid + 1) + "..." + right + "]");
        
        // Recursively sort
        mergeSortVisualized(arr, left, mid, depth + 1);
        mergeSortVisualized(arr, mid + 1, right, depth + 1);
        
        // Show merge step
        int[] beforeMerge = Arrays.copyOfRange(arr, left, right + 1);
        merge(arr, left, mid, right);
        System.out.println(indent + "└─ MERGE [" + left + "..." + right + "]:");
        System.out.println(indent + "   Before: " + Arrays.toString(beforeMerge));
        System.out.println(indent + "   After:  " + Arrays.toString(Arrays.copyOfRange(arr, left, right + 1)));
        System.out.println();
    }

    /**
     * Sorts the array and returns the number of merge operations performed.
     * Useful for understanding algorithm behavior and comparing performance.
     *
     * @param arr the array to sort
     * @return the number of merge operations
     */
    public static int sortAndCountMerges(int[] arr) {
        int[] mergeCount = {0}; // Use array to allow modification in recursive calls
        mergeSortCounted(arr, 0, arr.length - 1, mergeCount);
        return mergeCount[0];
    }

    /**
     * Recursive helper that counts merge operations.
     *
     * @param arr the array to sort
     * @param left the starting index
     * @param right the ending index
     * @param mergeCount array holding the count (allows modification in recursion)
     */
    private static void mergeSortCounted(int[] arr, int left, int right, int[] mergeCount) {
        if (left >= right) return;
        
        int mid = left + (right - left) / 2;
        mergeSortCounted(arr, left, mid, mergeCount);
        mergeSortCounted(arr, mid + 1, right, mergeCount);
        merge(arr, left, mid, right);
        mergeCount[0]++;
    }

    /**
     * Optimized merge sort that switches to Insertion Sort for small subarrays.
     * Hybrid approach combining the strengths of both algorithms.
     *
     * @param arr the array to sort
     * @param threshold the size threshold for switching to Insertion Sort (typically 10-15)
     */
    public static void sortOptimized(int[] arr, int threshold) {
        mergeSortOptimized(arr, 0, arr.length - 1, threshold);
    }

    /**
     * Recursive helper for optimized merge sort with Insertion Sort for small arrays.
     *
     * @param arr the array to sort
     * @param left the starting index
     * @param right the ending index
     * @param threshold the size threshold for switching to Insertion Sort
     */
    private static void mergeSortOptimized(int[] arr, int left, int right, int threshold) {
        if (left >= right) return;
        
        // Optimization: Use Insertion Sort for small subarrays
        if (right - left + 1 <= threshold) {
            insertionSort(arr, left, right);
            return;
        }
        
        int mid = left + (right - left) / 2;
        mergeSortOptimized(arr, left, mid, threshold);
        mergeSortOptimized(arr, mid + 1, right, threshold);
        
        // Optimization: Skip merge if already sorted
        if (arr[mid] <= arr[mid + 1]) {
            return;
        }
        
        merge(arr, left, mid, right);
    }

    /**
     * Helper method: Insertion Sort for small subarrays in optimized merge sort.
     *
     * @param arr the array
     * @param left the starting index
     * @param right the ending index
     */
    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    /**
     * Main method with comprehensive test suite demonstrating Merge Sort behavior.
     */
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("║           MERGE SORT - O(n log n) Algorithm Tests            ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════╝\n");

        // TODO: Uncomment these test cases after implementing the sort() method
        // Test 0: Merge funciont Test
        System.out.println("\n┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│ Test 0: Merge function Test only                            │");
        System.out.println("└─────────────────────────────────────────────────────────────┘\n");
        int[] arr = new int[] {3,5,9,1,2,7};
        System.out.println("Merging array : " + arr);
        merge(arr, 0, 2, 5);

        // Test 1: Run comprehensive test suite
        System.out.println("\n┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│ Test Suite: Comprehensive Tests                            │");
        System.out.println("└─────────────────────────────────────────────────────────────┘\n");
        // int[] arr1 = new int[] {7,2,1};
        // System.out.println("Sorting array : " + Arrays.toString(arr1));
        // sort(arr1);
        SortingTestSuite.runAllTests("Merge Sort", MergeSort::sort);

        // Test 2: Visualization on small array
        // System.out.println("\n\n┌─────────────────────────────────────────────────────────────┐");
        // System.out.println("│ Test: Visualization (Small Array)                          │");
        // System.out.println("└─────────────────────────────────────────────────────────────┘");
        // int[] visualArray = {38, 27, 43, 3, 9, 82, 10};
        // sortWithVisualization(visualArray);

        // Test 3: Count merge operations
        // System.out.println("\n\n┌─────────────────────────────────────────────────────────────┐");
        // System.out.println("│ Test: Merge Operations Count                               │");
        // System.out.println("└─────────────────────────────────────────────────────────────┘");
        // int[] countArray1 = {5, 2, 4, 7, 1, 3, 2, 6};
        // System.out.println("Array: " + Arrays.toString(countArray1));
        // int merges = sortAndCountMerges(countArray1);
        // System.out.println("Sorted: " + Arrays.toString(countArray1));
        // System.out.println("Number of merge operations: " + merges);
        // System.out.println("Expected merges for n=" + 8 + ": " + (8 - 1) + " (n-1 for any n)");

        // Test 4: Compare basic vs optimized
        // System.out.println("\n\n┌─────────────────────────────────────────────────────────────┐");
        // System.out.println("│ Test: Basic vs Optimized (with Insertion Sort)             │");
        // System.out.println("└─────────────────────────────────────────────────────────────┘");
        // int[] basicArray = {64, 34, 25, 12, 22, 11, 90, 88, 45, 50, 3, 78, 23, 19, 67};
        // int[] optimizedArray = Arrays.copyOf(basicArray, basicArray.length);
        // 
        // System.out.println("Original: " + Arrays.toString(basicArray));
        // 
        // long startBasic = System.nanoTime();
        // sort(basicArray);
        // long endBasic = System.nanoTime();
        // 
        // long startOpt = System.nanoTime();
        // sortOptimized(optimizedArray, 10);
        // long endOpt = System.nanoTime();
        // 
        // System.out.println("Basic Sorted:     " + Arrays.toString(basicArray));
        // System.out.println("Optimized Sorted: " + Arrays.toString(optimizedArray));
        // System.out.println("\nTime (Basic):     " + (endBasic - startBasic) + " ns");
        // System.out.println("Time (Optimized): " + (endOpt - startOpt) + " ns");
        // System.out.println("Speedup: " + String.format("%.2f", (double)(endBasic - startBasic) / (endOpt - startOpt)) + "x");

        // Test 5: Performance on different input patterns
        // System.out.println("\n\n┌─────────────────────────────────────────────────────────────┐");
        // System.out.println("│ Test: Performance on Different Input Patterns              │");
        // System.out.println("└─────────────────────────────────────────────────────────────┘");
        // 
        // int[] alreadySorted = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        // int[] reverseSorted = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        // int[] random = {3, 7, 1, 9, 2, 8, 4, 6, 5, 10};
        // 
        // System.out.println("\n🔹 Already Sorted:");
        // System.out.println("Before: " + Arrays.toString(alreadySorted));
        // int merges1 = sortAndCountMerges(alreadySorted);
        // System.out.println("After:  " + Arrays.toString(alreadySorted));
        // System.out.println("Merges: " + merges1 + " (still O(n log n), Merge Sort always divides)");
        // 
        // System.out.println("\n🔹 Reverse Sorted:");
        // System.out.println("Before: " + Arrays.toString(reverseSorted));
        // int merges2 = sortAndCountMerges(reverseSorted);
        // System.out.println("After:  " + Arrays.toString(reverseSorted));
        // System.out.println("Merges: " + merges2 + " (same as above, consistent O(n log n))");
        // 
        // System.out.println("\n🔹 Random:");
        // System.out.println("Before: " + Arrays.toString(random));
        // int merges3 = sortAndCountMerges(random);
        // System.out.println("After:  " + Arrays.toString(random));
        // System.out.println("Merges: " + merges3 + " (same as above, always O(n log n))");
        // 
        // System.out.println("\n📊 Key Insight: Merge Sort has CONSISTENT performance");
        // System.out.println("   Unlike Quick Sort (O(n²) worst) or Insertion Sort (O(n) best),");
        // System.out.println("   Merge Sort always performs O(n log n) regardless of input pattern.");

        System.out.println("\n" + "=".repeat(70));
        System.out.println("💡 TODO: Implement the sort(), mergeSort(), and merge() methods");
        System.out.println("   Then uncomment the tests above to see Merge Sort in action!");
        System.out.println("=".repeat(70));
    }
}
