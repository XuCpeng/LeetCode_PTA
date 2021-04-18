package cn.medemede.leecode;

import java.util.LinkedList;

/**
 * 用栈实现队列
 * <p>双栈，底部相连，前面的栈作为中转，后面的栈存储，仅当前面的栈为空时进行转移操作。
 */
public class QueueUsingStacks {
    LinkedList<Integer> s1;
    LinkedList<Integer> s2;

    /**
     * Initialize your data structure here.
     */
    public QueueUsingStacks() {
        s1 = new LinkedList<>();
        s2 = new LinkedList<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        s1.addLast(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        peek();
        return s2.pollFirst();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.addFirst(s1.pollLast());
            }
        }
        return s2.peekFirst();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}
