/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/14/13
 * Time: 10:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class IntToChar {
    static public void main(String[] args) {
        int[] test = {0, 120, 23, 26, 30, 10, 199, 2301, 344011, 1, 6789798, 123124532};
        for(int i: test) {
            System.out.println(i + " to string : " + count(i));
            System.out.println(i + " to string dp : " + countDP(i));
        }
    }

    static int countDP(int n) {
        String str = ""+n;
        int[] dp = new int[str.length()+1];
        //dp[1] = 1;
        for(int i = 1; i <= str.length(); i++) {
            if(str.charAt(i-1) == '0')
                dp[i] = dp[i-1];
            else {
                if(i == 1)
                    dp[1] = 1;
                else {
                    if(str.charAt(i-2) == '1' || (str.charAt(i-2) == '2' && str.charAt(i-1) <= '6'))
                        dp[i] = dp[i-1] + 1;
                }
            }
        }

        return dp[str.length()];
    }

    static public int count(int n) {
        if (n == 0)
            return 0;
        if (n <= 10 || n == 20)
            return 1;
        if (n <= 26)
            return 2;

        int ret = 0;
        int m = n % 10;
        if (m != 0)
            ret += count(n / 10);
        m = n % 100;
        if (m >= 10 && m <= 26)
            ret += count(n / 100);

        return ret;
    }
}
