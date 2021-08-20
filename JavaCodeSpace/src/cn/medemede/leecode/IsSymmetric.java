package cn.medemede.leecode;

import cn.medemede.leecode.modules.TreeNode;

/**
 * 剑指 Offer 28. 对称的二叉树
 */
public class IsSymmetric {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return checkNode(root.left, root.right);
    }

    private boolean checkNode(TreeNode t1, TreeNode t2) {
        if (t1 == null || t2 == null) {
            return t1 == null && t2 == null;
        }
        if (t1.val != t2.val) {
            return false;
        }
        return checkNode(t1.left, t2.right) && checkNode(t1.right, t2.left);
    }
}
