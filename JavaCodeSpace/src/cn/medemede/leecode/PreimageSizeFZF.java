package cn.medemede.leecode;

public class PreimageSizeFZF {

    /**
     * 阶乘后的零
     * <p>阶乘后的零的个数与因子5的个数有关。</p>
     * <p>fives = n/5 + n/25 + n/125 + n/625 + ... + 0</p>
     *
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        int res = 0;
        int i = 5;
        while (i <= n) {
            res += n / i;
            i *= 5;
        }
        return res;
    }

    public int trailingZeroes2(int n) {
        if (n == 0) {
            return 0;
        }
        return (n / 5) + trailingZeroes2(n / 5);
    }

    /**
     * 阶乘函数后 K 个零
     * <p>二分法</p>
     *
     * @param k
     * @return
     */
    public int preimageSizeFZF(long k) {
        return (int) (getPreimageSizeFZFRight(k) - getPreimageSizeFZFLeft(k));
    }

    private long getPreimageSizeFZFLeft(long k) {
        long left = 0;
        long right = Long.MAX_VALUE;
        while (left < right) {
            long mid = left + (right - left) / 2;
            long n = trailingZeroesLong(mid);
            if (n < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }

    private long getPreimageSizeFZFRight(long k) {
        long left = 0;
        long right = Long.MAX_VALUE;
        while (left < right) {
            long mid = left + (right - left) / 2;
            long n = trailingZeroesLong(mid);
            if (n > k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    public long trailingZeroesLong(long n) {
        long res = 0;
        long i = 5;
        while (i <= n) {
            res += n / i;
            i *= 5;
        }
        return res;
    }


}

