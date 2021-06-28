package cn.medemede.leecode;

import java.util.*;

/**
 * 公交路线
 */
public class NumBusesToDestination {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        HashMap<Integer, Set<Integer>> stationToBuses = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int x : routes[i]) {
                Set<Integer> v = stationToBuses.getOrDefault(x, new HashSet<>());
                v.add(i);
                stationToBuses.put(x, v);
            }
        }
        Set<Integer> tBuses = stationToBuses.get(target);
        if (tBuses == null || tBuses.isEmpty()) {
            return -1;
        }
        Queue<Integer> stationQueue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        stationQueue.add(source);
        visited.add(source);
        int k = 0;
        int n = 1;
        int res = 1;
        while (!stationQueue.isEmpty()) {
            Set<Integer> sBuses = stationToBuses.get(stationQueue.poll());
            for (int bus : sBuses) {
                if (tBuses.contains(bus)) {
                    return res;
                }
                for (int station : routes[bus]) {
                    if (!visited.contains(station)) {
                        stationQueue.add(station);
                        visited.add(station);
                    }
                }
            }
            k++;
            if (k == n) {
                k = 0;
                res++;
                n = stationQueue.size();
            }
        }
        return -1;
    }
}
