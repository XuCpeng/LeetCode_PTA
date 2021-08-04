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


    /**
     * 输出arr的最长递增子序列。（如果有多个答案，请输出其中 按数值(注：区别于按单个字符的ASCII码值)进行比较的字典序最小的那个）
     */
    public int[] LIS(int[] arr) {
        int[] nums = new int[arr.length];
        int[] lenTop = new int[arr.length];
        nums[0] = 1;
        int maxLen = 0;
        lenTop[maxLen] = arr[0];
        for (int i = 1; i < arr.length; ++i) {
            int left = 0;
            int right = maxLen;
            if (arr[i] > lenTop[maxLen]) {
                ++maxLen;
                nums[i] = maxLen + 1;
                lenTop[maxLen] = arr[i];
            } else {
                // 注意这里 left <= right 而不是 left < right，我们要替换的是第一个比 arr[i] 大的元素
                while (left <= right) {
                    int mid = (right + left) / 2;
                    if (lenTop[mid] > arr[i]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
                lenTop[left] = arr[i];
                nums[i] = left + 1;
            }
        }

        int[] res = new int[maxLen + 1];
        for (int i = nums.length - 1; i >= 0; --i) {
            if (nums[i] == maxLen + 1) {
                res[maxLen] = arr[i];
                --maxLen;
            }
        }
        return res;
    }


}

