import com.sun.source.tree.Tree;
import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;


/***********************************************************************************************************************************************
 1 - Assume the answer value and check whether it satisfies the condition

 ***********************************************************************************************************************************************/
public class Main  {
    static FastReader in=new FastReader();
    static final Random random=new Random();
    static long mod=1000000007L;
    static HashMap<Integer,Integer>map=new HashMap<>();

    public static void main(String args[]) throws IOException {
        int T = in.nextInt();

        int cse = 1;
        loop:
        while (T-- > 0) {

            int n = in.nextInt();
            int m = in.nextInt();
            ArrayList<Edge>[] graph = new ArrayList[n];

            for(int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
            }

            for(int i = 0; i < m; i++) {
                int u = in.nextInt() - 1;
                int v = in.nextInt() - 1;
                int w = in.nextInt();

                Edge e = new Edge(u,v,w);
                Edge r = new Edge(v,u,w);

                graph[u].add(e);
                graph[v].add(r);
            }

            int[] s = in.readintarray(n);

            PriorityQueue<Pair> q = new PriorityQueue<>();
            long[][] dist = new long[n][1001];

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < 1001; j++) {
                    dist[i][j] = Long.MAX_VALUE;
                }
            }

            dist[0][s[0]] = 0;
            boolean[][] visited = new boolean[n][1001];
            q.add(new Pair(0,0,s[0]));
            while(q.size() > 0) {
                Pair p = q.remove();

                int rv = p.v;
                int rsf = p.c;

                //print(p);
                if(visited[rv][rsf]) continue ;

                visited[rv][rsf] = true;

                for(Edge e : graph[rv]) {
                    int nbr = e.nbr;
                    int nsf = Math.min(rsf, s[nbr]);

                    if(dist[nbr][nsf] > p.d + 1l*e.w*rsf) {
                        dist[nbr][nsf] = p.d + 1l*e.w*rsf;
                        Pair np = new Pair(p.d + 1l*e.w*rsf, nbr, nsf);
                        q.add(np);
                    }



                }
            }

            long ans = Long.MAX_VALUE;
            for(int i = 0; i < 1001; i++) {
                ans = Math.min(ans, dist[n - 1][i]);
            }

            print(ans);

        }





    }


    public static class Pair implements Comparable<Pair>{
        long d;
        int v;
        int c;

        public Pair(long d, int v, int c) {
            this.d = d;
            this.v = v;
            this.c = c;
        }

        public int compareTo(Pair p) {
            return (int)(d - p.d);
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "d=" + d +
                    ", v=" + v +
                    ", c=" + c +
                    '}';
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



//    static class Pair implements Comparable<Pair>{
//        public int a;
//        public int b;
//
//        public Pair(int a, int b) {
//            this.a = a;
//            this.b = b;
//        }
//
//        @Override
//        public String toString() {
//            return a + " " + b;
//        }
//
//        @Override
//        public int compareTo(Pair o) {
//            return this.b - o.b;
//        }
//    }

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

    static class Edge {
        int v;
        int nbr;

        int w;

        public Edge(int v, int nbr, int w) {
            this.v = v;
            this.nbr = nbr;
            this.w = w;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "v=" + v +
                    ", nbr=" + nbr +
                    '}';
        }
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