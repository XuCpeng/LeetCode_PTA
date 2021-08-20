package cn.medemede.leecode;

/**
 * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
 */
public class Exchange {
    public int[] exchange(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            while (i < j && ((nums[i] & 1) == 1)) {
                i++;
            }
            while (i < j && ((nums[j] & 1) == 0)) {
                j--;
            }
            swap(nums, i, j);
            i++;
            j--;
        }
        return nums;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
