package cn.medemede.leecode.subsolutions;

import cn.medemede.leecode.TreeNode;

public class DeleteNode {

    /**
     * @param root
     * @param parent
     * @param leftOrRight
     * @param key
     */
    private void getDeleteNode(TreeNode root, TreeNode parent, int leftOrRight, int key) {
        if (root == null) {
            return;
        }
        if (root.val == key) {
            if (root.left != null || root.right != null) {
                TreeNode tmp;
                parent = root;
                if (root.right != null) {
                    tmp = root.right;
                    leftOrRight = 2;
                    while (tmp.left != null) {
                        parent = tmp;
                        tmp = tmp.left;
                        leftOrRight = 1;
                    }
                } else {
                    tmp = root.left;
                    leftOrRight = 1;
                    while (tmp.right != null) {
                        parent = tmp;
                        tmp = tmp.right;
                        leftOrRight = 2;
                    }
                }
                root.val = tmp.val;
            }
            if (leftOrRight == 1) {
                if (parent.left.right != null) {
                    parent.left = parent.left.right;
                } else {
                    parent.left = parent.left.left;
                }
            } else {
                if (parent.right.left != null) {
                    parent.right = parent.right.left;
                } else {
                    parent.right = parent.right.right;
                }
            }
        } else if (root.val > key) {
            getDeleteNode(root.left, root, 1, key);
        } else {
            getDeleteNode(root.right, root, 2, key);
        }
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode newHead = new TreeNode();
        newHead.left = root;
        getDeleteNode(root, newHead, 1, key);
        return newHead.left;
    }

    TreeNode deleteNode2(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            TreeNode minNode = root.right;
            while (minNode.left != null) {
                minNode = minNode.left;
            }
            root.val = minNode.val;
            root.right = deleteNode2(root.right, minNode.val);
        } else if (root.val > key) {
            root.left = deleteNode2(root.left, key);
        } else {
            root.right = deleteNode2(root.right, key);
        }
        return root;
    }


}

