package cn.medemede.leecode;

/**
 * 剑指 Offer 67. 把字符串转换成整数
 */
public class StrToInt {
    public int strToInt(String str) {
        char[] strChars = str.toCharArray();
        int i = 0;
        while (i < strChars.length && (strChars[i] == ' ')) {
            i++;
        }
        long res = 0;
        long flag = 1;
        if (i < strChars.length) {
            if (strChars[i] == '-') {
                flag = -1;
                i++;
            } else if (strChars[i] == '+') {
                i++;
            }
        }

        while (i < strChars.length && strChars[i] >= '0' && strChars[i] <= '9') {
            res = res * 10 + (strChars[i] - '0');
            if (flag == 1 && res > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (flag == -1 && (-res) < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            i++;
        }

        return (int) (res * flag);
    }
}
