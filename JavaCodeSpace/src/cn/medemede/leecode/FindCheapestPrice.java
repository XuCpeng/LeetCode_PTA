package cn.medemede.leecode;

import java.util.Arrays;

/**
 * 787. K 站中转内最便宜的航班
 */
public class FindCheapestPrice {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[][] dp = new int[k + 2][n];
        for (int[] x : dp) {
            Arrays.fill(x, Integer.MAX_VALUE);
        }
        dp[0][src] = 0;

        for (int i = 1; i < k + 2; i++) {
            for (int[] flight : flights) {
                if (dp[i - 1][flight[0]] != Integer.MAX_VALUE) {
                    dp[i][flight[1]] = Math.min(dp[i][flight[1]], dp[i - 1][flight[0]] + flight[2]);
                }
            }
        }
        int minPrice = Integer.MAX_VALUE;
        for (int i = 0; i < k + 2; i++) {
            minPrice = Math.min(minPrice, dp[i][dst]);
        }
        return minPrice == Integer.MAX_VALUE ? -1 : minPrice;
    }
}