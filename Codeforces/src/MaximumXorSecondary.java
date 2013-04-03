/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/11/13
 * Time: 12:51 AM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class MaximumXorSecondary {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        Stack<Integer> S = new Stack<Integer>();
        int ret = 0;
        while (n-- > 0) {
            int x = in.nextInt();
            while (!S.empty() && S.peek() < x) {
                ret = Math.max(ret, S.pop() ^ x);
            }
            if (!S.empty())
                ret = Math.max(ret, S.peek() ^ x);
            S.push(x);
        }

        System.out.println(ret);
    }
}
