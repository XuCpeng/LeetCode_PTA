package cn.medemede.leecode;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 输入字符串str,前缀长度u；输出str前u长中第k短的周期的长度。
 * <p>
 * Input:
 * abcabcabc
 * 4
 * 2 1
 * 6 1
 * 9 2
 * 1 14514
 * <p>
 * Output:
 * 2
 * 3
 * 6
 * -1
 */
public class KthPeriod {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        char[] strChars = str.toCharArray();
        int[] next = getNextVal(strChars);
        int q = in.nextInt();
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < q; i++) {
            queue.clear();
            int u = in.nextInt();
            int k = in.nextInt();
            queue.add(u);
            u--;
            while (u != 0) {
                queue.addFirst(u + 1);
                u = next[u];
            }
            boolean f = true;
            for (int x : queue) {
                if (k == 1) {
                    System.out.println(x);
                    f = false;
                }
                k--;
            }
            if (f) {
                System.out.println(-1);
            }

        }
    }

    private static int[] getNextVal(char[] needleChars) {
        int[] next = new int[needleChars.length];
        next[0] = -1;
        int k = -1;
        int i = 0;
        while (i < needleChars.length - 1) {
            if (k == -1 || needleChars[i] == needleChars[k]) {
                ++i;
                ++k;
                next[i] = k;
            } else {
                k = next[k];
            }
        }
        return next;
    }
}
