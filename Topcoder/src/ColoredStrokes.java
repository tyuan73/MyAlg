import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

// SRM 496 DIV1 250
public class ColoredStrokes
{
	public int getLeast(String[] picture)
	{
		int h = picture.length;
		int w = picture[0].length();
		int[][] b = new int[h+1][w+1];
		int total = 0;
		for(int i = 1; i <= h; i++)
		{
			String s = picture[i-1];
			for(int j = 1; j <= w; j++) {
				switch(s.charAt(j-1)) {
					case 'R':b[i][j] = 1; break;
					case 'B':b[i][j] = 2; break;
					case 'G':b[i][j] = 3; break;
				}
				if((b[i][j] & 1) > 0 && (b[i][j-1] & 1) == 0) total++;
				if((b[i][j] & 2) > 0 && (b[i-1][j] & 2) == 0) total++;
			}
		}

		return total;
	}
	
	
}
//Powered by [KawigiEdit] 2.0!
