package cn.medemede.leecode;

public class SumZero {
    public int[] sumZero(int n) {
        int i = 0;
        int[] res = new int[n];
        int k = n / 2;
        if (2 * k < n) {
            res[i] = 0;
            i++;
        }
        while (k > 0) {
            res[i++] = k;
            res[i++] = -k;
            k--;
        }
        return res;
    }
}
