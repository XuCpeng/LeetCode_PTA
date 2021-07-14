package cn.medemede.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 天际线问题
 * <p>
 * 扫描线
 */
public class GetSkyline {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> edges = new ArrayList<>();
        for (int[] building : buildings) {
            edges.add(building[0]);
            edges.add(building[1]);
        }
        Collections.sort(edges);
        int pre = -1;
        for (int x : edges) {
            int h = 0;
            for (int[] building : buildings) {
                if (building[0] <= x && building[1] > x) {
                    h = Math.max(h, building[2]);
                }
            }
            if (h != pre) {
                res.add(new ArrayList<>(Arrays.asList(x, h)));
                pre = h;
            }
        }
        return res;
    }
}
