public class 3Sum {
    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        
        if(num == null || num.length == 0)
            return ret;
        
        Arrays.sort(num);
        int n = num.length;
        for(int i = 0; i < n-2;) {
            int l = i+1, r = n - 1;
            while(l < r) {
                int sum = num[i]+num[l]+num[r];
                if(sum == 0) {
                    ArrayList<Integer> x = new ArrayList<Integer>();
                    x.add(num[i]);
                    x.add(num[l]);
                    x.add(num[r]);
                    ret.add(x);
                    l = nextI(num, l, true);
                    r = nextI(num, r, false);
                } else if(sum > 0)
                    r = nextI(num, r, false);
                else
                    l = nextI(num, l, true);
            }
            i = nextI(num, i, true);
        }
        
        return ret;
    }
    
    int nextI(int[] num, int i, boolean up) {
        int step = up? 1 : -1;
        i += step;
        while(i >= 0 && i < num.length) {
            if(num[i] != num[i-step])
                break;
            i += step;
        }
        return i;
    }
}