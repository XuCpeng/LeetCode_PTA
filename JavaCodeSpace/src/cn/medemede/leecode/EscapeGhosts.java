package cn.medemede.leecode;

/**
 * 789. 逃脱阻碍者
 */
public class EscapeGhosts {

    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        int playerDist = manhattanDistance(0, 0, target[0], target[1]);
        for (int[] ghost : ghosts) {
            if (manhattanDistance(ghost[0], ghost[1], target[0], target[1]) <= playerDist) {
                return false;
            }
        }
        return true;
    }

    private int manhattanDistance(int x, int y, int tX, int tY) {
        return Math.abs(x - tX) + Math.abs(y - tY);
    }
}
