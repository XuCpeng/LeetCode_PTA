package cn.medemede.leecode;

/**
 * 两个只出现一次的数字
 * <p>对整个数组求异或值count，利用count为1的位，对数组进行分组。</p>
 * <p>分组结果可以满足：1. 相同数字在同一组；2.两个只出现一次的数字在不同组。</p>
 */
public class SingleNumbers {
    public int[] singleNumbers(int[] nums) {
        int sum = 0;
        for (int x : nums) {
            sum ^= x;
        }
        int p = 1;
        while ((sum & p) == 0) {
            p = p << 1;
        }
        int a = 0;
        int b = 0;
        for (int x : nums) {
            if ((x & p) == 0) {
                a ^= x;
            } else {
                b ^= x;
            }
        }
        return new int[]{a, b};
    }
}
