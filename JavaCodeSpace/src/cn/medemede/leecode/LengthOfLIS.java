package cn.medemede.leecode;

public class LengthOfLIS {

    /**
     * 最长递增子序列
     * <p>动态规划</p>
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int max = 0;
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            // 非常重要：此处需要倒序遍历，j>=0
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            dp[i]++;
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 最长递增子序列
     * <p>二分查找，耐心排序，扑克牌堆</p>
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] top = new int[nums.length];
        top[0] = nums[0];
        int p = 1;
        for (int x : nums) {
            int left = 0;
            int right = p - 1;
            while (left < right) {
                int mid = (left + right) / 2;
                if (top[mid] >= x) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            if (top[right] >= x) {
                top[right] = x;
            } else {
                top[p] = x;
                p++;
            }
        }
        return p;
    }


}

