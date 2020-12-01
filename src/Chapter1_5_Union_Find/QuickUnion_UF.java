package Chapter1_5_Union_Find;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class QuickUnion_UF {
    private int[] id;
    private int count;
    public QuickUnion_UF(int N){
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++){
            id[i] = i;
        }
    }
    public int count(){return count;}
    public boolean connected(int p, int q){return find(p) == find(q);}
    public int find(int p){
        // follow the name of component
        while (p != id[p]) p = id[p];
        return p;
    }
    public void union(int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;
        //StdOut.println(pRoot + " " + qRoot);
        id[pRoot] = qRoot;
        //
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        QuickUnion_UF quickUnion_uf = new QuickUnion_UF(N);
        Stopwatch timer = new Stopwatch();
        while (!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (quickUnion_uf.connected(p, q)) continue;
            quickUnion_uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        double timeCost = timer.elapsedTime();
        StdOut.println(quickUnion_uf.count() + " Components");
        StdOut.printf("%2.1s seconds\n", timeCost);
    }
}
