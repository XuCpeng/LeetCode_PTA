package cn.medemede.leecode;

/**
 * 剑指 Offer 51. 数组中的逆序对
 */
public class ReversePairs {
    int[] tmp;
    int pairs;

    public int reversePairs(int[] nums) {
        this.tmp = new int[nums.length];
        this.pairs = 0;
        mergeSort(nums, 0, nums.length - 1);
        return pairs;
    }

    private void mergeSort(int[] nums, int low, int high) {

        if (low >= high) {
            return;
        }

        int mid = (low + high) / 2;
        mergeSort(nums, low, mid);
        mergeSort(nums, mid + 1, high);

        int left = low;
        int right = mid + 1;
        int p = low;
        while (left <= mid && right <= high) {
            if (nums[right] < nums[left]) {
                tmp[p++] = nums[right++];
                pairs += mid + 1 - left;
            } else {
                tmp[p++] = nums[left++];
            }
        }

        while (left <= mid) {
            tmp[p++] = nums[left++];
        }

        while (right <= high) {
            tmp[p++] = nums[right++];
        }

        for (int i = low; i <= high; i++) {
            nums[i] = tmp[i];
        }
    }
}
