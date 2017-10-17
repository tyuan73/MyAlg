package MoodysAnalyticsFallUniversityCodeSprint;


/*

*/

import java.util.*;
import java.io.*;

public class LetsPlayAGame {
    static int playGame(String s, int[] arr) {
        int n = s.length();
        int[][] a = new int[n][2];
        for (int i = 0; i < n; i++) {
            a[i][0] = arr[i];
            if (s.charAt(i) == 'G' || s.charAt(i) == 'W')
                a[i][1] = 1;
        }
        Arrays.sort(a, (x, y) -> (x[0] - y[0]));
        int len = 1;
        int[] skip = {0, 0};
        for (int i = 0; i < n - 1; i++) {
            if (a[i][1] != a[i + 1][1]) {
                len++;
            } else {
                skip[a[i][1]]++;
            }
        }
        return len + Math.min(skip[0], skip[1]);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String s = in.next();
        int[] arr = new int[n];
        for (int arr_i = 0; arr_i < n; arr_i++) {
            arr[arr_i] = in.nextInt();
        }
        int result = playGame(s, arr);
        System.out.println(result);
        in.close();
    }
}