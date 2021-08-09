package cn.medemede.leecode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 访问所有节点的最短路径
 */
public class ShortestPathLength {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        Queue<int[]> queue = new LinkedList<>();

        // seen[i][mask] 表示 以i为终点的 mask 路径是否被访问
        boolean[][] seen = new boolean[n][1 << n];
        for (int i = 0; i < n; ++i) {
            queue.offer(new int[]{i, 1 << i, 0});
            seen[i][1 << i] = true;
        }

        int ans = 0;
        while (!queue.isEmpty()) {
            int[] tuple = queue.poll();
            int u = tuple[0], mask = tuple[1], dist = tuple[2];
            if (mask == (1 << n) - 1) {
                ans = dist;
                break;
            }
            // 搜索相邻的节点
            for (int v : graph[u]) {
                // 将 mask 的第 v 位置为 1
                int maskV = mask | (1 << v);
                if (!seen[v][maskV]) {
                    queue.offer(new int[]{v, maskV, dist + 1});
                    seen[v][maskV] = true;
                }
            }
        }
        return ans;
    }


    // 使用 HashSet 代替seen数组，反而更慢
    public int shortestPathLength2(int[][] graph) {
        int n = graph.length;
        Queue<int[]> queue = new LinkedList<>();

        ArrayList<HashSet<Integer>> seenPath = new ArrayList<>(n);

        for (int i = 0; i < n; ++i) {
            queue.offer(new int[]{i, 1 << i, 0});
            HashSet<Integer> pathI = new HashSet<>();
            pathI.add(1 << i);
            seenPath.add(pathI);
        }

        int ans = 0;
        while (!queue.isEmpty()) {
            int[] tuple = queue.poll();
            int u = tuple[0], mask = tuple[1], dist = tuple[2];
            if (mask == (1 << n) - 1) {
                ans = dist;
                break;
            }
            // 搜索相邻的节点
            for (int v : graph[u]) {
                // 将 mask 的第 v 位置为 1
                int maskV = mask | (1 << v);
                if (!seenPath.get(v).contains(maskV)) {
                    queue.offer(new int[]{v, maskV, dist + 1});
                    seenPath.get(v).add(maskV);
                }
            }
        }
        return ans;
    }
}
