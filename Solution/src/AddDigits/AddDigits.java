package AddDigits;

/**
 * Created by Zhang Hongchuan on 2016/6/27.
 * https://leetcode.com/problems/add-digits/
 */
public class AddDigits {
    public int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }
}
