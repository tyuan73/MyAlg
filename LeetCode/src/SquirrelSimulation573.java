/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 5/7/17
 * Time: 11:50 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */
public class SquirrelSimulation573 {
    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int sum = 0, max = Integer.MIN_VALUE;
        for (int[] nut : nuts) {
            int d1 = Math.abs(nut[0] - tree[0]) + Math.abs(nut[1] - tree[1]);
            int d2 = Math.abs(nut[0] - squirrel[0]) + Math.abs(nut[1] - squirrel[1]);
            sum += d1;
            max = Math.max(max, d1 - d2);
        }
        return 2 * sum - max;
    }
}
