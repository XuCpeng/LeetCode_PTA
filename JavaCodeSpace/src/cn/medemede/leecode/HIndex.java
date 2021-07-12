package cn.medemede.leecode;

import java.util.Arrays;

public class HIndex {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length;
        int i = 0;
        int j = n;
        while (i < j) {
            int mid = (i + j) / 2;
            if (citations[mid] == n - mid) {
                return citations[mid];
            } else if (citations[mid] > n - mid) {
                j = mid;
            } else {
                i = mid + 1;
            }
        }
        return n - i;
    }

    public static void main(String[] args) {
        HIndex hIndex = new HIndex();
        System.out.println(hIndex.hIndex(new int[]{3, 0, 6, 1, 5}));
    }
}
