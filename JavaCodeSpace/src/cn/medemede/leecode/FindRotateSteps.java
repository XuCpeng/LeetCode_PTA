package cn.medemede.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FindRotateSteps {

    int[][] memo;
    /**
     * 自由之路
     * <p>动态规划，递归</p>
     */
    char[] rings;
    char[] keys;
    HashMap<Character, ArrayList<Integer>> charToIndex;

    public int findRotateSteps(String ring, String key) {
        this.rings = ring.toCharArray();
        this.keys = key.toCharArray();
        this.memo = new int[rings.length][keys.length];
        this.charToIndex = new HashMap<>();
        for (int[] x : memo) {
            Arrays.fill(x, -1);
        }
        for (int i = 0; i < rings.length; i++) {
            charToIndex.computeIfAbsent(rings[i], k -> new ArrayList<>());
            charToIndex.get(rings[i]).add(i);
        }
        return getFindRotateSteps(0, 0);
    }

    private int getFindRotateSteps(int i, int j) {
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int minSteps = Integer.MAX_VALUE;
        ArrayList<Integer> targetIndex = charToIndex.get(keys[j]);

        if (j == keys.length - 1) {
            for (Integer index : targetIndex) {
                int dis = findRotateStepsDistance(i, index);
                if (dis < minSteps) {
                    minSteps = dis;
                }
            }
            memo[i][j] = minSteps + 1;
            return memo[i][j];
        }

        for (Integer index : targetIndex) {
            int dis = findRotateStepsDistance(i, index) + getFindRotateSteps(index, j + 1);
            if (dis < minSteps) {
                minSteps = dis;
            }
        }

        memo[i][j] = minSteps + 1;
        return memo[i][j];
    }

    private int findRotateStepsDistance(int i, int j) {
        return Math.min((i - j + rings.length) % rings.length, (j - i + rings.length) % rings.length);
    }


}

