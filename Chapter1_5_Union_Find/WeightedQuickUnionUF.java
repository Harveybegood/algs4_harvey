package Chapter1_5_Union_Find;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WeightedQuickUnionUF {
    private int[] id; // parent link
    private int[] sz; // the size of component for roots
    private int count;// number of components
    public WeightedQuickUnionUF(int N){
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) id[i] = i;
        sz = new int[N];
        for (int i = 0; i < N; i++) sz[i] = 1;
    }
    public int count(){return count;}
    public boolean connected(int p, int q){return find(p) == find(q);}
    private int find(int p){
        // follow linkedlists to find a root
        if (p != id[p]) p = id[p];
        return p;
    }
    public void union(int p, int q){
        int i = find(p);
        int j = find(q);
        if (i == j) return;
        // make smaller root point to larger one
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        }else {
            id[j] = i;
            sz[i] += sz[j];
        }
        count--;
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        WeightedQuickUnionUF wuf = new WeightedQuickUnionUF(N);
        while (!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (wuf.connected(p, q)) continue;
            wuf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(wuf.count() + " Components.");
    }
}
