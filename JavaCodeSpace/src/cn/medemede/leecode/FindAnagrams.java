package cn.medemede.leecode;

import java.util.ArrayList;
import java.util.List;

public class FindAnagrams {

    /**
     * 寻找字母异位词，并返回索引
     * <p>滑动窗口</p>
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length()) {
            return res;
        }
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        int[] count = new int[128];
        for (char a : pChars) {
            count[a]++;
        }
        int left = 0;
        int right = 0;
        for (; right < pChars.length; right++) {
            count[sChars[right]]--;
        }
        if (isSubStr(count)) {
            res.add(left);
        }
        while (right < sChars.length) {
            count[sChars[right]]--;
            count[sChars[left]]++;
            left++;
            if (isSubStr(count)) {
                res.add(left);
            }
            right++;
        }
        return res;
    }

    private boolean isSubStr(int[] count) {
        for (int i = 96; i < 123; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }

}

