/**
 * Created by yuantian on 4/17/17.
 */
public class StudentAttendanceRecord_I_551 {
    public boolean checkRecord(String s) {
        int a = 0, l = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'A') {
                if (++a > 1) {
                    return false;
                }
            }

            if (s.charAt(i) == 'L') {
                if (++l > 2) {
                    return false;
                }
            } else {
                l = 0;
            }
        }
        return true;
    }
}
