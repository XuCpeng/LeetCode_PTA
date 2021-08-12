package cn.medemede.leecode;

/**
 * 剑指 Offer 62. 圆圈中最后剩下的数字
 * <p>
 * f(n,m)=(f(n-1,m)+m)%n
 * <p>
 * 方法二递推（从下往上），最终结果为f(n,m)：
 * <p>
 * ```
 * f(n,m)=(f(n-1,m)+m)%n;
 * f(n-1,m)=(f(n-2,m)+m)%(n-1);
 * f(n-2,m)=(f(n-3,m)+m)%(n-2);
 * ...;
 * f(2,m)=(f(1,m)+m)%2;
 * f(1,m)=0;
 * ```
 */
public class LastRemaining {
    public int lastRemaining(int n, int m) {
        int fNM = 0;
        int i = 2;
        while (i <= n) {
            fNM = (fNM + m) % i;
            i++;
        }
        return fNM;
    }
}
