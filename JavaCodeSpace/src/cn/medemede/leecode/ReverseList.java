package cn.medemede.leecode;

import cn.medemede.leecode.modules.ListNode;

public class ReverseList {

    /**
     * 翻转链表
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode nex = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nex;
        }
        return pre;
    }

    ListNode successor = null;

    public ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }
        ListNode newHead = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return newHead;
    }

    public ListNode reverseBetween2(ListNode head, int left, int right) {
        if (left == 1) {
            return reverseN(head, right - left + 1);
        }
        int i = 1;
        ListNode p = head;
        while (i < left - 1) {
            i++;
            p = p.next;
        }
        p.next = reverseN(p.next, right - left + 1);
        return head;
    }

    private ListNode getReverseBetween(ListNode node, ListNode q) {
        if (node == q) {
            return node;
        }
        getReverseBetween(node.next, q).next = node;
        return node;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (right - left + 1 < 2) {
            return head;
        }

        int i = 0;
        ListNode newHead = new ListNode();
        newHead.next = head;
        ListNode p = newHead;

        while (i < left - 1) {
            i++;
            p = p.next;
        }
        ListNode q = p;
        while (i < right) {
            q = q.next;
            i++;
        }
        ListNode r = q.next;
        getReverseBetween(p.next, q).next = r;
        p.next = q;
        return newHead.next;
    }

    public ListNode reverseN2(ListNode head, int n, int k) {
        if (n == 1) {
            successor = reverseN2(head.next, k, k);
            return head;
        }
        ListNode newHead = reverseN2(head.next, n - 1, k);
        head.next.next = head;
        head.next = successor;
        return newHead;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode newHead = new ListNode();
        newHead.next = head;
        ListNode h = newHead, p, q, r;
        int nums = 0;
        while (h.next != null) {
            nums++;
            h = h.next;
        }
        h = newHead;

        for (int j = 0; j < nums / k; j++) {
            p = h.next;
            q = p.next;
            for (int i = 0; i < k - 1 && q != null; i++) {
                r = q.next;
                q.next = p;
                p = q;
                q = r;
            }
            h.next.next = q;
            q = h.next;
            h.next = p;
            h = q;
        }
        return newHead.next;
    }

    private ListNode reverseKGroup2(ListNode head, int k) {
        ListNode p, q, r;
        p = head;
        for (int i = 0; i < k; i++) {
            if (p == null) {
                return head;
            }
            p = p.next;
        }
        p = head;
        q = p.next;
        for (int i = 0; i < k - 1 && q != null; i++) {
            r = q.next;
            q.next = p;
            p = q;
            q = r;
        }
        head.next = reverseKGroup2(q, k);
        return p;
    }


}

