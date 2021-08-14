package cn.medemede.leecode;

/**
 * 233. 数字 1 的个数
 * <p>
 * 思路：将各位的1相加。
 */
public class CountDigitOne {
    public int countDigitOne(int n) {
        // 10^k
        int mulk = 1;
        int res = 0;
        while (n >= mulk) {
            // 高位决定的本位1个数
            int high = (n / (mulk * 10)) * mulk;

            // 低位决定的本位1个数
            int low = 0;
            // 以本位为最高位的余数
            int tmp = n % (mulk * 10);
            if (tmp / mulk == 1) {
                // 本位为 1
                low = tmp % mulk + 1;
            } else if (tmp / mulk > 1) {
                // 本位大于1
                low = mulk;
            }

            res += high + low;
            mulk *= 10;
        }
        return res;
    }
}
