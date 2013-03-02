import java.util.Arrays;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 2/2/13
 * Time: 11:44 PM
 * To change this template use File | Settings | File Templates.
 * <p/>
 * 第二题是个 bipartite matching
 * http://en.wikipedia.org/wiki/Hopcroft%E2%80%93Karp_algorithm
 * <p/>
 */
public class Security {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = Integer.parseInt(in.nextLine());
        for (int t1 = 1; t1 <= t; t1++) {
            int m = Integer.parseInt(in.nextLine());
            String k1 = in.nextLine();
            String k2 = in.nextLine();

            String res = process(m, k1, k2);
            if (res.equals("g"))
                System.out.printf("Case #%d: IMPOSSIBLE\n", t1);
            else
                System.out.printf("Case #%d: %s\n", t1, res);
        }
    }

    static String process(int m, String k1, String k2) {
        String[][] map = new String[m][m];
        int l = k1.length() / m;
        boolean[] valid = new boolean[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = match(k1, k2, i * l, j * l, l) + j;
                if (!map[i][j].startsWith("g"))
                    valid[j] = true;
            }
            Arrays.sort(map[i]);
        }

        for (String[] x : map) {
            if (x[0].startsWith("g"))
                return "g";
        }
        for (boolean b : valid)
            if (!b)
                return "g";

        for (String[] x : map) {
            for (String y : x)
                System.out.print("  " + y);
            System.out.println();
        }

        StringBuilder res = new StringBuilder();
        boolean[] used = new boolean[m];
        if (getMin(map, used, 0, res, k1))
            return res.toString();
        else
            return "g";
    }

    static boolean getMin(String[][] map, boolean[] used, int index, StringBuilder res, String k1) {
        //System.out.println(index);

        int m = map.length;
        if (index == m) {
            return true;
        }

        int l = k1.length() / m;
        /*
        if(k1.substring(index*l, index*l+l).indexOf("?") < 0) {
            res.append(k1.substring(index*l, index*l+l));

            return getMin(map, used)
        }
        */

        for (int i = 0; i < m; i++) {
            String str = map[index][i];
            if (str.startsWith("g"))
                return false;
            int j = Integer.parseInt(str.substring(l));
            if (used[j])
                continue;
            used[j] = true;
            res.append(str.substring(0, l));
            if (getMin(map, used, index + 1, res, k1))
                return true;
            used[j] = false;
            res.setLength(res.length() - l);
        }
        return false;
    }

    static String match(String k1, String k2, int i, int j, int len) {
        StringBuffer sb = new StringBuffer();
        boolean match = true;
        for (int k = 0; k < len; k++) {
            char ch1 = k1.charAt(i + k);
            char ch2 = k2.charAt(j + k);
            if (ch1 != ch2 && ch1 != '?' && ch2 != '?') {
                match = false;
                break;
            }
            if (ch1 == '?' && ch2 == '?') {
                sb.append('a');
            } else {
                if (ch1 != '?')
                    sb.append(ch1);
                else
                    sb.append(ch2);
            }
        }
        if (!match) {
            sb.setLength(0);
            while (len-- > 0)
                sb.append('g');
        }
        return sb.toString();
    }
}
