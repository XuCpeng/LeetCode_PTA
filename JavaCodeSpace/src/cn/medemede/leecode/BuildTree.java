package cn.medemede.leecode;

import cn.medemede.leecode.modules.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class BuildTree {

    /**
     *
     */
    Map<Integer, Integer> indexHash = new HashMap<>();

    private TreeNode getBuildTree(int[] preorder, int index, int inStart, int inEnd) {
        if (inStart >= inEnd) {
            return null;
        }
        int val = preorder[index];
        TreeNode root = new TreeNode(val);
        int i = indexHash.get(val);
        root.left = getBuildTree(preorder, index + 1, inStart, i);
        root.right = getBuildTree(preorder, index + (i - inStart) + 1, i + 1, inEnd);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        for (int i = 0; i < inorder.length; i++) {
            indexHash.put(inorder[i], i);
        }
        return getBuildTree(preorder, 0, 0, inorder.length);
    }

}

