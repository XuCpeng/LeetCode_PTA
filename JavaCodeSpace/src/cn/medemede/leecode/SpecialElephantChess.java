package cn.medemede.leecode;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 棋盘为10*9，r1，c1是象的位置，r2，c2是将的位置，象只能按moves走，返回象吃掉将的最少步数。
 */
public class SpecialElephantChess {
    private static final int[][] moves = new int[][]{{3, 2}, {3, -2}, {-3, 2}, {-3, -2}, {-2, 3}, {2, 3}, {-2, -3}, {2, -3}};

    public static void main(String[] args) {
        boolean[][] viewed = new boolean[10][9];
        Scanner in = new Scanner(System.in);
        int r1 = in.nextInt();
        int c1 = in.nextInt();
        int r2 = in.nextInt();
        int c2 = in.nextInt();
        LinkedList<int[]> queue = new LinkedList<>();
        queue.addLast(new int[]{r1, c1});
        viewed[r1][c1] = true;
        int step = 0;
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                int[] elephant = queue.pollFirst();
                if (elephant[0] == r2 && elephant[1] == c2) {
                    System.out.println(step);
                    return;
                }
                for (int[] move : moves) {
                    r1 = elephant[0] + move[0];
                    c1 = elephant[1] + move[1];
                    if (r1 <= 9 && r1 >= 0 && c1 <= 8 && c1 >= 0 && !viewed[r1][c1]) {
                        queue.addLast(new int[]{r1, c1});
                        viewed[r1][c1] = true;
                    }
                }
            }
            step++;
        }
        System.out.println(-1);
    }
}
