import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class SimpleGuess
{
	public int getMaximum(int[] hints)
	{
		int max = 0;
		for(int i = 0; i < hints.length-1; i++)
			for(int j = i+1; j < hints.length; j++) {
				if(hints[i] % 2 != hints[j] %2)
					continue;
				int x = (hints[i]+hints[j])/2;
				int y = (hints[i]-hints[j])/2;
				if(y < 0) y = -y;
				max = Math.max(max, x*y);
			}
		
		return max;
	}
	
	
}
//Powered by [KawigiEdit] 2.0!
