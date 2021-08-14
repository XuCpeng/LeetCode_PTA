package cn.medemede.leecode;

/**
 * 剑指 Offer 14- I. 剪绳子
 */
public class CuttingRope {
    // 动态规划
    public int cuttingRope(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 2; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }

    // 数学
    public int cuttingRope2(int n) {
        if (n < 4) {
            return n - 1;
        }
        int b = n - (n / 3) * 3;
        if (b == 0) {
            return (int) Math.pow(3, n / 3);
        }
        if (b == 1) {
            return (int) Math.pow(3, n / 3 - 1) * 4;
        }
        return (int) Math.pow(3, n / 3) * 2;
    }
}
