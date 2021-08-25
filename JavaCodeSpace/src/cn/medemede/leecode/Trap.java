package cn.medemede.leecode;

public class Trap {

    /**
     * 接雨水
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int[] dp = new int[height.length];
        int left = 0;
        int right = 0;

        // 记录左边最高挡板
        for (int i = 0; i < height.length; i++) {
            dp[i] = left;
            left = Math.max(left, height[i]);
        }

        int res = 0;
        // 通过右挡板计算水面高度
        for (int i = height.length - 1; i >= 0; i--) {
            int x = Math.min(dp[i], right) - height[i];
            res += Math.max(x, 0);
            right = Math.max(right, height[i]);
        }
        return res;
    }

    /**
     * 接雨水
     * <p>双指针</p>
     *
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        int res = 0;
        int left = 0;
        int leftMax = 0;
        int rightMax = 0;
        int right = height.length - 1;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (leftMax < rightMax) {
                res += leftMax - height[left];
                left++;
            } else {
                res += rightMax - height[right];
                right--;
            }
        }
        return res;
    }


}

