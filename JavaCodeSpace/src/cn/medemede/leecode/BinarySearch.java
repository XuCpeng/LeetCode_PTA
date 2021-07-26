package cn.medemede.leecode;

public class BinarySearch {
    /**
     * 查找左边界：
     * 若target存在，返回最左侧等于target的下标;
     * 若target不存在，返回第一个大于target的下标.
     * <p>
     * 查找右边界：
     * 若target存在，返回第一个大于target的下标;
     * 若target不存在，返回第一个大于target的下标.
     */
    public int bSearch(int[] nums, int target, String leftOrRight) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        int left = 0;
        int right = n;
        while (left < right) {
            int mid = (left + right) / 2;
            if (leftOrRight.equals("left")) {
                if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            } else {
                if (nums[mid] > target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
        }
        return left;
    }


    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        System.out.println(binarySearch.bSearch(new int[]{0, 1, 1, 3}, 1, "left"));
        System.out.println(binarySearch.bSearch(new int[]{0, 1, 1, 3}, 2, "left"));
        System.out.println(binarySearch.bSearch(new int[]{0, 1, 1, 3}, 1, "right"));
        System.out.println(binarySearch.bSearch(new int[]{0, 1, 1, 3}, 2, "right"));
    }
}
