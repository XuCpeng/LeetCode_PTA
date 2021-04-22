package cn.medemede.leecode;

import java.util.*;

/**
 * 黑名单随机数
 *
 * <p>将黑名单数字所在位置的值，映射为尾部的值
 * <p>用HashMap记录黑名单位置的值
 * <p>映射之前要先把尾部的黑名单值去除
 */
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
