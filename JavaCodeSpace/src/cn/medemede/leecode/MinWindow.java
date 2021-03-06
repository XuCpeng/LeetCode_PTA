package cn.medemede.leecode;

public class MinWindow {

    /**
     * 最小覆盖子串
     * <p>滑动窗口</p>
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        char[] ss = s.toCharArray();
        char[] ts = t.toCharArray();
        int resLeft;
        int resRight;
        int left = 0;
        int right = 0;
        int[] count = new int[128];
        for (char a : ts) {
            count[a]++;
        }
        while (right < ss.length && !isSubStr(count)) {
            count[ss[right]]--;
            right++;
        }
        if (!isSubStr(count)) {
            return "";
        }
        while (isSubStr(count)) {
            count[ss[left]]++;
            left++;
        }
        resLeft = left - 1;
        resRight = right;
        while (right < ss.length) {
            while (right < ss.length && !isSubStr(count)) {
                count[ss[right]]--;
                right++;
            }
            while (isSubStr(count)) {
                count[ss[left]]++;
                left++;
            }
            if (right - left + 1 < resRight - resLeft) {
                resLeft = left - 1;
                resRight = right;
            }
        }

        return s.substring(resLeft, resRight);
    }

    private boolean isSubStr(int[] count) {
        for (int c : count) {
            if (c > 0) {
                return false;
            }
        }
        return true;
    }


}

