package cn.medemede.leecode;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Entropy {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        double n = s.length();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char a = s.charAt(i);
            int aNum = map.getOrDefault(a, 0);
            map.put(a, aNum + 1);
        }
        double count = 0.0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            double p = entry.getValue() / n;
            count += -p * (Math.log(p) / Math.log(2));
        }
        String value = String.valueOf(count);
        int len = value.length() - value.indexOf(".");
        if (len > 7) {
            System.out.printf("%.7f", count);
        } else {
            System.out.println(count);
        }

    }
}
