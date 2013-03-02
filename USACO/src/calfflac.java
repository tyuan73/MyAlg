/*
ID: tyuan731
LANG: JAVA
TASK: calfflac
*/
import java.io.*;
import java.util.*;

class calfflac {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("calfflac.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("calfflac.out")));
	
	StringBuffer sb = new StringBuffer();
	String line = f.readLine();
	while(line != null) {
		sb.append(line).append('\n');
		line = f.readLine();
	}
	sb.setLength(sb.length()-1);
	line = sb.toString();
	int[] map = new int[line.length()];
	char[] str = new char[line.length()];
	int index = 0;
	for(int i = 0; i < line.length(); i++) {
		char ch = line.charAt(i);
		if(ch >= 'A' && ch <= 'Z')
			ch -= 'A'-'a';
		if(ch >= 'a' && ch <= 'z')
		{	str[index] = ch; map[index++] = i;}
	}
	
	char[] newstr = preprocess(str, index);
	int[] rad = new int[newstr.length];
	int center = 0, r = 0;
	int maxLen = 1, start = 1;
	for(int i = 1; i < newstr.length-1; i++) {
		int mirror = 2*center - i;
		rad[i] = (r > i) ? Math.min(r-i, rad[mirror]) : 0;
		for(int j = i-rad[i]-1, k = i+rad[i]+1; newstr[j] == newstr[k]; j--, k++)
			rad[i]++;
		if(i+rad[i] > r) {
			center = i;
			r = i+ rad[i];
		}
		if(rad[i] > maxLen) {
			maxLen = rad[i];
			start = i;
		}
	}
	start = (start - maxLen - 1) / 2;

	out.println(maxLen);
	out.println(line.substring(map[start], map[start+maxLen-1]+1));
	
    out.close();                                  // close the output file
    System.exit(0);                               // don't omit this!
  }
  
  static char[] preprocess(char[] str, int len) {
	char[] ret = new char[len*2+3];
	ret[0] = '^';
	int j = 1;
	for(int i = 0; i < len; i++) {
		ret[j++] = '*';
		ret[j++] = str[i];
	}
	ret[ret.length-2] = '*';
	ret[ret.length-1] = '$';
	return ret;
  }
}