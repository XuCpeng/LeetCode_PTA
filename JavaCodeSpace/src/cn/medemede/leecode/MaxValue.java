package cn.medemede.leecode;

/**
 * 有界数组中指定下标处的最大值
 */
public class MaxValue {
    public int maxValue(int n, int index, int maxSum) {
        maxSum -= n;
        int h = 1;
        int left = index;
        int right = index;
        int nums = 1;
        while (maxSum > 0) {
            if (--left >= 0) nums++;
            if (++right < n) nums++;
            if (left < 0 && right >= n) {
                h += maxSum % n == 0 ? maxSum / n : maxSum / n + 1;
                return h;
            }
            h++;
            maxSum -= nums;
        }
        return h;
    }

    public static void main(String[] args) {
        MaxValue maxValue = new MaxValue();
        System.out.println(maxValue.maxValue(3, 2, 18));
    }
}
