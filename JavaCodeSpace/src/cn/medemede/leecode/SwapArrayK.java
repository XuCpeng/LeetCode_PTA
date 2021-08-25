package cn.medemede.leecode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SwapArrayK {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Integer> nums = new ArrayList<>();
        while (in.hasNextInt()) {
            nums.add(in.nextInt());
        }
        int n = nums.size();
        int k = 3;

        int nextK = 0;
        int l = 0;
        int r = k - 1;
        while (r < n) {
            while (l < r) {
                Collections.swap(nums, l, r);
                l++;
                r--;
            }
            nextK += k;
            l = nextK;
            r = l + k - 1;
        }
        if (l < n) {
            r = n - 1;
            while (l < r) {
                Collections.swap(nums, l, r);
                l++;
                r--;
            }
        }
        System.out.println(nums);
    }
}
