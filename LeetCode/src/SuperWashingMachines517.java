/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/4/17
 * Time: 10:21 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */
public class SuperWashingMachines517 {
    public int findMinMoves(int[] machines) {
        int total = 0;
        for (int x : machines) total += x;
        if ((total % machines.length) != 0) return -1;

        int avg = total / machines.length;
        int target = 0, real = 0, max = 0;
        for (int x : machines) {
            int d = Math.abs(real - target);
            max = Math.max(max, Math.max(x - avg, d));
            real += x;
            target += avg;
        }
        return max;
    }
}
