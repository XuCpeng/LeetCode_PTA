package cn.medemede.leecode;

public class LengthOfLongestSubstring {

    /**
     * 最长无重复子串
     * <p>滑动窗口</p>
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return 0;
        }
        char[] sChars = s.toCharArray();
        boolean[] memo = new boolean[128];
        int maxLen = 0;
        int left = 0;
        int right = 0;
        while (right < sChars.length) {
            if (!memo[sChars[right]]) {
                memo[sChars[right]] = true;
            } else {
                maxLen = Math.max(maxLen, right - left);
                while (sChars[left] != sChars[right]) {
                    memo[sChars[left]] = false;
                    left++;
                }
                left++;
            }
            right++;
        }
        maxLen = Math.max(maxLen, right - left);
        return maxLen;
    }

    public int lengthOfLongestSubstring2(String s) {
        int result = 0;
        int left = 0;
        int right = 0;
        boolean[] flag = new boolean[128];
        char[] ss = s.toCharArray();
        while (right < ss.length && !flag[ss[right]]) {
            flag[ss[right]] = true;
            right++;
        }
        if (right == ss.length) {
            return right;
        }
        result = right;
        while (right < ss.length) {
            while (ss[left] != ss[right]) {
                flag[ss[left]] = false;
                left++;
            }
            left++;
            right++;
            while (right < ss.length && !flag[ss[right]]) {
                flag[ss[right]] = true;
                right++;
            }
            if (right - left > result) {
                result = right - left;
            }
        }
        return result;
    }

}

