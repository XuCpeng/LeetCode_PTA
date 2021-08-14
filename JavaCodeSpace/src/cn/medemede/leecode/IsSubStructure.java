package cn.medemede.leecode;

import cn.medemede.leecode.modules.TreeNode;

/**
 * 剑指 Offer 26. 树的子结构
 */
public class IsSubStructure {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null) {
            return false;
        }
        return isSubStructureBySameHead(A, B) || (A != null && (isSubStructure(A.left, B) || isSubStructure(A.right, B)));
    }

    private boolean isSubStructureBySameHead(TreeNode T, TreeNode B) {
        if (T == null || B == null) {
            return B == null;
        }
        if (T.val != B.val) {
            return false;
        }
        return isSubStructureBySameHead(T.left, B.left) && isSubStructureBySameHead(T.right, B.right);
    }
}
