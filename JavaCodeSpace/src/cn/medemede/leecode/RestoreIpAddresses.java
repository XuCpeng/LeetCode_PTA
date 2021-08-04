package cn.medemede.leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * 复原 IP 地址
 */
public class RestoreIpAddresses {
    public List<String> restoreIpAddresses(String s) {
        char[] sChars = s.toCharArray();
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        if (sChars.length < 4) {
            return res;
        }
        for (int i = 1; i <= 3 && isIP(sChars, 0, i); i++) {
            for (int j = i + 1; j <= i + 3 && j <= sChars.length && isIP(sChars, i, j); j++) {
                for (int k = j + 1; k <= j + 3 && k <= sChars.length && isIP(sChars, j, k); k++) {
                    if (isIP(sChars, k, sChars.length)) {
                        sb.append(sChars, 0, i);
                        sb.append(".");

                        sb.append(sChars, i, j - i);
                        sb.append(".");

                        sb.append(sChars, j, k - j);
                        sb.append(".");

                        sb.append(sChars, k, sChars.length - k);

                        res.add(sb.toString());
                        sb.delete(0, sb.length());
                    }
                }
            }
        }
        return res;
    }

    private boolean isIP(char[] sChars, int i, int j) {
        if (j <= i || j - i > 3) {
            return false;
        }
        if (sChars[i] == '0') {
            return j == i + 1;
        }
        return Integer.parseInt(String.valueOf(sChars, i, j - i)) <= 255;
    }

    public static void main(String[] args) {
        RestoreIpAddresses restoreIpAddresses = new RestoreIpAddresses();
        System.out.println(restoreIpAddresses.restoreIpAddresses("25525511135"));
    }
}
