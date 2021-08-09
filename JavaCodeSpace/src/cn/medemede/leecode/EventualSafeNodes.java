package cn.medemede.leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * 找到最终的安全状态
 * <p>
 * 深度优先搜索 + 三色标记法：
 * 白色（用 0 表示）：该节点尚未被访问；
 * 灰色（用 1 表示）：该节点位于递归栈中，或者在某个环上；
 * 黑色（用 2 表示）：该节点搜索完毕，是一个安全节点。
 */

public class EventualSafeNodes {
    int[] memo;
    int[][] graph;

    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> res = new ArrayList<>();
        int n = graph.length;
        memo = new int[n];
        this.graph = graph;
        for (int i = 0; i < n; i++) {
            if (isSafe(i)) {
                res.add(i);
            }
        }
        return res;
    }

    private boolean isSafe(int i) {
        if (memo[i] != 0) {
            return memo[i] == 2;
        }
        memo[i] = 1;
        int j = 0;
        while (j < graph[i].length && isSafe(graph[i][j])) {
            j++;
        }


        if (j == graph[i].length) {
            // 无环
            memo[i] = 2;
            return true;
        } else {
            // 若有环 memo[i] = 1
            return false;
        }
    }
}
