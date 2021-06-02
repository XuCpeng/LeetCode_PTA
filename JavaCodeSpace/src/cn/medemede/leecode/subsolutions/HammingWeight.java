package cn.medemede.leecode.subsolutions;

public class HammingWeight {

    /**
     * 位1的个数
     * <p>n&(n-1)可消除n的最后一位1
     *
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            res++;
            n = n & (n - 1);
        }
        return res;
    }

}

