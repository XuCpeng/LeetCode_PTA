package cn.medemede.leecode;

import cn.medemede.leecode.modules.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的垂序遍历
 */
public class VerticalTraversal {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        ArrayList<int[]> nodes = new ArrayList<>();
        dfs(root, 0, 0, nodes);
        nodes.sort((a, b) -> a[1] == b[1] ? a[0] == b[0] ? a[2] - b[2] : a[0] - b[0] : a[1] - b[1]);
        int col = Integer.MAX_VALUE;
        List<List<Integer>> res = new ArrayList<>();
        for (int[] x : nodes) {
            if (col == Integer.MAX_VALUE || col != x[1]) {
                col = x[1];
                res.add(new ArrayList<>());
            }
            res.get(res.size() - 1).add(x[2]);
        }
        return res;
    }

    private void dfs(TreeNode root, int row, int col, ArrayList<int[]> nodes) {
        if (root == null) {
            return;
        }
        nodes.add(new int[]{row, col, root.val});
        dfs(root.left, row + 1, col - 1, nodes);
        dfs(root.right, row + 1, col + 1, nodes);
    }
}
