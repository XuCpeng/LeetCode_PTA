package cn.medemede.leecode.subsolutions;

public class LengthOfLongestSubstring {

    /**
     * 最长无重复子串
     * <p>滑动窗口</p>
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
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

