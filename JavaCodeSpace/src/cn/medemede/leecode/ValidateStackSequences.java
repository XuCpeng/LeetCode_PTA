package cn.medemede.leecode;

import java.util.LinkedList;

/**
 * 剑指 Offer 31. 栈的压入、弹出序列
 */
public class ValidateStackSequences {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        LinkedList<Integer> stack = new LinkedList<>();
        int p = 0;
        int q = 0;
        int n = pushed.length;
        while (p < n && q < n) {
            stack.addFirst(pushed[p]);
            while (!stack.isEmpty() && stack.peekFirst().equals(popped[q])) {
                stack.pollFirst();
                q++;
            }
            p++;
        }
        return p == n && q == n;
    }
}
