package cn.medemede.leecode;

public class IsMatch {

    int[][] memo;
    /**
     * 正则表达式匹配
     * <p>递归，备忘录</p>
     * <p>重点在于对*的处理，需要遍历匹配0次到多次的情况</p>
     */
    char[] sChars;
    char[] pChars;

    public boolean isMatch(String s, String p) {
        this.sChars = s.toCharArray();
        this.pChars = p.toCharArray();
        this.memo = new int[sChars.length + 1][pChars.length + 1];
        return getIsMatch(0, 0) == 1;
    }

    private int getIsMatch(int i, int j) {
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        if (i == sChars.length || j == pChars.length) {
            if (i != sChars.length) {
                return 2;
            }
            while (j + 1 < pChars.length && pChars[j + 1] == '*') {
                j += 2;
            }
            return j == pChars.length ? 1 : 2;
        }
        if (j + 1 < pChars.length && pChars[j + 1] == '*') {
            memo[i][j] = getIsMatch(i, j + 2);
            if (memo[i][j] == 2 && (sChars[i] == pChars[j] || pChars[j] == '.')) {
                memo[i][j] = getIsMatch(i + 1, j);
            }
        } else if (sChars[i] == pChars[j] || pChars[j] == '.') {
            memo[i][j] = getIsMatch(i + 1, j + 1);
        } else {
            memo[i][j] = 2;
        }
        return memo[i][j];
    }

}

