package cn.medemede.leecode;

import cn.medemede.leecode.modules.TreeNode;

public class InvertTree {

    /**
     * 翻转二叉树
     * <p>后序遍历进行翻转
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        invertTree(root.left);
        invertTree(root.right);
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        return root;
    }

}

