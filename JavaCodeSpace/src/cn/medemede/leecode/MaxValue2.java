package cn.medemede.leecode;

/**
 * 剑指 Offer 47. 礼物的最大价值
 */
public class MaxValue2 {
    int[][] memo;

    public int maxValue(int[][] grid) {
        memo = new int[grid.length][grid[0].length];
        return getMaxValue(grid, 0, 0);
    }

    private int getMaxValue(int[][] grid, int i, int j) {
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        int a = grid[i][j];
        int b = a;
        if (i < grid.length - 1) {
            a += getMaxValue(grid, i + 1, j);
        }
        if (j < grid[0].length - 1) {
            b += getMaxValue(grid, i, j + 1);
        }
        memo[i][j] = Math.max(a, b);
        return memo[i][j];
    }
}
