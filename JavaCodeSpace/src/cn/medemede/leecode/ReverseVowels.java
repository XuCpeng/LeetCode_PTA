package cn.medemede.leecode;

/**
 * 345. 反转字符串中的元音字母
 */
public class ReverseVowels {
    static char[] vowels = new char[]{'a', 'e', 'i', 'o', 'u'};

    public String reverseVowels(String s) {
        char[] sChars = s.toCharArray();
        int i = 0;
        int j = sChars.length - 1;
        while (i < j) {
            while (i < j && isNotVowel(sChars[i])) {
                i++;
            }
            while (j > i && isNotVowel(sChars[j])) {
                j--;
            }
            char tmp = sChars[i];
            sChars[i] = sChars[j];
            sChars[j] = tmp;
            i++;
            j--;
        }
        return String.valueOf(sChars);
    }

    private boolean isNotVowel(char c) {
        for (char v : vowels) {
            if (v == (c | ' ')) {
                return false;
            }
        }
        return true;
    }
}
