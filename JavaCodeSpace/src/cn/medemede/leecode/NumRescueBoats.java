package cn.medemede.leecode;

import java.util.Arrays;

/**
 * 881. 救生艇
 */
public class NumRescueBoats {
    public int numRescueBoats(int[] people, int limit) {
        int res = 0;
        Arrays.sort(people);
        int i = 0;
        int j = people.length - 1;
        while (i < j) {
            if (people[i] + people[j] <= limit) {
                i++;
            }
            j--;
            res++;
        }
        if (i == j) {
            res++;
        }
        return res;
    }
}
