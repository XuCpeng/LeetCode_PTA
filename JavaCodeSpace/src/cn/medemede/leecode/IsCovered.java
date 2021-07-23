package cn.medemede.leecode;

import java.util.Arrays;

/**
 * 检查是否区域内所有整数都被覆盖
 * <p>
 * 差分数组：diff[x[0]]++,diff[x[1]+1]--
 */
public class IsCovered {
    public boolean isCovered2(int[][] ranges, int left, int right) {
        int[] diff = new int[52];
        for (int[] x : ranges) {
            diff[x[0]]++;
            diff[x[1] + 1]--;
        }
        int count = 0;
        for (int i = 1; i <= right; i++) {
            count += diff[i];
            if (i >= left && count < 1) {
                return false;
            }
        }
        return true;
    }

    public boolean isCovered(int[][] ranges, int left, int right) {
        Arrays.sort(ranges, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        if (ranges[0][0] > left) {
            return false;
        }
        int n = ranges.length;
        int i = 0;
        int preRight = ranges[0][1];
        while (i < n && ranges[i][0] <= left) {
            if (preRight >= right) {
                return true;
            }
            preRight = Math.max(preRight, ranges[i][1]);
            i++;
        }

        while (i < n) {
            if (ranges[i][0] > preRight + 1) {
                return false;
            }
            if (ranges[i][1] > preRight) {
                preRight = ranges[i][1];
                if (preRight >= right)
                    return true;
            }
            i++;
        }
        return false;
    }

    public static void main(String[] args) {
        IsCovered isCovered = new IsCovered();
        System.out.println(isCovered.isCovered(new int[][]{{15, 36}, {15, 23}, {15, 44}, {30, 49}, {2, 19}, {27, 36}, {7, 42}, {12, 41}}, 19, 47));
    }
}
