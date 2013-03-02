/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 2/2/13
 * Time: 12:27 AM
 * To change this template use File | Settings | File Templates.
 * <p/>
 * ZJU1015
 * POJ1068
 */

import java.util.*;

public class POJParentEncoding {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = Integer.parseInt(in.nextLine());
        String[] strs = new String[t];
        for(int i = 0; i < t; i++ ) {
            String nouse = in.nextLine();
            strs[i] = in.nextLine();
        }
        in.close();

        for(String str: strs) {
            String[] nums = str.split(" ");
            int[] P = new int[nums.length];
            for(int i = 0; i < nums.length; i++)
                P[i] = Integer.parseInt(nums[i]);
            process(P);
        }
    }

    static void process(int[] P) {
        int pre = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < P.length; i++) {
            int open = P[i] - pre;
            while (open-- > 0) sb.append('(');
            sb.append(')');
            pre = P[i];
        }

        String str = sb.toString();
        int[] dp = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ')') continue;
            if (str.charAt(i - 1) == '(')
                dp[i] = 1;
            else {
                int total = dp[i - 1];
                int index = i - 2 * dp[i - 1] - 1;
                while (str.charAt(index) == ')') {
                    total += dp[index];
                    index -= 2 * dp[index];
                }
                dp[i] = total+1;
            }
        }
        sb.setLength(0);
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > 0)
                sb.append(' ').append(dp[i]);
        }
        System.out.println(sb.toString().substring(1));
    }
}
