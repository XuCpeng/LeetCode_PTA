package cn.medemede.leecode;

/**
 * 剑指 Offer 65. 不用加减乘除做加法
 */
public class Add {
    public int add(int a, int b) {
        while (b != 0) {
            // 非进位和 n
            int n = (a ^ b);
            // 进位 c, res = n + c
            int c = (a & b) << 1;
            a = n;
            b = c;
        }
        return a;
    }

    public int add2(int a, int b) {
        if (b == 0) {
            return a;
        }
        // 非进位和 n
        int n = (a ^ b);
        // 进位 c
        int c = (a & b) << 1;
        // res = n + c
        return add2(n, c);
    }
}
