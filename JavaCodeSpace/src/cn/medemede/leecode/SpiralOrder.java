package cn.medemede.leecode;

public class SpiralOrder {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[]{};
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int high = 0;
        int low = m - 1;
        int left = 0;
        int right = n - 1;
        int[] res = new int[m * n];
        int i = 0;

        while (high < low && left < right) {

            for (int j = left; j < right; j++) {
                res[i] = matrix[high][j];
                i++;
            }


            for (int j = high; j < low; j++) {
                res[i] = matrix[j][right];
                i++;
            }


            for (int j = right; j > left; j--) {
                res[i] = matrix[low][j];
                i++;
            }


            for (int j = low; j > high; j--) {
                res[i] = matrix[j][left];
                i++;
            }
            high++;
            right--;
            low--;
            left++;
        }
        if (i < m * n) {
            if (low > high) {
                for (int j = high; j <= low; j++) {
                    res[i] = matrix[j][left];
                    i++;
                }
            } else {
                for (int j = left; j <= right; j++) {
                    res[i] = matrix[high][j];
                    i++;
                }
            }
        }

        return res;
    }
}
