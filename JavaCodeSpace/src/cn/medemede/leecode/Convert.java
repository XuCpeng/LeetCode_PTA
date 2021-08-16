package cn.medemede.leecode;

/**
 * 6. Z 字形变换
 */
public class Convert {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        char[] sChars = s.toCharArray();
        int n = sChars.length;
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (i < n) {
            stringBuilder.append(sChars[i]);
            i = i + numRows + (numRows - 2);
        }
        for (int j = 1; j < numRows - 1; j++) {
            int k = j;
            boolean flag = false;
            while (k < n) {
                stringBuilder.append(sChars[k]);
                if (flag) {
                    k = k + 2 * j;
                    flag = false;
                } else {
                    k = k + 2 * (numRows - j - 1);
                    flag = true;
                }
            }
        }
        i = numRows - 1;
        while (i < n) {
            stringBuilder.append(sChars[i]);
            i = i + numRows + (numRows - 2);
        }
        return stringBuilder.toString();
    }
}
