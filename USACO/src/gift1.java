/*
ID: tyuan731
LANG: JAVA
TASK: gift1
*/
import java.io.*;
import java.util.*;

class gift1 {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("gift1.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
   // StringTokenizer st = new StringTokenizer(f.readLine());
	HashMap<String, Integer> people = new HashMap<String, Integer>();
	int total = Integer.parseInt(f.readLine());
	String[] keeper = new String[total];
	int i = 0;
	while(total-- > 0) {
		String name = f.readLine();
		people.put(name, 0);
		keeper[i++] = name;
	}
	
	String giver = null;
	while((giver = f.readLine()) != null) {
		StringTokenizer st = new StringTokenizer(f.readLine());	// read "200 3"
		int money = Integer.parseInt(st.nextToken());    // first integer
		int receiver = Integer.parseInt(st.nextToken());    // second integer
		int bal = people.get(giver);
		int rem = receiver == 0? 0 :  -money/receiver * receiver;
		people.put(giver, bal + rem);
		
		for(int j = 0; j < receiver; j++) {
			String rec = f.readLine();
			int b1 = people.get(rec);
			people.put(rec, b1 + money/receiver);
		}
		
		//out.println(i1+i2);                           // output result
	}
	
	for(String s: keeper)
		out.println(s + " " + people.get(s));
	
    out.close();                                  // close the output file
    System.exit(0);                               // don't omit this!
  }
}