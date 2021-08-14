package cn.medemede.leecode;

/**
 * 有效数字 self
 */
public class IsNumber {
    int i;
    int n;
    char[] cs;

    public boolean isNumber(String s) {
        cs = s.toCharArray();
        i = 0;
        n = cs.length;

        if (!isDecimal()) {
            i = 0;
            if (isNotInteger()) {
                return false;
            }
        }

        if (i < n && (cs[i] == 'e' || cs[i] == 'E')) {
            i++;
            if (isNotInteger()) {
                return false;
            }
        }

        return i == n;
    }

    private boolean isDecimal() {
        if (i < n && (cs[i] == '+' || cs[i] == '-')) {
            i++;
        }
        int tmp = i;
        while (i < n && ('0' <= cs[i] && cs[i] <= '9')) {
            i++;
        }
        if (i < n && cs[i] == '.') {
            i++;
        } else {
            return false;
        }
        while (i < n && ('0' <= cs[i] && cs[i] <= '9')) {
            i++;
        }
        return i > tmp + 1;
    }

    private boolean isNotInteger() {
        if (i < n && (cs[i] == '+' || cs[i] == '-')) {
            i++;
        }
        if (i < n && ('0' <= cs[i] && cs[i] <= '9')) {
            while (i < n && ('0' <= cs[i] && cs[i] <= '9')) {
                i++;
            }
        } else {
            return true;
        }
        return false;
    }
}
