package cn.medemede.leecode;

import java.util.PriorityQueue;

/**
 * 最大数
 */
public class LargestNumber {
    public String largestNumber(int[] nums) {
        PriorityQueue<String> pq =
                new PriorityQueue<>((a, b) -> (b + a).compareTo(a + b));
        for (int x : nums) {
            pq.add(String.valueOf(x));
        }
        if (pq.isEmpty() || pq.peek().charAt(0) == '0') {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            sb.append(pq.poll());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LargestNumber largestNumber = new LargestNumber();
        System.out.println(largestNumber.largestNumber(new int[]{10, 2}));
    }
}
