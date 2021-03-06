package cn.medemede.leecode;

public class SuperPow {

    /**
     * 快速求幂思想，取模
     * <p>a^b=(a^(b/2))^2, b为偶数</p>
     * <p>a^b=a*a^(b-1), b为奇数</p>
     *
     * @param a
     * @param k
     * @return
     */
    public int myPow(int a, int k) {
        if (k == 0) {
            return 1;
        }
        if (k % 2 == 0) {
            int sub = myPow(a, k / 2);
            return (sub * sub) % 1337;
        } else {
            return ((a % 1337) * myPow(a, k - 1)) % 1337;
        }
    }

    /**
     * 超级次方
     * <p>递归</p>
     * <p>(a*b)%k=(a%k)*(b%k)%k</p>
     *
     * @param a
     * @param b
     * @return
     */
    public int superPow(int a, int[] b) {
        return getSuperPow(a, b, b.length - 1);
    }

    private int getSuperPow(int a, int[] b, int i) {
        if (i < 0) {
            return 1;
        }
        int p1 = myPow(a, b[i]);
        int p2 = myPow(getSuperPow(a, b, i - 1), 10);
        return (p1 * p2) % 1337;
    }

    /**
     * 超级次方
     * <p>非递归</p>
     * <p>(a*b)%k=(a%k)*(b%k)%k</p>
     *
     * @param a
     * @param b
     * @return
     */
    public int superPow2(int a, int[] b) {
        int res = 1;
        for (int j : b) {
            res = myPow(res, 10) * myPow(a, j) % 1337;
        }
        return res;
    }

}

