import java.util.Arrays;

class MiscCoding {
	public static void main(String[] args) {
		//MiscCoding.transfer(-0);
		//MiscCoding.smallestWindow();
		MiscCoding.powof5();
	}
	
	static void powof5() {
		String[] arr = new String[22];
		long x = 1;
		for(int i = 0; i < 22; i++, x *= 5) {
			arr[i] = Long.toBinaryString(x);
		}
		
		Arrays.sort(arr);
		for(String s : arr) {
			System.out.printf("%52s with length: %3s\n", s, s.length()+"");
		}
	}
	
	/*
	[Google] Given an input array A of integers of size n, 
	and a query array B of integers of size m, find the smallest 
	window of input array that contains all the elements of query 
	array and also in the same order.
	例如:
	A = [1,9,3,4,12,13,9,12,21]
	B = [9, 12, 21] 那么应该返回A[6..8] = [9, 12, 21]
	*/
	static void smallestWindow() {
		int[] A = {1,9,3,4,12,13,9,12,21};
		int[] B = {9, 12, 21};
		
		System.out.println(smallestWindow(A, B, 0, 0));
	}
	
	static int smallestWindow(int[] A, int[] B, int i, int j) {
		if(A.length - i < B.length - j)
			return i;
		if(B.length == j)
			return i;
		if(A[i] == B[j])
			return Math.min(smallestWindow(A, B, i+1, j+1)-i,
					smallestWindow(A, B, i+1, j));
		return smallestWindow(A, B, i+1, j);
	}
	
	/*
	[Google] 一个int,转换成sign∗a∗2b的格式。
	例如: 7 = +1 ∗ 7 ∗ 2^0
	−14 = (−1) ∗ 7 ∗ 2^1
	*/
	static void transfer(int x) {
		if(x == 0) {
			System.out.println("0");
			return;
		}
			
		boolean sign = x < 0? true : false;
		x = sign ? -x : x;
		
		int count = 0;
		while((x & 1) == 0) {
			count++;
			x >>= 1;
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append(sign? "(-1)*" : "+1*");
		sb.append(x + "*");
		sb.append("2^" + count);
		System.out.println(sb.toString());
	}
}