class Sudoku {

    static int[][] board = { { 0, 8, 0, 7, 0, 1, 0, 3, 0 }, { 4, 0, 9, 0, 0, 0, 0, 0, 0 },
            { 0, 5, 0, 0, 6, 0, 4, 1, 8 },
            { 7, 0, 0, 0, 0, 9, 0, 0, 0 }, { 8, 0, 0, 6, 1, 0, 5, 0, 0 }, { 0, 3, 5, 0, 0, 0, 0, 2, 9 },
            { 0, 6, 0, 4, 0, 7, 0, 9, 0 }, { 1, 0, 0, 0, 0, 8, 0, 0, 4 }, { 0, 2, 0, 0, 5, 0, 0, 7, 0 } };

    static int GRID = 9;

    static boolean isValidRow(int num, int row) {
        for (int i = 0; i < GRID; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }
        return true;
    }

    static boolean isValidColumn(int num, int col) {
        for (int i = 0; i < GRID; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }
        return true;
    }

    static boolean isValidBox(int num, int row, int col) {
        // System.out.println(row + ":" + col);

        for (int i = (row / 3) * 3; i < (row / 3) * 3 + 3; i++) {
            for (int j = (col / 3) * 3; j < (col / 3) * 3 + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean Solve(int row, int col) {
        if (row > 8 || col > 8)
            return true;
        int nextRow = col == 8 ? row + 1 : row;
        int nextCol = col == 8 ? 0 : col + 1;
        if (board[row][col] != 0) {
            return Solve(nextRow, nextCol);
        }
        // System.out.println(row + ":" + col);
        for (int k = 1; k <= GRID; k++) {
            if (isValidRow(k, row) && isValidColumn(k, col) && isValidBox(k, row, col)) {
                board[row][col] = k;
                if (Solve(nextRow, nextCol)) {
                    return true;
                }
                board[row][col] = 0;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        if (Solve(0, 0)) {
            for (int i = 0; i < GRID; i++) {
                for (int j = 0; j < GRID; j++) {
                    System.out.print(board[i][j] + "  ");
                    if ((j + 1) % 3 == 0) {
                        System.out.print("|  ");
                    }
                }
                if ((i + 1) % 3 == 0) {
                    System.out.println("_");
                }
                System.out.println();
            }
        } else {
            System.out.println("Not Solvable");
        }
    }
}