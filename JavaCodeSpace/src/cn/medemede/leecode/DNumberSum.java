package cn.medemede.leecode;

public class DNumberSum {
    long getSum(int n) {
        long res = 0;
        for (int i = 0; i <= n; i++) {
            int tmp = i;
            long count = 0;
            while (tmp > 0) {
                count += tmp % 10;
                tmp /= 10;
            }
            res += (i - count);
        }
        return res;
    }

    public static void main(String[] args) {
        DNumberSum dNumberSum = new DNumberSum();
        System.out.println(dNumberSum.getSum(1000000000));
    }
}
