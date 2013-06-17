/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 2/27/13
 * Time: 12:57 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.Scanner;

public class LunchRush {
    static public void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int max = Integer.MIN_VALUE;
        while (n-- > 0) {
            int f = in.nextInt();
            int t = in.nextInt();
            int j = Math.max(0, t - k);
            max = Math.max(max, f - j);
        }
        System.out.println(max);
    }
}
