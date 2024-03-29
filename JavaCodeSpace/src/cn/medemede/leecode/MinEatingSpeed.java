package cn.medemede.leecode;

public class MinEatingSpeed {

    /**
     * 吃香蕉速度
     * <p>使用二分查找左边界
     * <p>查找到目标后right=mid，而非return
     * <p>mid=left+(right-left)/2 防止溢出
     * <p>left=mid+1,right=mid
     *
     * @param piles 每堆香蕉数量
     * @param h     总时间限制
     * @return 每小时最少吃多少根香蕉
     */
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 1;
        long count=0;
        for (int pile : piles) {
            if (pile > right) {
                right = pile;
            }
            count+=pile;
        }
        left=(int)(count/h);
        if(left==0){
            left=1;
        }
        while (left < right) {
            int mid = left + (right - left) / 2;
            int hours = 0;
            for (int pile : piles) {
                hours += pile / mid;
                if (pile % mid > 0) {
                    hours++;
                }
            }
            if (hours <= h) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

}

