package cn.medemede.leecode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 面试题 17.22. 单词转换
 * <p>
 * 用HashMap记录前驱；用used数组标记已使用的元素，不可重复使用；数组中可能含有beginWord，必须判断，否则成环。
 */
public class FindLadders {
    public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
        int n = wordList.size();
        LinkedList<String> res = new LinkedList<>();
        LinkedList<String> queue = new LinkedList<>();
        boolean[] used = new boolean[n];
        queue.addLast(beginWord);
        HashMap<String, String> wordToPrev = new HashMap<>();

        while (!queue.isEmpty()) {
            String str = queue.pollFirst();
            if (str.equals(endWord)) {
                String resStr = endWord;
                while (resStr != null) {
                    res.addFirst(resStr);
                    resStr = wordToPrev.get(resStr);
                }
                break;
            }
            for (int i = 0; i < n; i++) {
                if (!used[i]) {
                    String word = wordList.get(i);
                    if (diffOne(str, word) && !word.equals(beginWord)) {
                        used[i] = true;
                        queue.addLast(word);
                        wordToPrev.put(word, str);
                    }
                }
            }
        }
        return res;
    }

    private boolean diffOne(String a, String b) {
        int n = a.length();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                sum++;
                if (sum > 1) {
                    return false;
                }
            }
        }
        return sum == 1;
    }
}
