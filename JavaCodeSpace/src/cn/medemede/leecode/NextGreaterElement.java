package cn.medemede.leecode;

import java.util.HashMap;
import java.util.LinkedList;

public class NextGreaterElement {

    /**
     * HashMap
     * <p>
     * 单调栈：从顶部添加，被挡住的逐个删除
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> valToIndex = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            valToIndex.put(nums1[i], i);
        }
        int[] res = new int[nums1.length];
        LinkedList<Integer> stack = new LinkedList<>();
        for (int j = nums2.length - 1; j >= 0; j--) {
            while (!stack.isEmpty() && stack.peek() < nums2[j]) {
                stack.pop();
            }
            if (valToIndex.containsKey(nums2[j])) {
                res[valToIndex.get(nums2[j])] = stack.isEmpty() ? -1 : stack.peek();
            }
            stack.push(nums2[j]);
        }
        return res;
    }

    /**
     * 单调栈：从顶部添加，被挡住的逐个删除
     * <p>
     * 数组长度翻倍
     *
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 2 * nums.length - 1; i >= 0; i--) {
            int trueI = i % nums.length;
            while (!stack.isEmpty() && nums[trueI] >= stack.peek()) {
                stack.poll();
            }
            if (i < nums.length) {
                res[i] = stack.isEmpty() ? -1 : stack.peek();
            }
            stack.addFirst(nums[trueI]);
        }
        return res;
    }

}

