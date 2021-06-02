package cn.medemede.leecode.subsolutions;

import cn.medemede.leecode.Node;

public class Connect {

    /**
     * 连接二叉树的后继节点
     * <p> 使用辅助函数同时连接两个子树
     *
     * @param node1
     * @param node2
     */
    private void getConnect(Node node1, Node node2) {
        if (node1 == null) {
            return;
        }
        node1.next = node2;
        getConnect(node1.left, node1.right);
        getConnect(node2.left, node2.right);
        getConnect(node1.right, node2.left);
    }

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        getConnect(root.left, root.right);
        return root;
    }

}

