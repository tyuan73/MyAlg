import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;


// SRM 497 DIV1 250 points
public class PermutationSignature
{
	public int[] reconstruct(String signature)
	{
		signature += "I";
		int n =  signature.length();
		int[] ret = new int[n];
		
		int start = 0;
		for(int i = 0; i < n; i++) {
			if(signature.charAt(i) == 'I') {
				for(int j = start, k = i+1; j <= i; j++,k--) {
					ret[j] = k;
				}
				start = i+1;
			}
		}
		return ret;
	}
	
	<%: testing-code%>
}
//Powered by [KawigiEdit] 2.0!
