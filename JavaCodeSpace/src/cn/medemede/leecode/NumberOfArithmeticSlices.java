package cn.medemede.leecode;

/**
 * 等差数列划分
 */
public class NumberOfArithmeticSlices {
    public int numberOfArithmeticSlices(int[] nums) {
        int res = 0;
        int segment = 0;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                segment++;
                res += segment;
            } else {
                segment = 0;
            }
        }
        return res;
    }
}
