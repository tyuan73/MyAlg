/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/3/13
 * Time: 10:57 PM
 * To change this template use File | Settings | File Templates.
 */
import java.util.*;

public class CircleOfNumbers {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] arcs = new int[n][5];

        for(int i = 1; i <= 2*n; i++) {
            int from = in.nextInt()-1;
            int to = in.nextInt()-1;
            if(arcs[from][0] >= 4 || arcs[to][0] >= 4) {
                System.out.println(-1);
                return;
            }
            arcs[from][++arcs[from][0]] = to;
            arcs[to][++arcs[to][0]] = from;
        }

        for(int[] a :arcs) {
            if(a[0] != 4) {
                System.out.println(-1);
                return;
            }
            a[0] = 0;
            Arrays.sort(a);
        }

        if(n == 5) {
            System.out.println("1 2 3 4 5");
            return;
        }

        int[] res = new int[n];
        int[] next2 = findNext2(arcs);
        if(next2 == null) {
            System.out.println(-1); return;
        }

        int index = 0;
        res[++index] = next2[0];
        res[++index] = next2[1];
        arcs[0][0] = 1;
        arcs[next2[0]][0] = 1;
        arcs[next2[1]][0] = 1;

        int pre1 = next2[0], pre2 = next2[1];
        while(index < n-1) {
            int x = findNext(arcs, pre1, pre2);
            if(x == -1) {
                System.out.println(-1);
                return;
            }
            res[++index] = x;
            arcs[x][0] = 1;
            pre1 = pre2;
            pre2 = x;
        }

        StringBuilder sb = new StringBuilder();
        for(int i : res)
            sb.append(" ").append(i+1);
        System.out.println(sb.substring(1));
    }

    static int findNext(int[][] arcs, int pre, int pre1) {
        int a = 1, b = 1;
        int count = 0;
        int next = -1;
        while(a <= 4 && b <= 4) {
            if(arcs[pre][a] == arcs[pre1][b]) {
                if(arcs[arcs[pre][a]][0] != 1) {
                    count++;
                    next = arcs[pre][a];
                }
                a++; b++;
            } else if(arcs[pre][a] > arcs[pre1][b]) {
                b++;
            } else
                a++;
        }
        if(count == 1)
            return next;
        return -1;
    }

    static int[] findNext2(int[][] arcs) {
        int last = -1;
        for(int j = 1; j <= 4; j++) {
            int i = arcs[0][j];
            int count = 0;

            int a = 1, b = 1;
            while(a <= 4 && b <= 4) {
                if(arcs[0][a] == arcs[i][b]) {
                    last = arcs[0][a];
                    count++;
                    a++; b++;
                } else if (arcs[0][a] < arcs[i][b])
                    a++;
                else
                    b++;
            }

            if(count == 1 || (count == 2 && arcs.length == 6)) {
                int[] ret = new int[2];
                ret[0] = last;
                ret[1] = i;
                return ret;
            }
            if(count != 2)
                break;
        }
        return null;
    }
}
