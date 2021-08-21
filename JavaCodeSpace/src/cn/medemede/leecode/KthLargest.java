package cn.medemede.leecode;

import cn.medemede.leecode.modules.TreeNode;

/**
 * 剑指 Offer 54. 二叉搜索树的第k大节点
 */
public class KthLargest {
    int k;
    int res;

    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return res;
    }

    private void dfs(TreeNode root) {

        // 也可将k==0，放到dfs(root.left)的判断中，条件为k>0或k!=0
        if (root == null || k == 0) {
            return;
        }
        dfs(root.right);
        if (k == 1) {
            res = root.val;
        }
        k--; // k=0, 递归返回时k!=1,所以不会再更新res，且左子树遍历被短路
        dfs(root.left);
    }
}
