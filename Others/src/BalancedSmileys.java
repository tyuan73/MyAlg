import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 1/25/13
 * Time: 11:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class BalancedSmileys {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        String[] input = new String[n];
        for (int i = 0; i < n; i++) {
            input[i] = in.nextLine();
        }

        String[] strs = new String[n];
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            String str = input[i].replace(":(", "[");
            str = str.replace(":)", "]");
            for (int j = 0; j < str.length(); j++) {
                char ch = str.charAt(j);
                if ((ch < 'a' || ch > 'z') && ch != ':' && ch != ')' && ch != '(' && ch != ' ' && ch != '[' && ch != ']') {
                    sb.setLength(0);
                    sb.append(')');
                    break;
                }
                if (ch == '(' || ch == ')' || ch == '[' || ch == ']')
                    sb.append(ch);
            }
            strs[i] = sb.toString();
        }

        for (int i = 0; i < strs.length; i++) {
            if (valid(strs[i], 0, 0)) {
                System.out.println("Case #" + (i + 1) + ": " + "YES");
            } else
                System.out.println("Case #" + (i + 1) + ": " + "NO");
        }

        in.close();
    }

    static boolean valid(String s, int index, int count) {
        if (count < 0)
            return false;

        if (index == s.length()) {
            if (count == 0)
                return true;
            return false;
        }

        char ch = s.charAt(index);
        if (ch == '(') {
            return valid(s, index + 1, count + 1);
        } else if (ch == ')') {
            return valid(s, index + 1, count - 1);
        } else if (ch == '[') {
            return valid(s, index + 1, count) || valid(s, index + 1, count + 1);
        } else
            return valid(s, index + 1, count) || valid(s, index + 1, count - 1);
    }
}
