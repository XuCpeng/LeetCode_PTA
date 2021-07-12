package cn.medemede.leecode;

/**
 * 跳跃游戏 VII
 * <p>
 * 利用前缀数组，存储前缀的和，通过pre[right]-pre[left-1]>0判断当前i可达
 */
public class CanReach {

    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        char[] sChars = s.toCharArray();
        if (sChars[n - 1] != '0') {
            return false;
        }
        if (maxJump >= n - 1) {
            return true;
        }
        int[] pre = new int[n];
        for (int i = 0; i < minJump; i++) {
            pre[i] = 1;
        }
        int f = 0;
        for (int i = minJump; i < n; i++) {
            f = 0;
            if (sChars[i] == '0') {
                int l = i - maxJump;
                int r = i - minJump;
                int count = pre[r] - (l > 0 ? pre[l - 1] : 0);
                if (count > 0) {
                    f = 1;
                }
            }
            pre[i] = pre[i - 1] + f;
        }
        return f == 1;
    }
}
