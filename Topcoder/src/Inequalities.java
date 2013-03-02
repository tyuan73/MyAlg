import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class Inequalities
{
	public int maximumSubset(String[] inequalities)
	{
		int n = inequalities.length;
		double[][] b = new double[n][2];
		for(int i = 0; i < n; i++)
		{b[i][0] = -1;b[i][1] = 1001;}
		
		for(int i = 0; i<n; i++) {
			String s = inequalities[i];
			String[]  parts = s.split(" ");
			int c = Integer.parseInt(parts[2]);
			String s1 = parts[1];
			if(s1.equals("=")) {
				b[i][0] = c;b[i][1] = c;
			} else if(s1.equals(">=")) {
				b[i][0] = c;
			} else if(s1.equals("<=")) {
				b[i][1] = c;
			} else if(s1.equals(">")) {
				b[i][0] = c+0.1;
			} else {
				b[i][1] = c-0.1;
			}
		}
		
		int max = 1;
		int[] flag = {0,1};
		for(int i = 0; i < n; i++) {
			for(int f :flag) {
				int cur = 0;
				double left = b[i][f];
				for(int j = 0; j < n; j++) {
					if(left >= b[j][0] && left <= b[j][1])
						cur++;
				}
				max = Math.max(max, cur);
			}
		}
		return max;
	}
	
	
}
//Powered by [KawigiEdit] 2.0!
