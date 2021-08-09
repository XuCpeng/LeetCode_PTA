package cn.medemede.leecode;


/**
 * 环形数组是否存在循环
 * <p>
 * 快慢指针
 */
public class CircularArrayLoop {
    int n;
    int[] nums;

    public boolean circularArrayLoop(int[] nums) {
        this.n = nums.length;
        this.nums = nums;
        int fast;
        int slow;

        // 遍历所有节点作为起点
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                continue;
            }
            slow = i;
            fast = getNext(i);

            // slow未变向，fast未变向，且slow和fast不为0
            while (nums[slow] * nums[getNext(slow)] > 0 && nums[fast] * nums[getNext(fast)] > 0) {

                // 快慢指针相遇
                if (slow == fast) {
                    // 循环长度不为 1
                    if (slow != getNext(slow)) {
                        return true;
                    }
                    break;
                }
                slow = getNext(slow);
                fast = getNext(getNext(fast));
            }

            // 以i为起点的路径不含环，所以可以将该条路径上所有点置0，后续若进入此路径无需再判断
            int pathI = i;
            while (nums[pathI] * nums[getNext(pathI)] > 0) {
                int tmp = pathI;
                pathI = getNext(pathI);
                nums[tmp] = 0;
            }
        }
        return false;
    }


    private int getNext(int cur) {
        return ((nums[cur] + cur) % n + n) % n;
    }
}
