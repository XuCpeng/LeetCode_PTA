package cn.medemede.leecode.subsolutions;

public class CountPrimes {

    /**
     * 小于n的质数的个数
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        boolean[] notPrime = new boolean[n];
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            if (!notPrime[i]) {
                ans += 1;
                if ((long) i * i < n) {
                    // 从x*x开始,而不是从2x开始
                    for (int j = i * i; j < n; j += i) {
                        notPrime[j] = true;
                    }
                }
            }
        }
        return ans;
    }

}

