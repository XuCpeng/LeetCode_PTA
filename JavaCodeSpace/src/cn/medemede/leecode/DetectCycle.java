package cn.medemede.leecode;

import cn.medemede.leecode.modules.ListNode;

public class DetectCycle {

    /**
     * 链表找环，返回环的起点
     * <p>快慢指针法，如果有环的话fast.next.next==slow
     * <p>快慢指针相遇后，将慢针回拨至起点，再次相遇时即为环的起点。
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                slow = head;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
        return null;
    }


}

