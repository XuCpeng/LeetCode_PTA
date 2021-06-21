package cn.medemede.leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二进制手表
 */
public class BinaryWatch {

    public List<String> readBinaryWatch(int turnOn) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                if (Integer.bitCount(i) + Integer.bitCount(j) == turnOn) {
                    res.add(i + (j < 10 ? ":0" : ":") + j);
                }
            }
        }
        return res;
    }


    int[] hours = new int[]{8, 4, 2, 1};
    int[] mins = new int[]{32, 16, 8, 4, 2, 1};
    int turnOn;
    List<Integer> hourList = new ArrayList<>();
    List<Integer> minList = new ArrayList<>();

    public List<String> readBinaryWatch2(int turnOn) {
        this.turnOn = turnOn;
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            hourList.clear();
            minList.clear();
            getHour(i, 0, 0, 0);
            getMin(turnOn - i, 0, 0, 0);
            for (Integer h : hourList) {
                for (Integer m : minList) {
                    res.add(h + (m < 10 ? ":0" : ":") + m);
                }
            }
        }
        return res;
    }

    private void getMin(int k, int i, int num, int numSize) {
        if (numSize == k) {
            if (num < 60)
                minList.add(num);
            return;
        }
        if (i >= 6 || numSize + (6 - i) < k) {
            return;
        }
        getMin(k, i + 1, num, numSize);
        num += mins[i];
        getMin(k, i + 1, num, numSize + 1);
    }

    private void getHour(int k, int i, int num, int numSize) {
        if (numSize == k) {
            if (num < 12)
                hourList.add(num);
            return;
        }
        if (i >= 4 || numSize + (4 - i) < k) {
            return;
        }
        getHour(k, i + 1, num, numSize);
        num += hours[i];
        getHour(k, i + 1, num, numSize + 1);
    }
}
