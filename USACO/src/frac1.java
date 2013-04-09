/*
ID: tyuan731
LANG: JAVA
TASK: frac1
*/
import java.io.*;
import java.util.*;

class frac1 {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("frac1.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
	
	int n = Integer.parseInt(f.readLine());
	int[] ret = new int[n+1];
	Arrays.fill(ret, 1);
	ret[1] = 0;
	int count = 0;
	while(count < n) {
		double min = 2.0;
		int index = 0;
		for(int j = 1; j <= n; j++) {
			if(ret[j] == j)
				continue;
			double x = (double)ret[j] / (double) j;
			if(x < min) {
				min = x;
				index = j;
			}
		}
		out.println(ret[index] + "/" + index);
		ret[index]++;
		while(ret[index] < index && gcd(ret[index], index) > 1)
			ret[index]++;
		if(ret[index] == index)
			count++;
	}
	out.println("1/1");
	
    out.close();                                  // close the output file
    System.exit(0);                               // don't omit this!
  }
  
  static int gcd(int x, int y) {
	while(x > 1) {
		int a = y%x;
		y = x;
		x = a;
	}
	return x == 0? y : x;
  }
}

/***
Here's a super fast solution from Russ:

We notice that we can start with 0/1 and 1/1 as our ``endpoints'' and recursively generate the middle points by adding numerators and denominators.

0/1                                                              1/1
                               1/2
                  1/3                      2/3
        1/4              2/5         3/5                 3/4
    1/5      2/7     3/8    3/7   4/7   5/8       5/7         4/5

Each fraction is created from the one up to its right and the one up to its left. This idea lends itself easily to a recursion that we cut off when we go too deep.

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

int n;
FILE *fout;

// print the fractions of denominator <= n between n1/d1 and n2/d2 
void
genfrac(int n1, int d1, int n2, int d2)
{
	if(d1+d2 > n)	// cut off recursion
		return;

	genfrac(n1,d1, n1+n2,d1+d2);
	fprintf(fout, "%d/%d\n", n1+n2, d1+d2);
	genfrac(n1+n2,d1+d2, n2,d2);
}

void
main(void)
{
	FILE *fin;

	fin = fopen("frac1.in", "r");
	fout = fopen("frac1.out", "w");
	assert(fin != NULL && fout != NULL);

	fscanf(fin, "%d", &n);

	fprintf(fout, "0/1\n");
	genfrac(0,1, 1,1);
	fprintf(fout, "1/1\n");
}



**/