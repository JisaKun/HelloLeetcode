package NimGame;

/**
 * Created by Zhang Hongchuan on 2016/6/27.
 * https://leetcode.com/problems/nim-game/
 */
public class NimGame {

    public boolean canWinNim(int n) {
        if (n <= 3) {
            return true;
        }
        boolean[] flag = new boolean[4];  // flag[0] as new flag
        flag[1] = true;
        flag[2] = true;
        flag[3] = true;

        for (int i = 4; i <= n; i++) {
            flag[0] = !(flag[1] && flag[2] && flag[3]);
            for (int j = 1; j < 4; j++) {
                flag[j] = flag[(j + 1) % 4];
            }
        }
        return flag[3];
    }
}
