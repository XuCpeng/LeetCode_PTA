package cn.medemede.leecode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Permute {

    /**
     * 全排列
     * <p>回溯，标记数组，保序</p>
     *
     * @param nums
     * @return
     */
    List<List<Integer>> res;
    boolean[] flag;

    public List<List<Integer>> permute(int[] nums) {
        this.res = new LinkedList<>();
        this.flag = new boolean[nums.length];
        LinkedList<Integer> track = new LinkedList<>();
        getPermute(nums, track);
        return res;
    }

    private void getPermute(int[] nums, LinkedList<Integer> track) {
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!flag[i]) {
                track.addLast(nums[i]);
                flag[i] = true;
                getPermute(nums, track);
                track.removeLast();
                flag[i] = false;
            }
        }
    }

    /**
     * 全排列
     * <p>回溯，交换，不保序</p>
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute2(int[] nums) {
        this.res = new LinkedList<>();
        LinkedList<Integer> track = new LinkedList<>();
        for (int x : nums) {
            track.add(x);
        }
        getPermute2(0, track);
        return res;
    }

    private void getPermute2(int i, LinkedList<Integer> track) {
        if (track.size() == i) {
            res.add(new LinkedList<>(track));
            return;
        }
        for (int j = i; j < track.size(); j++) {
            Collections.swap(track, i, j);
            getPermute2(i + 1, track);
            Collections.swap(track, i, j);
        }
    }

}

