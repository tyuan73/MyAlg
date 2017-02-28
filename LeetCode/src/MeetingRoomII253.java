/**
 * Created by yuantian on 2/28/17.
 */

import java.util.*;

public class MeetingRoomII253 {
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
}

