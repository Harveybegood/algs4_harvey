package Chapter1_5_Union_Find;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
*   Repeat Exercise 1.5.5 for weighted quick-union
*
* */
public class Ex1_5_6 {
    // write down codes for weighted quick-union
    private int[] id;
    private int[] sz;
    private int count;
    public Ex1_5_6(int N){
        count = N;
        id = new int[N];
        sz = new int[id.length];
        for (int i = 0; i < id.length; i++)id[i] = i;
        for (int i = 0; i < id.length; i++)sz[i] = 1;
    }
    public int count(){return count;}
    public boolean connected(int p, int q){return find(p) == find(q);}
    public int find(int p){
        while (p != id[p]) p = id[p];
        return p;
    }
    public void union(int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);
        if (sz[pRoot] < sz[qRoot]){
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }else {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
        count--;
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        Ex1_5_6 WUF = new Ex1_5_6(N);
        while (!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (p == q) continue;
            WUF.union(p, q);
            StdOut.println(p + "  " + q);
        }
    }

    /*
    *   Initializing object, 1 for initializing count variable, 1 for creating array id, 1 for creating array sz.
    *   10^9 for initializing parent, 10^9 for initializing sz: ~2*10^9
    *   For finding,1 to 10^6 find(p)
    *   For union, 10^6
    *
    *
    *
    *
    *
    * */
}
