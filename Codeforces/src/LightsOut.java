/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/1/13
 * Time: 12:19 AM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class LightsOut {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] lights = new int[5][5];

        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                int x = in.nextInt();
                if ((x % 2) == 1) {
                    lights[i][j]++;
                    lights[i - 1][j]++;
                    lights[i + 1][j]++;
                    lights[i][j - 1]++;
                    lights[i][j + 1]++;
                }
            }
        }

        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++)
                if ((lights[i][j] % 2) == 1)
                    System.out.print(0);
                else
                    System.out.print(1);
            System.out.println();
        }
    }
}
