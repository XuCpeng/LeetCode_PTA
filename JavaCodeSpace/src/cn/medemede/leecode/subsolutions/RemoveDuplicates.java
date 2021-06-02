package cn.medemede.leecode.subsolutions;

public class RemoveDuplicates {

    /**
     * 原地删除有序数组重复元素
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int i = 0;
        int j = 0;
        while (j < nums.length) {
            while (j < nums.length - 1 && nums[j + 1] == nums[j]) {
                j++;
            }
            nums[i] = nums[j];
            i++;
            j++;
        }
        return i;
    }

}

