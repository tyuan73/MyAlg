/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/4/13
 * Time: 10:29 AM
 * To change this template use File | Settings | File Templates.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Ladder {
    static class Ele {
        int start;
        int end;
        int dir;

        Ele(int s, int e, int d) {
            this.start = s;
            this.end = e;
            this.dir = d;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++)
            a[i] = in.nextInt();
        a[0] = a[1];

        /*
        int[] b = new int[n+1];
        for(int i = 1; i <= n; i++) {
            if(a[i] == a[i-1])
                b[i] = 0;
            else if(a[i] > a[i-1]) {
                b[i] = 1;
            } else
                b[i] = -1;
        }
        */

        ArrayList<Ele> ladder = new ArrayList<Ele>();
        int l = 0, f = 0;
        int d = 1;
        for (int i = 2; i <= n; i++) {
            if (a[i] == a[i - 1]) {
                if (a[i - 1] != a[i - 2])
                    f = i - 1;
            } else if (a[i] > a[i - 1]) {
                if (d != 1) {
                    if (a[i - 1] == a[1 - 2]) {
                        ladder.add(new Ele(l, f, -1));
                        ladder.add(new Ele(f, i - 1, 0));
                    } else
                        ladder.add(new Ele(l, i - 1, -1));
                    l = i - 1;
                    d = 1;
                }
            } else {
                if (d != -1) {
                    if (a[i - 1] == a[i - 2]) {
                        ladder.add(new Ele(l, f, 1));
                        ladder.add(new Ele(f, i - 1, 0));
                    } else
                        ladder.add(new Ele(l, i - 1, 1));
                    l = i - 1;
                    d = -1;
                }
            }
        }
        ladder.add(new Ele(l, n, d));

        while (m-- > 0) {
            int i = in.nextInt();
            int j = in.nextInt();
            if (i == j) {
                System.out.println("Yes");
                continue;
            }
            boolean ok = true;
            for (int k = 0; k < ladder.size(); k++) {
                Ele e = ladder.get(k);
                if (i >= e.end) continue;
                //if(i )

                //if(e.start <= i && e.end >= i) {
                if (e.start >= j && e.end >= j) {
                    System.out.println("Yes");
                    break;
                }
                Ele e1 = ladder.get(k + 1);
                if (e1.start >= j && e.end >= j) {
                    if (e1.dir == 0 || e.dir >= 0) {
                        System.out.println("Yes");
                        break;
                    }
                    System.out.println("No");
                    break;
                }
                /*
                    Ele e2 = ladder.get(k+2);
                    if(e2.start >= j && e2.end >= j) {
                        if((e.dir == 1 && e1.dir == 0) || (e.dir == 0 && e1.dir == 1) || () ) {
                            System.out.println("Yes");
                            break;
                        }
                    }
                    */
                System.out.println("No");
                //}
            }
        }

    }
}
