import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class AlternateColors
{
	public String getColor(long r, long g, long b, long k)
	{
		long min = Math.min(r, g);
		min = Math.min(min, b);
		
		if(k <= 3*min) {
			long x = k % 3;
			return getColor(x);
		}
		
		k -= 3*min;
		r -= min;
		g -= min;
		b -= min;
		if(r == 0){
			min = Math.min(g, b);
			if( k <= 2*min) {
				long x = k % 2;
				return x == 0? "BLUE" : "GREEN";
			}
		}
		if(g == 0) {
			min = Math.min(r, b);
			if(k <= 2*min) {
				long x = k % 2;
				return x == 0? "BLUE" : "RED";
			}
		}
		
		if(b == 0) {
			min = Math.min(r, g);
			if(k <= 2*min) {
				long x = k %2;
				return x == 0? "GREEN": "RED";
			}
		}
		
		r -= min;
		g -= min;
		b -= min;
		if(r > 0)
			return "RED";
		else if (g > 0)
			return "GREEN";
		
		return "BLUE";
	}

	
	String getColor(long i) {
		if(i == 0) return "BLUE";
		if(i == 1) return "RED";
		if(i == 2) return "GREEN";
		return "";
	}
	
	
}
//Powered by [KawigiEdit] 2.0!
