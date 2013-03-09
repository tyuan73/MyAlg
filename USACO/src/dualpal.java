
/**
 ID: tyuan731
 LANG: JAVA
 TASK: dualpal
 **/

import java.io.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Helena
 * Date: 2/5/13
 * Time: 12:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class dualpal {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("dualpal.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        // Get line, break into tokens
        int N = Integer.parseInt(st.nextToken());    // first integer
        int S = Integer.parseInt(st.nextToken());    // second integer
        for (int s = S + 1, n = 0; n < N; s++) {
            if (isOne(s)) {
                n++;
                out.println(s);
            }
        }

        out.close();                                  // close the output file
        System.exit(0);                               // don't omit this!
    }

    static boolean isOne(int s) {
        int[] t = new int[32];
        int count = 0;
        for (int base = 2; base <= 10; base++) {
            int index = 0;
            int x = s;
            while (x > 0) {
                t[index++] = x % base;
                x /= base;
            }
            boolean ok = true;
            for (int i = 0, j = index - 1; i < j; i++, j--) {
                if (t[i] != t[j]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                count++;
                if (count == 2)
                    return true;
            }
        }

        return false;
    }
}
