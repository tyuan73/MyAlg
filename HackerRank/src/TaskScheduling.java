/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 2/21/13
 * Time: 11:06 PM
 * To change this template use File | Settings | File Templates.
 */
import java.util.*;

public class TaskScheduling {
    static class Schedule {
        int dl = 0;
        int mi = 0;
        int done = 0;
        Schedule(int deadline, int min) {this.dl = deadline; this.mi = min;}
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        //Schedule[] sch = new Schedule[n];
        int max = 0;
        ArrayList<Schedule> sch = new ArrayList<Schedule>();
        for(int i = 0; i < n; i++) {
            int dl = in.nextInt();
            int mi = in.nextInt();
            Schedule s = new Schedule(dl, mi);
            int j = i-1;
            while(j >= 0 && sch.get(j).dl > s.dl) {
                Schedule s1 = sch.get(j);
                if(s1.dl <= s.dl) break;
                s1.done += s.mi;
                max = Math.max(max, s1.done - s1.dl);
                j--;
            }
            sch.add(j+1, s);
            if(j < 0)
                s.done = s.mi;
            else
                s.done = sch.get(j).done + s.mi;

            max = Math.max(max, s.done - s.dl);

            System.out.println(max);
        }
    }
}
