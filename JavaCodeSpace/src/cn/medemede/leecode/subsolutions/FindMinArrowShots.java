package cn.medemede.leecode.subsolutions;

import java.util.Arrays;
import java.util.Comparator;

public class FindMinArrowShots {

    /**
     * 用最少数量的箭引爆气球
     * <p>排序,贪心</p>
     *
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(a -> a[1]));
        int res = 0;
        int right = points[0][0];
        for (int[] x : points) {
            if (x[0] > right) {
                res++;
            }
            right = x[1];
        }
        return res;
    }

}

