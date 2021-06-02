package cn.medemede.leecode.subsolutions;

public class IsPowerOfTwo {

    /**
     * 2的幂数
     * <p>n&(n-1)可消除n的最后一位1
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        return (n & (n - 1)) == 0;
    }

}

