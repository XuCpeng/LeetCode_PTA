package cn.medemede.leecode.subsolutions;

import cn.medemede.leecode.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class FindDuplicateSubtrees {

    /**
     * 寻找重复字树
     * <p>字符串表示二叉树</p>
     */
    HashMap<String, Boolean> subTreeMap = new HashMap<>();
    List<TreeNode> result = new LinkedList<>();

    private String buildSubTree(TreeNode root) {
        if (root == null) {
            return "#";
        }
        String subTree = buildSubTree(root.left) + "," + buildSubTree(root.right) + "," + root.val;
        if (subTreeMap.get(subTree) == null) {
            subTreeMap.put(subTree, true);
        } else if (subTreeMap.get(subTree)) {
            result.add(root);
            subTreeMap.put(subTree, false);
        }
        return subTree;
    }

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        buildSubTree(root);
        return result;
    }

}

