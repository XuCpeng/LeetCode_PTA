package cn.medemede.leecode.subsolutions;

import java.util.Arrays;

public class LongestCommonSubsequence {

    /**
     * 最长公共子序列
     * <p>动态规划，dp数组，非递归，自底向上</p>
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        char[] t1 = text1.toCharArray();
        char[] t2 = text2.toCharArray();
        int[][] dp = new int[t1.length + 1][t2.length + 1];
        for (int i = 1; i <= t1.length; i++) {
            for (int j = 1; j <= t2.length; j++) {
                if (t1[i - 1] == t2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[t1.length][t2.length];
    }


    /**
     * 最长公共子序列
     * <p>动态规划，dp函数，递归，备忘录，自顶向下</p>
     * <p>之所以是自顶向下，是因为递归算法的真正开始计算是在退栈的时候，所以虽然递归算法的推进是从0开始不断+1，
     * 但实际计算是在==length之后开始，所以相当于--，所以与非递归算法在t1[i] != t2[j]时是-1，而递归算法是+1</p>
     *
     * @param text1
     * @param text2
     * @return
     */
    int[][] memo;

    public int longestCommonSubsequence2(String text1, String text2) {
        char[] t1 = text1.toCharArray();
        char[] t2 = text2.toCharArray();
        memo = new int[t1.length][t2.length];
        for (int[] m : memo) {
            Arrays.fill(m, -1);
        }
        return getLongestCommonSubsequence(t1, 0, t2, 0);
    }

    private int getLongestCommonSubsequence(char[] t1, int i, char[] t2, int j) {
        if (i == t1.length || j == t2.length) {
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        if (t1[i] == t2[j]) {
            memo[i][j] = getLongestCommonSubsequence(t1, i + 1, t2, j + 1) + 1;
        } else {
            memo[i][j] = Math.max(
                    getLongestCommonSubsequence(t1, i + 1, t2, j),
                    getLongestCommonSubsequence(t1, i, t2, j + 1));
        }
        return memo[i][j];
    }
}

