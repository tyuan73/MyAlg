/*
ID: tyuan731
LANG: JAVA
TASK: ride
*/
import java.io.*;
import java.util.*;

class ride {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("ride.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
	
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