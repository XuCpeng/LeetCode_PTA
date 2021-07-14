package cn.medemede.leecode;

import java.util.*;

/**
 * 绝对差值和
 * <p>
 * 利用二分法寻找与nums2中元素最相近的值进行替换，再比较大小
 */
public class MinAbsoluteSumDiff {
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int n = nums1.length;
        long res = 0;
        int base = 1000000007;

        Set<Integer> nums = new HashSet<>();
        int[] diffs = new int[n];
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            res = res + diff;
            diffs[i] = diff;
            nums.add(nums1[i]);
        }
        if (res == 0) {
            return 0;
        }
        List<Integer> numsList = new ArrayList<>(nums);
        Collections.sort(numsList);
        int maxAdd = 0;
        for (int i = 0; i < n; i++) {
            int p = 0;
            int q = numsList.size();
            while (p < q) {
                int mid = (p + q) / 2;
                if (numsList.get(mid) <= nums2[i]) {
                    p = mid + 1;
                } else {
                    q = mid;
                }
            }
            if (p < numsList.size()) {
                int diff1 = Math.abs(numsList.get(p) - nums2[i]);
                maxAdd = Math.max(maxAdd, diffs[i] - diff1);
            }
            if (p > 0) {
                int diff2 = Math.abs(numsList.get(p - 1) - nums2[i]);
                maxAdd = Math.max(maxAdd, diffs[i] - diff2);
            }
        }
        return (int) ((res - maxAdd) % base);
    }

    public static void main(String[] args) {
        MinAbsoluteSumDiff minAbsoluteSumDiff = new MinAbsoluteSumDiff();
        minAbsoluteSumDiff.minAbsoluteSumDiff(new int[]{1, 28, 21}, new int[]{9, 21, 20});
    }
}
