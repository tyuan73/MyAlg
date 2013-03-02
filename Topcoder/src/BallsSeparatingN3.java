import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class BallsSeparatingN3
{
	public int minOperations(int[] red, int[] green, int[] blue)
	{
		int n = red.length;
		if(n < 3) return -1;
		int[][] moves = new int[n][3];
		int ret = 0;
		for(int i = 0; i < n; i++) {
			int cr = green[i] + blue[i];
			int cg = red[i] + blue[i];
			int cb = red[i] + green[i];
			int min = Math.min(cr, Math.min(cg, cb));
			moves[i][0] = cr - min;
			moves[i][1] = cg - min;
			moves[i][2] = cb - min;
			ret += min;
		}
		
		int min  = Integer.MAX_VALUE;
		for(int r = 0; r < n; r++) {
			for(int g = 0; g < n; g++) {
				if(g == r) continue;
				for(int b = 0; b < n; b++) {
					if(r == b || g == b) continue;
					min = Math.min(min, moves[r][0] + moves[g][1] + moves[b][2]);
				}
			}
		}
		
		return ret + min;
	}
}
//Powered by [KawigiEdit] 2.0!
