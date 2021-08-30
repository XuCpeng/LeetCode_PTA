package cn.medemede.leecode;

import java.util.Random;

/**
 * 528. 按权重随机选择
 */
public class PickIndex {
    Random r;
    int[] wSums;
    int sum;

    public PickIndex(int[] w) {
        r = new Random();
        wSums = new int[w.length];
        sum = 0;
        for (int i = 0; i < w.length; i++) {
            sum += w[i];
            wSums[i] = sum;
        }
    }

    public int pickIndex() {
        int tmp = r.nextInt(sum);
        int i = 0;
        int j = wSums.length;
        while (i < j) {
            int mid = (i + j) / 2;
            if (wSums[mid] > tmp) {
                j = mid;
            } else {
                i = mid + 1;
            }
        }
        return i;
    }
}
