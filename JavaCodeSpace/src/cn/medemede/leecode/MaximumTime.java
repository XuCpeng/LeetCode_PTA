package cn.medemede.leecode;

/**
 * 替换隐藏数字得到的最晚时间
 */
public class MaximumTime {
    public String maximumTime(String time) {
        char[] timeChar = time.toCharArray();
        if (timeChar[0] == '?') {
            if (timeChar[1] != '?' && timeChar[1] > '3') {
                timeChar[0] = '1';
            } else {
                timeChar[0] = '2';
            }
        }
        if (timeChar[1] == '?') {
            if (timeChar[0] == '2') {
                timeChar[1] = '3';
            } else {
                timeChar[1] = '9';
            }
        }
        if (timeChar[3] == '?') {
            timeChar[3] = '5';
        }
        if (timeChar[4] == '?') {
            timeChar[4] = '9';
        }
        return String.copyValueOf(timeChar);
    }
}
