public class KthEleIn2SortedArray {
	public static void main(String[] args) {
		int[] A = {1};
		int[] B = {1};
		KthEleIn2SortedArray kes = new KthEleIn2SortedArray();
		System.out.println("ret = " + kes.findMedianSortedArrays(A, B));
		
		int x = -1 / 2;
		System.out.println("x = " + x);
	}
	
    public double findMedianSortedArrays(int A[], int B[]) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(B.length < A.length)
            return internal(B, A);
        return internal(A, B);
    }
    
    double internal(int A[], int B[]) {
        int n = A.length;
        int m = B.length;
        
        int k = (n + m - 1) / 2;
        //n = Math.min(n, k); <== this is wrong.
        int l = 0, r = n;
        while(l < r) {
            int midA = (l + r) / 2;
            int midB = k - midA;
            if(midB > k|| A[midA] < B[midB])
                l = midA + 1;
            else
                r = midA;
        }
        
        int x = l > 0 ? A[l - 1]: Integer.MIN_VALUE;
        int y = k - l >= 0 ? B[k - l]: Integer.MIN_VALUE;
        x = Math.max(x, y);
        if((n + m) % 2 == 1)
            return x;
            
        y = l >= n? Integer.MAX_VALUE : A[l];
        int z = k - l + 1 >= m ? Integer.MAX_VALUE : B[k - l + 1];
        y = Math.min(y, z);
        return ((double) x + (double) y) / 2.0;
    }
}