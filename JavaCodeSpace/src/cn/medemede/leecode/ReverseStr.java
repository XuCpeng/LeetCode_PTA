package cn.medemede.leecode;

/**
 * 541. 反转字符串 II
 */
public class ReverseStr {
    public String reverseStr(String s, int k) {
        char[] sChars = s.toCharArray();
        int n = sChars.length;
        int i = 0;
        while (i < n) {
            int prevI = i;
            int j = i + k - 1;
            if (j >= n) {
                j = n - 1;
            }
            while (i < j) {
                char tmp = sChars[i];
                sChars[i] = sChars[j];
                sChars[j] = tmp;
                i++;
                j--;
            }
            i = prevI + 2 * k;
        }
        return String.valueOf(sChars);
    }
}
