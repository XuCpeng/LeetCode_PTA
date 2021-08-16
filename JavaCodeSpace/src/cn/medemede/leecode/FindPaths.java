package cn.medemede.leecode;

/**
 * 576. 出界的路径数
 */
public class FindPaths {
    int base = 1000000007;
    int res;
    int[][] dp;
    int m;
    int n;

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        this.m = m;
        this.n = n;
        this.res = 0;
        this.dp = new int[m][n];
        dp[startRow][startColumn] = 1;
        for (int k = 0; k < maxMove; k++) {
            int[][] tmp = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (dp[i][j] > 0) {
                        getPath(tmp, i, j);
                    }
                }
            }
            dp = tmp;
        }
        return res;
    }

    private void getPath(int[][] tmp, int i, int j) {
        int newI = i + 1;
        if (newI == m) {
            res += dp[i][j];
            res %= base;
        } else {
            tmp[newI][j] += dp[i][j];
            tmp[newI][j] %= base;
        }
        newI = i - 1;
        if (newI < 0) {
            res += dp[i][j];
            res %= base;
        } else {
            tmp[newI][j] += dp[i][j];
            tmp[newI][j] %= base;
        }
        int newJ = j + 1;
        if (newJ == n) {
            res += dp[i][j];
            res %= base;
        } else {
            tmp[i][newJ] += dp[i][j];
            tmp[i][newJ] %= base;
        }
        newJ = j - 1;
        if (newJ < 0) {
            res += dp[i][j];
            res %= base;
        } else {
            tmp[i][newJ] += dp[i][j];
            tmp[i][newJ] %= base;
        }
    }
}
