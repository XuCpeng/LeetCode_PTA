package cn.medemede.leecode;

/**
 * 根据字符出现频率排序
 */
public class FrequencySort {
    public String frequencySort(String s) {
        int n = s.length();
        int[] charFrequencies = new int[128];
        for (int i = 0; i < n; i++) {
            charFrequencies[s.charAt(i)]++;
        }
        StringBuilder sb = new StringBuilder();
        while (true) {
            int maxFreq = 0;
            char maxChar = 0;
            for (int i = 0; i < 128; i++) {
                if (charFrequencies[i] > maxFreq) {
                    maxFreq = charFrequencies[i];
                    maxChar = (char) i;
                }
            }
            if (maxFreq == 0) {
                break;
            } else {
                charFrequencies[maxChar] = 0;
                for (int i = 0; i < maxFreq; i++) {
                    sb.append(maxChar);
                }
            }
        }
        return sb.toString();
    }
}
