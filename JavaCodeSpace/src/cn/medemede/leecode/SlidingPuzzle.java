package cn.medemede.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class SlidingPuzzle {

    /**
     * 滑动谜题
     * <p>DFS，数组表示，慢。建议用字符串。</p>
     * <p>预先处理并记录状态转移矩阵</p>
     *
     * @param board
     * @return
     */
    public int slidingPuzzle(int[][] board) {
        ArrayList<Integer> oneBoard = new ArrayList<>();
        for (int j = 0; j < 3; j++) {
            oneBoard.add(board[0][j]);
        }
        for (int j = 0; j < 3; j++) {
            oneBoard.add(board[1][j]);
        }
        LinkedList<ArrayList<Integer>> queue = new LinkedList<>();
        queue.add(oneBoard);

        ArrayList<ArrayList<Integer>> mapping = new ArrayList<>();
        mapping.add(new ArrayList<>(Arrays.asList(1, 3)));
        mapping.add(new ArrayList<>(Arrays.asList(0, 2, 4)));
        mapping.add(new ArrayList<>(Arrays.asList(1, 5)));
        mapping.add(new ArrayList<>(Arrays.asList(0, 4)));
        mapping.add(new ArrayList<>(Arrays.asList(1, 3, 5)));
        mapping.add(new ArrayList<>(Arrays.asList(2, 4)));

        ArrayList<ArrayList<Integer>> visited = new ArrayList<>();
        visited.add(oneBoard);

        int res = 0;
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                ArrayList<Integer> aBoard = queue.pollFirst();
                if (isPuzzle(aBoard)) {
                    return res;
                }
                int zero = zeroIndex(aBoard);
                for (int x : mapping.get(zero)) {
                    ArrayList<Integer> tmp = new ArrayList<>(aBoard);
                    Collections.swap(tmp, zero, x);
                    if (!containPuzzle(visited, tmp)) {
                        queue.addLast(tmp);
                        visited.add(tmp);
                    }
                }
            }
            res++;
        }

        return -1;
    }

    private boolean isPuzzle(ArrayList<Integer> oneBoard) {
        for (int i = 0; i < 5; i++) {
            if (oneBoard.get(i) != i + 1) {
                return false;
            }
        }
        return oneBoard.get(5) == 0;
    }

    private int zeroIndex(ArrayList<Integer> aBoard) {
        for (int i = 0; i < 6; i++) {
            if (aBoard.get(i) == 0) {
                return i;
            }
        }
        return 0;
    }

    private boolean containPuzzle(ArrayList<ArrayList<Integer>> visited, ArrayList<Integer> a) {
        for (ArrayList<Integer> x : visited) {
            if (equalPuzzle(x, a)) {
                return true;
            }
        }
        return false;
    }

    private boolean equalPuzzle(ArrayList<Integer> a, ArrayList<Integer> b) {
        for (int i = 0; i < 6; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return false;
            }
        }
        return true;
    }
}

