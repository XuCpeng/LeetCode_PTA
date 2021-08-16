package cn.medemede.leecode;

public class CountArrangement {
    int res;
    boolean[] flag;

    public int countArrangement(int n) {
        res = 0;
        flag = new boolean[n];
        getTracks(1, n);
        return res;
    }

    private void getTracks(int i, int n) {
        if (i > n) {
            res++;
            return;
        }
        for (int j = 0; j < n; j++) {
            if (!flag[j] && ((j + 1) % i == 0 || i % (j + 1) == 0)) {
                flag[j] = true;
                getTracks(i + 1, n);
                flag[j] = false;
            }
        }
    }
}
