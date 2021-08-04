package cn.medemede.leecode;

import cn.medemede.leecode.modules.TreeNode;

/**
 * 剑指 Offer 68 - II. 二叉树的最近公共祖先
 */
public class LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        return left == null ? right : (right == null ? left : root);
    }
}
