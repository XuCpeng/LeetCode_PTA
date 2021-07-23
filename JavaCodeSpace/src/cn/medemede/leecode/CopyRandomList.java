package cn.medemede.leecode;

import cn.medemede.leecode.modules.Node;

import java.util.HashMap;

/**
 * 复制带随机指针的链表
 */
public class CopyRandomList {
    HashMap<Node, Node> visited;

    public Node copyRandomList(Node head) {
        visited = new HashMap<>();
        return getCopyNode(head);
    }

    private Node getCopyNode(Node head) {
        if (visited.containsKey(head)) {
            return visited.get(head);
        }
        if (head == null) {
            return null;
        }
        Node newNode = new Node(head.val);
        visited.put(head, newNode);
        if (head.next != null) {
            newNode.next = getCopyNode(head.next);
        }
        if (head.random != null) {
            newNode.random = getCopyNode(head.random);
        }

        return newNode;
    }
}
