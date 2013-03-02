/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 2/24/13
 * Time: 12:19 AM
 * To change this template use File | Settings | File Templates.
 *
 */

import java.util.*;

public class Requirement {
    static int n = 0;
    static int m = 0;
    static ArrayList<Integer>[] req;
    static int[] order;
    static int cur = 0;
    static int[] value;
    static int total = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();


        req = new ArrayList[n];
        for(int i = 0; i < n; i++)
            req[i] = new ArrayList<Integer>();

        for(int i = 0; i < m; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            req[from].add(to);
        }

        // detect cycle
        int[] status = new int[n];
        order = new int[n];
        cur = 0;
        for(int i = 0; i < n; i++) {
            if(status[i] == 0)
                if(hasCycle(i, status)) {
                    System.out.println(0);
                    return;
                }
        }

        for(int i : order)
            System.out.printf("%5d", i);
        System.out.println("OK");

        value = new int[n];
        count();
        System.out.println(total);

        for(int i : value)
            System.out.printf("%5d", i);
        System.out.println("OK");
    }

    static void count() {
        for(int i = 0; i < order.length; i++) {
            int r = order[i];
            //int orig = value[r];
            for(; value[r] <= 9; value[r]++){
                relax(i);
                total += 10 - value[0];
                System.out.println("total = "+ total);
            }
            //Arrays.fill(value, 0);
        }
    }

    static void relax(int i) {
        for(; i >= 0; i--) {
            int r = order[i];
            for(int next : req[r]) {
                if(value[next] < value[r])
                    value[next] = value[r];
            }
        }
    }

    static boolean hasCycle(int index, int[] status) {
        status[index] = 1;
        for(int i : req[index]) {
            if(status[i] == 1)
                return true;
            if(status[i] == 0 && hasCycle(i, status))
                return true;
        }
        status[index] = 2;
        order[cur++] = index;
        return false;
    }
}
