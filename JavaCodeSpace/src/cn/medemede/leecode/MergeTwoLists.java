package cn.medemede.leecode;

import cn.medemede.leecode.modules.ListNode;

public class MergeTwoLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode p = l1;
        ListNode q = l2;
        ListNode head = new ListNode();
        ListNode r = head;
        while (p != null && q != null) {
            if (p.val < q.val) {
                r.next = p;
                p = p.next;
            } else {
                r.next = q;
                q = q.next;
            }
            r = r.next;
        }
        while (p != null) {
            r.next = p;
            r = r.next;
            p = p.next;
        }
        while (q != null) {
            r.next = q;
            r = r.next;
            q = q.next;
        }
        return head.next;
    }
}
