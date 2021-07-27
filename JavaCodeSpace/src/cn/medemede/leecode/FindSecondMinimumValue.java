package cn.medemede.leecode;

import cn.medemede.leecode.modules.TreeNode;

/**
 * 二叉树中第二小的节点
 */
public class FindSecondMinimumValue {

    public int findSecondMinimumValue2(TreeNode root) {
        long res = getSecondByNode(root);
        return res == Long.MAX_VALUE ? -1 : (int) res;
    }

    private long getSecondByNode(TreeNode root) {
        if (root.left == null) {
            return Long.MAX_VALUE;
        }
        if (root.left.val == root.right.val) {
            return Math.min(getSecondByNode(root.right), getSecondByNode(root.left));
        }
        if (root.left.val == root.val) {
            return Math.min(root.right.val, getSecondByNode(root.left));
        } else {
            return Math.min(root.left.val, getSecondByNode(root.right));
        }
    }

    public int findSecondMinimumValue(TreeNode root) {
        if (root.left == null) {
            return -1;
        }
        if (root.left.val == root.right.val) {
            int a = findSecondMinimumValue(root.right);
            int b = findSecondMinimumValue(root.left);
            if (a == -1) {
                return b;
            }
            if (b == -1) {
                return a;
            }
            return Math.min(a, b);
        }
        if (root.left.val == root.val) {
            int a = findSecondMinimumValue(root.left);
            if (a == -1) {
                return root.right.val;
            }
            return Math.min(root.right.val, a);
        } else {
            int b = findSecondMinimumValue(root.right);
            if (b == -1) {
                return root.left.val;
            }
            return Math.min(root.left.val, b);
        }
    }
}
