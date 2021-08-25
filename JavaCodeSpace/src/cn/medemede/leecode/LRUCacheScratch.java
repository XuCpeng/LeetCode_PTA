package cn.medemede.leecode;

import java.util.HashMap;

class Node {
    int key;
    int val;
    Node next;
    Node prev;

    public Node() {
    }

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
        this.next = null;
        this.prev = null;
    }
}

public class LRUCacheScratch {


    int capacity;
    Node head;
    Node eldest;
    int len;

    HashMap<Integer, Node> map;

    public LRUCacheScratch(int capacity) {
        this.capacity = capacity;
        this.head = new Node();
        this.eldest = new Node();
        head.next = eldest;
        eldest.prev = head;
        this.map = new HashMap<>();
        this.len = 0;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node n = map.get(key);
            swapNodeToHead(n);
            return n.val;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (map.containsKey(key)) {
            Node n = map.get(key);
            n.val = value;
            swapNodeToHead(n);
        } else {
            len++;
            if (len > capacity) {
                removeEldestNode();
            }
            addNewNode(key, value);
        }
    }

    private void addNewNode(int key, int value) {
        Node n = new Node(key, value);
        map.put(key, n);
        n.next = head.next;
        n.prev = head;
        head.next.prev = n;
        head.next = n;
    }

    private void removeEldestNode() {
        map.remove(eldest.prev.key);
        eldest.prev.prev.next = eldest;
        eldest.prev = eldest.prev.prev;
        len--;
    }

    private void swapNodeToHead(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;

        node.next = head.next;
        head.next.prev = node;

        head.next = node;
        node.prev = head;
    }
}
