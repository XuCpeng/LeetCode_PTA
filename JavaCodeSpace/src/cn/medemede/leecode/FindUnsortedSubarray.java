package cn.medemede.leecode;

/**
 * 581. 最短无序连续子数组
 */
public class FindUnsortedSubarray {
    public int findUnsortedSubarray(int[] nums) {
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        int left = 0;
        int right = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (maxValue > nums[i]) {
                right = i;
            } else {
                maxValue = nums[i];
            }
            if (minValue < nums[n - i - 1]) {
                left = n - i - 1;
            } else {
                minValue = nums[n - i - 1];
            }
        }

        return left == right ? 0 : right - left + 1;

    }
}
