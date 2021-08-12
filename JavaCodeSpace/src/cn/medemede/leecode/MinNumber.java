package cn.medemede.leecode;

public class MinNumber {
    StringBuilder sb;

    public String minNumber(int[] nums) {
        sb = new StringBuilder();
        quickSort(nums, 0, nums.length - 1);

        for (int x : nums) {
            sb.append(x);
        }
        return sb.toString();
    }

    private void quickSort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }
        int left = low;
        int right = high;
        while (left < right) {
            while (right > left && compareXY(nums[right], nums[low])) {
                right--;
            }
            while (left < right && compareXY(nums[low], nums[left])) {
                left++;
            }
            swap(nums, left, right);
        }
        swap(nums, low, left);

        quickSort(nums, low, left - 1);
        quickSort(nums, left + 1, high);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private boolean compareXY(int x, int y) {
        sb.append(x);
        sb.append(y);
        long a = Long.parseLong(sb.toString());
        sb.delete(0, sb.length());
        sb.append(y);
        sb.append(x);
        long b = Long.parseLong(sb.toString());
        sb.delete(0, sb.length());
        return a >= b;
    }
}
