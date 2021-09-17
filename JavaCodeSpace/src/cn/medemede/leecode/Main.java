package cn.medemede.leecode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = Integer.parseInt(in.nextLine());
        for (int i = 0; i < T; i++) {
            String[] nandm = in.nextLine().split(" ");
            int n = Integer.parseInt(nandm[0]);
            int m = Integer.parseInt(nandm[1]);
            char[][] board = new char[n][m];
            boolean[][] visited = new boolean[n][m];
            int keys = 0;
            int startX = -1;
            int startY = -1;

            for (int j = 0; j < n; j++) {
                char[] row = in.nextLine().toCharArray();
                for (int k = 0; k < m; k++) {
                    board[j][k] = row[k];
                    if (row[k] == 'S') {
                        startX = j;
                        startY = k;
                    }

                    if (row[k] == 'K') {
                        keys++;
                    }
                }
            }
            int res = 0;

            int step = 0;
            LinkedList<int[]> queue = new LinkedList<>();
            queue.addLast(new int[]{startX, startY});
            visited[startX][startY] = true;


            while (!queue.isEmpty() && keys != 0) {
                int tmpN = queue.size();
                for (int q = 0; q < tmpN; q++) {
                    int[] index = queue.pollFirst();
                    char c = board[index[0]][index[1]];
                    if (c == 'K') {
                        keys--;
                        res += step;
                        step = -1;
                        board[index[0]][index[1]] = 'O';
                        queue.clear();
                        queue.addLast(index);
                        for (int j = 0; j < n; j++) {
                            Arrays.fill(visited[j], false);
                        }
                        visited[index[0]][index[1]] = true;
                        break;
                    }
                    if (index[0] - 1 >= 0 && !visited[index[0] - 1][index[1]] && board[index[0] - 1][index[1]] != 'X') {
                        queue.addLast(new int[]{index[0] - 1, index[1]});
                    }
                    if (index[1] - 1 >= 0 && !visited[index[0]][index[1] - 1] && board[index[0]][index[1] - 1] != 'X') {
                        queue.addLast(new int[]{index[0], index[1] - 1});
                    }
                    if (index[0] + 1 < n && !visited[index[0] + 1][index[1]] && board[index[0] + 1][index[1]] != 'X') {
                        queue.addLast(new int[]{index[0] + 1, index[1]});
                    }
                    if (index[1] + 1 < m && !visited[index[0]][index[1] + 1] && board[index[0]][index[1] + 1] != 'X') {
                        queue.addLast(new int[]{index[0], index[1] + 1});
                    }
                }
                step++;
            }
            if (keys != 0) {
                System.out.println(-1);
                break;
            }
            char c = 'X';
            while (!queue.isEmpty()) {
                int tmpN = queue.size();
                for (int q = 0; q < tmpN; q++) {
                    int[] index = queue.pollFirst();
                    c = board[index[0]][index[1]];
                    if (c == 'E') {
                        res += step;
                        System.out.println(res);
                        break;
                    }
                    if (index[0] - 1 >= 0 && !visited[index[0] - 1][index[1]] && board[index[0] - 1][index[1]] != 'X') {
                        queue.addLast(new int[]{index[0] - 1, index[1]});
                    }
                    if (index[1] - 1 >= 0 && !visited[index[0]][index[1] - 1] && board[index[0]][index[1] - 1] != 'X') {
                        queue.addLast(new int[]{index[0], index[1] - 1});
                    }
                    if (index[0] + 1 < n && !visited[index[0] + 1][index[1]] && board[index[0] + 1][index[1]] != 'X') {
                        queue.addLast(new int[]{index[0] + 1, index[1]});
                    }
                    if (index[1] + 1 < m && !visited[index[0]][index[1] + 1] && board[index[0]][index[1] + 1] != 'X') {
                        queue.addLast(new int[]{index[0], index[1] + 1});
                    }
                }
                if (c == 'E') {
                    break;
                }
                step++;
            }
            if (c != 'E') {
                System.out.println(-1);
            }
        }
    }
}