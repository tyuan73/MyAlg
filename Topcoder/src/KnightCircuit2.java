import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class KnightCircuit2
{
	public int maxSize(int w, int h)
	{
		boolean[][] board = new boolean[h][w];
		visit(board, w, h);
		
		int count = 0;
		for(int i = 0; i < h; i++)
			for(int j = 0; j< w; j++)
				if(board[i][j])
					count++;
		
		return count;
	}
	
	void visit(boolean[][] board, int w, int h) {
		Stack<Integer> S = new Stack<Integer>();
		S.push(0); S.push(0);
		
		while(!S.empty()){
			int j = S.pop();
			int i = S.pop();
			if(board[i][j])
				continue;
			board[i][j] = true;
			int i1 = i + 1;
			int i2 = i - 1;
			int j1 = j + 2;
			int j2 = j - 2;
			if(i1 < h) {
				if(j1 < w) {
				S.push(i1); S.push(j1);}
				if(j2 >= 0) {
				S.push(i1); S.push(j2);
				}
			}
			if(i2 >= 0) {
				if(j1 < w) {
				S.push(i2); S.push(j1);
				}
				if(j2 >= 0)
				{S.push(i2); S.push(j2);}
			}
			i1 = i + 2;
			i2 = i - 2;
			j1 = j + 1;
			i2 = j - 1;
			if(i1 < h) {
				if(j1 < w)
				{S.push(i1); S.push(j1);}
				if(j2 >= 0)
				{S.push(i1); S.push(j2);}
			}
			if(i2 >= 0) {
				if(j1 < w) 
				{S.push(i2); S.push(j1);}
				if(j2 >= 0)
				{S.push(i2); S.push(j2);}
			}
		}
	}
	
	
}
//Powered by [KawigiEdit] 2.0!
