package cn.medemede.leecode;

import java.util.Arrays;

public class Multiply {

    /**
     * 字符串乘法
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        while (i >= 0 && num1.charAt(i) == '0') {
            sb.append('0');
            i--;
        }
        while (j >= 0 && num2.charAt(j) == '0') {
            sb.append('0');
            j--;
        }
        if (i < 0 || j < 0) {
            return "0";
        }
        char[] carrying = new char[i + 2];
        Arrays.fill(carrying, '0');
        while (j >= 0) {
            int pre = 0;
            int p = i;
            while (p >= 0) {
                int v = (num1.charAt(p) - '0') * (num2.charAt(j) - '0');
                v += pre;
                v += carrying[p] - '0';
                pre = v / 10;
                carrying[p + 1] = (char) (v % 10 + '0');
                p--;
            }
            carrying[0] = (char) (pre + '0');
            sb.insert(0, carrying[i + 1]);
            j--;
        }
        while (i > 0) {
            sb.insert(0, carrying[i]);
            i--;
        }
        if (carrying[0] > '0') {
            sb.insert(0, carrying[0]);
        }

        return sb.toString();
    }

}

