package cn.medemede.leecode;

/**
 * 552. 学生出勤记录 II
 * <p>
 * 动态规划：dp[n + 1][2][3];
 * dp[i][j][k]=x表示：到第i天共缺勤j次，以第i天为结尾（包含第i天）共连续迟到k天的情况有x种。
 */
public class CheckRecord {
    public int checkRecord(int n) {
        int base = 1000000007;
        long[][][] dp = new long[n + 1][2][3];
        dp[0][0][0] = 1;
        for (int i = 1; i <= n; i++) {
            // 第i天为 P
            dp[i][0][0] = dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2];
            dp[i][1][0] = dp[i - 1][1][0] + dp[i - 1][1][1] + dp[i - 1][1][2];

            // 第i天为 L
            dp[i][0][1] = dp[i - 1][0][0];
            dp[i][1][1] = dp[i - 1][1][0];
            dp[i][0][2] = dp[i - 1][0][1];
            dp[i][1][2] = dp[i - 1][1][1];

            // 第i天为 A
            dp[i][1][0] += dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2];

            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] %= base;
                }
            }
        }

        int res = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                res += dp[n][i][j];
                res %= base;
            }
        }

        return res;
    }
}
