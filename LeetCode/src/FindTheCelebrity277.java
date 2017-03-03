/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/2/17
 * Time: 9:29 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */
public class FindTheCelebrity277 {

    public int findCelebrity(int n) {
        int cel = 0;
        // if celebrity exits, it is a trap. anyone knows him/her, and
        // never get out.
        for (int i = 1; i < n; i++) {
            if (knows(cel, i))
                cel = i;
        }

        // verify the candiate is a real trap: all come in, no come out.
        for (int i = 0; i < n; i++) {
            if (i != cel && (knows(cel, i) || !knows(i, cel)))
                return -1;
        }
        return cel;
    }

    private boolean knows(int a, int b) {
        return true;
    }
}
