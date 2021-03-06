package cn.medemede.leecode;

import java.util.Arrays;

public class MinDistance {

    /**
     * 两个字符串的删除操作
     * <p>动态规划，递归，备忘录<p/>
     *
     * @param word1
     * @param word2
     * @return
     */
    int[][] memo;

    public int minDistance(String word1, String word2) {
        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();
        memo = new int[w1.length][w2.length];
        for (int[] m : memo) {
            Arrays.fill(m, -1);
        }
        return getMinDistance(w1, 0, w2, 0);
    }

    private int getMinDistance(char[] w1, int i, char[] w2, int j) {
        if (i == w1.length) {
            return w2.length - j;
        }
        if (j == w2.length) {
            return w1.length - i;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        if (w1[i] == w2[j]) {
            memo[i][j] = getMinDistance(w1, i + 1, w2, j + 1);
        } else {
            memo[i][j] = Math.min(getMinDistance(w1, i + 1, w2, j), getMinDistance(w1, i, w2, j + 1)) + 1;
        }
        return memo[i][j];
    }

}

