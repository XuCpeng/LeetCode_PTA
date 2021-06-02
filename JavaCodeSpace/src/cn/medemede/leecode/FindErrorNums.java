package cn.medemede.leecode;

public class FindErrorNums {

    /**
     * 错误的集合
     * <p>利用原数组及其索引做 hash 表，用数组长度做标记</p>
     *
     * @param nums
     * @return
     */
    public int[] findErrorNums(int[] nums) {
        int a = 0;
        int b = 0;
        int n = nums.length;
        for (int x : nums) {
            nums[(x - 1) % n] += n;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= n) {
                a = i + 1;
            } else if (nums[i] > 2 * n) {
                b = i + 1;
            }
        }
        return new int[]{b, a};
    }

}

