package cn.medemede.leecode;

import java.util.Random;

public class RandomPickIndex {

    int[] nums;
    Random r;

    public RandomPickIndex(int[] nums) {
        this.nums = nums;
        this.r = new Random();
    }

    public int pick(int target) {
        int i = 1;
        int res = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] == target) {
                if (r.nextInt(i) == 0) {
                    res = j;
                }
                i++;
            }
        }
        return res;
    }

}
