package cn.medemede.leecode;

/**
 * 和相同的二元子数组
 * <p>
 * 双滑动窗口
 */
public class NumSubarraysWithSum {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int n = nums.length;
        int res = 0;
        int left1 = 0;
        int left2 = 0;
        int right = 0;
        int sum = 0;
        int sum2 = 0;

        while (right < n) {

            sum += nums[right];
            while (left1 <= right && sum > goal) {
                sum -= nums[left1];
                left1++;
            }

            sum2 += nums[right];
            while (left2 <= right && sum2 >= goal) {
                sum2 -= nums[left2];
                left2++;
            }

            res += left2 - left1;

            right++;
        }

        return res;
    }

    public static void main(String[] args) {
        NumSubarraysWithSum numSubarraysWithSum = new NumSubarraysWithSum();
        System.out.print(numSubarraysWithSum.numSubarraysWithSum(new int[]{0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, 0));
    }
}
