/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/13/13
 * Time: 10:31 AM
 * To change this template use File | Settings | File Templates.
 */

import java.util.Scanner;

public class BitPlusPlus {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int x = 0;
        while (n-- > 0) {
            String line = in.next();
            if (line.indexOf("+") >= 0)
                x++;
            else
                x--;
        }
        System.out.println(x);
    }
}
