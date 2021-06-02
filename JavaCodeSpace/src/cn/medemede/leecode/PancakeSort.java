package cn.medemede.leecode;

import java.util.ArrayList;
import java.util.List;

public class PancakeSort {

    /**
     * 煎饼排序
     * <p>依次寻找大值，通过两次翻转翻到后面（类似冒泡）</p>
     *
     * @param arr
     * @return
     */
    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> res = new ArrayList<>();
        int j = arr.length - 1;
        while (j > 0) {
            int i = 0;
            int maxV = arr[i];
            for (int k = 1; k <= j; k++) {
                if (arr[k] > maxV) {
                    maxV = arr[k];
                    i = k;
                }
            }
            if (i < j) {
                // 找到目标值arr[i],将其翻转到最前面
                pancakeFlip(arr, i);
                res.add(i + 1);
                // 将最前面的目标值翻转到后面的目标位置arr[j]
                pancakeFlip(arr, j);
                res.add(j + 1);
            }
            j--;
        }
        return res;
    }

    private void pancakeFlip(int[] arr, int k) {
        int i = 0;
        while (i < k) {
            int tmp = arr[i];
            arr[i] = arr[k];
            arr[k] = tmp;
            i++;
            k--;
        }
    }


}

