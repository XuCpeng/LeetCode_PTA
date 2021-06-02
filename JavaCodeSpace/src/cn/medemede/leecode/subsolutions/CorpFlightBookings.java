package cn.medemede.leecode.subsolutions;

public class CorpFlightBookings {

    /**
     * 航班预订统计
     * <p>差分数组</p>
     * <p>diff[i] 就是 nums[i] 和 nums[i-1] 之差</p>
     *
     * @param bookings
     * @param n
     * @return
     */
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] res = new int[n];
        int[] diff = new int[n + 1];

        for (int[] x : bookings) {
            diff[x[0] - 1] += x[2];
            diff[x[1]] -= x[2];
        }
        res[0] = diff[0];
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] + diff[i];
        }

        return res;
    }

}

