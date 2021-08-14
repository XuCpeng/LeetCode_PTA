package cn.medemede.leecode;

/**
 * 剑指 Offer 12. 矩阵中的路径
 */
public class Exist {
    char[] wordChars;
    int m;
    int n;
    boolean[][] visited;
    char[][] board;

    public boolean exist(char[][] board, String word) {
        wordChars = word.toCharArray();
        m = board.length;
        if (m == 0) {
            return false;
        }
        this.board = board;
        n = board[0].length;
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == wordChars[0] && hasPath(i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasPath(int i, int j, int k) {
        if (k == wordChars.length) {
            return true;
        }
        if (i >= m || i < 0 || j >= n || j < 0) {
            return false;
        }
        if (!visited[i][j] && board[i][j] == wordChars[k]) {
            visited[i][j] = true;
            if (hasPath(i + 1, j, k + 1) || hasPath(i, j + 1, k + 1) || hasPath(i - 1, j, k + 1) || hasPath(i, j - 1, k + 1)) {
                return true;
            }
            visited[i][j] = false;
        }
        return false;
    }
}
