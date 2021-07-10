package cn.medemede.leecode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TimeMap {
    /**
     * Initialize your data structure here.
     */
    HashMap<String, TreeMap<Integer, String>> data;
    int time;

    public TimeMap() {
        data = new HashMap<>();
        time = 0;

    }

    public void set(String key, String value, int timestamp) {
        TreeMap<Integer, String> valueTree = data.computeIfAbsent(key, k -> new TreeMap<>());
        valueTree.put(timestamp, value);
        data.put(key, valueTree);
        time++;
    }

    public String get(String key, int timestamp) {
        if (data.containsKey(key)) {
            Map.Entry<Integer, String> tmp = data.get(key).floorEntry(timestamp);
            if (tmp != null) {
                return tmp.getValue();
            }

        }
        return "";
    }
}
