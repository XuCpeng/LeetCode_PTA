package cn.medemede.leecode;

/**
 * 面试题 01.07. 旋转矩阵
 * <p>
 * 先左右翻转，再按对角线翻转
 */
public class Rotate {
    public void rotate(int[][] matrix) {
        int i = 0;
        int j = matrix.length - 1;
        while (i < j) {
            for (int k = 0; k < matrix[0].length; k++) {
                swap(matrix, i, k, j, k);
            }
            i++;
            j--;
        }
        i = 0;
        while (i < matrix.length) {
            j = i + 1;
            while (j < matrix[0].length) {
                swap(matrix, i, j, j, i);
                j++;
            }
            i++;
        }
    }

    private void swap(int[][] matrix, int i, int j, int p, int q) {
        int tmp = matrix[i][j];
        matrix[i][j] = matrix[p][q];
        matrix[p][q] = tmp;
    }
}
