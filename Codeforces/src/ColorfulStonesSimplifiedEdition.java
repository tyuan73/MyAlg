/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/8/13
 * Time: 12:56 AM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class ColorfulStonesSimplifiedEdition {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String stones = in.next();
        String inst = in.next();
        int step = 0;
        for (int i = 0; i < inst.length(); i++) {
            if (inst.charAt(i) == stones.charAt(step))
                step++;
        }
        System.out.println(step + 1);
    }
}
