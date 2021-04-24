package cn.medemede.leecode;

/**
 * 最小编辑距离
 * <p>动态规划,备忘录</p>
 */

public class MinEditDistance {
    char[] w1;
    char[] w2;
    int[][] memo;

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    private int dp(int i, int j) {
        if (i >= w1.length) {
            return w2.length - j;
        }
        if (j >= w2.length) {
            return w1.length - i;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        if (w1[i] == w2[j]) {
            //相同，不需要处理
            memo[i][j] = dp(i + 1, j + 1);
        } else {
            memo[i][j] = min(
                    // 插入w1
                    dp(i, j + 1) + 1,
                    // 替换w1
                    dp(i + 1, j + 1) + 1,
                    // 删除w1
                    dp(i + 1, j) + 1
            );
        }
        return memo[i][j];
    }

    public int minDistance(String word1, String word2) {
        w1 = word1.toCharArray();
        w2 = word2.toCharArray();
        memo = new int[w1.length][w2.length];
        for (int i = 0; i < w1.length; i++) {
            for (int j = 0; j < w2.length; j++) {
                memo[i][j] = -1;
            }
        }
        return dp(0, 0);
    }
}
