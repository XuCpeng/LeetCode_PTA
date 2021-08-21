package cn.medemede.leecode;

/**
 * 443. 压缩字符串
 */
public class Compress {
    public int compress(char[] chars) {
        int count = 0;
        int i = 0;
        int p = 0;
        int n = chars.length;
        while (i < n) {
            int j = i + 1;
            while (j < n && chars[j] == chars[i]) {
                j++;
            }
            chars[p] = chars[i];
            p++;
            count++;
            if (j - i > 1) {
                String tmp = String.valueOf(j - i);
                int tmpN = tmp.length();
                count += tmpN;
                for (int k = 0; k < tmpN; k++) {
                    chars[p] = tmp.charAt(k);
                    p++;
                }
            }
            i = j;
        }
        return count;
    }
}
