/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/6/13
 * Time: 11:44 AM
 * To change this template use File | Settings | File Templates.
 */

import java.util.Scanner;

public class AddingDigits {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int a = in.nextInt();
        int b = in.nextInt();
        int n = in.nextInt();

        a *= 10;
        for (int i = 0; i <= 9; i++) {
            if (((a + i) % b) == 0) {
                a += i;
                StringBuilder sb = new StringBuilder();
                sb.append(a);
                while (n-- > 1)
                    sb.append("0");

                System.out.println(sb.toString());
                return;
            }
        }

        System.out.println(-1);
    }
}
