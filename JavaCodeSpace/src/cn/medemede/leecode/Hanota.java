package cn.medemede.leecode;

import java.util.List;

/**
 * 面试题 08.06. 汉诺塔问题
 * <p>
 * A->C
 */
public class Hanota {
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        move(A.size(), A, B, C);
    }

    private void move(int n, List<Integer> A, List<Integer> B, List<Integer> C) {

        if (n == 1) {
            // 只剩一个盘子时，直接将它从第一个柱子移动到第三个柱子
            C.add(A.remove(A.size() - 1));
        } else {
            // 首先将 n-1 个盘子，从第一个柱子移动到第二个柱子
            move(n - 1, A, C, B);
            // 将最后一个盘子移动到第三个柱子上
            C.add(A.remove(A.size() - 1));
            // 最后将第二个柱子上的 n-1 个盘子，移动到第三个柱子上
            move(n - 1, B, A, C);
        }
    }
}
