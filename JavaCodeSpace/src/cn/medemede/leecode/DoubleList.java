package cn.medemede.leecode;

public class DoubleList {
    private final DoubleNode head;
    private final DoubleNode tail;
    private int size;

    public DoubleList() {
        head = new DoubleNode(0, 0);
        tail = new DoubleNode(0, 0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public void addLast(DoubleNode x) {
        tail.prev.next = x;
        x.prev = tail.prev;
        tail.prev = x;
        x.next = tail;
        size++;
    }

    public void remove(DoubleNode x) {
        x.prev.next = x.next;
        x.next.prev = x.prev;
        size--;
    }

    public DoubleNode removeFirst() {
        if (head.next == tail) {
            return null;
        }
        DoubleNode first = head.next;
        remove(first);
        return first;
    }

    public int getSize() {
        return size;
    }

}
