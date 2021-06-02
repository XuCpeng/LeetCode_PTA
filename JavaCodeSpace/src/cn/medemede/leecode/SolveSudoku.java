package cn.medemede.leecode;

public class SolveSudoku {

    /**
     * 数独
     * <p>递归，回溯</p>
     *
     * @param board
     */
    public void solveSudoku(char[][] board) {
        getSolveSudoku(board, 0);
    }

    private boolean getSolveSudoku(char[][] board, int preI) {
        int i = preI;
        int j = 0;
        for (; i < 9; i++) {
            for (j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    break;
                }
            }
            if (j < 9 && board[i][j] == '.') {
                break;
            }
        }

        if (i == 9 && j == 9) {
            return true;
        }
        for (int k = '1'; k <= '9'; k++) {
            if (canSetSudoku(k, i, j, board)) {
                board[i][j] = (char) k;
                if (!getSolveSudoku(board, i)) {
                    board[i][j] = '.';
                } else {
                    return true;
                }
            }
        }
        return false;
    }


    private boolean canSetSudoku(int k, int i, int j, char[][] board) {
        for (int l = 0; l < 9; l++) {
            if (board[i][l] == k || board[l][j] == k) {
                return false;
            }
        }

        int p = i;
        int q;
        while (p % 3 != 0) {
            p--;
            q = j;
            if (board[p][q] == k) {
                return false;
            }
            while (q % 3 != 0) {
                q--;
                if (board[p][q] == k) {
                    return false;
                }
            }
            q = j + 1;
            while (q % 3 != 0) {
                if (board[p][q] == k) {
                    return false;
                }
                q++;
            }
        }
        p = i + 1;
        while (p % 3 != 0) {
            q = j;
            if (board[p][q] == k) {
                return false;
            }
            while (q % 3 != 0) {
                q--;
                if (board[p][q] == k) {
                    return false;
                }
            }
            q = j + 1;
            while (q % 3 != 0) {
                if (board[p][q] == k) {
                    return false;
                }
                q++;
            }
            p++;
        }

        return true;
    }


}

