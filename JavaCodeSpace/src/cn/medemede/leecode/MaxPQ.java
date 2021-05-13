package cn.medemede.leecode;

/**
 * 最大堆的实现(未验证)
 *
 * <p>
 * 二叉堆就是一种完全二叉树，所以适合存储在数组中，而且二叉堆拥有一些特殊性质。
 *
 * <p>
 * 二叉堆的操作很简单，主要就是上浮和下沉，来维护堆的性质（堆有序），核心代码也就十行。
 *
 * <p>
 * 优先级队列是基于二叉堆实现的，主要操作是插入和删除。插入是先插到最后，然后上浮到正确位置；删除是调换位置后再删除，然后下沉到正确位置。
 */
public class MaxPQ<T extends Comparable<T>> {
    T[] treeArray;
    int capacity;
    int last;

    public MaxPQ(int cap) {
        this.treeArray = (T[]) new Comparable[cap + 1];
        this.capacity = cap;
        this.last = 0;
    }

    public void push(T x) {
        last++;
        treeArray[last] = x;
        swim(last);
    }

    public T pop() {
        T res = treeArray[1];
        treeArray[1] = treeArray[last];
        last--;
        sink(1);
        return res;
    }

    public T peek() {
        return treeArray[1];
    }

    public void swim(int k) {
        int root = k / 2;
        while (root > 0 && less(root, k)) {
            swap(root, k);
            k = root;
            root = k / 2;
        }
    }

    public void sink(int k) {
        while (2 * k <= last) {
            int target = 2 * k;
            if (target < last && less(target, target + 1)) target++;
            if (!less(k, target)) break;
            swap(k, target);
            k = target;
        }
    }

    /* treeArray[i] 是否比 treeArray[j] 小？ */
    private boolean less(int i, int j) {
        return treeArray[i].compareTo(treeArray[j]) < 0;
    }

    private void swap(int k1, int k2) {
        T tmp = treeArray[k1];
        treeArray[k1] = treeArray[k2];
        treeArray[k2] = tmp;
    }

    public static void main(String[] args) {
        MaxPQ<Integer> maxPQ = new MaxPQ<>(5);
        maxPQ.push(1);
        maxPQ.push(2);
        maxPQ.push(3);
        maxPQ.push(4);
        maxPQ.push(5);
        System.out.println(maxPQ.peek() + " " + maxPQ.pop() + " " + maxPQ.pop() + " " + maxPQ.pop());
    }

}
