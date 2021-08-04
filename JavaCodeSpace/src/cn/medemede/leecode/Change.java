package cn.medemede.leecode;

/**
 * 零钱兑换 II
 * <p>
 * 硬币组合数，coins不重复
 */
public class Change {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        // 每次加入一枚不同面额的硬币，更新所有金额的组合数
        for (int coin : coins) {
            for (int j = 1; j <= amount; j++) {
                if (j - coin >= 0) {
                    dp[j] += dp[j - coin];
                }
            }
        }
        return dp[amount];
    }
}
