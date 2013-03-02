import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

/**
 * SRM 571 DIV1 250
 *
 * recursive version
 */
public class FoxAndMp3Recursive
{
	public String[] playList(int n)
	{
		ArrayList<Integer> ret = new ArrayList<Integer>();
		getNumbers(n, 1, ret);
		int k = ret.size();
		String[] output = new String[k];
		for(int i = 0; i < ret.size(); i++)
			output[i] = ret.get(i) + ".mp3";
		return output;
	}
	
	void getNumbers(int n, int num, ArrayList<Integer> ret) {
		if(num > n || ret.size() >= 50 || ret.size() >= n)
			return;
		ret.add(num);
		getNumbers(n, num*10, ret);
		int max = Math.min(n, num / 10 * 10 +9);
		for(num = num+1; num <= max; num++) {
			if(num * 10 <= n) {
				getNumbers(n, num, ret);
				break;
			}
			if(ret.size() < 50 && ret.size() < n)
				ret.add(num);
			//if(ret.size() >= 50 || ret.size() >= n)
			//	return;
		}
	}
	
	
}
//Powered by [KawigiEdit] 2.0!
