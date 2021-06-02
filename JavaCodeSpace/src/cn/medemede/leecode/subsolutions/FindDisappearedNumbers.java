package cn.medemede.leecode.subsolutions;

import java.util.ArrayList;
import java.util.List;

public class FindDisappearedNumbers {

    /**
     * 找到所有数组中消失的数字
     * <p>利用原数组及其索引做 hash 表，用数组长度做标记</p>
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        ArrayList<Integer> res = new ArrayList<>();
        int n = nums.length;
        for (int x : nums) {
            nums[(x - 1) % n] += n;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= n) {
                res.add(i + 1);
            }
        }
        return res;
    }


}

