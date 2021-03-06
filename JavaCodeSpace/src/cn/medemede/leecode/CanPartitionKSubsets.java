package cn.medemede.leecode;

import cn.medemede.leecode.modules.Result;

import java.util.Arrays;

public class CanPartitionKSubsets {

    /**
     * 划分为k个相等的子集
     * <p>题解</p>
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean canPartitionKSubsets2(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k > 0) return false;
        int target = sum / k;
        // 排序
        Arrays.sort(nums);

        int row = nums.length - 1;

        if (nums[row] > target) return false;

        return canPartitionKSubsetDFS(new int[k], row, nums, target);

    }

    public boolean canPartitionKSubsetDFS(int[] groups, int row, int[] nums, int target) {
        if (row < 0) {
            return true;
        }
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] + nums[row] <= target) {
                groups[i] += nums[row];
                if (canPartitionKSubsetDFS(groups, row - 1, nums, target)) {
                    return true;
                }
                groups[i] -= nums[row];
            }
            // 注意
            if (groups[i] == 0) break;
        }

        return false;
    }

    /**
     * 划分为k个相等的子集
     * <p>官方解（错误）</p>
     */
    boolean search(int used, int todo, Result[] memo, int[] nums, int target) {
        if (memo[used] == null) {
            memo[used] = Result.FALSE;
            int targ = (todo - 1) % target + 1;
            for (int i = 0; i < nums.length; i++) {
                if ((((used >> i) & 1) == 0) && nums[i] <= targ) {
                    if (search(used | (1 << i), todo - nums[i], memo, nums, target)) {
                        memo[used] = Result.TRUE;
                        break;
                    }
                }
            }
        }
        return memo[used] == Result.TRUE;
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k > 0) return false;

        Result[] memo = new Result[1 << nums.length];
        memo[(1 << nums.length) - 1] = Result.TRUE;
        return search(0, sum, memo, nums, sum / k);
    }

}

