package cn.medemede.leecode;

/**
 * 剑指 Offer 46. 把数字翻译成字符串
 */
public class TranslateNum {

    // 递归
    public int translateNum(int num) {
        if (num < 10) {
            return 1;
        }
        int tmp = num % 100;
        if (tmp > 9 && tmp < 26) {
            return translateNum(num / 10) + translateNum(num / 100);
        } else {
            return translateNum(num / 10);
        }
    }

    // 动态规划
    public int translateNum2(int num) {
        int tmp = num;
        int n = 0;
        while (tmp > 0) {
            n++;
            tmp /= 10;
        }
        int[] numbers = new int[n];
        int i = n - 1;
        while (num > 0) {
            numbers[i] = num % 10;
            num /= 10;
            i--;
        }
        int dp2 = 1;
        int dp1 = 1;
        for (i = 1; i < n; i++) {
            int tmp1 = dp1;
            if (numbers[i - 1] > 0 && numbers[i - 1] * 10 + numbers[i] < 26) {
                tmp1 = dp1 + dp2;
            }
            dp2 = dp1;
            dp1 = tmp1;
        }
        return dp1;
    }
}
