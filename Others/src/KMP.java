/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 1/31/13
 * Time: 10:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class KMP {
    public static void main(String[] args) {
        String t = "adhadfd";              // the text string
        String p = "adfd";              // the pattern

        int[] prefix = getPrefix(p);
        int j = 0;
        for (int i = 0; i < t.length(); i++) {
            while (j > 0 && p.charAt(j) != t.charAt(i))
                j = prefix[j - 1];
            if (p.charAt(j) == t.charAt(i))
                j++;
            if (j == p.length()) {
                System.out.println("pattern found");
                return;
            }
        }
        System.out.println("not found");
    }

    static int[] getPrefix(String p) {
        int[] prefix = new int[p.length()];
        int j = 0;
        for (int i = 1; i < p.length(); i++) {
            while (j > 0 && p.charAt(j) != p.charAt(i))
                j = prefix[j - 1];
            if (p.charAt(j) == p.charAt(i))
                j++;
            prefix[i] = j;
        }
        return prefix;
    }
}
