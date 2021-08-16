package cn.medemede.leecode;

/**
 * 剑指 Offer 66. 构建乘积数组
 */
public class ConstructArr {
    public int[] constructArr(int[] a) {
        int n = a.length;
        if (n == 0) {
            return new int[]{};
        }
        int[] b = new int[n];
        b[0] = 1;
        for (int i = 1; i < n; i++) {
            b[i] = b[i - 1] * a[i - 1];
        }
        int postV = a[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            b[i] = b[i] * postV;
            postV *= a[i];
        }
        return b;
    }
}
