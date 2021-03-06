package cn.medemede.leecode;

import cn.medemede.leecode.modules.TreeNode;

public class IsValidBST {

    /**
     * 二叉查找树中序遍历为升序
     */
    TreeNode pre = null;

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST(root.left)) {
            return false;
        }
        if (pre != null && root.val <= pre.val) {
            return false;
        }
        pre = root;
        return isValidBST(root.right);
    }

    /**
     * @param root
     * @return
     */
    public boolean isValidBSTFf(TreeNode root) {
        return root == null ||
                (root.left != null ? root.left.val < root.val && isValidBSTFf(root.left)
                        : root.right == null || (root.right.val > root.val && isValidBSTFf(root.right)));
    }

    private boolean getIsValidBST(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }
        if ((min != null && root.val <= min) || (max != null && root.val >= max)) {
            return false;
        }
        return getIsValidBST(root.left, min, root.val) && getIsValidBST(root.right, root.val, max);
    }

    public boolean isValidBST2(TreeNode root) {
        return getIsValidBST(root, null, null);
    }


}

