package cn.medemede.leecode;

import java.util.List;

/**
 * 串联字符串的最大长度
 */
public class MaxLength {
    List<String> arr;
    int[] count;
    int n;

    public int maxLength(List<String> arr) {
        this.arr = arr;
        count = new int[128];
        n = arr.size();
        return getLength(0);
    }

    private int getLength(int i) {
        if (i >= n) {
            int res = 0;
            for (int x : count) {
                res += x;
            }
            return res;
        }
        char[] str = arr.get(i).toCharArray();
        int j = 0;
        for (; j < str.length; j++) {
            char c = str[j];
            if (count[c] == 0) {
                count[c] = 1;
            } else {
                break;
            }
        }
        int a = 0;
        int b;
        if (j == str.length) {
            a = getLength(i + 1);
        }
        for (j = j - 1; j >= 0; j--) {
            count[str[j]] = 0;
        }
        b = getLength(i + 1);
        return Math.max(a, b);
    }
}
