package cn.medemede.leecode;

public class LongestConsecutive {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> vals=new HashSet<>();
        for(int x:nums){
            vals.add(x);
        }
        int maxLen=0;
        for(int x:nums){
            if(!vals.contains(x-1)){
                int len=0;
                while(vals.contains(x)){
                    len++;
                    x++;
                }
                maxLen=Math.max(maxLen,len);
            }
        }
        return maxLen;
    }
}
