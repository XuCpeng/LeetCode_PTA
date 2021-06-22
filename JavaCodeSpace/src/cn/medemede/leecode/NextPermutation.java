package cn.medemede.leecode;

/**
 * 下一个排列
 * <p>
 * 首先从后向前查找第一个顺序对 (i,i+1)，满足 a[i] < a[i+1]。这样「较小数」即为 a[i]。此时 [i+1,n) 必然是下降序列。
 * <p>
 * 如果找到了顺序对，那么在区间 [i+1,n) 中从后向前查找第一个元素 jj 满足 a[i] < a[j]。这样「较大数」即为 a[j]。
 * <p>
 * 交换 a[i] 与 a[j]，此时可以证明区间 [i+1,n) 必为降序。我们可以直接使用双指针反转区间 [i+1,n) 使其变为升序。
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 1;
        int j = nums.length - 1;

        // 寻找 nums[i - 1] < nums[i]
        while (i > 0 && nums[i - 1] >= nums[i]) {
            i--;
        }

        if (i > 0) {
            // 寻找 nums[j] > nums[i-1]
            while (j > i - 1 && nums[j] <= nums[i - 1]) {
                j--;
            }
            swap(nums, i - 1, j);
        }

        // 反转 nums[i] ～ nums[n]
        j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int a = arr[i];
        arr[i] = arr[j];
        arr[j] = a;
    }
}
