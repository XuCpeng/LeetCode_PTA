package cn.medemede.leecode;

import java.util.Random;

/**
 * 用 Rand7() 实现 Rand10()
 */
public class Rand10 {
    Random random = new Random();

    // a random integer in the range 1 to 10
    public int rand10() {
        int num = 41;
        while (num > 40) {
            num = (rand7() - 1) * 7 + rand7();
        }
        return num % 10 + 1;
    }

    public int sampleRand10() {
        int num = 11;
        while (num > 10) {
            num = (rand7() - 1) * 7 + rand7();
        }
        return num;
    }

    // a random integer in the range 1 to 7
    private int rand7() {
        return random.nextInt(7) + 1;
    }
}
