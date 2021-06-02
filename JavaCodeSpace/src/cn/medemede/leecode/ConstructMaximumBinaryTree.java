package cn.medemede.leecode;

import cn.medemede.leecode.modules.TreeNode;

public class ConstructMaximumBinaryTree {

    /**
     * 构造最大二叉树
     *
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private TreeNode getConstructMaximumBinaryTree(int[] nums, int start, int end) {
        if (start >= end) {
            return null;
        }
        int index = start;
        int max = nums[start];
        for (int i = start + 1; i < end; i++) {
            if (nums[i] > max) {
                index = i;
                max = nums[i];
            }
        }
        TreeNode root = new TreeNode(max);
        root.left = getConstructMaximumBinaryTree(nums, start, index);
        root.right = getConstructMaximumBinaryTree(nums, index + 1, end);
        return root;
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return getConstructMaximumBinaryTree(nums, 0, nums.length);
    }

}

