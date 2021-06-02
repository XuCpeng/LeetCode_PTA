package cn.medemede.leecode;

import java.util.LinkedList;

public class DailyTemperatures {

    /**
     * 单调栈：从顶部添加，被挡住的逐个删除
     * <p>
     * 索引入栈
     *
     * @param T
     * @return
     */
    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = T.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && T[stack.peek()] <= T[i]) {
                stack.poll();
            }
            if (!stack.isEmpty()) {
                res[i] = stack.peek() - i;
            }
            stack.addFirst(i);
        }
        return res;
    }

}

