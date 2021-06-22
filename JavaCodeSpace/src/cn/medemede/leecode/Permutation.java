package cn.medemede.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 字符串的排列
 */
public class Permutation {
    Set<String> stringSet;
    int n;

    /**
     * 回溯+Swap+Set
     *
     * @param s
     * @return
     */
    public String[] permutation(String s) {
        this.n = s.length();
        this.stringSet = new HashSet<>();
        getPermutation(0, s.toCharArray());
        String[] res = new String[stringSet.size()];
        int i = 0;
        for (String str : stringSet) {
            res[i] = str;
            i++;
        }
        return res;
    }

    private void getPermutation(int i, char[] track) {
        if (i == n) {
            stringSet.add(String.copyValueOf(track));
            return;
        }
        for (int j = i; j < n; j++) {
            swap(track, i, j);
            getPermutation(i + 1, track);
            swap(track, i, j);
        }
    }

    /**
     * 先排序，依次获取下一个字典序的字符串
     *
     * @param s
     * @return
     */
    public String[] permutation2(String s) {
        this.n = s.length();
        ArrayList<String> strings = new ArrayList<>();
        char[] cs = s.toCharArray();
        Arrays.sort(cs);
        strings.add(String.copyValueOf(cs));
        while (nextPermutation(cs)) {
            strings.add(String.copyValueOf(cs));
        }
        String[] res = new String[strings.size()];
        int i = 0;
        for (String str : strings) {
            res[i] = str;
            i++;
        }
        return res;
    }

    public boolean nextPermutation(char[] track) {
        int i = n - 1;
        int j = n - 1;

        // 寻找 nums[i - 1] < nums[i]
        while (i > 0 && track[i - 1] >= track[i]) {
            i--;
        }
        if (i == 0) {
            return false;
        }

        // 寻找 nums[j] > nums[i-1]
        while (j > i - 1 && track[j] <= track[i - 1]) {
            j--;
        }
        swap(track, i - 1, j);

        // 反转 nums[i] ～ nums[n]
        j = n - 1;
        while (i < j) {
            swap(track, i, j);
            i++;
            j--;
        }
        return true;
    }

    private void swap(char[] track, int i, int j) {
        if (i == j) {
            return;
        }
        char a = track[i];
        track[i] = track[j];
        track[j] = a;
    }
}
