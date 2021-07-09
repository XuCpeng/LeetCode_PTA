package cn.medemede.leecode;

/**
 * 主要元素
 * <p>
 * Boyer-Moore 投票算法
 */
public class MajorityElement {
    public int majorityElement(int[] nums) {
        int res = 0;
        int count = 0;
        for (int x : nums) {
            if (count == 0) {
                res = x;
                count++;
            } else if (x == res) {
                count++;
            } else {
                count--;
            }
        }
        count = 0;
        for (int x : nums) {
            if (x == res) {
                count++;
            }
        }
        if (count > nums.length / 2) {
            return res;
        } else {
            return -1;
        }
    }
}
