package cn.medemede.leecode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * 随机集合: 插入，删除，获取随机元素这三个操作的时间复杂度必须都是 O(1)
 * <p>如果我们想在 O(1) 的时间删除数组中的某一个元素 val，可以先把这个元素交换到数组的尾部，然后再 pop 掉。</p>
 * <p>需要一个哈希表来记录每个元素值对应的索引。</p>
 */
public class RandomizedSet {
    ArrayList<Integer> values;
    HashMap<Integer, Integer> valToIndex;
    Random random;
    int size;

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {
        values = new ArrayList<>();
        valToIndex = new HashMap<>();
        random = new Random();
        size = 0;
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (valToIndex.containsKey(val))
            return false;
        if (size < values.size()) {
            values.set(size, val);
        } else {
            values.add(val);
        }
        valToIndex.put(val, size);
        size++;
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (!valToIndex.containsKey(val))
            return false;
        int valIndex = valToIndex.get(val);
        Integer tail = values.get(size - 1);
        values.set(valIndex, tail);
        valToIndex.put(tail, valIndex);
        size--;
        valToIndex.remove(val);
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        return values.get(random.nextInt(size));
    }

    public static void main(String[] args) {
        RandomizedSet rs = new RandomizedSet();
        rs.insert(0);
        rs.insert(1);
        rs.remove(0);
        rs.insert(2);
        rs.remove(1);
        System.out.println(rs.getRandom());
    }
}
