package p10057;

/**
 * Created by yuantian on 4/6/15.
 */

import java.util.*;

class Main1 {
    final static int MAX = 65537;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] count = new int[MAX];

        while (in.hasNextLine()) {
            int n = Integer.parseInt(in.nextLine());
            Arrays.fill(count, 0);
            for (int i = 0; i < n; i++) {
                count[Integer.parseInt(in.nextLine())]++;
            }

            int midV = (n + 1) / 2;
            int c1 = 0, mid = 0;
            while (mid < MAX) {
                c1 += count[mid];
                if (c1 >= midV)
                    break;
                mid++;
            }
            if ((n & 1) == 1 || c1 > midV) {
                // if n is odd, or (n is even && c1 > midV)
                System.out.println(mid + " " + count[mid] + " 1");
            } else {
                int up = mid + 1;
                while (up < MAX && count[up] == 0) {
                    up++;
                }
                System.out.println(mid + " " + (count[mid] + count[up]) + " " + (up - mid + 1));
            }
        }
    }
}
