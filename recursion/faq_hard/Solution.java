package recursion.faq_hard;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<String>> partition(String s) {
        // ******************* Initialization and Result Storage *******************
        // Create a list to store all possible palindromic partitions.
        List<List<String>> ans = new ArrayList<>();

        // ******************* Initial Call to Helper *******************
        // Start partitioning with the index set to 0 and an empty list to store the current partitions.
        partitionHelper(s, ans, 0, new ArrayList<>());

        // Return the final list of palindromic partitions.
        return ans;
    }

    private void partitionHelper(String s, List<List<String>> ans, int idx, ArrayList<String> list) {

        // ******************* Base Case: All Characters Processed *******************
        // If the current index (idx) equals the length of the string, it means we have processed all characters.
        // Add the current list of palindromic substrings to the final answer list.
        if (idx == s.length()) {
            ans.add(new ArrayList<>(list)); // Add a copy of the current partition to the answer.
            return;
        }

        // ******************* Recursive Case: Try All Possible Substrings *******************
        // Iterate over the string starting from the current index to explore all possible substrings.
        for (int i = idx; i < s.length(); i++) {

            // ******************* Palindrome Check *******************
            // If the substring from index `idx` to `i` is a palindrome, we can include it in the current partition.
            if (isPalindrome(s, idx, i)) {
                // ******************* Include Substring in Partition *******************
                // Add the palindrome substring `s.substring(idx, i+1)` to the current partition list.
                list.add(s.substring(idx, i + 1));

                // ******************* Recursive Call to Process the Rest of the String *******************
                // Recursively partition the rest of the string starting from the index `i+1`.
                partitionHelper(s, ans, i + 1, list);

                // ******************* Backtracking Step *******************
                // Remove the last added palindrome substring to explore other possible partitions.
                list.remove(list.size() - 1);
            }
        }

    /*
        Example Walkthrough:
        Consider the string `s = "aab"`
        1. Start with an empty list and begin partitioning from index 0.
        2. Check all possible substrings starting from index 0:
           - Substring "a" (from index 0 to 0) is a palindrome. Include "a".
           - Recursive call to partition the remaining string "ab" (starting from index 1).
        3. For the remaining string "ab":
           - Substring "a" (from index 1 to 1) is a palindrome. Include "a".
           - Recursive call to partition the remaining string "b" (starting from index 2).
           - Substring "b" (from index 2 to 2) is a palindrome. Include "b".
           - Add the partition ["a", "a", "b"] to the result.
        4. Backtrack and explore other partitions: ["aa", "b"].

        The valid partitions for "aab" are: ["a", "a", "b"] and ["aa", "b"].

        ******************* Time Complexity Analysis *******************
        Time complexity: O(n * 2^n), where n is the length of the input string `s`.
        - There are `2^n` possible partitions (since each character in the string can either be part of a new substring or an extension of the previous substring).
        - For each partition, we check if each substring is a palindrome, which takes O(n) in the worst case.
        - Therefore, the overall time complexity is O(n * 2^n).

        ******************* Space Complexity Analysis *******************
        Space complexity: O(n) for the recursion depth and O(n) for the list storing the current partition.
        - The recursion depth is at most `n`, where `n` is the length of the string.
        - The space used by the result list is proportional to the number of palindromic partitions and their size.
    */
    }

    private boolean isPalindrome(String s, int start, int end) {

        // ******************* Sneaky Condition: Handle Edge Case *******************
        // If the start index exceeds the end index, the substring is trivially a palindrome (e.g., empty string or single character).
        if (start > end) {
            return true;
        }

        // ******************* Palindrome Check for Characters at Start and End *******************
        // Compare the characters at the start and end positions. If they don't match, the substring is not a palindrome.
        if (s.charAt(start) != s.charAt(end)) {
            return false;
        }

        // ******************* Recursive Call to Check Inner Substring *******************
        // If the characters match, recursively check the substring between `start+1` and `end-1`.
        return isPalindrome(s, start + 1, end - 1);
    }

    public boolean exist(char[][] board, String word) {
    /*
        ******************* Initialization and Start Search *******************
        We initiate the search for the word in the board by calling the helper method.
        The helper method loops through all the cells in the board to find the first character of the word.
    */
        return wordExistHelper(board, word);
    }

    private boolean wordExistHelper(char[][] board, String word) {
    /*
        ******************* Visited Matrix *******************
        Create a 2D boolean array `vis` to track visited cells. We avoid revisiting cells during our search.
    */
        boolean[][] vis = new boolean[board.length][board[0].length];

    /*
        ******************* Loop Through the Board *******************
        Traverse each cell of the board. For every cell that matches the first character of the word,
        we call `checkBoard` to explore whether the full word can be found starting from that cell.
    */
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // Check if the current cell matches the first character of the word
                if (board[i][j] == word.charAt(0)) {
                    // Call the backtracking function `checkBoard` to explore in all directions
                    if (checkBoard(board, word, i, j, board.length, board[0].length, 0, vis)) {
                        return true; // If a match is found, return true
                    }
                }
            }
        }
        return false; // No match found
    }

    private boolean checkBoard(char[][] board, String word, int row, int col, int rowLen, int colLen, int idx, boolean[][] vis) {

        // ******************* Base Case: Word Found *******************
        // If `idx` equals the length of the word, it means we have matched all characters in the word.
        if (idx == word.length()) {
            return true; // Word found
        }

        // ******************* Boundary and Visited Check *******************
        // Ensure that the current cell (row, col) is within the board boundaries and hasn't been visited.
        // We also check for invalid indices.
        if (row < 0 || col < 0 || row >= rowLen || col >= colLen || vis[row][col]) {
            return false; // Out of bounds or already visited
        }

        // ******************* Character Mismatch Check *******************
        // If the character in the current cell doesn't match the current character in the word (`word.charAt(idx)`), return false.
        if (board[row][col] != word.charAt(idx)) {
            return false; // Current cell doesn't match the expected character
        }

        // ******************* Mark Current Cell as Visited *******************
        vis[row][col] = true; // Mark the cell as visited to avoid revisiting it in the current path

        // ******************* Explore Four Directions (Up, Right, Down, Left) *******************
        // Check if moving in any of the four possible directions (up, right, down, left) allows us to find the word.
        boolean isAnsUp = checkBoard(board, word, row - 1, col, rowLen, colLen, idx + 1, vis);     // Move up
        boolean isAnsRight = checkBoard(board, word, row, col + 1, rowLen, colLen, idx + 1, vis);  // Move right
        boolean isAnsDown = checkBoard(board, word, row + 1, col, rowLen, colLen, idx + 1, vis);   // Move down
        boolean isAnsLeft = checkBoard(board, word, row, col - 1, rowLen, colLen, idx + 1, vis);   // Move left

        // ******************* Unmark Current Cell (Backtracking Step) *******************
        // We unmark the current cell as visited to explore new paths for the word.
        vis[row][col] = false; // This is essential for backtracking.

        // Return true if any of the four directions found the word
        return isAnsUp || isAnsDown || isAnsLeft || isAnsRight;

    /*
        ******************* Time Complexity Analysis *******************
        Time complexity: O(n * m * 4^L), where:
        - n = number of rows in the board,
        - m = number of columns in the board,
        - L = length of the word.
        The worst-case scenario is that we explore all four directions for each character in the word,
        leading to a branching factor of 4 at each step. For each cell, the word could potentially
        branch in 4 directions, and the recursion depth would go up to L, the length of the word.

        ******************* Space Complexity Analysis *******************
        Space complexity: O(L), where L is the length of the word.
        - The space is primarily consumed by the recursion call stack, which can be as deep as L in the worst case.
        - Additionally, the `vis` array uses O(n * m) space, but it can be reused across recursive calls,
          so it doesn't contribute to the recursion stack space.
    */
    }

    public List<List<String>> solveNQueens(int n) {
    /*
        ******************* Initialization of Chessboard *******************
        Create a chessboard of size n x n, initialized with dots representing empty cells.
        We use a 2D array of Strings to represent the board.
    */
        List<List<String>> ans = new ArrayList<>();
        String[][] chessBoard = new String[n][n]; // Initialize a blank chessboard of n x n size

        // Fill the chessboard with empty cells denoted by "."
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chessBoard[i][j] = ".";
            }
        }

    /*
        ******************* Start Recursive Search *******************
        We begin placing queens starting from row 0 by calling the helper function.
    */
        solveNQueensHelper(ans, chessBoard, n, 0);
        return ans;
    }

    private void solveNQueensHelper(List<List<String>> ans, String[][] chessBoard, int n, int row) {
    /*
        ******************* Base Case: All Queens Placed *******************
        If `row == n`, it means we have successfully placed n queens, one on each row.
        Add the current configuration of the chessboard as a valid solution.
    */
        if (row == n) {
            // Convert the chessboard from a 2D array into a list of strings for each row
            List<String> solution = new ArrayList<>();
            for (String[] boardRow : chessBoard) {
                solution.add(String.join("", boardRow)); // Join the elements of each row into a single string
            }
            ans.add(solution); // Add the solution to the final answer list
            return;
        }

    /*
        ******************* Try Placing Queens in Each Column *******************
        Loop through each column in the current row and try placing a queen.
        For each valid position, recursively attempt to place queens on the next rows.
    */
        for (int col = 0; col < n; col++) {
            if (canPlace(chessBoard, row, col)) { // Check if we can place a queen at (row, col)
                chessBoard[row][col] = "Q"; // Place the queen

                // Recursive call to attempt to place the next queen in the next row
                solveNQueensHelper(ans, chessBoard, n, row + 1);

                // Backtrack: Remove the queen from (row, col) and try the next column
                chessBoard[row][col] = ".";
            }
        }
    }

    private boolean canPlace(String[][] chessBoard, int row, int col) {
    /*
        ******************* Column Check *******************
        Check if there's a queen placed in the same column in any of the previous rows.
        We only need to check rows above the current row because we are placing queens row by row.
    */
        for (int i = 0; i < row; i++) {
            if ("Q".equals(chessBoard[i][col])) { // Check if there's a queen in the same column
                return false; // Cannot place queen if there's another queen in the same column
            }
        }

    /*
        ******************* Diagonal Check (Top-Left to Bottom-Right) *******************
        Check if there's a queen placed on the top-left diagonal of the current cell.
        Move upwards and leftwards while checking the diagonal.
    */
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if ("Q".equals(chessBoard[i][j])) { // Check if there's a queen on the top-left diagonal
                return false; // Cannot place queen if there's another queen on the diagonal
            }
        }

    /*
        ******************* Diagonal Check (Top-Right to Bottom-Left) *******************
        Check if there's a queen placed on the top-right diagonal of the current cell.
        Move upwards and rightwards while checking the diagonal.
    */
        for (int i = row, j = col; i >= 0 && j < chessBoard.length; i--, j++) {
            if ("Q".equals(chessBoard[i][j])) { // Check if there's a queen on the top-right diagonal
                return false; // Cannot place queen if there's another queen on the diagonal
            }
        }

        return true; // It's safe to place the queen at (row, col)

    /*
        ******************* Time Complexity Analysis *******************
        Time complexity: O(n!), where n is the size of the chessboard.
        - The first queen has n possible columns to be placed in.
        - The second queen has (n - 1) valid columns, and so on.
        - In the worst case, this leads to n! recursive calls (factorial growth).

        ******************* Space Complexity Analysis *******************
        Space complexity: O(n^2) for the chessboard and O(n) for the recursion depth.
        - The chessboard is a 2D array of size n x n.
        - The recursion stack depth is at most n since we place one queen per row.
    */
    }

    public static ArrayList<String> findPath(int[][] m) {
    /*
        ******************* Initialization *******************
        - `ans` is used to store all valid paths from the top-left to bottom-right.
        - `n` is the size of the grid (assumed to be square).
        - `visited[][]` keeps track of cells we've already visited to avoid cycles.
        - We start the recursive pathfinding from the top-left corner (0,0) and initialize an empty string `""` to represent the current path.
    */
        ArrayList<String> ans = new ArrayList<>();
        int n = m.length;  // Size of the grid
        boolean visited[][] = new boolean[n][n];  // Visited array to track the visited cells

        // Call the recursive helper function to start finding paths from (0, 0)
        function(0, 0, m, n, n, "", ans, visited);

        // If no valid path is found, return "-1"
        if (ans.isEmpty()) {
            ans.add("-1");
        }
        return ans;
    }

    public static void function(int i, int j, int[][] arr, int n, int m, String str, ArrayList<String> ans, boolean[][] visited) {
    /*
        ******************* Boundary and Condition Check *******************
        - Check if the current cell (i, j) is within bounds.
        - Ensure the cell is not blocked (arr[i][j] != 0) and hasn't been visited.
    */
        if (i < n && j < m && i >= 0 && j >= 0 && arr[i][j] != 0 && visited[i][j] == false) {

        /*
            ******************* Base Case: Destination Reached *******************
            If the current cell is the bottom-right corner, add the current path to the result list.
        */
            if (i == n - 1 && j == m - 1) {
                ans.add(str);
                return;
            }

            // Mark the current cell as visited
            visited[i][j] = true;

        /*
            ******************* Recursive Exploration *******************
            Explore all four directions: Down, Up, Right, and Left, appending the direction to the path string.
        */
            function(i + 1, j, arr, n, m, str + "D", ans, visited);  // Move Down
            function(i - 1, j, arr, n, m, str + "U", ans, visited);  // Move Up
            function(i, j + 1, arr, n, m, str + "R", ans, visited);  // Move Right
            function(i, j - 1, arr, n, m, str + "L", ans, visited);  // Move Left

        /*
            ******************* Backtrack *******************
            After exploring all possible directions, backtrack by marking the current cell as unvisited,
            so it can be reused in other paths.
        */
            visited[i][j] = false;

        } else {
            // If the cell is out of bounds or blocked or visited, return to the previous call
            return;
        }
        /*
        ******************* Time Complexity Analysis *******************
        Time Complexity: O(4^(n*m))
        - At each step, we can move in four possible directions (up, down, left, right).
        - In the worst case, the recursive function explores all cells in the grid.
        - Since we can backtrack, each cell might be revisited multiple times, but in different ways.
        - This results in 4 choices per cell, so the complexity is O(4^(n*m)).

        ******************* Space Complexity Analysis *******************
        Space Complexity: O(n*m)
        - The visited array takes O(n*m) space to store the state of each cell.
        - The recursion stack depth is proportional to the number of cells, which is O(n*m).
    */
    }

    public void solveSudoku(char[][] board) {
        /*
            This function initiates the recursive backtracking process to solve the Sudoku puzzle.
            We start from the top-left corner of the board (0,0) and begin placing valid numbers.
        */
        solveSudokuHelper(board, 0, 0);
    }

    private boolean solveSudokuHelper(char[][] board, int row, int col) {
        /*
            ******************* Base Case *******************
            - If we reach the last row and column, the board is solved, so return true.
            - This triggers the backtracking to stop.
        */
        if (row == 9) {
            return true; // Puzzle is successfully solved
        }

        // Move to the next row if we finish the current row
        if (col == 9) {
            return solveSudokuHelper(board, row + 1, 0);
        }

        /*
            ******************* Skip Filled Cells *******************
            If the current cell is already filled, move to the next cell by calling the function
            with updated row and col (col + 1).
        */
        if (board[row][col] != '.') {
            return solveSudokuHelper(board, row, col + 1);
        }

        /*
            ******************* Recursive Exploration *******************
            Try placing digits from '1' to '9' in the current cell.
            If the digit is valid, place it and recursively try to solve the rest of the board.
        */
        for (char c = '1'; c <= '9'; c++) {
            if (isValid(board, row, col, c)) {
                board[row][col] = c;

                // Recur to place the next number. If the board is solved, return true.
                if (solveSudokuHelper(board, row, col + 1)) {
                    return true;
                }

                // Backtrack: reset the current cell if the solution is not valid.
                board[row][col] = '.';
            }
        }

        // If no number works, return false to backtrack to the previous cell.
        return false;
    }

    private boolean isValid(char[][] board, int r, int c, char ch) {
        /*
            ******************* Check Row and Column *******************
            - Check if `ch` is already present in the current row `r` or column `c`.
        */
        for (int i = 0; i < 9; i++) {
            if (board[r][i] == ch || board[i][c] == ch) {
                return false; // Invalid if the number already exists
            }
        }

        /*
            ******************* Check 3x3 Subgrid *******************
            - Find the top-left corner of the 3x3 subgrid that contains (r, c).
            - Check if `ch` is present in this subgrid.
        */
        int subGridRowStart = (r / 3) * 3;
        int subGridColStart = (c / 3) * 3;

        for (int i = subGridRowStart; i < subGridRowStart + 3; i++) {
            for (int j = subGridColStart; j < subGridColStart + 3; j++) {
                if (board[i][j] == ch) {
                    return false; // Invalid if the number already exists in the subgrid
                }
            }
        }

        // The digit can be placed safely in the current cell.
        return true;
    }
        /*
        ******************* Time Complexity Analysis *******************
        Time Complexity: O(9^(n*n))
        - In the worst case, we attempt to fill every cell with digits 1-9.
        - For each empty cell, we try all 9 digits, leading to a time complexity of O(9^(n*n)), where n is the size of the Sudoku grid (9x9).
        - The worst-case scenario happens when the board has the maximum number of empty cells and requires backtracking for every placement.

        ******************* Space Complexity Analysis *******************
        Space Complexity: O(n*n) for recursion stack + O(1) for the board
        - The recursion depth will be at most `n*n` because we are recursing for each cell.
        - The board itself is mutated in-place, so it doesn't require extra space.
        - The auxiliary space is O(n*n) for the recursion stack depth in the worst case.
    */

    /*
       ******************* Helper Method 1: isSafe *******************
       This method checks whether it's safe to color the current node with color `col`.
       We iterate over all the neighbors of the current node (using the adjacency list).
       If any neighbor has the same color `col`, we return false, indicating that it’s not safe to use this color.

       Time Complexity: O(k), where k is the number of neighbors of the current node.
   */
    private boolean isSafe(int col, int node, int[] colors, List<List<Integer>> adj) {
        for (int neighbor : adj.get(node)) {
            // If any neighbor has the same color, it's unsafe to color this node with `col`.
            if (colors[neighbor] == col) return false;
        }
        return true;
    }

    /*
        ******************* Helper Method 2: solve *******************
        This is the recursive backtracking method that attempts to color all nodes.
        - If all nodes are colored (node == n), we return true, indicating that the coloring is possible.
        - Otherwise, for the current node, we try all colors from 1 to `m` (total colors).
        - If it's safe to use a color, we assign it and recursively try to color the next node.
        - If coloring fails, we backtrack by resetting the current node's color to 0.

        Time Complexity: O(m^n), where `m` is the number of available colors and `n` is the number of nodes.
    */
    private boolean solve(int node, int m, int n, int[] colors, List<List<Integer>> adj) {
        // Base case: if all nodes are colored, return true
        if (node == n) return true;

        // Try coloring the current node with all possible colors (from 1 to m)
        for (int i = 1; i <= m; i++) {
            if (isSafe(i, node, colors, adj)) {
                colors[node] = i; // Assign color `i` to this node
                // Recur to color the next node
                if (solve(node + 1, m, n, colors, adj)) return true;
                // Backtrack: reset the color if it doesn't lead to a solution
                colors[node] = 0;
            }
        }

        // If no color can be assigned to this node, return false
        return false;
    }

    /*
        ******************* Main Method: graphColoring *******************
        - This method initializes the adjacency list and starts the recursive backtracking.
        - `edges`: the input edges of the graph.
        - `m`: the number of colors available.
        - `n`: the number of nodes in the graph.

        Time Complexity: O(m^n * k), where `m` is the number of colors, `n` is the number of nodes, and `k` is the average number of neighbors.
        Space Complexity: O(n) for storing the colors and the recursion stack.
    */
    public boolean graphColoring(int[][] edges, int m, int n) {
        // Step 1: Create an adjacency list from the edges
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // Populate the adjacency list
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]); // Add an edge from node `edge[0]` to `edge[1]`
            adj.get(edge[1]).add(edge[0]); // Since the graph is undirected, add the reverse edge too
        }

        // Step 2: Initialize a colors array where colors[i] will store the color assigned to node `i`
        int[] colors = new int[n];

        // Step 3: Start the backtracking process from node 0
        return solve(0, m, n, colors, adj);
    }

    /*
        Example Walkthrough:
        Consider a graph with 3 nodes and 2 edges: (0-1), (1-2).
        - If m = 2 (2 available colors), we try to color each node with one of the 2 colors.
        - We start with node 0 and color it with color 1, then move to node 1.
        - Node 1 can't be colored with 1 (since its neighbor node 0 has color 1), so we use color 2.
        - Move to node 2, and color it with color 1.
        - The graph is successfully colored with 2 colors.

        ******************* Time Complexity Analysis *******************
        Time complexity: O(m^n * k), where `m` is the number of available colors and `n` is the number of nodes.
        - For each node, we try all `m` colors, and in the worst case, we backtrack through all `n` nodes.
        - For each node, we also check if the color is valid by iterating through its `k` neighbors.

        ******************* Space Complexity Analysis *******************
        Space complexity: O(n) for storing the `colors` array and O(n) for the recursion stack depth.
        - The adjacency list uses O(n + E), where E is the number of edges.
        - The recursion stack has depth at most `n`, and the `colors` array is size `n`.
    */
}
