package cn.medemede.leecode;

/**
 * 有效数字 self
 */
public class IsNumber {

    // 题解
    public boolean isNumber2(String s) {
        boolean hasNum = false;
        boolean hasDot = false;
        boolean hasE = false;
        boolean hasSymbol = false;

        char[] sChars = s.toCharArray();
        int i = 0;
        int n = sChars.length;
        while (i < n && sChars[i] == ' ') {
            i++;
        }
        while (i < n) {
            char c = sChars[i];
            if (c == '+' || c == '-') {
                if (hasSymbol || hasNum || hasDot) {
                    return false;
                }
                hasSymbol = true;
            } else if (c == '.') {
                if (hasDot || hasE) {
                    return false;
                }
                hasDot = true;
            } else if (c == 'e' || c == 'E') {
                if (hasE || !hasNum) {
                    return false;
                }
                hasNum = false;
                hasDot = false;
                hasSymbol = false;
                hasE = true;
            } else if (c >= '0' && c <= '9') {
                hasNum = true;
            } else if (c == ' ') {
                break;
            } else {
                return false;
            }
            i++;
        }
        while (i < n && sChars[i] == ' ') {
            i++;
        }
        return hasNum && i == n;
    }

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
