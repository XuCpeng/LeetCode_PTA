package cn.medemede.leecode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] firstLine = in.nextLine().split(" ");
        int h = Integer.parseInt(firstLine[0]);
        int w = Integer.parseInt(firstLine[1]);
        int t = Integer.parseInt(firstLine[2]);
        int sX = -1;
        int sY = -1;
        char[][] board = new char[h][w];
        boolean[][] visited = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            char[] row = in.nextLine().toCharArray();
            for (int j = 0; j < w; j++) {
                board[i][j] = row[j];
                if (row[j] == 'S') {
                    sX = i;
                    sY = j;
                }
            }
        }
        HashMap<int[], int[]> prev = new HashMap<>();
        LinkedList<int[]> queue = new LinkedList<>();
        int[] start = new int[]{sX, sY};
        queue.addLast(start);
        visited[sX][sY] = true;
        int[] index = start;
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                index = queue.pollFirst();
                if (board[index[0]][index[1]] == 'G') {
                    break;
                }
                LinkedList<int[]> tmpIndexList = new LinkedList<>();
                if (index[0] - 1 >= 0 && !visited[index[0] - 1][index[1]]) {
                    int[] tmp = new int[]{index[0] - 1, index[1]};
                    prev.put(tmp, index);
                    visited[index[0] - 1][index[1]] = true;
                    if (board[tmp[0]][tmp[1]] == '.') {
                        tmpIndexList.addFirst(tmp);
                    } else {
                        tmpIndexList.addLast(tmp);
                    }
                }
                if (index[1] - 1 >= 0 && !visited[index[0]][index[1] - 1]) {
                    int[] tmp = new int[]{index[0], index[1] - 1};
                    prev.put(tmp, index);
                    visited[index[0]][index[1] - 1] = true;
                    if (board[tmp[0]][tmp[1]] == '.') {
                        tmpIndexList.addFirst(tmp);
                    } else {
                        tmpIndexList.addLast(tmp);
                    }
                }
                if (index[0] + 1 < h && !visited[index[0] + 1][index[1]]) {
                    int[] tmp = new int[]{index[0] + 1, index[1]};
                    prev.put(tmp, index);
                    visited[index[0] + 1][index[1]] = true;
                    if (board[tmp[0]][tmp[1]] == '.') {
                        tmpIndexList.addFirst(tmp);
                    } else {
                        tmpIndexList.addLast(tmp);
                    }
                }
                if (index[1] + 1 < w && !visited[index[0]][index[1] + 1]) {
                    int[] tmp = new int[]{index[0], index[1] + 1};
                    prev.put(tmp, index);
                    visited[index[0]][index[1] + 1] = true;
                    if (board[tmp[0]][tmp[1]] == '.') {
                        tmpIndexList.addFirst(tmp);
                    } else {
                        tmpIndexList.addLast(tmp);
                    }
                }
                for (int[] tmpIndex : tmpIndexList) {
                    queue.addLast(tmpIndex);
                }
            }
            if (board[index[0]][index[1]] == 'G') {
                break;
            }
        }
        int steps = 0;
        int countX = 0;
        while (index != start) {
            char c = board[index[0]][index[1]];
            if (c == '#') {
                countX++;
            } else {
                steps++;
            }
            index = prev.get(index);
        }
        if (countX == 0) {
            System.out.println(-1);
        } else {
            System.out.println((t - steps) / countX);
        }
    }
}