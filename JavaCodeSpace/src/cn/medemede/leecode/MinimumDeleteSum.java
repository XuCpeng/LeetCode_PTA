package cn.medemede.leecode;

import java.util.Arrays;

public class MinimumDeleteSum {

    /**
     * 两个字符串的最小ASCII删除和
     * <p>动态规划，递归，备忘录</p>
     *
     * @param s1
     * @param s2
     * @return
     */
    int[][] memo;

    public int minimumDeleteSum(String s1, String s2) {
        char[] w1 = s1.toCharArray();
        char[] w2 = s2.toCharArray();
        memo = new int[w1.length][w2.length];
        for (int[] m : memo) {
            Arrays.fill(m, -1);
        }
        return getMinimumDeleteSum(w1, 0, w2, 0);
    }

    private int getMinimumDeleteSum(char[] w1, int i, char[] w2, int j) {
        if (i == w1.length) {
            int sum = 0;
            while (j < w2.length) {
                sum += w2[j];
                j++;
            }
            return sum;
        }
        if (j == w2.length) {
            int sum = 0;
            while (i < w1.length) {
                sum += w1[i];
                i++;
            }
            return sum;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        if (w1[i] == w2[j]) {
            memo[i][j] = getMinimumDeleteSum(w1, i + 1, w2, j + 1);
        } else {
            memo[i][j] = Math.min(getMinimumDeleteSum(w1, i + 1, w2, j) + w1[i], getMinimumDeleteSum(w1, i, w2, j + 1) + w2[j]);
        }
        return memo[i][j];
    }

}

