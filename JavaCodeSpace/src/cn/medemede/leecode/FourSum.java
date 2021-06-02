package cn.medemede.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {

    /**
     * 四个数的和
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        if (n < 4) {
            return result;
        }
        Arrays.sort(nums);
        for (int i = 0; i < n - 3; i++) {
            if (i != 0) {
                continue;
            }
            int target2 = target - nums[i];
            for (int j = i + 1; j < n - 2; j++) {
                if (j != i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int target3 = target2 - nums[j];
                int l = n - 1;
                for (int k = j + 1; k < l; k++) {
                    if (k != j + 1 && nums[k] == nums[k - 1]) {
                        continue;
                    }
                    int target4 = target3 - nums[k];
                    for (; l > k; l--) {
                        if (l != nums.length - 1 && nums[l] == nums[l + 1]) {
                            continue;
                        }
                        if (nums[l] < target4) {
                            break;
                        }
                        if (nums[l] == target4) {
                            result.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k], nums[l])));
                        }
                    }
                }
            }
        }
        return result;
    }

}

