package cn.medemede.leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * 797. 所有可能的路径
 */
public class AllPathsSourceTarget {
    List<List<Integer>> res;
    boolean[] usedNode;
    int[][] graph;

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        this.graph = graph;
        res = new ArrayList<>();
        usedNode = new boolean[graph.length];
        usedNode[0] = true;
        List<Integer> track = new ArrayList<>();
        track.add(0);
        getPath(0, track);
        return res;
    }


    private void getPath(int i, List<Integer> track) {
        if (i == graph.length - 1) {
            res.add(new ArrayList<>(track));
            return;
        }
        for (int j = 0; j < graph[i].length; j++) {
            if (!usedNode[graph[i][j]]) {
                usedNode[graph[i][j]] = true;
                track.add(graph[i][j]);
                getPath(graph[i][j], track);
                track.remove(track.size() - 1);
                usedNode[graph[i][j]] = false;
            }
        }
    }
}
