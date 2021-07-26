package cn.medemede.leecode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 得到子序列的最少操作次数
 * <p>
 * 转换为最长单调子序列问题，贪心+二分查找
 */
public class MinOperations {
    public int minOperations(int[] target, int[] arr) {
        HashMap<Integer, Integer> numToIndex = new HashMap<>();
        for (int i = 0; i < target.length; i++) {
            numToIndex.put(target[i], i);
        }
        ArrayList<Integer> arrIndex = new ArrayList<>();
        for (int x : arr) {
            if (numToIndex.containsKey(x)) {
                arrIndex.add(numToIndex.get(x));
            }
        }
        ArrayList<Integer> d = new ArrayList<>();
        if (arrIndex.isEmpty()) {
            return target.length;
        }
        d.add(arrIndex.get(0));
        for (int x : arrIndex) {
            int n = d.size();
            if (x > d.get(n - 1)) {
                d.add(x);
            } else {
                int p = 0;
                int q = n;
                while (p < q) {
                    int mid = (p + q) / 2;
                    if (d.get(mid) < x) {
                        p = mid + 1;
                    } else {
                        q = mid;
                    }
                }
                d.set(p, x);
            }
        }
        return target.length - d.size();
    }
}
