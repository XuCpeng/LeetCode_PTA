package cn.medemede.leecode;

import cn.medemede.leecode.modules.TreeNode;

import java.util.LinkedList;

public class BTreeTraverse {


    public void preOrderRec(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val);
        preOrderRec(root.left);
        preOrderRec(root.right);
    }

    public void inOrderRec(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderRec(root.left);
        System.out.println(root.val);
        inOrderRec(root.right);
    }

    public void postOrderRec(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrderRec(root.left);
        postOrderRec(root.right);
        System.out.println(root.val);
    }

    public void preOrderNotRec(TreeNode root) {
        TreeNode p = root;
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                System.out.println(p.val);
                stack.addFirst(p);
                p = p.left;
            } else {
                p = stack.pollFirst().right;
            }
        }
    }

    public void inOrderNotRec(TreeNode root) {
        TreeNode p = root;
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.addFirst(p);
                p = p.left;
            } else {
                TreeNode q = stack.pollFirst();
                System.out.println(q.val);
                p = q.right;
            }
        }
    }

    public void postOrderNotRec(TreeNode root) {
        TreeNode p;
        TreeNode prev = null;
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.addFirst(root);
        while (!stack.isEmpty()) {
            p = stack.peekFirst();
            if ((p.left == null && p.right == null) || (prev != null && (prev == p.right || prev == p.left))) {
                System.out.println(p.val);
                stack.pollFirst();
                prev = p;
            } else {
                if (p.right != null) {
                    stack.addFirst(p.right);
                }
                if (p.left != null) {
                    stack.addFirst(p.left);
                }
            }
        }
    }

    public static void main(String[] args) {
        BTreeTraverse bTreeTraverse = new BTreeTraverse();
        TreeNode head = new TreeNode(2);
        TreeNode t2 = new TreeNode(4);
        TreeNode t3 = new TreeNode(5);
        TreeNode t4 = new TreeNode(6);
        TreeNode t5 = new TreeNode(7);
        TreeNode t7 = new TreeNode(8);
        head.left = t2;
        head.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.right = t7;
        bTreeTraverse.postOrderNotRec(head);
        bTreeTraverse.postOrderRec(head);

    }
}
