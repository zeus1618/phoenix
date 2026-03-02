package arraysAndHashing;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;

/**
 * LeetCode Problem #36: Valid Sudoku
 * Difficulty: Medium
 * 
 * Problem Description:
 * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be 
 * validated according to the following rules:
 * 
 * 1. Each row must contain the digits 1-9 without repetition.
 * 2. Each column must contain the digits 1-9 without repetition.
 * 3. Each of the nine 3x3 sub-boxes of the grid must contain the digits 1-9 
 *    without repetition.
 * 
 * Note:
 * - A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * - Only the filled cells need to be validated according to the mentioned rules.
 * - Empty cells are represented by '.'
 * 
 * Constraints:
 * - board.length == 9
 * - board[i].length == 9
 * - board[i][j] is a digit '1'-'9' or '.'
 * 
 * Link: https://leetcode.com/problems/valid-sudoku/
 * 
 * Approach:
 * Single-pass validation using HashSet arrays for rows, columns, and 3x3 boxes.
 * Create three HashSet arrays (9 sets each) to track digits seen in each row, column,
 * and box. In one pass through the board, check each non-empty cell against all three
 * corresponding sets simultaneously. Use box index formula: (row/3)*3 + (col/3) to map
 * 2D coordinates to box numbers 0-8. Early return false on first duplicate found.
 * 
 * Evolution:
 * 1. First attempt: ArrayList with three separate passes - correct but inefficient
 * 2. Final solution: HashSet arrays with single pass - optimal approach
 * 
 * Time Complexity: O(1) or O(81) = constant since board is always 9x9
 * Space Complexity: O(1) or O(27×9) = constant space for 27 HashSets of max 9 elements each
 * 
 * Key Learnings:
 * - ArrayList.contains() is O(n) vs HashSet O(1) - data structure choice matters for lookups
 * - Single pass (81 cells) vs three passes (243 cells) - combining checks reduces redundant work
 * - Box index formula (i/3)*3 + (j/3) elegantly maps row/col to box number without complex logic
 * - HashSet arrays provide optimal balance: O(1) lookups, clear code, reasonable memory
 * - Fixed-size problems (9x9) simplify to O(1) complexity but constant factors still matter
 * 
 * Alternative Approaches:
 * - Boolean arrays: Fastest (direct indexing) but fixed to 9 digits
 * - String encoding: Single HashSet with encoded strings, elegant but string overhead
 * - Three-pass HashSet: Clear separation of concerns but redundant board traversals
 */
public class ValidSudoku {

    /**
     * Determines if a 9x9 Sudoku board is valid.
     * 
     * @param board 9x9 Sudoku board where board[i][j] is '1'-'9' or '.'
     * @return true if the board is valid, false otherwise
     */
    public boolean isValidSudoku(char[][] board) {
        Set<Character>[] rowSet = new HashSet[9];
        Set<Character>[] colSet = new HashSet[9];
        Set<Character>[] gridSet = new HashSet[9];
        for(int i=0; i<9; i++){
            rowSet[i] = new HashSet<>();
            colSet[i] = new HashSet<>();
            gridSet[i] = new HashSet<>();
        }

        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(board[i][j] == '.'){
                    continue;
                }
                int boxGrid = (i/3)*3 + j/3;
                if(rowSet[i].contains(board[i][j])) return false;
                 else rowSet[i].add(board[i][j]);
                if(colSet[j].contains(board[i][j])) return false;
                 else colSet[j].add(board[i][j]);
                 if(gridSet[boxGrid].contains(board[i][j])) return false;
                 else gridSet[boxGrid].add(board[i][j]);
            }
        }
        return true;
    }

    public boolean isValidSudokuFirstAttempt(char[][] board) {
        // Map<Character, Integer> m = new HashMap<>();
        List<Character> m = new ArrayList<>(9);
        //check horizontals
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if((board[i][j]) == '.'){
                    continue;
                }
                if(m.contains(board[i][j])){
                    return false;
                } else {
                    m.add(board[i][j]);
                }
            }
            m.clear();
        }
        //check verticals
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if((board[j][i]) == '.'){
                    continue;
                }
                if(m.contains(board[j][i])){
                    return false;
                } else {
                    m.add(board[j][i]);
                }
            }
            m.clear();
        }

        //check blocks
        int x = 0;
        int y = 0;
        while (y!=9){
            for(int i=x; i<x+3; i++){
                for(int j=y; j<y+3; j++){
                    if((board[j][i]) == '.'){
                        continue;
                    }
                    if(m.contains(board[j][i])){
                        return false;
                    } else {
                        m.add(board[j][i]);
                    }
                }
            }
            m.clear();
            x+=3;
            if(x==9){
                x=0;
                y+=3;
            }
        }

        return true;
    }

    // ============================================================================
    // Test Infrastructure - Helper Methods
    // ============================================================================

    /**
     * Helper method to run a single test case and display results.
     * 
     * @param testNum the test case number
     * @param board the sudoku board to validate
     * @param expected the expected result
     * @param actual the actual result from the solution
     * @param description brief description of the test case
     */
    private static void runTest(int testNum, char[][] board, boolean expected, boolean actual, String description) {
        String status = (expected == actual) ? "✓ PASS" : "✗ FAIL";
        System.out.printf("Test %d: %s - %s%n", testNum, status, description);
        System.out.printf("  Input Board:%n");
        printBoard(board);
        System.out.printf("  Expected: %b%n", expected);
        System.out.printf("  Got:      %b%n", actual);
        
        if (expected != actual) {
            System.out.printf("  ERROR: Expected %b but got %b%n", expected, actual);
        }
        System.out.println();
    }

    /**
     * Helper method to print the sudoku board in a readable format.
     * 
     * @param board the sudoku board to print
     */
    private static void printBoard(char[][] board) {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("  ---------------------");
            }
            System.out.print("  ");
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Prints a summary table of all test results.
     * 
     * @param results array of test results (true = pass, false = fail)
     */
    private static void printSummary(boolean[] results) {
        int passed = 0;
        for (boolean result : results) {
            if (result) passed++;
        }

        System.out.println("=".repeat(50));
        System.out.println("TEST SUMMARY");
        System.out.println("=".repeat(50));
        System.out.printf("Total Tests: %d%n", results.length);
        System.out.printf("Passed:      %d%n", passed);
        System.out.printf("Failed:      %d%n", results.length - passed);
        System.out.printf("Success Rate: %.1f%%%n", (passed * 100.0 / results.length));
        System.out.println("=".repeat(50));
    }

    // ============================================================================
    // Main Method - Test Cases
    // ============================================================================

    public static void main(String[] args) {
        ValidSudoku solution = new ValidSudoku();
        boolean[] results = new boolean[5];

        System.out.println("Valid Sudoku - Test Cases");
        System.out.println("=".repeat(50));
        System.out.println();

        // Test 1: Valid board (Example 1 from LeetCode)
        char[][] board1 = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        boolean result1 = solution.isValidSudoku(board1);
        runTest(1, board1, true, result1, "Valid board from LeetCode Example 1");
        results[0] = (result1 == true);

        // Test 2: Invalid board - duplicate in row (Example 2 from LeetCode)
        char[][] board2 = {
            {'8','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        boolean result2 = solution.isValidSudoku(board2);
        runTest(2, board2, false, result2, "Invalid - duplicate '8' in first column");
        results[1] = (result2 == false);

        // Test 3: Valid board - all cells filled
        char[][] board3 = {
            {'5','3','4','6','7','8','9','1','2'},
            {'6','7','2','1','9','5','3','4','8'},
            {'1','9','8','3','4','2','5','6','7'},
            {'8','5','9','7','6','1','4','2','3'},
            {'4','2','6','8','5','3','7','9','1'},
            {'7','1','3','9','2','4','8','5','6'},
            {'9','6','1','5','3','7','2','8','4'},
            {'2','8','7','4','1','9','6','3','5'},
            {'3','4','5','2','8','6','1','7','9'}
        };
        boolean result3 = solution.isValidSudoku(board3);
        runTest(3, board3, true, result3, "Valid - complete solved sudoku");
        results[2] = (result3 == true);

        // Test 4: Invalid - duplicate in 3x3 sub-box
        char[][] board4 = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','5','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        boolean result4 = solution.isValidSudoku(board4);
        runTest(4, board4, false, result4, "Invalid - duplicate '5' in top-left 3x3 box");
        results[3] = (result4 == false);

        // Test 5: Valid - mostly empty board
        char[][] board5 = {
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','5','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'}
        };
        boolean result5 = solution.isValidSudoku(board5);
        runTest(5, board5, true, result5, "Valid - mostly empty board with one number");
        results[4] = (result5 == true);

        // Print summary
        printSummary(results);
    }
}
