/*
ID: tyuan731
LANG: JAVA
TASK: sort3
*/
import java.io.*;
import java.util.*;

class sort3 {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("sort3.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
	
	int n = Integer.parseInt(f.readLine());
	int[] a = new int[n];
	int[] count = new int[3];
	for(int i = 0; i < n; i++) {
		a[i] = Integer.parseInt(f.readLine());
		count[a[i]-1]++;
	}
	
	int total = 0;
	int a12 = 0, a13 = 0, a23 = 0;
	for(int i = 0; i < count[0]; i++) {
		if(a[i] == 2)
			a12++;
		else if(a[i] == 3)
			a13++;
	}
	for(int i = count[0]; i < count[0]+count[1]; i++) {
		if(a[i] == 1 && a12 > 0) {
			a12--;
			total++;
		} else if(a[i] == 3)
			a23++;
	}
	for(int i = count[0]+count[1]; i < n; i++) {
		if(a[i] == 1 && a13 > 0) {
			a13--;
			total++;
		} else if(a[i] == 2 && a23 > 0) {
			a23--;
			total++;
		}
	}
	total += (a13+a12) * 2;
	
	out.println(total);
	
    out.close();                                  // close the output file
    System.exit(0);                               // don't omit this!
  }
}
