package cn.medemede.leecode;

import java.util.*;

public class SkipBlackListRandom {
    Random random;
    int target;
    HashMap<Integer, Integer> indexToVal;

    public SkipBlackListRandom(int N, int[] blacklist) {
        random = new Random();
        indexToVal = new HashMap<>();
        target = N - blacklist.length;
        Set<Integer> set = new HashSet<>();
        for (int i = target; i < N; i++) {
            set.add(i);
        }
        for (int a : blacklist) {
            set.remove(a);
        }
        Iterator<Integer> it = set.iterator();
        for (int a : blacklist) {
            if (a < target)
                indexToVal.put(a, it.next());
        }
    }

    public int pick() {
        int r = random.nextInt(target);
        if (indexToVal.containsKey(r)) {
            return indexToVal.get(r);
        }
        return r;
    }
}
