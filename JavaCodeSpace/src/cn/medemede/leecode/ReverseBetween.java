package cn.medemede.leecode;

import cn.medemede.leecode.modules.ListNode;

import java.util.LinkedList;

/**
 * 反转链表 2
 */
public class ReverseBetween {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode newHead = new ListNode();
        newHead.next = head;
        ListNode p = newHead;
        LinkedList<ListNode> stack = new LinkedList<>();
        int i = 1;
        for (; i < left && p != null; i++) {
            p = p.next;
        }
        ListNode prev = p;
        if (p == null) {
            return head;
        }
        p = p.next;
        for (; i <= right && p != null; i++) {
            stack.addFirst(p);
            p = p.next;
        }
        ListNode after = p;
        p = prev;
        while (!stack.isEmpty()) {
            p.next = stack.pollFirst();
            p = p.next;
        }
        p.next = after;
        return newHead.next;
    }
}
