package cn.medemede.leecode;

import java.util.LinkedHashMap;

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