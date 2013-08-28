/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 8/28/13
 * Time: 2:32 PM
 * To change this template use File | Settings | File Templates.
 */
import java.util.*;

public class MagicNumbers320A {
    static public void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String str = in.next();
        int t = 2;
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch != '1' && ch != '4') {
                System.out.println("NO");
                return;
            }
            if(ch == '1') {
                t = 0;
            } else {
                t++;
            }
            if(t > 2) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
}
