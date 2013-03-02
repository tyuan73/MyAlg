class SeparateNum {
	public static void main(String[] args) {
		
		int[] test = {-1, -2,3,4,5,-6, -9,6,7,9,-10, 11};
		//int[] test = {1,2, 0};
		SeparateNum s = new SeparateNum();
		//s.separateNumbers(test);
		//s.quickSort(test, 0, test.length - 1);
		s.split(test);
		for(int i : test)
			System.out.print(" " + i);
		System.out.println("");
	}
	
	void separateNumbers(int[] t) {
		// assume no zero
		int l = -1;
		for(int r = 0; r < t.length; r++) {
			if(t[r] < 0) {
				l++;
				int x = t[l];
				t[l] = t[r];
				t[r] = x;
			}
		}
	}
	void quickSort(int[] t, int l, int r) {
		if(l >= r)
			return;
		int i = partition(t, l, r);
		quickSort(t, l, i- 1);
		quickSort(t, i + 1, r);
	}
	int partition(int[] t, int l, int r) {
		//int l = 0, r = t.length - 1;
		//if(l >= r)
		//	return l;
		int orig = l;
		int pivot = t[l++];
		while(true) {
			while(l <= r && t[l] <= pivot) l++;
			while(r >= l && t[r] > pivot) r--;
			if(l >=r) break;
			int x = t[l];
			t[l] = t[r];
			t[r] = x;
		}
		t[orig] = t[l - 1];
		t[l - 1] = pivot;
		return l - 1;
	}
	
public static void split(int[] a){
    if (a == null || a.length <= 1) return;
    int i = 0, j = 0;
    while (i<a.length-1 && a[i] < 0){
        i++;
    }
    j = i+1;
    while (j<a.length){
        while (j<a.length && a[j]>=0){
            j++;
        }
        if (j >= a.length) break;
        int m = j;
        while (j<a.length && a[j]<0){
            j++;
        }
        swap(a, i, m, j-1);
        i += j - m;
    }
}

private static void swap(int[] a, int l, int m, int r){
    reverse(a, l, m-1);
    reverse(a, m, r);
    reverse(a, l, r);
}

private static void reverse(int[] a, int l, int r){
    while (l < r){
        int tmp = a[l]; a[l] = a[r]; a[r] = tmp;
        l++; r--;
    }
}
}