package cn.medemede.leecode;

import java.util.Arrays;

/**
 * 有效三角形的个数
 */
public class TriangleNumber {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int l = j + 1;
                int r = nums.length;
                while (l < r) {
                    int mid = (l + r) / 2;
                    if (nums[mid] < nums[i] + nums[j]) {
                        l = mid + 1;
                    } else {
                        r = mid;
                    }
                }
                res += l - 1 - j;
            }
        }
        return res;
    }
}
