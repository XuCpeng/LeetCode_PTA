package cn.medemede.leecode;

import cn.medemede.leecode.modules.ListNode;

/**
 * 面试题 02.05. 链表求和
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode p = l1;
        ListNode q = l2;
        int push = 0;
        int sum = p.val + q.val + push;
        push = sum / 10;
        p.val = sum % 10;

        while (p.next != null && q.next != null) {
            sum = p.next.val + q.next.val + push;
            push = sum / 10;
            p.next.val = sum % 10;
            p = p.next;
            q = q.next;
        }
        while (p.next != null) {
            sum = p.next.val + push;
            push = sum / 10;
            p.next.val = sum % 10;
            p = p.next;
        }
        while (q.next != null) {
            sum = q.next.val + push;
            push = sum / 10;
            q.next.val = sum % 10;
            p.next = q.next;
            p = p.next;
            q = q.next;
        }
        if (push != 0) {
            p.next = new ListNode(push);
        }
        return l1;
    }
}
