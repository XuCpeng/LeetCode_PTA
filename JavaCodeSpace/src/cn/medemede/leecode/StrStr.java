package cn.medemede.leecode;

public class StrStr {

    /**
     * 实现 strStr()
     * <p>KMP算法</p>
     * <p>https://wiki.jikexueyuan.com/project/kmp-algorithm/define.html
     * <p>
     * next 数组的求解竟然如此简单：就是找最大对称长度的前缀后缀，然后整体右移一位，初值赋为 -1
     * （当然，你也可以直接计算某个字符对应的 next 值，就是看这个字符之前的字符串中有多大长度的相同前缀后缀）。
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0;
        }
        char[] haystackChars = haystack.toCharArray();
        char[] needleChars = needle.toCharArray();
        int[] next = getNextVal(needleChars);

        int i = 0;
        int j = 0;

        while (i < haystackChars.length && j < needleChars.length) {
            if (j == -1 || haystackChars[i] == needleChars[j]) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }

        if (j == needleChars.length) {
            return i - needleChars.length;
        } else {
            return -1;
        }
    }

    private int[] getNextVal(char[] needleChars) {
        int[] next = new int[needleChars.length];
        next[0] = -1;
        int k = -1;
        int i = 0;
        while (i < needleChars.length - 1) {
            if (k == -1 || needleChars[i] == needleChars[k]) {
                ++i;
                ++k;
                next[i] = k;
            } else {
                k = next[k];
            }
        }
        return next;
    }


}

