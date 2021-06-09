package cn.medemede.leecode;

import java.util.Random;

public class TargetAverage {
    public static void main(String[] args) {
        int score = 82;
        int k = 6;
        int sum = k * score;
        Random r = new Random();
        int adds = k * 5;
        int base = (sum - adds) / k;
        adds = sum - base * k;
        for (int i = 0; i < k; i++) {
            int tmp = r.nextInt(adds);
            System.out.println(base + tmp);
            adds -= tmp;
        }
    }
}
