package cn.medemede.leecode;

import java.util.Scanner;

public class EatGrapes {

    /**
     * 吃葡萄
     * <p>a>2*(b+c):ceil(a/2)</p>
     * <p>a<=2*(b+c):ceil(a/3)</p>
     */
    public void eatGrapes() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            long a = in.nextLong();
            long b = in.nextLong();
            long c = in.nextLong();
            long tmp;
            if (b > a) {
                tmp = a;
                a = b;
                b = tmp;
            }
            if (c > a) {
                tmp = a;
                a = c;
                c = tmp;
            }
            if (c > b) {
                tmp = b;
                b = c;
                c = tmp;
            }
            if ((a + 1) / 2 > b + c) {
                System.out.println((a + 1) / 2);
            } else {
                System.out.println((a + b + c + 2) / 3);
            }

        }
    }

}

