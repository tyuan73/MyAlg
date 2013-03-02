class PermutateArray2 {

	public static void main(String[] args) {
		PermutateArray2 pa = new PermutateArray2();
		
		for(int i = 1; i <= 24; i++) {
		//System.out.println("k = " + i);
			pa.getPermutation(4, i);
		}
		
		pa.getPermutation(1, 1);
	}
	
	public String getPermutation(int n, int k) {
        int[] table = new int[10];
        table[0] = 1;
        for(int i = 1; i <= 9; i++)
            table[i] = table[i-1]*i;
        
        int[] ans = new int[n];
		
		k--;
		for(int i = 0, j = n-1; i < n; i++, j--) {
			int x = k / table[j] + 1;
			ans[i] = x;
			System.out.println("k= " + k + " table[j] = " + table[j] + " x = " + x);
			k %= table[j];
		}
		
		return getStr(ans);
	}

    public String getPermutation1(int n, int k) {
        int[] table = new int[10];
        table[1] = 1;
        for(int i = 2; i <= 9; i++)
            table[i] = table[i-1]*i;
        
        int[] ans = new int[n];
        for(int i = 0; i < n; i++)
            ans[i] = i+1;
        
		int index = n;
        while( k > 0) {
				//System.out.println("k = " + k  + " index = " + index);			

			if(table[index] > k) {
				index--;
				continue;
			}
            //index = find(table, k);
            int dis = (k-1)/table[index];
		System.out.println("k = " + k  + "num =" + table[index] + " index = " + index + " dist =" + dis);			
            k %= table[index];
			if(dis > 0) 
				swap(ans, n-index-1, n-index-1+dis);
        }
        reverse(ans, n-index, n-1);
        
        return getStr(ans);
    }
	
	void reverse(int[] arr, int from, int to) {
		for(; from < to; from++, to--)
		{
			int x = arr[from];
			arr[from] = arr[to];
			arr[to] = x;
		}
	}
    
    void swap(int[] arr, int from , int to) {
        int x = arr[to];
        for(int i = to; i > from; i--)
        {
            arr[i] = arr[i-1];
        }
        arr[from] = x;
    }
    
    String getStr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < arr.length; i++)
            sb.append(arr[i]);
		System.out.println(sb.toString());
        return sb.toString();
    }
}