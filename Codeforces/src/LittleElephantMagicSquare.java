/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/4/13
 * Time: 2:53 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class LittleElephantMagicSquare {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int[][] square = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                square[i][j] = in.nextInt();
            }
        }

        int sum = square[0][1] + square[0][2];
        int diff = square[2][0] + square[2][1] - square[1][0] - square[1][2];
        square[1][1] = (sum + diff) / 2;
        square[2][2] = sum - square[1][1];
        square[0][0] = square[2][2] + square[2][1] - square[1][0];

        for (int i = 0; i < 3; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 3; j++) {
                sb.append(" ").append(square[i][j]);
            }
            System.out.println(sb.substring(1));
        }
    }
}
