package cn.medemede.leecode;

import java.util.Scanner;

public class SpecialKeyBoard {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String firstLine = in.nextLine();
        String[] firstLines = firstLine.split(" ");

        // 键盘为n行m列
        int n = Integer.parseInt(firstLines[0]);
        int m = Integer.parseInt(firstLines[1]);
        // 移动一格的时间为x
        int x = Integer.parseInt(firstLines[2]);
        // 转向的时间为y
        int y = Integer.parseInt(firstLines[3]);
        // 点击的时间为z
        int z = Integer.parseInt(firstLines[4]);

        //输入键盘
        int[][] keyBoard = new int[127][2];
        for (int i = 0; i < n; i++) {
            char[] row = in.nextLine().toCharArray();
            for (int j = 0; j < m; j++) {
                char c = row[j];
                keyBoard[c][0] = i;
                keyBoard[c][1] = j;
            }
        }

        // 需要打印的字符串
        char[] strChars = in.nextLine().toCharArray();

        // 起始位置为键盘左上角
        int p = 0;
        int q = 0;

        // 所需时间
        int count = 0;
        for (char c : strChars) {
            int diffX = Math.abs(keyBoard[c][0] - p);
            int diffY = Math.abs(keyBoard[c][1] - q);
            count += (diffX + diffY) * x;
            if (diffX != 0 && diffY != 0) {
                count += y;
            }
            count += z;
            p = keyBoard[c][0];
            q = keyBoard[c][1];
        }
        System.out.println(count);
    }
}
