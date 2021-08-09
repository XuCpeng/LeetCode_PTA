package cn.medemede.leecode;

import java.util.Arrays;

/**
 * 313. 超级丑数
 */
public class NthSuperUglyNumber {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 1;
        int[] ps = new int[primes.length];
        Arrays.fill(ps, 1);
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
