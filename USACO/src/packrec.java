/*
ID: tyuan731
LANG: JAVA
TASK: packrec
*/
import java.io.*;
import java.util.*;

class packrec {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("packrec.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("packrec.out")));
	
	int[][] rect = new int[4][2];
	int n =  4;
	for(int i = 0; i < 4; i++) {
		StringTokenizer st = new StringTokenizer(f.readLine());
		rect[i][0] = Integer.parseInt(st.nextToken());
		rect[i][1] = Integer.parseInt(st.nextToken());
	}
	
	int[] maxArea = {10000};
	ArrayList<Integer[]> ret = new ArrayList<Integer[]>();
	perm(rect, maxArea, ret, 0);

	out.println(maxArea[0]);
	for(Integer[] a: ret)
		out.println(a[0] + " " + a[1]);

    out.close();                                  // close the output file
    System.exit(0);                               // don't omit this!
  }
  
  static void perm(int[][] rect, int[] maxArea, ArrayList<Integer[]> ret, int index) {
	if(index == 3) {
		//debug
		/*
		for(int[] p : rect) {
			for(int q : p)
				System.out.printf("%5d", q);
			System.out.println();
		}
		System.out.println();	
		*/
		cal1(rect, maxArea, ret);
		cal2(rect, maxArea, ret);
		cal3(rect, maxArea, ret);
		cal4(rect, maxArea, ret);
		//cal5(rect, maxArea, ret);
		cal6(rect, maxArea, ret);
		return;			
	}

	for(int i = index; i < 4; i++) {
		int x = rect[index][0];
		rect[index][0] = rect[i][0];
		rect[i][0] = x;
		x = rect[index][1];
		rect[index][1] = rect[i][1];
		rect[i][1] = x;
		
		perm(rect, maxArea, ret, index+1);
		
		x = rect[index][0];
		rect[index][0] = rect[i][0];
		rect[i][0] = x;
		x = rect[index][1];
		rect[index][1] = rect[i][1];
		rect[i][1] = x;
	}
  }

  static void cal1(int[][] rect, int[] maxArea, ArrayList<Integer[]> ret) {
	int n = 15;
	while(n-- >= 0) {
		int width = 0, height = 0;
		for(int k = 0; k < 4; k++) {
			int flag = ((1 << k) & n) >> k;
			width += rect[k][flag];
			height = Math.max(height, rect[k][1-flag]);
		}
		add(width, height, maxArea, ret);
	}
  }
   
   static void cal2(int[][] rect, int[] maxArea, ArrayList<Integer[]> ret) {
	int n = 15;
	while(n-- >= 0) {
		int width1 = 0, height1 = 0;
		int width2 = 0, height2 = 0;
		int width = 0, height = 0;
		for(int k = 0; k < 4; k++) {
			int flag = ((1 << k) & n) >> k;
			if(k == 0) {
				width1 = rect[k][flag];
				height1 = rect[k][1-flag];
			} else {
				width2 += rect[k][flag];
				height2 = Math.max(height2, rect[k][1-flag]);
			}
		}
		width = Math.max(width1, width2);
		height = height1 + height2;
		add(width, height, maxArea, ret);
	}
  }

  static void cal3(int[][] rect, int[] maxArea, ArrayList<Integer[]> ret) {
	int n = 15;
	while(n-- >= 0) {
		int width1 = 0, height1 = 0;
		int width2 = 0, height2 = 0;
		int width3 = 0, height3 = 0;
		int width = 0, height = 0;
		for(int k = 0; k < 4; k++) {
			int flag = ((1 << k) & n) >> k;
			if(k == 0) {
				width1 = rect[k][flag];
				height1 = rect[k][1-flag];
			} else if (k == 1) {
				width2 = rect[k][flag];
				height2 = rect[k][1-flag];
			} else {
				width3 += rect[k][flag];
				height3 = Math.max(height3, rect[k][1-flag]);
			}
		}
		width = width1 + Math.max(width2, width3);
		height = Math.max(height1, height2 + height3);
		add(width, height, maxArea, ret);
	}
  }
 
   static void cal4(int[][] rect, int[] maxArea, ArrayList<Integer[]> ret) {
	int n = 15;
	while(n-- >= 0) {
		int width1 = 0, height1 = 0;
		int width2 = 0, height2 = 0;
		int width3 = 0, height3 = 0;
		int width = 0, height = 0;
		for(int k = 0; k < 4; k++) {
			int flag = ((1 << k) & n) >> k;
			if(k == 0) {
				width1 = rect[k][flag];
				height1 = rect[k][1-flag];
			} else if (k == 3) {
				width2 = rect[k][flag];
				height2 = rect[k][1-flag];
			} else {
				width3 = Math.max(width3, rect[k][flag]);
				height3 += rect[k][1-flag];
			}
		}
		width = width1 + width2 + width3;
		height = Math.max(Math.max(height1, height2), height3);
		add(width, height, maxArea, ret);
	}
  }
  
  // the same as cal4? Yes!
   static void cal5(int[][] rect, int[] maxArea, ArrayList<Integer[]> ret) {
	int n = 15;
	while(n-- >= 0) {
		int width1 = 0, height1 = 0;
		int width2 = 0, height2 = 0;
		int width3 = 0, height3 = 0;
		int width = 0, height = 0;
		for(int k = 0; k < 4; k++) {
			int flag = ((1 << k) & n) >> k;
			if(k == 0) {
				width1 = rect[k][flag];
				height1 = rect[k][1-flag];
			} else if (k == 1) {
				width2 = rect[k][flag];
				height2 = Math.max(height1, rect[k][1-flag]);
			} else {
				width3 = Math.max(width3, rect[k][flag]);
				height3 += rect[k][1-flag];
			}
		}
		width = width1 + width2 + width3;
		height = Math.max(height2, height3);
		add(width, height, maxArea, ret);
	}
  }
 
   static void cal6(int[][] rect, int[] maxArea, ArrayList<Integer[]> ret) {
	int n = 15;
	while(n-- >= 0) {
		int[] widths = new int[4];
		int[] heights = new int[4];
		int width = 0, height = 0;
		for(int k = 0; k < 4; k++) {
			int flag = ((1 << k) & n) >> k;
			widths[k] = rect[k][flag];
			heights[k] = rect[k][1-flag];
		}

		if(heights[2]+heights[3] <= heights[0]) {
			width = Math.max(Math.max(widths[2], widths[3])+ widths[0], widths[1]);
			height = heights[0] + heights[1];
		} else if(heights[0] + heights[1] <= heights[2]) {
			width = Math.max(widths[3], Math.max(widths[0], widths[1]) + widths[2]);
			height = heights[2] + heights[3];
		} else {
			if(heights[0] >= heights[2] && widths[2] >= widths[3]) {
				width = Math.max(widths[0]+widths[2], widths[1]+widths[3]);
				height = Math.max(heights[0]+heights[1], heights[2]+heights[3]);
			} else if (heights[0] < heights[2]) {
				height = heights[2] + Math.max(heights[1], heights[3]);
				width = Math.max(widths[0]+widths[2], widths[1]+widths[3]);
			} else {
				height = heights[0] + Math.max(heights[1], heights[3]);
				width = Math.max(widths[0]+widths[2], widths[1]+widths[3]);
			}
		}

		add(width, height, maxArea, ret);
	}
  }
  
	static void add(int width, int height, int[] maxArea, ArrayList<Integer[]> ret) {
  		if(width * height < maxArea[0]) {
			maxArea[0] = width*height;
			ret.clear();
			Integer[] a = new Integer[2];
			a[0] = width < height ? width : height;
			a[1] = width >= height ? width : height;
			ret.add(a);
		} else if(width * height == maxArea[0]) {
			Integer[] a = new Integer[2];
			a[0] = width < height ? width : height;
			a[1] = width >= height ? width : height;
			int i = ret.size()-1;
			while(i >= 0) {
				Integer[] b = ret.get(i);
				if(b[0] == a[0]) return;
				if(b[0] < a[0]) break;
				i--;
			}
			ret.add(i+1, a);
		}
	}
}