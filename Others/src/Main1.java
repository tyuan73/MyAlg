import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
		if(k > n) k = n;
		int[] A = new int[n];
		
		for(int i = 0; i < n; i++) {
			A[i] = in.nextInt();
		}
		
		int[] B = new int[n];
		int[] C = new int[n];
		int[] max = new int[n - k + 1];
		int[] min = new int[n - k + 1];
		B[0] = 0; C[0] = 0;
		int l = 0, r = 1; int cl = 0, cr = 1;
		int j = 0;
		for(int i = 1; i < n; i++) {
			int x = A[i];
			int ll = l;
			while(ll < r) {
				int mid = (ll + r) / 2;
				if(A[B[mid]] > x)
					ll = mid + 1;
				else
					r = mid;
			}
			B[r++] = i;
			
			ll = cl;
			while(ll < cr) {
				int mid = (ll + cr) / 2;
				if(A[C[mid]] < x)
					ll = mid + 1;
				else
					cr = mid;
			}
			C[cr++] = i;
			
			if(i >= k - 1) {
				if(i - k >= B[l]) l++;
				if(i - k >= C[cl]) cl++;
				max[j] = A[B[l]];
				min[j] = A[C[cl]];
				j++;
				//System.out.print(" " + A[C[cl]]);
				//System.out.print(" " + A[B[l]]);
			}
		}
		
		//System.out.println("");
		//StringBuffer sb = new StringBuffer();
		for(int i : min)
			System.out.print(i + " ");
		System.out.println();
		for(int i : max)
			System.out.print(i + " ");
	}
}