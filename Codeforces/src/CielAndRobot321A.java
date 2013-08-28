/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 8/28/13
 * Time: 4:08 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class CielAndRobot321A {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int x = in.nextInt();
        int y = in.nextInt();

        String moves = in.next();
        int n = moves.length() + 1;
        int[][] map = new int[n][2];
        for (int i = 0; i < moves.length(); i++) {
            char ch = moves.charAt(i);
            switch (ch) {
                case 'U':
                    map[i + 1][0] = map[i][0];
                    map[i + 1][1] = map[i][1] + 1;
                    break;
                case 'D':
                    map[i + 1][0] = map[i][0];
                    map[i + 1][1] = map[i][1] - 1;
                    break;
                case 'L':
                    map[i + 1][0] = map[i][0] - 1;
                    map[i + 1][1] = map[i][1];
                    break;
                case 'R':
                    map[i + 1][0] = map[i][0] + 1;
                    map[i + 1][1] = map[i][1];
                    break;
            }
        }

        int lenX = map[n - 1][0], lenY = map[n - 1][1];
        for (int i = 0; i < n; i++) {
            int diffx = x - map[i][0], diffy = y - map[i][1];
            if (diffx == 0 && diffy == 0) {
                System.out.println("Yes");
                return;
            }
            if (lenX == 0 && lenY == 0) {
                continue;
            }
            if (lenX == 0) {
                if (diffx != 0) {
                    continue;
                }
                if (diffy % lenY == 0 && diffy / lenY > 0) {
                    System.out.println("Yes");
                    return;
                }
            } else if (lenY == 0) {
                if (diffy != 0) {
                    continue;
                }
                if (diffx % lenX == 0 && diffx / lenX > 0) {
                    System.out.println("Yes");
                    return;
                }
            } else if ((diffx % lenX) == 0 && (diffy % lenY) == 0) {
                if (diffx / lenX == diffy / lenY && diffx / lenX > 0) {
                    System.out.println("Yes");
                    return;
                }
            }
        }
        System.out.println("No");
    }
}
