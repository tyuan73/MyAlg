/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 2/27/13
 * Time: 11:39 AM
 * To change this template use File | Settings | File Templates.
 */
import java.util.*;

public class Triplets {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] d = new int[n];
        for(int i = 0; i < n; i++)
            d[i] = in.nextInt();

        HashSet<Integer> first = new HashSet<Integer>();
        HashSet<Integer> second;
        HashSet<Integer> third;
        int total = 0;
        for(int i = 0; i < n-2; i++) {
            int x = d[i];
            if(first.contains(x)) continue;
            first.add(x);
            second = new HashSet<Integer>();
            for(int j = i+1; j < n-1; j++) {
                int y = d[j];
                if( y <= x || second.contains(y)) continue;
                second.add(y);
                third = new HashSet<Integer>();
                for(int k = j+1; k < n; k++) {
                    int z = d[k];
                    if(z <= y || third.contains(z)) continue;
                    third.add(z);
                    total++;
                }
            }
        }

        System.out.println(total);
    }
}
