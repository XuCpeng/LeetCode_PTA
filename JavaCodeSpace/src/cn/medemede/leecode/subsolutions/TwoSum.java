package cn.medemede.leecode.subsolutions;

import java.util.HashMap;

public class TwoSum {

    /**
     * 双指针
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        int[] res = new int[2];
        while (left < right) {
            if (numbers[left] + numbers[right] == target) {
                res[0] = left + 1;
                res[1] = right + 1;
                break;
            } else if (numbers[left] + numbers[right] < target) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }

    /**
     * 两数之和为target
     *
     * <p>使用HashMap存储value到index的映射。
     * <p>重点在于初始化hashmap的过程中就进行判断，可以避免重复值的影响。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> indexToVal = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (indexToVal.containsKey(target - nums[i])) {
                return new int[]{indexToVal.get(target - nums[i]), i};
            }
            indexToVal.put(nums[i], i);
        }
        return new int[]{};
    }

    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        int a = 0;
        int b = 1;
        int res = a + b;
        while (n > 1) {
            res = a + b;
            a = b;
            b = res;
            n--;
        }
        return res;
    }

}

