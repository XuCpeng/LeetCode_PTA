package cn.medemede.leecode;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] ss = in.nextLine().split(",");
        int k = Integer.parseInt(ss[0]);
        int m = Integer.parseInt(ss[1]);
        int n = ss.length - 2;
        if (k > n) {
            System.out.println(-1);
            return;
        }
        int[] nums = new int[n];
        nums[0] = Integer.parseInt(ss[2].substring(1));
        for (int i = 1; i < n - 1; i++) {
            nums[i] = Integer.parseInt(ss[i + 2]);
        }
        nums[n - 1] = Integer.parseInt(ss[n + 1].substring(0, ss[n + 1].length() - 1));

        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] > m) {
                k--;
                if (k == 0) {
                    System.out.println(i);
                    return;
                }
            }
        }
        System.out.println(-1);
    }


}