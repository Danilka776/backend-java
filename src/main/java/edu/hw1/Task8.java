package edu.hw1;


public final class Task8 {
    private Task8() {
    }

    private static final int BOARD_SIZE = 7;

    private static boolean hasNeighbour1(int[][] board, int i, int j) {
        if (j - 1 >= 0) {
            if (board[i - 2][j - 1] == 1) {
                return true;
            }
        }
        if (j + 1 <= BOARD_SIZE) {
            if (board[i - 2][j + 1] == 1) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasNeighbour2(int[][] board, int i, int j) {
        if (j - 2 >= 0) {
            if (board[i - 1][j - 2] == 1) {
                return true;
            }
        }
        if (j + 2 <= BOARD_SIZE) {
            if (board[i - 1][j + 2] == 1) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasNeighbour3(int[][] board, int i, int j) {
        if (j - 2 >= 0) {
            if (board[i + 1][j - 2] == 1) {
                return true;
            }
        }
        if (j + 2 <= BOARD_SIZE) {
            if (board[i + 1][j + 2] == 1) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasNeighbour4(int[][] board, int i, int j) {
        if (j - 1 >= 0) {
            if (board[i + 2][j - 1] == 1) {
                return true;
            }
        }
        if (j + 1 <= BOARD_SIZE) {
            if (board[i + 2][j + 1] == 1) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasNeighbour(int[][] board, int i, int j) {
        boolean ans = false;
        if (i - 2 >= 0) {
            ans = hasNeighbour1(board, i, j);
        }
        if (i - 1 >= 0) {
            ans = hasNeighbour2(board, i, j);
        }
        if (i + 1 <= BOARD_SIZE) {
            ans = hasNeighbour3(board, i, j);
        }
        if (i + 2 <= BOARD_SIZE) {
            ans = hasNeighbour4(board, i, j);
        }
        return ans;
    }

    public static boolean knightBoardCapture(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 1 && hasNeighbour(board, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

}
