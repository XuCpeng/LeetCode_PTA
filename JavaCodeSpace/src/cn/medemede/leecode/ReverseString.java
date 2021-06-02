package cn.medemede.leecode;

public class ReverseString {

    /**
     * 翻转字符串 char[]
     *
     * @param s
     */
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        char tmp;
        while (left < right) {
            tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
            left++;
            right--;
        }
    }

}

