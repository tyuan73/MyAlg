/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/4/13
 * Time: 9:49 AM
 * To change this template use File | Settings | File Templates.
 */

import java.util.Scanner;

public class PointonSpiral {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int y = in.nextInt();
        int xa = Math.abs(x);
        int ya = Math.abs(y);

        int turns = 0;
        if (xa >= ya) {
            if (x < 0) {
                turns = (xa - 1) * 4 + 3;
                if (xa == ya && y > 0)
                    turns--;
            } else {
                if (xa == ya && y <= 0) {
                    turns = ya * 4;
                } else {
                    turns = (xa - 1) * 4 + 1;
                    if (xa == ya + 1 && y <= 0) {
                        turns--;
                    }
                }
            }
        } else {
            if (y > 0) {
                turns = (ya - 1) * 4 + 2;
                if (xa == ya && y > 0)
                    turns--;
            } else {
                turns = ya * 4;
                if (ya == xa && x < 0)
                    turns--;
            }
        }

        System.out.println(turns);
    }
}
