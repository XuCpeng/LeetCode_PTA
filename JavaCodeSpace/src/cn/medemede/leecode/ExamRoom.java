package cn.medemede.leecode;

import java.util.HashMap;
import java.util.Objects;
import java.util.TreeSet;

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(n);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */
class ExamRoom {

    class Line {
        public int s;
        public int e;

        public Line(int s, int e) {
            this.s = s;
            this.e = e;
        }

        public int getDistance() {
            if (s == -1) return e;
            if (e == n) return n - 1 - s;
            return (e - s) / 2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Line line = (Line) o;
            return s == line.s && e == line.e;
        }

        @Override
        public int hashCode() {
            return Objects.hash(s, e);
        }
    }

    TreeSet<Line> pq;
    HashMap<Integer, Line> startToLine;
    HashMap<Integer, Line> endToLine;
    int n;

    public ExamRoom(int n) {
        this.n = n;
        this.startToLine = new HashMap<>();
        this.endToLine = new HashMap<>();
        this.pq = new TreeSet<>((a, b) -> {
            int distA = a.getDistance();
            int distB = b.getDistance();
            if (distA == distB)
                return a.s - b.s;
            return distB - distA;
        });
        pq.add(new Line(-1, n));
    }

    public int seat() {
        int i;
        Line maxLine = pq.pollFirst();
        if (maxLine.s == -1) {
            i = 0;
        } else if (maxLine.e == n) {
            i = n - 1;
        } else {
            i = (maxLine.s + maxLine.e) / 2;
        }
        Line left = new Line(maxLine.s, i);
        Line right = new Line(i, maxLine.e);
        pq.add(left);
        pq.add(right);
        endToLine.remove(maxLine.e);
        startToLine.remove(maxLine.s);
        startToLine.put(maxLine.s, left);
        endToLine.put(i, left);
        startToLine.put(i, right);
        endToLine.put(maxLine.e, right);
        return i;
    }

    public void leave(int p) {
        Line left = endToLine.get(p);
        Line right = startToLine.get(p);
        Line tmp = new Line(left.s, right.e);
        pq.add(tmp);
        startToLine.put(left.s, tmp);
        endToLine.put(right.e, tmp);
        pq.remove(left);
        pq.remove(right);
    }
}

