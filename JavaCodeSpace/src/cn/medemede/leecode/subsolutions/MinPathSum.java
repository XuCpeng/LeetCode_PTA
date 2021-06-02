package cn.medemede.leecode.subsolutions;

public class MinPathSum {

    /**
     * 最小路径和
     * <p>动态规划，递归，备忘录</p>
     *
     * @param grid
     * @return
     */
    int[][] memo;

    public int minPathSum(int[][] grid) {
        memo = new int[grid.length][grid[0].length];
        return getMinPathSum(grid, 0, 0);
    }

    private int getMinPathSum(int[][] grid, int i, int j) {
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        int val = grid[i][j];
        if (i != grid.length - 1 && j != grid[0].length - 1) {
            memo[i][j] = val + Math.min(getMinPathSum(grid, i + 1, j), getMinPathSum(grid, i, j + 1));
        } else if (i != grid.length - 1) {
            memo[i][j] = val + getMinPathSum(grid, i + 1, j);
        } else if (j != grid[0].length - 1) {
            memo[i][j] = val + getMinPathSum(grid, i, j + 1);
        } else {
            memo[i][j] = val;
        }
        return memo[i][j];
    }

    /**
     * 最小路径和
     * <p>动态规划，非递归，左上角正序</p>
     *
     * @param grid
     * @return
     */
    public int minPathSum2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[m - 1][n - 1];
    }
}

