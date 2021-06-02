package cn.medemede.leecode;

public class ShipWithinDays {

    /**
     * 最小船载
     * <p>使用二分查找左边界
     * <p>查找到目标后right=mid，而非return
     * <p>mid=left+(right-left)/2 防止溢出
     * <p>left=mid+1,right=mid
     *
     * @param weights
     * @param D
     * @return
     */
    public int shipWithinDays(int[] weights, int D) {
        int left = 0;
        int right = 0;
        for (int weight : weights) {
            if (weight > left) {
                left = weight;
            }
            right += weight;
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            int days = 0;
            int tmpWeight = 0;

            for (int weight : weights) {
                tmpWeight += weight;
                if (tmpWeight > mid) {
                    days++;
                    tmpWeight = weight;
                }
            }
            days++;
            if (days <= D) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

}

