/**
 * Created by yuantian on 7/17/17.
 */

import java.util.*;

public class ExclusiveTimeofFunctions636 {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] time = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push(0);
        int cur = 0;
        for (String s : logs) {
            String[] sp = s.split(":");
            int func = Integer.parseInt(sp[0]);
            int t = Integer.parseInt(sp[2]);
            if (sp[1].equals("start")) {
                int pre = st.peek();
                time[pre] += t - cur;
                st.push(func);
                cur = t;
            } else {
                int pre = st.pop();
                time[pre] += t - cur + 1;
                cur = t + 1;
            }
        }
        return time;
    }

    /**
     * Hacker version
     */
    public int[] exclusiveTime1(int n, List<String> logs) {
        int[] time = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push(0);
        int last = 0;
        for (String s : logs) {
            String[] sp = s.split(":");
            if (sp[1].equals("start")) {
                time[st.peek()] += -last + (last = Integer.parseInt(sp[2]));
                st.push(Integer.parseInt(sp[0]));
            } else {
                time[st.pop()] += -last + (last = Integer.parseInt(sp[2]) + 1);
            }
        }
        return time;
    }
}
