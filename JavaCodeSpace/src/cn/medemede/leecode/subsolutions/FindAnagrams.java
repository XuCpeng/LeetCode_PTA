package cn.medemede.leecode.subsolutions;

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
        if (isSubStr2(count)) {
            res.add(left);
        }
        while (right < sChars.length) {
            count[sChars[right]]--;
            count[sChars[left]]++;
            left++;
            if (isSubStr2(count)) {
                res.add(left);
            }
            right++;
        }
        return res;
    }

}

