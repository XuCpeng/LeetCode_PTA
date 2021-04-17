package cn.medemede.leecode;

import java.util.PriorityQueue;

public class MedianFinder {
    PriorityQueue<Integer> larger;
    PriorityQueue<Integer> smaller;

    /**
     * initialize your data structure here.
     */
    public MedianFinder() {
        larger = new PriorityQueue<>();
        smaller = new PriorityQueue<>((a, b) -> b - a);
    }

    public void addNum(int num) {
        if (larger.size() == smaller.size()) {
            if (larger.peek() == null || num > larger.peek())
                larger.add(num);
            else
                smaller.add(num);
        } else if (larger.size() > smaller.size()) {
            if (num > larger.peek()) {
                smaller.add(larger.poll());
                larger.add(num);
            } else
                smaller.add(num);

        } else {
            if (num < smaller.peek()) {
                larger.add(smaller.poll());
                smaller.add(num);
            } else
                larger.add(num);
        }
    }

    public double findMedian() {
        if (larger.size() == smaller.size())
            return (larger.peek() + smaller.peek()) / 2.0;
        else if (larger.size() > smaller.size())
            return larger.peek();
        else
            return smaller.peek();
    }
}
