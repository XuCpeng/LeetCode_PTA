package cn.medemede.leecode;

import java.util.Arrays;

public class PrintNumbers {
    int p;
    int start;
    String[] res;

    public String[] printNumbers(int n) {
        int maxN = (int) Math.pow(10, n);
        res = new String[maxN];
        p = 0;
        start = n - 1;
        dfs(0, new char[n]);
        return res;
    }

    private void dfs(int i, char[] num) {
        if (i == num.length) {
            res[p] = String.valueOf(num).substring(start);
            p++;

            int k = start;
            while (k < num.length && num[k] == '9') {
                k++;
            }
            if (k == num.length) {
                start--;
            }
            return;
        }
        for (int k = '0'; k <= '9'; k++) {
            num[i] = (char) k;
            dfs(i + 1, num);
        }
    }

    public static void main(String[] args) {
        PrintNumbers printNumbers = new PrintNumbers();
        System.out.println(Arrays.toString(printNumbers.printNumbers(2)));
    }
}
