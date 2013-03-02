import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

/**  SRM 569 DIV1 500 **/
public class TheJediTest
{
	public int minimumSupervisors(int[] students, int K)
	{
		int n = students.length;
		int total = 0;
		for(int i = 0; i < n-1; i++) {
			int x1 = students[i]% K;
			if(x1 == 0) {
				total += students[i] / K;
				continue;
			}
			int x2 = students[i+1] % K;
			if(x1+x2 >= K) {
				students[i] += K - x1;
				students[i+1] -= K - x1;
			} else {
				if(i+2<n) {
					int x3 = students[i+2] % K;
					if(x1+x2+x3 > K) {
						x3 = K - x1 - x2;
					}
					students[i] -= x1;
					students[i+1] += x1 + x3;
					students[i+2] -= x3;
				} else {
					students[i] += x2;
					students[i+1] -= x2;
				}
			}
			total += (students[i] + K -1) / K;
		}
		return total + (students[n-1] + K -1)/K;
	}
	
	
}
//Powered by [KawigiEdit] 2.0!
