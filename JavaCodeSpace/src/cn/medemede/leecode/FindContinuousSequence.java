package cn.medemede.leecode;

import java.util.ArrayList;

/**
 * 剑指 Offer 57 - II. 和为s的连续正数序列
 */
public class FindContinuousSequence {
    public int[][] findContinuousSequence(int target) {
        ArrayList<int[]> res = new ArrayList<>();
        int i = 1;
        int j = 2;
        int sum = 3;
        while (j < target) {
            if (sum > target) {
                while (sum > target && i < j) {
                    sum -= i;
                    i++;
                }
            }
            if (sum == target) {
                int[] tmp = new int[j - i + 1];
                for (int k = i; k <= j; k++) {
                    tmp[k - i] = k;
                }
                res.add(tmp);
            }
            j++;
            sum += j;
        }
        return res.toArray(new int[res.size()][]);
    }
}
