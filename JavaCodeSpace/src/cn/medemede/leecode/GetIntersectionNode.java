package cn.medemede.leecode;

import cn.medemede.leecode.modules.ListNode;

import java.util.HashSet;

/**
 * 两个链表的第一个公共节点
 * <p>
 * 双指针，pA到达链表A的末尾后指向链表B的头节点，pB到达链表B的末尾后指向链表A的头节点，直到pA==pB时返回。
 * 若len(ListA) < len(ListB)，则当pB指向headA时，pA恰好遍历了ListB的len(ListB)-len(ListA)个节点，所以可以同步到达交点或者末尾null。
 */
public class GetIntersectionNode {
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != pB) {
            if (pA == null) {
                pA = headB;
            } else {
                pA = pA.next;
            }
            if (pB == null) {
                pB = headA;
            } else {
                pB = pB.next;
            }
        }
        return pA;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> nodes = new HashSet<>();
        while (headA != null || headB != null) {
            if (headA != null) {
                if (nodes.contains(headA)) {
                    return headA;
                } else {
                    nodes.add(headA);
                }
                headA = headA.next;
            }
            if (headB != null) {
                if (nodes.contains(headB)) {
                    return headB;
                } else {
                    nodes.add(headB);
                }
                headB = headB.next;
            }
        }
        return null;
    }
}
