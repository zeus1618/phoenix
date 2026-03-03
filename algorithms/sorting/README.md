# Sorting Algorithms

A comprehensive collection of sorting algorithm implementations with detailed documentation, complexity analysis, and a common test suite.

## 📁 Structure

```
algorithms/sorting/
├── SortingTestSuite.java    # Common test infrastructure for all sorting algorithms
├── BubbleSort.java           # Bubble Sort implementation
├── SelectionSort.java        # Selection Sort implementation
└── README.md                 # This file
```

## 🎯 Purpose

This package serves as a learning resource for understanding different sorting algorithms through:
- **Hands-on implementation** of classic sorting algorithms
- **Comprehensive testing** using a shared test suite
- **Performance comparison** between different approaches
- **Detailed documentation** of time/space complexity and use cases

## 🧪 Common Test Suite

The `SortingTestSuite` class provides:

### Test Categories
- **Edge Cases**: Empty array, single element, two elements, all duplicates
- **Normal Cases**: Random arrays, sorted arrays, reverse sorted arrays
- **Special Cases**: Negative numbers, mixed positive/negative, large arrays (100 elements)

### Usage

```java
// Run all tests for your sorting algorithm
SortingTestSuite.runAllTests("Algorithm Name", YourClass::sortMethod);

// Test a single case
int[] input = {3, 1, 4, 1, 5};
int[] expected = {1, 1, 3, 4, 5};
SortingTestSuite.testSingleCase("Custom Test", input, expected, YourClass::sortMethod);
```

### Output Format
- ✅/❌ Pass/Fail indicators
- Test number and description
- Input, expected, and actual arrays (for small arrays or failures)
- Summary statistics with success rate

## 📚 Algorithms

### 1. Bubble Sort (`BubbleSort.java`)

**Status**: ✅ Implemented

**Variations**:
- `sort()`: Basic implementation
- `sortOptimized()`: Early termination when sorted
- `sortWithVisualization()`: Step-by-step display

**Complexity**:
- Time: O(n²) average/worst, O(n) best (optimized)
- Space: O(1)

**Best For**:
- Small datasets (< 20 elements)
- Educational purposes
- When stability and simplicity are priorities

### 2. Selection Sort (`SelectionSort.java`)

**Status**: 🔄 Ready for implementation

**Variations**:
- `sort()`: Basic implementation
- `sortBidirectional()`: Finds both min/max in each pass
- `sortWithVisualization()`: Step-by-step display with sorted region markers

**Complexity**:
- Time: O(n²) always (best/average/worst)
- Space: O(1)
- Swaps: O(n) - minimum number of swaps

**Best For**:
- Small datasets (< 20 elements)
- When memory write operations are expensive
- Systems with costly swaps (flash memory, EEPROM)

**Comparison with Bubble Sort**:
- Same O(n²) time but Selection Sort makes fewer swaps
- Selection Sort is NOT stable, Bubble Sort is stable
- Selection Sort always O(n²), Bubble Sort can be O(n) when optimized

## 🚀 Getting Started

### Step 1: Implement a Sorting Algorithm

Open `BubbleSort.java` and implement the TODO methods:

```java
public static void sort(int[] arr) {
    // Your implementation here
}
```

### Step 2: Uncomment Test Cases

In the `main()` method, uncomment the test lines:

```java
SortingTestSuite.runAllTests("Bubble Sort (Basic)", BubbleSort::sort);
```

### Step 3: Run Tests

```bash
# Compile
javac algorithms/sorting/BubbleSort.java

# Run
java algorithms.sorting.BubbleSort
```

### Step 4: Verify All Tests Pass

Look for the test summary showing all green ✅ passes.

## 📋 Planned Algorithms

Future implementations to add:
- **Insertion Sort**: O(n²) - builds sorted array one element at a time
- **Merge Sort**: O(n log n) - divide and conquer approach
- **Quick Sort**: O(n log n) average - partition-based sorting
- **Heap Sort**: O(n log n) - uses binary heap data structure
- **Counting Sort**: O(n+k) - non-comparison based (integers only)
- **Radix Sort**: O(d×n) - sorts by digit/character position
- **Bucket Sort**: O(n+k) - distributes elements into buckets

## 📊 Performance Comparison Table

| Algorithm | Best | Average | Worst | Space | Stable | Notes |
|---|---|---|---|---|---|---|
| Bubble Sort | O(n) | O(n²) | O(n²) | O(1) | ✅ | Simple, educational |
| Selection Sort | O(n²) | O(n²) | O(n²) | O(1) | ❌ | Minimal swaps |
| Insertion Sort | O(n) | O(n²) | O(n²) | O(1) | ✅ | Good for small/nearly sorted |
| Merge Sort | O(n log n) | O(n log n) | O(n log n) | O(n) | ✅ | Consistent performance |
| Quick Sort | O(n log n) | O(n log n) | O(n²) | O(log n) | ❌ | Fast in practice |
| Heap Sort | O(n log n) | O(n log n) | O(n log n) | O(1) | ❌ | Good worst-case guarantee |

## 🎓 Learning Path

Recommended order to learn sorting algorithms:

1. **Bubble Sort** ⭐ Start Here
   - Simplest algorithm, understand the basics of comparison and swapping
   
2. **Selection Sort** ⭐ Next
   - Introduces the concept of finding minimum/maximum
   - Learn about minimizing swap operations
   
3. **Insertion Sort**
   - Learn about building sorted portions incrementally
   
4. **Merge Sort**
   - First O(n log n) algorithm, introduces divide-and-conquer
   
5. **Quick Sort**
   - Most used in practice, understand partitioning
   
6. **Heap Sort**
   - Combines sorting with heap data structure
   
7. **Counting/Radix/Bucket Sort**
   - Non-comparison based, specialized use cases

## 💡 Tips

- **Start with small arrays** when debugging - easier to trace
- **Use visualization methods** to understand how elements move
- **Compare implementations** - basic vs optimized versions
- **Measure performance** on different input patterns (sorted, reverse, random)
- **Understand trade-offs** - time vs space, simplicity vs performance

## 🔗 Resources

- [Sorting Algorithm Animations](https://www.toptal.com/developers/sorting-algorithms)
- [Big-O Cheat Sheet](https://www.bigocheatsheet.com/)
- [Visualization Tool](https://visualgo.net/en/sorting)

---

**Next Steps**: 
1. ✅ Bubble Sort implemented - all tests passing
2. 🔄 Implement `SelectionSort.sort()` method
3. Run tests and ensure all pass
4. Try the bidirectional version
5. Compare with Bubble Sort performance
6. Move on to Insertion Sort!
