import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

/**  SRM 569 DIV2 250 **/
public class TheJediTestDiv2
{
	public int countSupervisors(int[] students, int Y, int J)
	{
		int total = 0; 
		int max = 0;
		for(int s : students) {
			int x = (s + J - 1)/J;
			total += x;
			if(s <= Y) {
				max = Math.max(max, x);
			} else {
				int y = (s-Y+J-1)/J;
				max = Math.max(max, x-y); 
			}
		}
		return total - max;
	}
	
	
}
//Powered by [KawigiEdit] 2.0!
