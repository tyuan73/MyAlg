/**
 * Created by miaomiao on 7/8/17.
 */
public class SolvetheEquation640 {
    public String solveEquation(String eq) {
        String[] split = eq.split("=");
        int[] left = parse(split[0]);
        int[] right = parse(split[1]);
        int coef = left[0] - right[0];
        int value = right[1] - left[1];
        if (coef == 0) {
            return value == 0 ? "Infinite solutions" : "No solution";
        }
        return "x=" + (value / coef);
    }

    private int[] parse(String eq) {
        int[] ret = {0, 0};
        String[] sp = eq.split("(?=[+-])");
        for (String s : sp) {
            if (s.endsWith("x")) {
                String pre = s.substring(0, s.length() - 1);
                if (pre.length() == 0) ret[0] += 1;
                else if (pre.length() == 1 && (pre.charAt(0) == '+')) ret[0] += 1;
                else if (pre.length() == 1 && (pre.charAt(0) == '-')) ret[0] += -1;
                else ret[0] += Integer.parseInt(pre);
            } else {
                ret[1] += Integer.parseInt(s);
            }
        }
        return ret;
    }
}
