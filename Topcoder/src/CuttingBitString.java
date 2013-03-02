import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class CuttingBitString
{
	public int getmin(String S)
	{
		if(S.charAt(S.length() - 1) == '0')
			return -1;
			
		String[] arr = new String[22];
		long x = 1;
		for(int i = 0; i < 22; i++, x *= 5)
			arr[i] = Long.toBinaryString(x);
		Arrays.sort(arr);
		
		return get(arr, S);
	}
	
	int get(String[] arr, String s) {
		if(s.length() == 0)
			return 0;
		
		int pos = search(arr, s);
		int min = 100;
		for(int i = pos; i >= 0; i--) {
			if(prefix(arr[i], s)) {
				String sub = s.substring(arr[i].length());
				int ret = get(arr, sub);
				if(ret >= 0)
					min = Math.min(min, ret + 1);
			} 
		}
		return min == 100? -1 : min;
	}
	
	boolean prefix(String pre, String s) {
		if(pre.length() > s.length())
			return false;
		for(int i = 0; i < pre.length(); i++) 
			if(pre.charAt(i) != s.charAt(i))
				return false;
		return true;
	}
	
	int search(String[] arr, String s) {
		int l = 0, r = 21;
		while(l <= r) {
			int mid = (l + r) / 2;
			int res = compare(s, arr[mid]);
			if(res == 0)
				return mid;
			else if (res < 0)
				r = mid - 1;
			else
				l = mid + 1;
		}
		return l;
	}
	
	int compare(String s1, String s2) {
		int n1 = s1.length();
		int n2 = s2.length();
		for(int i = 0; i < n1 && i < n2; i++) {
			if(s1.charAt(i) < s2.charAt(i))
				return -1;
			else if(s1.charAt(i) > s2.charAt(i))
				return 1;
		}
		
		if(n1 == n2)
			return 0;
		if(n1 > n2)
			return 1;
		return -1;
	}
	
	
}
//Powered by [KawigiEdit] 2.0!
