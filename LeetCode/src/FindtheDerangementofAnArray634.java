/**
 * Created by yuantian on 7/3/17.
 */
public class FindtheDerangementofAnArray634 {
    static final long P = 1000000007;

    /**
     * Derangement:
     * https://en.wikipedia.org/wiki/Derangement#Counting_derangements
     */


    /**
     * First solution using: dr[i] = (i-1) * (dr[i-1] + dr[i-2])
     */
    public int findDerangement(int n) {
        long dr1 = 1, dr2 = 0;
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = ((i - 1) * (dr1 + dr2)) % P;
            dr2 = dr1;
            dr1 = ans;
        }
        return (int) ans;
    }

    /**
     * The above solution can be shortened:
     */
    public int findDerangement1(int n) {
        long dr1 = 1, dr2 = 6;
        for (int i = 1; i <= n; i++) {
            dr1 = ((i - 1) * (dr2 + (dr2 = dr1))) % P;
        }
        return (int) dr1;
    }

    /**
     * A shorter solution
     * See: https://en.wikipedia.org/wiki/Derangement#Counting_derangements
     * for more details.
     */
    public int findDerangement2(int n) {
        long ans = 0;
        for (int i = 0, param = 1; i <= n; i++, param = -param) {
            ans = ((i * ans) + param) % P;
        }
        return (int) ans;
    }
}
