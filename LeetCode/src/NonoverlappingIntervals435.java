/**
 * Created by yuantian on 2/17/17.
 */

import java.util.*;

public class NonoverlappingIntervals435 {
    public int eraseOverlapIntervals(Interval[] intervals) {
        Arrays.sort(intervals, (Interval a, Interval b) -> a.end - b.end);
        int ans = 0;
        int curEnd = Integer.MIN_VALUE;
        for (Interval intv : intervals) {
            if (intv.start < curEnd) {
                ans++;
            } else {
                curEnd = intv.end;
            }
        }
        return ans;
    }
}
