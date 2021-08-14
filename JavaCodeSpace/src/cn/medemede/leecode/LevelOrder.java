package cn.medemede.leecode;

import cn.medemede.leecode.modules.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 剑指 Offer 32 - I. 从上到下打印二叉树
 */
public class LevelOrder {
    public int[] levelOrder(TreeNode root) {
        if (root == null) {
            return new int[]{};
        }
        ArrayList<Integer> res = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.pollFirst();
                res.add(node.val);
                if (node.left != null)
                    queue.addLast(node.left);
                if (node.right != null)
                    queue.addLast(node.right);
            }
        }
        int[] resArray = new int[res.size()];
        int j = 0;
        for (int x : res) {
            resArray[j] = x;
            j++;
        }
        return resArray;
    }
}
