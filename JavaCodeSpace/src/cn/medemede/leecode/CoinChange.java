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
            // 非常重要：此处不能倒序遍历，因为每种硬币数量是无限的，所以可重复使用
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    /**
     * 零钱兑换
     * <p>给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
     * <p>动态规划，正向dp数组</p>
     *
     * @param amount
     * @param coins
     * @return
     */
    public int coinChange2(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            // 非常重要：此处不能倒序遍历，因为每种硬币数量是无限的，所以可重复使用
            for (int j = 1; j <= amount; j++) {
                if (j - coin >= 0) {
                    dp[j] += dp[j - coin];
                }
            }
        }
        return dp[amount];
    }
}

