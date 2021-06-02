package cn.medemede.leecode.subsolutions;

import cn.medemede.leecode.TreeNode;

public class Flatten {

    /**
     * 将二叉树展开为右叉树
     * <p>先展开左子树，并返回左子树的右下节点作为右子树的根节点
     *
     * @param root
     * @return
     */
    private TreeNode getFlatten(TreeNode root) {
        TreeNode left = root.left;
        TreeNode right = root.right;
        if (left != null) {
            root.right = left;
            root.left = null;
            root = getFlatten(left);
        }
        if (right != null) {
            root.right = right;
            return getFlatten(right);
        } else {
            return root;
        }
    }

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        getFlatten(root);
    }

}

