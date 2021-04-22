package cn.medemede.leecode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * LFU 算法的淘汰策略是 Least Frequently Used，也就是每次淘汰那些使用次数最少的数据。
 * <p>
 * HashMap<Integer, Integer> ketToVal;
 * HashMap<Integer, Integer> keyToFreq;
 * HashMap<Integer, LinkedHashSet<Integer>> freqToKeys;
 * </p>
 */
public class LFUCache {
    int capacity;
    HashMap<Integer, Integer> ketToVal;
    HashMap<Integer, Integer> keyToFreq;
    HashMap<Integer, LinkedHashSet<Integer>> freqToKeys;
    Integer minFreq;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        ketToVal = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqToKeys = new HashMap<>();
        minFreq = 1;
    }

    private void addFreqByKey(int key) {
        Integer freq = keyToFreq.get(key);

        if (freq == null) {
            freq = 0;
            minFreq = 1;
        }

        keyToFreq.put(key, freq + 1);
        freqToKeys.computeIfAbsent(freq + 1, k -> new LinkedHashSet<>()).add(key);

        LinkedHashSet<Integer> keys = freqToKeys.get(freq);
        if (keys != null) {
            keys.remove(key);
            if (keys.isEmpty()) {
                freqToKeys.remove(freq);
                if (freq.equals(minFreq)) {
                    minFreq++;
                }
            }
        }
    }

    public void removeFriendly() {
        LinkedHashSet<Integer> minKeys = freqToKeys.get(minFreq);
        Iterator<Integer> it = minKeys.iterator();
        Integer key = it.next();
        ketToVal.remove(key);
        keyToFreq.remove(key);
        it.remove();
        if (minKeys.isEmpty()) {
            freqToKeys.remove(minFreq);
        }
    }

    public int get(int key) {
        if (ketToVal.containsKey(key)) {
            addFreqByKey(key);
            return ketToVal.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (!ketToVal.containsKey(key) && ketToVal.size() == capacity) {
            removeFriendly();
        }
        ketToVal.put(key, value);
        addFreqByKey(key);
    }
}