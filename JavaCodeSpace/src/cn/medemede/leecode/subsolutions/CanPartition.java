package cn.medemede.leecode.subsolutions;

public class CanPartition {

    /**
     * 背包问题：将数组分割成两个子集，使得两个子集的元素和相等。进行状态压缩
     * <p> 若可分割为两个子集，则子集大小恰好为 count / 2
     * <p> dp数组的大小为 (count / 2) + 1
     * <p> 反向遍历，防止刚刚计算过的结果影响当前结果（因为当前结果需要计算dp[j - nums[i]]）
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int x : nums) {
            sum += x;
        }
        if (sum % 2 != 0) {
            return false;
        }
        sum = sum / 2;

        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int i = 1; i <= nums.length; i++) {
            // 非常重要：此处需要倒序遍历，每个数字只能用一次
            for (int j = sum; j > 0; j--) {
                if (!dp[j] && j - nums[i - 1] >= 0) {
                    dp[j] = dp[j - nums[i - 1]];
                }
            }
        }
        return dp[sum];
    }

    /**
     * 背包问题：将数组分割成两个子集，使得两个子集的元素和相等。未进行状态压缩。
     * <p> 若可分割为两个子集，则子集大小恰好为 count / 2
     * <p> dp数组的大小为 nums.length, (count / 2) + 1
     * <p> 遍历整个数组，相当于依次添加每个物品，dp[i][j]表示前i个物品是否恰好可以装满重量为j的背包
     *
     * @param nums
     * @return
     */
    public boolean canPartition2(int[] nums) {
        int count = 0;
        for (int num : nums) {
            count += num;
        }
        if (count % 2 != 0) {
            return false;
        }
        count = count / 2;

        boolean[][] dp = new boolean[nums.length][count + 1];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= count) {
                //物品i肯定能恰好装满重量为nums[i]的背包
                dp[i][nums[i]] = true;
            }
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j <= count; j++) {
                dp[i][j] = dp[i - 1][j] || (j - nums[i] > 0 && dp[i - 1][j - nums[i]]);
            }
        }

        return dp[nums.length - 1][count];
    }


}

