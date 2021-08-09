package cn.medemede.leecode;

import java.util.Arrays;

public class MergeSort {
    int[] tmp;

    public void sort(int[] nums) {
        tmp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1);
    }

    private void mergeSort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }

        // 划分数组
        int mid = (low + high) / 2;
        // 类似后序遍历
        mergeSort(nums, low, mid);
        mergeSort(nums, mid + 1, high);

        int left = low;
        int right = mid + 1;
        int cur = 0;
        while (left <= mid && right <= high) {
            if (nums[left] <= nums[right]) {
                tmp[cur] = nums[left];
                left++;
            } else {
                tmp[cur] = nums[right];
                right++;
            }
            cur++;
        }
        while (left <= mid) {
            tmp[cur] = nums[left];
            cur++;
            left++;
        }
        while (right <= high) {
            tmp[cur] = nums[right];
            cur++;
            right++;
        }
        for (int i = low; i <= high; i++) {
            nums[i] = tmp[i - low];
        }
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] nums = new int[]{5, 5, 6, 3, 7, 9, 100};
        mergeSort.sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
