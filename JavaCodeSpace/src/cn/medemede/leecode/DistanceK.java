package cn.medemede.leecode;

import cn.medemede.leecode.modules.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 二叉树中所有距离为 K 的结点
 */
public class DistanceK {
    Set<Integer> res;
    int k;

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        this.k = k;
        res = new HashSet<>();
        getAfterNode(target, k);
        getBeforeNode(root, target);
        if (k != 0)
            res.remove(target.val);
        return new ArrayList<>(res);
    }

    private int getBeforeNode(TreeNode root, TreeNode target) {
        if (root == null) {
            return -1;
        }
        if (root == target) {
            return 0;
        }
        int a = getBeforeNode(root.left, target);
        int b = getBeforeNode(root.right, target);
        if (a == b && a == -1) {
            return -1;
        }
        int distantarget = (a == -1 ? b : a) + 1;
        if (distantarget == k) {
            res.add(root.val);
        }
        if (a == -1) {
            getAfterNode(root.left, k - distantarget - 1);
        } else {
            getAfterNode(root.right, k - distantarget - 1);
        }

        return distantarget;
    }

    private void getAfterNode(TreeNode root, int dis) {
        if (root == null) {
            return;
        }
        if (dis < 0) {
            return;
        }
        if (dis == 0) {
            res.add(root.val);
            return;
        }
        getAfterNode(root.left, dis - 1);
        getAfterNode(root.right, dis - 1);
    }
}
