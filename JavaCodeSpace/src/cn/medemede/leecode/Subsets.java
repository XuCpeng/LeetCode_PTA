package cn.medemede.leecode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Subsets {

    /**
     * 子集
     * <p>旧子集添加新元素组成新的子集</p>
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        res = new ArrayList<>();
        res.add(new LinkedList<>());
        for (int num : nums) {
            int size = res.size();
            for (int j = 0; j < size; j++) {
                LinkedList<Integer> newSubSet = new LinkedList<>(res.get(j));
                newSubSet.add(num);
                res.add(newSubSet);
            }
        }
        return res;
    }

    /**
     * 子集
     * <p>回溯，递归</p>
     *
     * @param nums
     * @return
     */
    List<List<Integer>> res;

    public List<List<Integer>> subsets2(int[] nums) {
        res = new ArrayList<>();
        getSubsets(0, nums, new LinkedList<>());
        return res;
    }

    private void getSubsets(int i, int[] nums, LinkedList<Integer> track) {
        if (i == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }

        getSubsets(i + 1, nums, track);
        track.add(nums[i]);
        getSubsets(i + 1, nums, track);
        track.removeLast();
    }

}

