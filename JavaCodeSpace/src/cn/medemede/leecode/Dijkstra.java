package cn.medemede.leecode;

import java.util.Arrays;

/**
 * Dijkstra
 */
public class Dijkstra {

    public int networkDelayTime(int[][] times, int n, int k) {

        // 除以2是因为后面需要相加
        final int INF = Integer.MAX_VALUE / 2;

        int[][] g = new int[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(g[i], INF);
        }
        for (int[] t : times) {
            int x = t[0] - 1;
            int y = t[1] - 1;
            g[x][y] = t[2];
        }

        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[k - 1] = 0;
        boolean[] used = new boolean[n];

        for (int i = 0; i < n; ++i) {

            // 挑选一个到源节点最近的已确定的节点 x
            int x = -1;
            for (int y = 0; y < n; ++y) {
                if (!used[y] && (x == -1 || dist[y] < dist[x])) {
                    x = y;
                }
            }
            used[x] = true;

            // 更新所有节点,通过x可以更近
            for (int y = 0; y < n; ++y) {
                dist[y] = Math.min(dist[y], dist[x] + g[x][y]);
            }
        }
        // 获取最大值
        int ans = Arrays.stream(dist).max().getAsInt();
        return ans == INF ? -1 : ans;
    }
}
