package cn.medemede.leecode.subsolutions;

import cn.medemede.leecode.TreeNode;

public class Rob {

    /**
     * 打家劫舍，相连房间触发警报
     * <p>
     * dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length < 2) {
            return nums[0];
        }

        int a = nums[0];
        int b = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int tmp = b;
            b = Math.max(b, a + nums[i]);
            a = tmp;
        }
        return b;
    }

    /**
     * 打家劫舍 II，环形
     *
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length < 2) {
            return nums[0];
        }
        int res;

        int a = nums[0];
        int b = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length - 1; i++) {
            int tmp = b;
            b = Math.max(b, a + nums[i]);
            a = tmp;
        }
        res = b;

        a = 0;
        b = nums[1];
        for (int i = 2; i < nums.length; i++) {
            int tmp = b;
            b = Math.max(b, a + nums[i]);
            a = tmp;
        }
        res = Math.max(b, res);

        return res;
    }

    /**
     * 打家劫舍 Ⅲ，树形
     * <p>返回二元组，表示选择与非选择</p>
     */
    public int rob3(TreeNode root) {
        int[] res = getRob3(root);
        return Math.max(res[0], res[1]);
    }

    private int[] getRob3(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] a = getRob3(node.left);
        int[] b = getRob3(node.right);
        return new int[]{Math.max(a[0], a[1]) + Math.max(b[0], b[1]), node.val + a[0] + b[0]};
    }

}

