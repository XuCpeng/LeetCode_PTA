package cn.medemede.leecode;

import java.util.Arrays;

public class MaxEnvelopes {

    /**
     * 俄罗斯套娃信封问题
     * <p>先排序：先按a[0]递增排序，a[0]相同时再按a[1]递减排序</p>
     * <P>后等同于最长递增子序列</P>
     *
     * @param envelopes
     * @return
     */
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }

        Arrays.sort(envelopes, (a, b) -> (a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]));
        int[] dp = new int[envelopes.length];
        dp[0] = envelopes[0][1];
        int res = 1;
        for (int[] x : envelopes) {
            int left = 0;
            int right = res - 1;
            while (left < right) {
                int mid = (left + right) / 2;
                if (dp[mid] >= x[1]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            if (dp[right] >= x[1]) {
                dp[right] = x[1];
            } else {
                dp[res] = x[1];
                res++;
            }
        }
        return res;
    }

}

