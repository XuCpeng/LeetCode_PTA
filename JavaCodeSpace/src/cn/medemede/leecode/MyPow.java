package cn.medemede.leecode;

/**
 * 剑指 Offer 16. 数值的整数次方
 */
public class MyPow {
    public double myPow(double x, int n) {
        double res = 1.0;
        if (n == 0) {
            return res;
        }

        // 防止-n越界
        long nL = n;

        // n<0时进行转换
        if (nL < 0) {
            x = 1 / x;
            nL = -nL;
        }

        while (nL > 0) {
            // n为奇数时，需要多乘一个x
            if ((nL & 1) == 1) {
                res *= x;
            }
            // x^n=(x^2)^(n/2)
            x *= x;

            // n/2
            nL >>= 1;
        }

        return res;
    }
}
