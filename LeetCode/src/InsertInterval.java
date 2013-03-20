/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/5/13
 * Time: 3:59 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class InsertInterval {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }

    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        int i = 0;
        ArrayList<Interval> ret = new ArrayList<Interval>();
        while (i < intervals.size()) {
            Interval next = intervals.get(i);
            if (newInterval.start <= next.end && newInterval.end >= next.start) {
                newInterval.start = Math.min(newInterval.start, next.start);
                newInterval.end = Math.max(newInterval.end, next.end);
                i++;
            } else if (newInterval.end < next.start) {
                ret.add(newInterval);
                while (i < intervals.size())
                    ret.add(intervals.get(i++));
                return ret;
            } else {
                ret.add(next);
                i++;
            }
        }

        ret.add(newInterval);
        return ret;
    }
}
