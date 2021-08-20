package cn.medemede.leecode;

/**
 * 剑指 Offer 53 - II. 0～n-1中缺失的数字
 */
public class MissingNumber {
    public int missingNumber(int[] nums) {
        int i = 0;
        int j = nums.length;
        while (i < j) {
            int mid = (i + j) / 2;
            if (nums[mid] == mid) {
                i = mid + 1;
            } else {
                j = mid;
            }
        }
        return j;
    }
}
