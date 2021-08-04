package cn.medemede.leecode;

public class CoinChange {

    /**
     * 零钱兑换
     * <p>计算可以凑成总金额所需的最少的硬币个数,每种硬币的数量是无限的</p>
     * <p>动态规划，正向dp数组</p>
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i < amount + 1; i++) {
            dp[i] = amount + 1;
        }
        for (int i = 1; i < amount + 1; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}

