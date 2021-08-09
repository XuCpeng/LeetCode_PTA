package cn.medemede.leecode;

import java.util.Arrays;

public class QuickSort {
    public void quickSort(int[] nums, int low, int high) {
        // low,high为闭区间
        if (low > high) {
            return;
        }
        // 起始位置为最左端
        int left = low;
        // 起始位置为最右端
        int right = high;
        while (left < right) {
            // 注意顺序！！先减右边！包含等于，nums[low]为轴值
            while (right > left && nums[right] >= nums[low]) {
                right--;
            }
            while (left < right && nums[left] <= nums[low]) {
                left++;
            }
            swap(nums, left, right);
        }

        // 轴值的最终坐标为left；交换轴值nums[low]和nums[left]
        swap(nums, low, left);

        quickSort(nums, low, left - 1);
        quickSort(nums, left + 1, high);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] nums = new int[]{5, 5, 6, 3, 7, 9, 100};
        quickSort.quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }
}
