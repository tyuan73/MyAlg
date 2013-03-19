/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/12/13
 * Time: 3:26 PM
 * To change this template use File | Settings | File Templates.
 */
import java.util.*;

public class PermutationSequence {
	public static void main(String[] args) {
		PermutationSequence ps = new PermutationSequence();
		System.out.println(ps.getPermutation(2, 2));
	}
	
    public String getPermutation(int n, int k) {
        int[] table = new int[10];
        table[1] = 1;
        for(int i = 2; i <= 9; i++)
            table[i] = table[i-1]*i;
            
        int[] num = new int[n];
        for(int i = 0; i < n; i++)
            num[i] = i+1;
        int index = n;
        while(k > 0) {
            while(table[index] > k)
                index--;
            int dis = (k-1) / table[index];
            k %= table[index];
            if(dis > 0) {
                int start = n-index-1;
                int x = num[start+dis];
                for(int i = start+dis; i > start; i--)
                    num[i] = num[i-1];
                num[start] = x;
            }
        }
        
		for(int i = n-index, j = n-1; i < j; i++, j--) {
			int x = num[i];
			num[i] = num[j];
			num[j] = x;
		}

        StringBuilder sb = new StringBuilder();
        for(int i : num)
            sb.append(i);
        return sb.toString();
    }
}
