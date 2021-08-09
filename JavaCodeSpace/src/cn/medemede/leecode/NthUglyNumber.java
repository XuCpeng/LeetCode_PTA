package cn.medemede.leecode;

import java.util.Arrays;

/**
 * 丑数 II
 */
public class NthUglyNumber {

    // 简化
    public int nthUglyNumber2(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int p2 = 1, p3 = 1, p5 = 1;
        for (int i = 2; i <= n; i++) {
            int num2 = dp[p2] * 2, num3 = dp[p3] * 3, num5 = dp[p5] * 5;
            dp[i] = Math.min(Math.min(num2, num3), num5);
            if (dp[i] == num2) {
                p2++;
            }
            if (dp[i] == num3) {
                p3++;
            }
            if (dp[i] == num5) {
                p5++;
            }
        }
        return dp[n];
    }

    // 可通用
    public int nthUglyNumber(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 1;
        int[] primes = new int[]{2, 3, 5};
        int[] ps = new int[]{1, 1, 1};
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < ps.length; j++) {
                if (dp[i] > dp[ps[j]] * primes[j]) {
                    dp[i] = dp[ps[j]] * primes[j];
                }
            }
            for (int j = 0; j < ps.length; j++) {
                if (dp[i] == dp[ps[j]] * primes[j]) {
                    ps[j]++;
                }
            }
        }
        return dp[n];
    }
}
