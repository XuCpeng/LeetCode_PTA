package cn.medemede.leecode;

import cn.medemede.leecode.modules.ListNode;

public class DeleteDuplicates {

    /**
     * 移除有序链表重复元素
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode p = head;
        while (p != null && p.next != null) {
            if (p.val == p.next.val) {
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        return head;
    }

}

