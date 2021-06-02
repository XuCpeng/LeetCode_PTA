package cn.medemede.leecode;

import cn.medemede.leecode.modules.ListNode;

public class IsPalindrome {

    /**
     * 是否为回文串
     *
     * <p>全局p指针从前向后移动，head指针作为递归参数从后向前移动
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        p = head;
        return getIsPalindrome(head.next);
    }

    private ListNode p;

    private boolean getIsPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        if (getIsPalindrome(head.next) && p.val == head.val) {
            p = p.next;
            return true;
        } else {
            return false;
        }
    }

    /**
     * 是否为回文串
     *
     * <p>快慢指针，slow指向中心，将后串翻转，再对比
     *
     * @param head
     * @return
     */
    public boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode q;
        if (fast == null) {
            fast = slow;
        } else {
            fast = slow.next;
        }
        q = fast.next;
        fast.next = null;
        while (q != null) {
            ListNode r = q.next;
            q.next = fast;
            fast = q;
            q = r;
        }
        while (head != slow) {
            if (head.val != fast.val) {
                return false;
            }
            head = head.next;
            fast = fast.next;
        }
        return true;
    }

}

