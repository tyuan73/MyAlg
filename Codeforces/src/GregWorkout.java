/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/4/13
 * Time: 1:27 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class GregWorkout {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        String[] str = {"chest","biceps", "back"};
        int[] exe = new int[3];
        for(int i = 0; i < n; i++) {
             exe[i%3] += in.nextInt();
        }
        int index = exe[0] > exe[1] ? 0 : 1;
        index = exe[index] > exe[2] ? index : 2;
        System.out.println(str[index]);
    }
}
