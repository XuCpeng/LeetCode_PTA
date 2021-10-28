package cn.medemede.leecode;

import java.util.HashSet;
import java.util.LinkedList;

public class MinNumberZero {
    HashSet<String> memo;
    StringBuilder sb;

    public int minNumber(String str) {
        int length = str.length();
        memo = new HashSet<>();
        LinkedList<String> queue = new LinkedList<>();
        queue.addLast(str);
        memo.add(str);
        int res = 0;
        sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int index = 0; index < n; index++) {
                char[] tmpChars = queue.pollFirst().toCharArray();
                int i = 0;
                for (; i < length; i++) {
                    if (tmpChars[i] != '0') {
                        break;
                    }
                }
                if (i == length) {
                    return res;
                }
                for (int j = 0; j < length; j++) {
                    for (int k = j; k < length; k++) {
                        char[] tmp = tmpChars.clone();
                        int l = j;
                        for (; l <= k; l++) {
                            if (tmp[l] == '0') {
                                tmp[l] = '1';
                            } else {
                                tmp[l] = '0';
                            }
                        }
                        String tmpStr = sb.append(tmp).toString();
                        if (!memo.contains(tmpStr)) {
                            memo.add(tmpStr);
                            queue.addLast(tmpStr);
                        }
                        sb.delete(0, length);
                        tmp = tmpChars.clone();
                        l = j;
                        int p = k;
                        for (; l <= k; l++) {
                            tmp[l] = tmpChars[p];
                            p--;
                        }
                        tmpStr = sb.append(tmp).toString();
                        if (!memo.contains(tmpStr)) {
                            memo.add(tmpStr);
                            queue.addLast(tmpStr);
                        }
                        sb.delete(0, length);
                    }
                }
            }
            res++;
        }
        return res;
    }

    public static void main(String[] args) {
        MinNumberZero minNumberZero = new MinNumberZero();
        System.out.println(minNumberZero.minNumber("01011011001"));
    }
}
