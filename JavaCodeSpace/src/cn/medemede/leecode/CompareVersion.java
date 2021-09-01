package cn.medemede.leecode;

/**
 * 165. 比较版本号
 */
public class CompareVersion {
    public int compareVersion(String version1, String version2) {
        String[] v1ss = version1.split("\\.");
        String[] v2ss = version2.split("\\.");
        int n = Math.min(v1ss.length, v2ss.length);

        for (int i = 0; i < n; i++) {
            char[] v1I = v1ss[i].toCharArray();
            char[] v2I = v2ss[i].toCharArray();
            int p = 0;
            int q = 0;
            while (p < v1I.length && v1I[p] == '0') {
                p++;
            }
            while (q < v2I.length && v2I[q] == '0') {
                q++;
            }
            if (v1I.length - p > v2I.length - q) {
                return 1;
            }
            if (v1I.length - p < v2I.length - q) {
                return -1;
            }
            while (p < v1I.length && q < v2I.length) {
                if (v1I[p] > v2I[q]) {
                    return 1;
                }
                if (v1I[p] < v2I[q]) {
                    return -1;
                }
                p++;
                q++;
            }
        }
        if (v1ss.length > n) {
            for (int i = n; i < v1ss.length; i++) {
                char[] v1I = v1ss[i].toCharArray();
                for (char c : v1I) {
                    if (c != '0') {
                        return 1;
                    }
                }
            }
        }

        if (v2ss.length > n) {
            for (int i = n; i < v2ss.length; i++) {
                char[] v2I = v2ss[i].toCharArray();
                for (char c : v2I) {
                    if (c != '0') {
                        return -1;
                    }
                }
            }
        }

        return 0;
    }
}
