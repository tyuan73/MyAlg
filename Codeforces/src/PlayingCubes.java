/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/4/13
 * Time: 2:02 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class PlayingCubes {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        if(n < m) {
            int x = n;
            n = m;
            m = x;
        }

        System.out.println((n-1) + " " + m);
    }
}
