package cn.medemede.leecode;

import java.util.Objects;

/**
 * 直线上最多的点数
 */
public class MaxPoints {
    static class Line {
        int x1;
        int y1;
        int x2;
        int y2;
        int y2minusy1;
        int x2minuxx1;

        public Line(int[] a, int[] b) {
            x1 = a[0];
            y1 = a[1];
            x2 = b[0];
            y2 = b[1];
            y2minusy1 = y2 - y1;
            x2minuxx1 = x2 - x1;
        }

        public boolean inLine(int[] c) {
            return (c[0] - x1) * y2minusy1 == x2minuxx1 * (c[1] - y1);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Line line = (Line) o;
            return x1 == line.x1 && y1 == line.y1 && x2 == line.x2 && y2 == line.y2;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x1, y1, x2, y2);
        }
    }


    public int maxPoints(int[][] points) {
        int maxLen = 1;
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                Line line = new Line(points[i], points[j]);
                int num = 0;
                for (int[] x : points) {
                    if (line.inLine(x)) {
                        num++;
                    }
                }
                if (num > maxLen) {
                    maxLen = num;
                }
            }
        }
        return maxLen;
    }
}
