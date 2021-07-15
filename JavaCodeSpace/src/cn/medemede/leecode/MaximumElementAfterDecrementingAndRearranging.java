package cn.medemede.leecode;

import java.util.Arrays;

public class MaximumElementAfterDecrementingAndRearranging {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        arr[0] = 1;
        int i = 1;
        int n = arr.length;
        while (i < n) {
            if (arr[i] - arr[i - 1] > 1) {
                arr[i] = arr[i - 1] + 1;
            }
            i++;
        }
        return arr[n - 1];
    }
}
