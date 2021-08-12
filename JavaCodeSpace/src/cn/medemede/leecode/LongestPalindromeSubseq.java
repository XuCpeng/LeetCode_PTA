package cn.medemede.leecode;

public class LongestPalindromeSubseq {

    /**
     * 最长回文子序列
     * <p>动态规划，递归</p>
     *
     * @param s
     * @return
     */
    int[][] memo;

    public int longestPalindromeSubseq(String s) {
        memo = new int[s.length()][s.length()];
        return getLongestPalindromeSubseq(s.toCharArray(), 0, s.length() - 1);
    }

    private int getLongestPalindromeSubseq(char[] s, int i, int j) {
        if (i > j) {
            return 0;
        }
        if (i == j) {
            return 1;
        }
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        if (s[i] == s[j]) {
            memo[i][j] = getLongestPalindromeSubseq(s, i + 1, j - 1) + 2;
        } else {
            memo[i][j] = Math.max(getLongestPalindromeSubseq(s, i + 1, j), getLongestPalindromeSubseq(s, i, j - 1));
        }
        return memo[i][j];
    }

    /**
     * 最长回文子序列
     * <p>动态规划，非递归，dp数组</p>
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq2(String s) {
        char[] chars = s.toCharArray();
        int[][] dp = new int[chars.length][chars.length];
        for (int i = 0; i < chars.length; i++) {
            dp[i][i] = 1;
        }
        for (int i = chars.length - 1; i >= 0; i--) {
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[i] == chars[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][chars.length - 1];
    }

}

