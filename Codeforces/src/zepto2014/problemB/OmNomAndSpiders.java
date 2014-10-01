package zepto2014.problemB;

/**
 * Created by yuantian on 6/16/14.
 */
/*
http://codeforces.com/contest/436/problem/B
 */

import java.util.*;

public class OmNomAndSpiders {
    public static void main(String[] main) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        in.nextLine();
        int[] out = new int[m];
        in.nextLine();
        for(int row = 1; row < n; row++) {
            String line = in.nextLine();
            for(int i = 0; i < m; i++) {
                char ch = line.charAt(i);
                switch(ch) {
                    case 'U':
                        if (row % 2 == 0)
                            out[i]++;
                        break;
                    case 'L':
                        int col = i - row;
                        if (col >= 0) {
                            out[col]++;
                        }
                        break;
                    case 'R':
                        col = i + row;
                        if (col < m) {
                            out[col]++;
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        for(int i : out) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
