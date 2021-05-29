package cn.medemede.leecode;

import java.util.HashSet;
import java.util.Objects;


public class RectangleCover {

    class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public boolean isRectangleCover(int[][] rectangles) {
        if (rectangles.length == 0) {
            return true;
        }
        HashSet<Point> points = new HashSet<>();
        int[] bigR = rectangles[0];
        int area = 0;
        for (int[] r : rectangles) {
            area += (r[2] - r[0]) * (r[3] - r[1]);
            if (r[0] <= bigR[0] && r[1] <= bigR[1]) {
                bigR[0] = r[0];
                bigR[1] = r[1];
            }
            if (r[2] >= bigR[2] && r[3] >= bigR[3]) {
                bigR[2] = r[2];
                bigR[3] = r[3];
            }
            addPoints(points, r);
        }
        return (bigR[2] - bigR[0]) * (bigR[3] - bigR[1]) == area && points.size() == 4 && isRectangle(points, bigR);
    }


    private Point[] get4Points(int[] r) {
        return new Point[]{
                new Point(r[0], r[1]),
                new Point(r[0], r[3]),
                new Point(r[2], r[1]),
                new Point(r[2], r[3])
        };
    }

    private void addPoints(HashSet<Point> points, int[] r) {
        Point[] peeks = get4Points(r);
        for (Point p : peeks) {
            if (points.contains(p)) {
                points.remove(p);
            } else {
                points.add(p);
            }
        }
    }

    private boolean isRectangle(HashSet<Point> points, int[] bigR) {
        Point[] peeks = get4Points(bigR);
        for (Point p : peeks) {
            if (!points.contains(p)) {
                return false;
            }
        }
        return true;
    }
}
