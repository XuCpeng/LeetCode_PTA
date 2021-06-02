package cn.medemede.leecode;

import cn.medemede.leecode.modules.ListNode;

public class HasCycle {

    /**
     * 链表是否有环
     * <p>指向头结点法，把所有已经检查过的节点指向头结点，如果有环的话p.next.next也指向头结点
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        if (head.next == head) {
            return true;
        }
        ListNode p = head.next;
        head.next = head;
        ListNode q;
        while (p.next != null) {
            if (p.next.next == head) {
                return true;
            } else {
                q = p.next;
                p.next = head;
                p = q;
            }
        }
        return false;
    }

    /**
     * 链表是否有环
     * <p>快慢指针法，如果有环的话fast.next.next==slow
     *
     * @param head
     * @return
     */
    public boolean hasCycle2(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }


}

