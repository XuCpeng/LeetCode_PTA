package cn.medemede.leecode;

/**
 * 1583. 统计不开心的朋友
 */
public class UnhappyFriends {

    // 官方
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        int[][] order = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                order[i][preferences[i][j]] = j;
            }
        }
        int[] match = new int[n];
        for (int[] pair : pairs) {
            int person0 = pair[0], person1 = pair[1];
            match[person0] = person1;
            match[person1] = person0;
        }
        int unhappyCount = 0;
        for (int x = 0; x < n; x++) {
            int y = match[x];
            int index = order[x][y];
            for (int i = 0; i < index; i++) {
                int u = preferences[x][i];
                int v = match[u];
                if (order[u][x] < order[u][v]) {
                    unhappyCount++;
                    break;
                }
            }
        }
        return unhappyCount;
    }


    int res;
    int[][] preferences;
    boolean[] notHappys;

    public int unhappyFriends2(int n, int[][] preferences, int[][] pairs) {
        res = 0;
        this.preferences = preferences;
        notHappys = new boolean[n];
        for (int[] pair : pairs) {
            int x = pair[0];
            int y = pair[1];
            int pXToY = preferencesXToY(x, y);
            int pYToX = preferencesXToY(y, x);
            for (int j = 0; j < pairs.length && (!notHappys[x] || !notHappys[y]); j++) {
                int u = pairs[j][0];
                int v = pairs[j][1];
                testX(x, u, v, pXToY);
                testX(y, u, v, pYToX);
            }
        }
        return res;
    }

    private void testX(int x, int u, int v, int pXToY) {
        if (notHappys[x]) {
            return;
        }
        int pXToU = preferencesXToY(x, u);
        if (pXToU < pXToY) {
            int pUToX = preferencesXToY(u, x);
            int pUToV = preferencesXToY(u, v);
            if (pUToX < pUToV) {
                notHappys[x] = true;
            }
        }
        if (!notHappys[x]) {
            int pXToV = preferencesXToY(x, v);
            if (pXToV < pXToY) {
                int pVToX = preferencesXToY(v, x);
                int pVToU = preferencesXToY(v, u);
                if (pVToX < pVToU) {
                    notHappys[x] = true;
                }
            }
        }
        if (notHappys[x]) {
            res++;
        }
    }

    private int preferencesXToY(int x, int y) {
        int pXToY = 0;
        while (pXToY < preferences[x].length && preferences[x][pXToY] != y) {
            pXToY++;
        }
        if (pXToY == preferences[x].length) {
            return Integer.MAX_VALUE;
        }
        return pXToY;
    }
}
