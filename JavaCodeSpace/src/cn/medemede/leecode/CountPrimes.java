package cn.medemede.leecode;

public class CountPrimes {

    /**
     * 小于n的质数的个数。
     * 质数：在大于 1 的自然数中，除了 1 和它本身以外不再有其他因数的自然数
     * <p>
     * 埃氏筛：质数的倍数都不是质数。
     * 从 x*x 开始标记，因为前面的已经被标记。
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

