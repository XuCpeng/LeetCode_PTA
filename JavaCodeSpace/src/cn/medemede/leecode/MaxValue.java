package cn.medemede.leecode;

/**
 * 有界数组中指定下标处的最大值
 */
public class MaxValue {
    private int cal(double a, double b, double c) {
        return (int) ((-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a));
    }

    public int maxValue2(int n, int index, int maxSum) {
        int h = 1;
        maxSum -= n;
        if (maxSum <= 0) {
            return h;
        }
        int l = index;
        int r = index;
        int len = 1;
        while (maxSum > 0 && --l >= 0 && ++r < n) {
            len += 2;
            maxSum -= len;
            h++;
        }
        if (maxSum <= 0) {
            return h;
        }
        while (maxSum > 0 && (--l >= 0 || ++r < n)) {
            len += 1;
            maxSum -= len;
            h++;
        }
        if (maxSum > 0) {
            h += maxSum % n == 0 ? maxSum / n : maxSum / n + 1;
        }
        return h;
    }

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
        System.out.println(maxValue.maxValue2(6, 1, 10));
    }
}
