/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 1/29/13
 * Time: 12:05 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.Scanner;

public class DivideOrange {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        int n = Integer.parseInt(line.split(" ")[0]);
        int k = Integer.parseInt(line.split(" ")[1]);

        String[] as = in.nextLine().split(" ");
        int[][] output = new int[k][n];
        for (int i = 0; i < k; i++) {
            output[i][0] = Integer.parseInt(as[i]);
        }

        in.close();

        int index = 0;
        int col = 1;
        for (int i = 0; i < n * k; i++) {
            int x = 0;
            while (x < k && output[x][0] != i + 1) x++;
            if (x == k) {
                output[index][col] = i + 1;
                index++;
                if (index == k) {
                    col++;
                    index = 0;
                }
            }
        }
        for (int[] x : output) {
            StringBuilder sb = new StringBuilder();
            for (int y : x) {
                sb.append(' ').append(y);
            }
            System.out.println(sb.toString().substring(1));
        }
    }
}
