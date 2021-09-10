package cn.medemede.leecode;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * A到B的最小操作次数，每次操作可以加K或者减K，K初始值为1，每次操作后K自增1。
 * <p>
 * 层序遍历
 */
public class AToB {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int diff = Math.abs(a - b);
            int k = 1;
            int res = 0;
            LinkedList<Integer> queue = new LinkedList<>();
            queue.addLast(diff);
            boolean flag = false;
            while (!flag) {
                int n = queue.size();
                for (int j = 0; j < n; j++) {
                    int diffNum = queue.pollFirst();
                    if (diffNum == 0) {
                        System.out.println(res);
                        flag = true;
                        break;
                    }
                    queue.addLast(diffNum - k);
                    queue.addLast(diffNum + k);
                }
                k++;
                res++;
            }
        }
    }
}
