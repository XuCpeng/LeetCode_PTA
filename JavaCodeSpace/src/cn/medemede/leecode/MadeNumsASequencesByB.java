package cn.medemede.leecode;

import java.util.Scanner;

/**
 * Ai可以替换为Bi，求使得数组A严格递增的最小替换次数
 */
public class MadeNumsASequencesByB {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] numsA = new int[n];
        int[] numsB = new int[n];
        for (int i = 0; i < n; i++) {
            numsA[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            numsB[i] = in.nextInt();
        }

        int dp0 = 0;
        int dp1 = 1;
        for (int i = 1; i < n; i++) {
            int dpi0 = Integer.MAX_VALUE;
            int dpi1 = Integer.MAX_VALUE;
            boolean f = false;
            if (numsA[i] > numsA[i - 1]) {
                dpi0 = dp0;
                f = true;
            }
            if (numsA[i] > numsB[i - 1]) {
                dpi0 = Math.min(dpi0, dp1);
                f = true;
            }
            if (numsB[i] > numsA[i - 1]) {
                dpi1 = dp0 + 1;
                f = true;
            }
            if (numsB[i] > numsB[i - 1]) {
                dpi1 = Math.min(dpi1, dp1 + 1);
                f = true;
            }
            if (!f) {
                System.out.println(-1);
                return;
            }
            dp0 = dpi0;
            dp1 = dpi1;
        }
        System.out.println(Math.min(dp0, dp1));
    }
}
