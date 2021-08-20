package cn.medemede.leecode;

/**
 * 剑指 Offer 剪绳子
 */
public class CuttingRope {
    // 动态规划
    public int cuttingRope(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            // 尝试剪长度为i的绳子
            for (int j = 2; j < i; j++) {
                // 剪下第一段j，剩下的若不剪 j * (i - j)，剩下的若剪 j * dp[i - j])
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }

    // 数学
    public int cuttingRope2(int n) {
        int base = 1000000007;
        long res = 1;
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        if (n == 4) {
            return 4;
        }
        while (n > 4) {
            res *= 3;
            res %= base;
            n -= 3;
        }
        res *= n;
        return (int) (res % base);
    }

    // 数学
    public int cuttingRope3(int n) {
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
