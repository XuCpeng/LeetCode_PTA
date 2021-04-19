package cn.medemede.leecode;

import java.util.LinkedHashMap;

/**
 * LRU 缓存淘汰算法就是一种常用策略。LRU 的全称是 Least Recently Used，也就是说我们认为最近使用过的数据应该是是「有用的」，很久都没用过的数据应该是无用的，内存满了就优先删那些很久没用过的数据。
 *
 * <p>
 *     使用LinkedHashMap，底层可简单理解为HashMap+LinkedList，初始化参数中启用访问顺序并重写实现removeEldestEntry方法即可。
 * </p>
 */
public class LRUCache extends LinkedHashMap<Integer, Integer> {
    protected int capacity;

    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry eldest) {
        return size() > this.capacity;
    }

    public LRUCache(int capacity) {
        super(capacity, 1, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        if (super.containsKey(key)) {
            return (int) super.get(key);
        }
        return -1;
    }
}