/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/8/17
 * Time: 10:48 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */
public class FlipGameII_294 {
    /**
     * Recursive - O((n/2)!) ?
     */
    public boolean canWin(String s) {
        return flip(s.toCharArray());
    }

    private boolean flip(char[] game) {
        for (int i = 1; i < game.length; i++) {
            if (game[i] == '+' && game[i - 1] == '+') {
                game[i] = game[i - 1] = '-';
                if (!flip(game)) {
                    game[i] = game[i - 1] = '+';
                    return true;
                }
                game[i] = game[i - 1] = '+';
            }
        }
        return false;
    }
}
