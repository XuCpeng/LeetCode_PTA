package cn.medemede.leecode;

public class CanWinNim {

    /**
     * Nim 游戏
     *
     * @param n
     * @return
     */
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }

}

