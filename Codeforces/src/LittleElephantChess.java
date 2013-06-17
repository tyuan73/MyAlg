/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/4/13
 * Time: 2:41 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.Scanner;

public class LittleElephantChess {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = 8;
        while (n-- > 0) {
            String row = in.next();
            for (int i = 1; i < 8; i++) {
                if (row.charAt(i) == row.charAt(i - 1)) {
                    System.out.println("NO");
                    return;
                }
            }
        }

        System.out.println("YES");
    }
}
