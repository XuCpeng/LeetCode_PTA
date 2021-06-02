package cn.medemede.leecode.subsolutions;

import java.util.ArrayList;
import java.util.HashMap;

public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        HashMap<Character, ArrayList<Integer>> charToIndex = new HashMap<>();
        int m = s.length();
        int n = t.length();
        for (int i = 0; i < n; i++) {
            char c = t.charAt(i);
            ArrayList<Integer> index = charToIndex.computeIfAbsent(c, k -> new ArrayList<>());
            index.add(i);
            charToIndex.put(c, index);
        }
        int last = -1;
        for (int i = 0; i < m; i++) {
            char c = s.charAt(i);
            ArrayList<Integer> index = charToIndex.get(c);
            if (index == null) {
                return false;
            }
            int p = 0, q = index.size();
            while (p < q) {
                int mid = (q - p) / 2 + p;
                if (index.get(mid) <= last) {
                    p = mid + 1;
                } else {
                    q = mid;
                }
            }
            if (p >= index.size()) {
                return false;
            } else {
                last = index.get(p);
            }
        }
        return true;
    }
}
