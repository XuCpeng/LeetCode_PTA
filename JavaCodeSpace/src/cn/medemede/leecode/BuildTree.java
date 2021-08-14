package cn.medemede.leecode;

import cn.medemede.leecode.modules.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class BuildTree {

    /**
     * 前序遍历+中序遍历，构建二叉树
     */
    Map<Integer, Integer> inOrderIndexMap = new HashMap<>();

    private TreeNode getBuildTree(int[] preorder, int preOrderIndex, int inOrderStart, int inOrderEnd) {
        if (inOrderStart >= inOrderEnd) {
            return null;
        }
        int val = preorder[preOrderIndex];
        TreeNode root = new TreeNode(val);
        int inOrderIndex = inOrderIndexMap.get(val);
        root.left = getBuildTree(preorder, preOrderIndex + 1, inOrderStart, inOrderIndex);
        root.right = getBuildTree(preorder, preOrderIndex + (inOrderIndex - inOrderStart) + 1, inOrderIndex + 1, inOrderEnd);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            inOrderIndexMap.put(inorder[i], i);
        }
        return getBuildTree(preorder, 0, 0, inorder.length);
    }

}

