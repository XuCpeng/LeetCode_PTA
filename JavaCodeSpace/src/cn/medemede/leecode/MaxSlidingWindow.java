package cn.medemede.leecode;

import java.util.LinkedList;

public class MaxSlidingWindow {

    /**
     * 单调队列：从尾部添加，被挡住的逐个删除
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 1) {
            return nums;
        }
        int[] res = new int[nums.length - k + 1];
        int i = 0;
        int j = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        while (j < k) {
            while (!queue.isEmpty() && queue.peekLast() < nums[j]) {
                queue.removeLast();
            }
            queue.addLast(nums[j]);
            j++;
        }
        res[i] = queue.peekFirst();
        while (j < nums.length) {
            if (nums[i] == queue.peekFirst()) {
                queue.removeFirst();
            }
            while (!queue.isEmpty() && queue.peekLast() < nums[j]) {
                queue.removeLast();
            }
            queue.addLast(nums[j]);
            i++;
            res[i] = queue.peek();
            j++;
        }
        return res;
    }


    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[]{};
        }
        int[] res = new int[nums.length - k + 1];
        int left = 0;
        int right = 0;
        while (right < k) {
            if (nums[right] >= nums[res[0]]) {
                res[0] = right;
            }
            right++;
        }
        int i = 1;
        while (right < nums.length) {
            if (nums[right] >= nums[res[i - 1]]) {
                res[i] = right;
            } else if (left == res[i - 1]) {
                int maxNumIndex = left + 1;
                for (int j = left + 2; j <= right; j++) {
                    if (nums[j] >= nums[maxNumIndex]) {
                        maxNumIndex = j;
                    }
                }
                res[i] = maxNumIndex;
            } else {
                res[i] = res[i - 1];
            }
            right++;
            left++;
            i++;
        }

        for (int j = 0; j < res.length; j++) {
            res[j] = nums[res[j]];
        }
        return res;
    }

}

