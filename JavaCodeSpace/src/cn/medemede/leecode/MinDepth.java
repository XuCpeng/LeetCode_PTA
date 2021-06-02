package cn.medemede.leecode;

import cn.medemede.leecode.modules.TreeNode;

import java.util.LinkedList;

public class MinDepth {

    /**
     * 二叉树最小深度
     * <p>广度优先遍历BFS，遇到叶子返回结果</p>
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        TreeNode last = root;
        while (!queue.isEmpty()) {
            TreeNode tmp = queue.pollFirst();
            if (tmp.left == null && tmp.right == null) {
                res++;
                return res;
            }
            if (tmp.left != null) {
                queue.addLast(tmp.left);
            }
            if (tmp.right != null) {
                queue.addLast(tmp.right);
            }
            if (tmp == last) {
                res++;
                last = queue.peekLast();
            }
        }
        return res;
    }


}

