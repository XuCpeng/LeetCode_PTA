package cn.medemede.leecode;


import java.util.ArrayList;
import java.util.List;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        int[] mome = new int[strs.length];
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            if (mome[i] == 0) {
                int[] chars = new int[128];
                List<String> strList = new ArrayList<>();
                strList.add(strs[i]);
                for (int j = 0; j < strs[i].length(); j++) {
                    chars[strs[i].charAt(j)]++;
                }

                for (int j = i + 1; j < strs.length; j++) {
                    if (mome[i] == 0 && strs[i].length() == strs[j].length()) {
                        int[] nextChars = new int[128];
                        for (int k = 0; k < strs[j].length(); k++) {
                            chars[strs[j].charAt(k)]++;
                        }
                        if (isTarget(chars, nextChars)) {
                            mome[j] = 1;
                            strList.add(strs[j]);
                        }
                    }
                }
                res.add(strList);
            }
        }
        return res;
    }

    private boolean isTarget(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }
}
