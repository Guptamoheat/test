import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/***********************************************************************************************************************************************

 ***********************************************************************************************************************************************/
public class Main  {
    static FastReader in=new FastReader();
    static final Random random=new Random();
    static long mod=1000000007L;
    static HashMap<Integer,Integer> map=new HashMap<>();

    public static void main(String args[]) throws IOException {
        int T = in.nextInt();

        int cse = 1;
        loop:
        while (T-- > 0) {


        }





    }





    /************************************************************************************************************************************************************
     Template Starts
     ************************************************************************************************************************************************************/

    /*******************************************************************************************************************
     Disjoint Set Union
     *******************************************************************************************************************/
    public static int find(int x, int[] parent) {
        if(x == parent[x]) {
            return x;
        }

        return parent[x] = find(parent[x], parent);
    }
    public static boolean union(int x, int y, int[] parent, int[] rank) {
        int lx = find(x,  parent);
        int ly = find(y, parent);

        if(lx != ly) {
            if(rank[lx] > rank[ly]) {
                parent[ly] = lx;
            } else if (rank[lx] < rank[ly]) {
                parent[lx] = ly;
            } else {
                parent[lx] = ly;
                rank[ly]++;
            }

            return true;
        }

        return false;
    }

    /*******************************************************************************************************************
     ******************************************************************************************************************/




    public static long kadane(ArrayList<Integer> list) {
        long ans = Long.MIN_VALUE;
        long train = 0;
        for(int i = 0; i < list.size(); i++) {
            if(train >= 0) {
                train += list.get(i);
            } else {
                train = list.get(i);
            }

            ans = max(train, ans);
        }
        return ans;
    }

    public static long[] prefix(int[] arr) {
        long[] prefix = new long[arr.length + 1];

        for(int i = 1; i <= arr.length; i++) {
            prefix[i] = prefix[i - 1] + (long)arr[i - 1];
        }

        return prefix;
    }


    static int[][] dirs = {{0,1}, {1,0}, {0,-1},{-1,0}};

    static int max(int a, int b)
    {
        if(a<b)
            return b;
        return a;
    }

    static long max(long a, long b) {
        if(a<b)
            return b;
        return a;
    }


    static void ruffleSort(int[] a) {
        int n=a.length;
        for (int i=0; i<n; i++) {
            int oi=random.nextInt(n), temp=a[oi];
            a[oi]=a[i]; a[i]=temp;
        }
        Arrays.sort(a);
    }

    static void ruffleSort(long[] a) {
        int n=a.length;
        for (int i=0; i<n; i++) {
            int oi=random.nextInt(n);
            long temp=a[oi];
            a[oi]=a[i]; a[i]=temp;
        }
        Arrays.sort(a);
    }

    static < E > void print(E res)
    {
        System.out.println(res);
    }


    static  int gcd(int a,int b)
    {
        if(b==0)
        {
            return a;
        }
        return gcd(b,a%b);
    }

    static  long gcd(long a,long b)
    {
        if(b==0)
        {
            return a;
        }
        return gcd(b,a%b);
    }

    static int lcm(int a, int b)
    {
        return (a / gcd(a, b)) * b;
    }


    static int abs(int a)
    {
        if(a<0)
            return -1*a;
        return a;
    }

    static class FastReader
    {
        BufferedReader br;
        StringTokenizer st;

        public FastReader()
        {
            br = new BufferedReader(new
                    InputStreamReader(System.in));
        }

        String next()
        {
            while (st == null || !st.hasMoreElements())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException  e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt()
        {
            return Integer.parseInt(next());
        }

        long nextLong()
        {
            return Long.parseLong(next());
        }

        double nextDouble()
        {
            return Double.parseDouble(next());
        }
        String nextLine()
        {
            String str = "";
            try
            {
                str = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return str;
        }

        int [] readintarray(int n) {
            int res [] = new int [n];
            for(int i = 0; i<n; i++)res[i] = nextInt();
            return res;
        }
        long [] readlongarray(int n) {
            long res [] = new long [n];
            for(int i = 0; i<n; i++)res[i] = nextLong();
            return res;
        }


        int[][] readintmatrix(int n, int m) {
            int[][] res = new int[n][m];

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    res[i][j] = in.nextInt();
                }
            }

            return res;
        }
    }

}