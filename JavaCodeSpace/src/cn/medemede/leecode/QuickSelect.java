package cn.medemede.leecode;

import java.util.Random;

public class QuickSelect {

    Random random = new Random();

    public int findKthLargest(int[] nums, int k) {
        int lo = 0;
        int hi = nums.length - 1;
        k = nums.length - k;
        shuffle(nums);
        while (lo <= hi) {
            int p = partition(nums, lo, hi);
            if (p == k) {
                return nums[p];
            }
            if (p < k) {
                lo = p + 1;
            } else {
                hi = p - 1;
            }
        }
        return -1;
    }

    //标准快排划分
    int partition(int[] nums, int lo, int hi) {
        if (lo == hi) return lo;
        // 将 nums[lo] 作为默认分界点 pivot
        int pivot = nums[lo];
        // j = hi + 1 因为 while 中会先执行 --
        int i = lo;
        int j = hi + 1;
        while (true) {
            // 保证 nums[lo..i] 都小于 pivot
            while (nums[++i] < pivot) {
                if (i == hi) break;
            }
            // 保证 nums[j..hi] 都大于 pivot
            while (nums[--j] > pivot) {
                if (j < i) break;
            }
            if (i >= j) break;
            // 如果走到这里，一定有：
            // nums[i] > pivot && nums[j] < pivot
            // 所以需要交换 nums[i] 和 nums[j]，
            // 保证 nums[lo..i] < pivot < nums[j..hi]
            swap(nums, i, j);
        }
        // 将 pivot 值交换到正确的位置
        swap(nums, j, lo);
        // 现在 nums[lo..j-1] < nums[j] < nums[j+1..hi]
        return j;
    }

    // 交换数组中的两个元素
    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 对数组元素进行随机打乱
    void shuffle(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int j = i + random.nextInt(n - i);
            swap(nums, i, j);
        }
    }
}
