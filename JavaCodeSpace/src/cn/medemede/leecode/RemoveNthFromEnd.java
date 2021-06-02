package cn.medemede.leecode;

import cn.medemede.leecode.modules.ListNode;

public class RemoveNthFromEnd {

    /**
     * 删除链表倒数第n个节点
     * <p>双指针</p>
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode newNode = new ListNode();
        newNode.next = head;
        ListNode left = newNode;
        ListNode right = newNode;
        while (right != null && n > 0) {
            right = right.next;
            n--;
        }
        while (right != null) {
            left = left.next;
            right = right.next;
        }
        left.next = left.next.next;
        return newNode.next;
    }

}

