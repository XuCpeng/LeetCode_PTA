package cn.medemede.leecode;

import java.util.*;

public class FindLadders {
    private boolean diff(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int diffIndex = 0;
        for (int i = 0; diffIndex < 2 && i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diffIndex++;
            }
        }
        return diffIndex ==1;
    }

    public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<String> res = new ArrayList<>();
        Collections.sort(wordList);
        int i = 0;
        while (i < wordList.size()) {
            if (wordList.get(i).equals(endWord)) {
                break;
            }
            i++;
        }
        if (i==wordList.size()) {
            return new ArrayList<>();
        }
        res.add(beginWord);
        String[] targets = new String[]{beginWord, endWord};
        Arrays.sort(targets);
        int j = i;
        if (beginWord.equals(targets[0])) {
            for (; j >= 0; j--) {
                String wordStr = wordList.get(j);
                if (diff(beginWord, wordStr)) {
                    break;
                }
            }


            for (int k = j; k < i; k++) {
                if (diff(wordList.get(k), re) {
                    return new ArrayList<>();
                }
            }
            res.addAll(wordList.subList(j, i + 1));


        } else {
            for (; j < wordList.size(); j++) {
                String wordStr = wordList.get(j);
                if (diff(beginWord, wordStr)) {
                    break;
                }
            }
            for (int k = i; k < j; k++) {
                if (!diff(wordList.get(k), wordList.get(k + 1))) {
                    return new ArrayList<>();
                }
            }
            List<String> tmpList = wordList.subList(i, j + 1);
            Collections.reverse(tmpList);
            res.addAll(tmpList);
        }

        return res;
    }
}
