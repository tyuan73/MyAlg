/**
 * Created by yuantian on 2/28/17.
 */

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * 4 different solutions  - all in O(n * lg(n)) runtime.
 * the first and the second are base on the same idea: counting - add 1 for each start and minus 1 for each end.
 * the third is based on a PriorityQueue.
 * the fourth is the fastest.
 */

public class MeetingRoomII253 {
    /**
     * the same idea as the next one. implemented with TreeMap;
     */
    public int minMeetingRooms(Interval[] intervals) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (Interval i : intervals) {
            map.put(i.start, map.getOrDefault(i.start, 0) + 1);
            map.put(i.end, map.getOrDefault(i.end, 0) - 1);
        }

        int max = 0, count = 0;
        for (Integer key : map.keySet()) {
            max = Math.max(max, count = count + map.get(key));
        }
        return max;
    }

    /**
     * The same idea as the first one. implemented with array. Faster then previous one.
     */
    class Ele implements Comparable<Ele> {
        int key, val;

        Ele(int key, int val) {
            this.key = key;
            this.val = val;
        }

        public int compareTo(Ele e) {
            if (this.key == e.key)
                return this.val - e.val;
            return this.key - e.key;
        }
    }

    public int minMeetingRooms1(Interval[] intervals) {
        int n = intervals.length;
        Ele[] a = new Ele[2 * n];
        for (int i = 0, j = 0; i < n; i++) {
            a[j++] = new Ele(intervals[i].start, 1);
            a[j++] = new Ele(intervals[i].end, -1);
        }

        Arrays.sort(a);

        int max = 0, count = 0;
        for (Ele x : a) {
            count += x.val;
            max = Math.max(max, count);
        }
        return max;
    }

    /**
     * A solution based on PriorityQueue
     */
    public int minMeetingRooms2(Interval[] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, (a, b) -> (a.start - b.start));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(Integer.MIN_VALUE);
        for (Interval i : intervals) {
            if (i.start >= pq.peek())
                pq.poll();
            pq.add(i.end);
        }
        return pq.size();
    }

    /**
     * A fastest solution
     */
    public int minMeetingRooms3(Interval[] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int rooms = 0;
        int endsItr = 0;
        for (int i = 0; i < starts.length; i++) {
            if (starts[i] < ends[endsItr])
                rooms++;
            else
                endsItr++;
        }
        return rooms;
    }
}

