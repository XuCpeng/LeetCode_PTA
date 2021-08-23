package cn.medemede.leecode;

/**
 * 1646. 获取生成数组中的最大值
 */
public class GetMaximumGenerated {
    public int getMaximumGenerated(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        int[] nums = new int[n + 1];
        nums[0] = 0;
        nums[1] = 1;
        int max = 1;
        // 遍历一半即可全部填充完
        for (int i = 1; i < n / 2 + 1; i++) {
            // 偶数位
            if (2 * i <= n) {
                nums[2 * i] = nums[i];
            }
            // 奇数位，最大值一定在奇数位
            if (2 * i + 1 <= n) {
                nums[2 * i + 1] = nums[i] + nums[i + 1];
                max = Math.max(max, nums[2 * i + 1]);
            }
        }
        return max;
    }
}
