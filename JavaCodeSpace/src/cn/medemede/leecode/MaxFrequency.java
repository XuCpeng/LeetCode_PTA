package cn.medemede.leecode;

import java.util.Arrays;

/**
 * 最高频元素的频数
 * <p>
 * 滑动窗口：
 * 每次右移一位右边界，增加(right - left) * (nums[right] - nums[right -1])；
 * 右移一位左边界，减小 nums[right] - nums[left]。
 */
public class MaxFrequency {

    public int maxFrequency2(int[] nums, int k) {
        Arrays.sort(nums);
        int l = 0;
        int maxRes = 1;
        long total = 0;
        for (int i = 1; i < nums.length; i++) {
            total += (long) (nums[i] - nums[i - 1]) * (i - l);
            while (total > k) {
                total -= nums[i] - nums[l];
                l++;
            }
            maxRes = Math.max(maxRes, i - l + 1);
        }
        return maxRes;
    }

    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int i = nums.length - 1;
        int maxRes = 0;
        while (i >= 0) {
            int num = 0;
            int j = i - 1;
            while (j >= 0 && nums[j] == nums[i]) {
                j--;
            }
            num += i - j;
            int p = j;
            int step = k;
            while (p >= 0 && step > 0) {
                step = step - (nums[i] - nums[p]);
                if (step >= 0) {
                    num++;
                }
                p--;
            }
            i = j;
            maxRes = Math.max(maxRes, num);
        }

        return maxRes;
    }

    public static void main(String[] args) {
        MaxFrequency maxFrequency = new MaxFrequency();
        System.out.println(maxFrequency.maxFrequency(new int[]{1, 2, 4}, 5));
    }
}
