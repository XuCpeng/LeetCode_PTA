package cn.medemede.leecode;

import cn.medemede.leecode.modules.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LevelOrder2 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        int i = 0;
        while (!queue.isEmpty()) {
            int n = queue.size();
            LinkedList<Integer> list = new LinkedList<>();
            boolean flag = i % 2 == 0;
            for (int j = 0; j < n; j++) {
                TreeNode node = queue.pollFirst();
                if (flag) {
                    list.addLast(node.val);
                } else {
                    list.addFirst(node.val);
                }
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }
            res.add(list);
            i++;
        }
        return res;
    }
}
