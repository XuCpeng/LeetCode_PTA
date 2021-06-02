package cn.medemede.leecode;

import cn.medemede.leecode.modules.ListNode;

import java.util.Random;

public class LinkedListRandomNode {

    ListNode list;
    Random r;

    /**
     * 链表随机节点
     * <p>取一个：当你遇到第 i 个元素时，应该有 1/i 的概率选择该元素，1 - 1/i 的概率保持原有的选择</p>
     * <p>取k个：当你遇到第 i 个元素时，应该有 k/i 的概率选择该元素，1 - k/i 的概率保持原有的选择</p>
     *
     * @param head The linked list's head.
     *             Note that the head is guaranteed to be not null, so it contains at least one node.
     */
    public LinkedListRandomNode(ListNode head) {
        this.list = head;
        this.r = new Random();
    }

    /**
     * Returns a random node's value.
     */
    public int getRandom() {
        int res = 0;
        int i = 0;
        ListNode p = list;
        // while 循环遍历链表
        while (p != null) {
            // 生成一个 [0, i) 之间的整数
            // 这个整数等于 0 的概率就是 1/i
            if (r.nextInt(++i) == 0) {
                res = p.val;
            }
            p = p.next;
        }
        return res;
    }

    /* 返回链表中 k 个随机节点的值 */
    int[] getRandom(int k) {
        int[] res = new int[k];
        ListNode p = list;

        // 前 k 个元素先默认选上
        for (int j = 0; j < k && p != null; j++) {
            res[j] = p.val;
            p = p.next;
        }

        int i = k;
        // while 循环遍历链表
        while (p != null) {
            // 生成一个 [0, i) 之间的整数
            int j = r.nextInt(++i);
            // 这个整数小于 k 的概率就是 k/i
            if (j < k) {
                res[j] = p.val;
            }
            p = p.next;
        }
        return res;
    }
}
