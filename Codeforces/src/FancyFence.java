/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/11/13
 * Time: 11:10 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class FancyFence {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        while(n-- > 0) {
            int d = in.nextInt();

            if(d < 180 && (360 % (180-d)) == 0)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}
