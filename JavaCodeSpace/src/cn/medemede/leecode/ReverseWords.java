package cn.medemede.leecode;

/**
 * 剑指 Offer 58 - I. 翻转单词顺序
 */
public class ReverseWords {
    public String reverseWords(String s) {
        char[] sChars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        int n = sChars.length;
        int j = n - 1;
        while (j >= 0) {
            while (j >= 0 && sChars[j] == ' ') {
                j--;
            }
            if (j >= 0) {
                int i = j - 1;
                while (i >= 0 && sChars[i] != ' ') {
                    i--;
                }
                int k = i + 1;
                if (sb.length() != 0) {
                    sb.append(' ');
                }
                while (k <= j) {
                    sb.append(sChars[k]);
                    k++;
                }
                j = i;
            }
        }
        return sb.toString();
    }
}
