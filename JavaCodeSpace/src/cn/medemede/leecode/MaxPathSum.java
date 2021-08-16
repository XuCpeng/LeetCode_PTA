package cn.medemede.leecode;

import cn.medemede.leecode.modules.TreeNode;

/**
 * 124. 二叉树中的最大路径和
 */
public class MaxPathSum {
    int res;

    public int maxPathSum(TreeNode root) {
        res = Integer.MIN_VALUE;
        getMaxSum(root);
        return res;
    }

    private int getMaxSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getMaxSum(root.left);
        int right = getMaxSum(root.right);

        int val = root.val;
        val = Math.max(val, val + left);
        val = Math.max(val, val + right);
        res = Math.max(res, val);

        return Math.max(root.val, Math.max(left, right) + root.val);
    }
}
