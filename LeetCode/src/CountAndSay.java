/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/5/13
 * Time: 11:03 AM
 * To change this template use File | Settings | File Templates.
 */

public class CountAndSay {
    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);

    }

    public String countAndSay(int n) {
        String str = "1";
        while (n-- > 1) {
            int count = 1;
            char pre = str.charAt(0);
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < str.length(); i++) {
                if (str.charAt(i) == pre) {
                    count++;
                } else {
                    sb.append(count).append(pre);
                    pre = str.charAt(i);
                    count = 1;
                }
            }
            sb.append(count).append(pre);
            str = sb.toString();
        }

        return str;
    }
}
