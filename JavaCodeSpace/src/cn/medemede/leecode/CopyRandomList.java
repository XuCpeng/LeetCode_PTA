package cn.medemede.leecode;

import cn.medemede.leecode.modules.Node;

import java.util.HashMap;

/**
 * 复制带随机指针的链表
 */
public class CopyRandomList {
    HashMap<Node, Node> visited = new HashMap<>();

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        if (visited.containsKey(head)) {
            return visited.get(head);
        }
        Node newNode = new Node(head.val);
        visited.put(head, newNode);
        newNode.next = copyRandomList(head.next);
        newNode.random = copyRandomList(head.random);
        return newNode;
    }
}
