/**
 * Created by yuantian on 5/18/15.
 * <p/>
 * <p/>
 * <p/>
 * Albert and Bernard now want to know how old Cheryl is.
 * Cheryl:	I have two younger brothers. The product of all our ages (i.e. my age and the ages of my two brothers) is 144, assuming that we use whole numbers for our ages.
 * Albert:	We still don’t know your age. What other hints can you give us?
 * Cheryl:	The sum of all our ages is the bus number of this bus that we are on.
 * Bernard:	Of course we know the bus number, but we still don’t know your age.
 * Cheryl:	Oh, I forgot to tell you that my brothers have the same age.
 * Albert and Bernard:	Oh, now we know your age.
 */

/**
 *
 *
 Albert and Bernard now want to know how old Cheryl is.
 Cheryl:	I have two younger brothers. The product of all our ages (i.e. my age and the ages of my two brothers) is 144, assuming that we use whole numbers for our ages.
 Albert:	We still don’t know your age. What other hints can you give us?
 Cheryl:	The sum of all our ages is the bus number of this bus that we are on.
 Bernard:	Of course we know the bus number, but we still don’t know your age.
 Cheryl:	Oh, I forgot to tell you that my brothers have the same age.
 Albert and Bernard:	Oh, now we know your age.

 */

import java.util.*;

public class CherylAge {
    static class Tri {
        public int a, b, c;

        Tri(int x, int y, int z) {
            this.a = x;
            this.b = y;
            this.c = z;
        }
    }

    public static void main(String[] args) {

        for (int i = 4; i < 1000; i++)
            if (cal(i)) {
                System.out.println(i);
                System.out.println();
            }
    }

    static boolean cal(int n) {
        //int n = 5* 5*5* 4;
        int[] c1 = new int[n + 5];
        int[] c2 = new int[n + 5];

        List<Tri>[] ans = new List[n + 5];
        for (int i = 0; i < n + 5; i++)
            ans[i] = new ArrayList<Tri>();

        int a = 0, b = 0, c = 0;
        int rem;
        for (a = 1; a <= n; a++) {
            if ((n % a) != 0) continue;
            rem = n / a;
            for (b = 1; b <= a; b++) {
                if ((rem % b) != 0) continue;
                c = rem / b;
                if (c > b) continue;

                if (a == b || b == c) {
                    c1[a + b + c]++;
                } else {
                    c2[a + b + c]++;
                }
                ans[a + b + c].add(new Tri(a, b, c));
            }
        }

        int count = 0;
        for (int i = 0; i < n + 5; i++)
            if (c1[i] == 1 && c2[i] > 0) {
                count++;
            }

        if (count == 1) {
            print(ans);
            return true;
        }
        return false;

        /*
        for (int i = 0; i < n+5; i++) {
            if (ans[i].size() > 0) {
                for (int j = 0; j < ans[i].size(); j++) {
                    Tri t = ans[i].get(j);
                    System.out.print(i + " = " + t.a + "+" + t.b + "+" + t.c + ", ");
                }
                System.out.println();
            }
        }
        */
    }

    static void print(List<Tri>[] ans) {
        for (int i = 0; i < ans.length; i++) {
            if (ans[i].size() > 0) {
                for (int j = 0; j < ans[i].size(); j++) {
                    Tri t = ans[i].get(j);
                    System.out.print(i + " = " + t.a + "+" + t.b + "+" + t.c + ", ");
                }
                System.out.println();
            }
        }
    }
}
