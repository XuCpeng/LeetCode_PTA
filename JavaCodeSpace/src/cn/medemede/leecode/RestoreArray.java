package cn.medemede.leecode;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 从相邻元素对还原数组
 * <p>
 * Hashmap存储每个元素的相邻元素，根据元素数量判断头节点，与前一节点不同的即为后继节点
 */
public class RestoreArray {
    Map<Integer, List<Integer>> map;

    public int[] restoreArray(int[][] adjacentPairs) {
        map = new HashMap<>();
        for (int[] x : adjacentPairs) {
            addLinked(x[0], x[1]);
            addLinked(x[1], x[0]);
        }
        int[] res = new int[adjacentPairs.length + 1];
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() < 2) {
                res[0] = entry.getKey();
                break;
            }
        }
        res[1] = map.get(res[0]).get(0);
        for (int i = 2; i < res.length; i++) {
            List<Integer> v = map.get(res[i - 1]);
            res[i] = v.get(0).equals(res[i - 2]) ? v.get(1) : v.get(0);
        }
        return res;
    }

    private void addLinked(int a, int b) {
        map.putIfAbsent(a, new ArrayList<>());
        map.get(a).add(b);
    }
}
