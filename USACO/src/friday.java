/*
ID: tyuan731
LANG: JAVA
TASK: friday
*/
import java.io.*;
import java.util.*;

class friday {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("friday.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
	
	int years = Integer.parseInt(f.readLine());
	int[] days = {31,28,31,30,31,30,31,31,30,31,30,31};
	int[] weeks = new int[7];
	
	int start = 2;
	for(int y = 1900; y < 1900 + years; y++) {
		if((y % 400) == 0 || ((y%4) == 0 && (y%100) != 0))
			days[1] = 29;
		else
			days[1] = 28;
		for(int m = 0; m < 12; m++) {
			weeks[(start+5)%7]++;
			start = (start + days[m])%7;
			
		}
	}
	StringBuffer sb = new StringBuffer();
	for(int w : weeks)
		sb.append(w + " ");
	String o = sb.toString();
	out.println(o.substring(0,o.length() - 1));
	
	/*
	String line = null;
	while((line = f.readLine()) != null) {
		//StringTokenizer st = new StringTokenizer(line);
		int i1 = getNum(line);
		line = f.readLine();
		int i2 = getNum(line);
		if((i1 % 47) == (i2%47))
			out.println("GO");                           // output result
		else
			out.println("STAY");
	}
	*/
	
    out.close();                                  // close the output file
    System.exit(0);                               // don't omit this!
  }
  
  static int getNum(String line) {
	int ret = 1;
	for(int i = 0; i < line.length(); i++)
		ret *= line.charAt(i) - 'A' + 1;
	return ret;
  }
}