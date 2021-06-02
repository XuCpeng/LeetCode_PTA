package cn.medemede.leecode;

import java.util.HashMap;

public class SubarraySum {

    /**
     * 和为K的子数组（中等）
     * <p>HashMap记录前缀和及其数量</p>
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        HashMap<Integer, Integer> subSums = new HashMap<>();
        subSums.put(0, 1);
        int res = 0;
        for (int num : nums) {
            count += num;
            res += subSums.getOrDefault(count - k, 0);
            subSums.put(count, subSums.getOrDefault(count, 0) + 1);
        }
        return res;
    }

}

