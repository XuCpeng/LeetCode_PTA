package cn.medemede.leecode;

import java.util.HashMap;

/**
     * 大餐计数
 */
public class CountPairs {
    public int countPairs(int[] deliciousness) {
        int res = 0;
        HashMap<Integer, Integer> baseToNum = new HashMap<>();
        int maxVal = 0;
        for (int x : deliciousness) {
            maxVal = Math.max(maxVal, x);
        }
        maxVal *= 2;
        int base = 1000000007;
        for (int j : deliciousness) {
            for (int sum = 1; sum <= maxVal; sum <<= 1) {
                int count = baseToNum.getOrDefault(sum - j, 0);
                res = (res + count) % base;
            }
            baseToNum.put(j, baseToNum.getOrDefault(j, 0) + 1);
        }
        return res;
    }
}
