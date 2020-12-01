package Chapter1_5_Union_Find;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
* Do exercise 1.5.1, but use weighted quick-union.
* Show the contents of the id[] array and the number of times the array is accessed for each input pair
* 4-3, 3-8, 6-5, 9-4, 2-1, 5-0, 7-2, 6-1
* */
public class Ex1_5_3 {
    private int[] id;
    private int[] sz;
    private int count;
    public Ex1_5_3(int N){
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) id[i] = i;
        sz = new int[N];
        for (int i = 0; i < N; i++) sz[i] = 1;
    }
    public int count(){return count;}
    public int find(int p){
        while (p != id[p]){
            p = id[p];
        }
        return p;
    }
    public boolean connected(int p, int q){return find(p) == find(q);}
    public void union(int p, int q){
        int i = find(p);
        int j = find(q);
        if (sz[i] < sz[j]){
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
        Ex1_5_3 weightedUF = new Ex1_5_3(N);
        while (!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (weightedUF.connected(p, q)) continue;
            weightedUF.union(p, q);
            StdOut.println(p + "  " + q);
        }
        StdOut.println(weightedUF.count()+ " Connected");
    }
}
