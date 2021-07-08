package cn.medemede.leecode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NumWays {
    int res = 0;
    int n;

    public int numWays(int n, int[][] relation, int k) {
        this.n = n;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int[] x : relation) {
            List<Integer> list = map.computeIfAbsent(x[0], o -> new ArrayList<>());
            list.add(x[1]);
            map.put(x[0], list);
        }
        getNum(map, 0, k);
        return res;
    }

    private void getNum(HashMap<Integer, List<Integer>> map, int i, int k) {
        if (k == 0) {
            if (i == n - 1) {
                res++;
            }
            return;
        }
        List<Integer> list = map.get(i);
        if (list != null)
            for (int x : list) {
                getNum(map, x, k - 1);
            }
    }
}
