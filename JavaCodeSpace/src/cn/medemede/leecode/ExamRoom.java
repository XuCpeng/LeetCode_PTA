package cn.medemede.leecode;

import java.util.TreeSet;

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(n);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */
class ExamRoom {

    TreeSet<int[]> pq = new TreeSet<>((a, b) -> a[1] - a[0] - (b[1] - b[0]) == 0 ? a[0] - b[0] : a[1] - a[0] - (b[1] - b[0]));

    public ExamRoom(int n) {

    }

    public int seat() {

    }

    public void leave(int p) {

    }
}

