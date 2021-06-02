package cn.medemede.leecode.subsolutions;

public class SuperEggDrop {

    /**
     * 鸡蛋掉落
     * <p>最基本的方法：动态规划，递归，备忘录。超时！</p>
     * <p>可行的方法：动态规划，递归，备忘录，二分搜索。</p>
     *
     * @param k
     * @param n
     * @return
     */
    int[][] memo;

    public int superEggDrop(int k, int n) {
        this.memo = new int[k + 1][n + 1];
        return getSuperEggDrop(k, n);
    }

    private int getSuperEggDrop(int k, int n) {
        if (k == 1) {
            return n;
        }
        if (n == 0) {
            return 0;
        }
        if (memo[k][n] != 0) {
            return memo[k][n];
        }
        int left = 1;
        int right = n;
        int steps = Integer.MAX_VALUE;
        while (left <= right) {
            int mid = (left + right) / 2;
            // 当前层碎了，鸡蛋-1，向下搜索
            int a = getSuperEggDrop(k - 1, mid - 1);
            // 当前层没碎，鸡蛋数不变，向上搜索
            int b = getSuperEggDrop(k, n - mid);
            if (a > b) {
                right = mid - 1;
                steps = Math.min(steps, a + 1);
            } else {
                left = mid + 1;
                steps = Math.min(steps, b + 1);
            }
        }
        memo[k][n] = steps;
        return memo[k][n];
    }

    /**
     * 鸡蛋掉落
     * <p>高级方法：动态规划，dp数组。</p>
     * <p>dp[k][m] = n，当前有 k 个鸡蛋，可以尝试扔 m 次鸡蛋，最坏情况下最多能确切测试一栋 n 层的楼。</p>
     *
     * @param k
     * @param n
     * @return
     */
    public int superEggDrop2(int k, int n) {
        int[][] dp = new int[k + 1][n + 1];
        int i = 0;
        while (dp[k][i] < n) {
            i++;
            for (int j = 1; j <= k; j++) {
                dp[j][i] = dp[j][i - 1] + dp[j - 1][i - 1] + 1;
            }
        }
        return i;
    }

    /**
     * 鸡蛋掉落
     * <p>高级方法：动态规划，dp数组，状态压缩。</p>
     * <p>dp[k] = n，当前有 k 个鸡蛋，可以尝试扔"当前次"(下面代码中为i次)鸡蛋，最坏情况下最多能确切测试一栋 n 层的楼。</p>
     *
     * @param k
     * @param n
     * @return
     */
    public int superEggDrop3(int k, int n) {
        int[] dp = new int[k + 1];
        int i = 0;
        while (dp[k] < n) {
            i++;
            for (int j = k; j > 0; j--) {
                dp[j] = dp[j] + dp[j - 1] + 1;
            }
        }
        return i;
    }

}

