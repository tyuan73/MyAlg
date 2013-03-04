/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/4/13
 * Time: 1:48 AM
 * To change this template use File | Settings | File Templates.
 */
import java.util.*;

public class K_MultipleFreeSet {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();

        if(k == 1) {
            System.out.println(n);
            return ;
        }

        HashSet<Integer> set = new HashSet<Integer>();
        int[] data = new int[n];
        for(int i = 0; i < n; i++) {
            data[i] = in.nextInt();
            //set.add(data[i]);
        }
        //shuffle(data);
        Arrays.sort(data);
        int start = Arrays.binarySearch(data, k);

        if(start < 0)
            start = -start - 1;
        for(int i : data) {
            //int x = data[i];
            if((i%k) != 0 || !set.contains(i/k)) {
                set.add(i);
            }
        }

        System.out.println(set.size());
    }

    static void shuffle(int[] a)
    {
        int n=a.length;
        Random rnd = new Random(System.nanoTime());
        for (int i=0; i<n; ++i)
        {
            int v=rnd.nextInt(n-i);
            int tmp=a[i+v];
            a[i+v]=a[i];
            a[i]=tmp;
        }
    }
}
