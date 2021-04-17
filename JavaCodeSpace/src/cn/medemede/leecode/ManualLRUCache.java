package cn.medemede.leecode;

import java.util.HashMap;

public class ManualLRUCache {
    private HashMap<Integer, DoubleNode> map;
    private DoubleList cache;
    private int cap;

    public ManualLRUCache(int cap) {
        map = new HashMap<>();
        cache = new DoubleList();
        this.cap = cap;
    }

    private void addRecently(int key, int val) {
        DoubleNode tmp = new DoubleNode(key, val);
        cache.addLast(tmp);
        map.put(key, tmp);
    }

    private void deleteByKey(int key) {
        cache.remove(map.get(key));
        map.remove(key);
    }

    private void removeLastRecently() {
        DoubleNode lastRecentlyNode = cache.removeFirst();
        map.remove(lastRecentlyNode.key);
    }

    private void makeAsRecently(int key) {
        DoubleNode tmp = map.get(key);
        cache.remove(tmp);
        cache.addLast(tmp);
    }

    public Integer get(int key) {
        if (map.containsKey(key)) {
            makeAsRecently(key);
            return map.get(key).val;
        }
        return null;
    }

    public void put(int key, int val) {
        if (map.containsKey(key)) {
            deleteByKey(key);
        } else if (cache.getSize() >= cap) {
            removeLastRecently();
        }
        addRecently(key, val);
    }
}