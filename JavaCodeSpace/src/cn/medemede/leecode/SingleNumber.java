package cn.medemede.leecode;

public class SingleNumber {

    /**
     * 只出现一次的数字
     * <p>a^a=0, a^0=a</p>
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int x : nums) {
            res ^= x;
        }
        return res;
    }

    /**
     * 两个只出现一次的数字
     * <p>对整个数组求异或值count，利用count为1的位，对数组进行分组。</p>
     * <p>分组结果可以满足：1. 相同数字在同一组；2.两个只出现一次的数字在不同组。</p>
     *
     * @param nums
     * @return
     */
    public int[] singleNumber2(int[] nums) {
        int count = 0;
        for (int x : nums) {
            count ^= x;
        }
        int i = 1;
        for (int j = 0; j < 32; j++) {
            if ((count & i) > 0) {
                break;
            }
            i = i << 1;
        }
        int res1 = 0;
        int res2 = 0;
        for (int x : nums) {
            if ((x & i) == 0) {
                res1 ^= x;
            } else {
                res2 ^= x;
            }
        }
        return new int[]{res1, res2};
    }

}

