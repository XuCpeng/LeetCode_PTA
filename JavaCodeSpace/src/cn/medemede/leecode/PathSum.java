package cn.medemede.leecode;

import cn.medemede.leecode.modules.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 34. 二叉树中和为某一值的路径
 */
public class PathSum {
    List<List<Integer>> res;

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        dfsPath(root, new ArrayList<>(), 0, target);
        return res;
    }


    private void dfsPath(TreeNode root, List<Integer> path, int sum, int target) {

        sum += root.val;
        path.add(root.val);

        if (root.left == null && root.right == null && sum == target) {
            res.add(new ArrayList<>(path));
        }
        if (root.left != null) {
            dfsPath(root.left, path, sum, target);
        }

        if (root.right != null) {
            dfsPath(root.right, path, sum, target);
        }

        path.remove(path.size() - 1);
    }
}
