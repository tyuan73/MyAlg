/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/8/13
 * Time: 1:04 AM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class RoadsideTreesSimplifiedEdition {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int total = -1;
        int cur = 0;
        for (int i = 0; i < n; i++) {
            int h = in.nextInt();
            total += 2 + Math.abs(h - cur);
            cur = h;
        }

        System.out.println(total);
    }
}
