package cn.medemede.leecode;

public class Search {

    /**
     * 搜索旋转排序数组
     * <p>无重复元素，关键：确定[i,j]之间的有序部分，其他不要多想</p>
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int i = 0;
        int j = nums.length;
        if (target == nums[0]) {
            return 0;
        }
        if (target == nums[nums.length - 1]) {
            return nums.length - 1;
        }
        while (i < j) {
            int mid = (i + j) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > nums[0]) {
                if (target > nums[0] && target < nums[mid]) {
                    j = mid;
                } else {
                    i = mid + 1;
                }
            } else {
                if (target < nums[0] && target > nums[mid]) {
                    i = mid + 1;
                } else {
                    j = mid;
                }
            }
        }
        return -1;
    }

    /**
     * 搜索旋转排序数组 II
     * <p>有重复元素，当nums[mid]==nums[i]==nums[j]时无法判断有序部分，i++,j--</p>
     *
     * @param nums
     * @param target
     * @return
     */
    public boolean search2(int[] nums, int target) {
        int i = 0;
        int j = nums.length;
        if (target == nums[0] || target == nums[nums.length - 1]) {
            return true;
        }

        while (i < j) {
            int mid = (i + j) / 2;
            if (nums[mid] == target) {
                return true;
            }

            if (nums[i] == nums[mid] && nums[i] == nums[j - 1]) {
                i++;
                j--;
            } else if (nums[i] <= nums[mid]) {
                // 左侧有序
                if (target >= nums[i] && target < nums[mid]) {
                    j = mid;
                } else {
                    i = mid + 1;
                }
            } else {
                // 右侧有序
                if (target <= nums[j - 1] && target > nums[mid]) {
                    i = mid + 1;
                } else {
                    j = mid;
                }
            }
        }
        return false;
    }

}

