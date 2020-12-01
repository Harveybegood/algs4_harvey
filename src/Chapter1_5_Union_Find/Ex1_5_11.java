package Chapter1_5_Union_Find;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
*   Implement weighted quick-find, where you always change the id[] entries of the smaller component to the identifier
*   of the larger component. How does this change affect performance?
*
* */
public class Ex1_5_11 {
    private int count;
    private int[] id;
    private int[] sz;
    private Ex1_5_11(int N){
        count = N;
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < id.length; i++)id[i] = i;
        for (int i = 0; i < id.length; i++)sz[i] = 1;
    }
    public int count(){return count;}
    public int find(int p) {return id[p];}
   /* public int find(int p){
        while (p != id[p]) p = id[i];
        return p;
    }*/
    public boolean connected(int p, int q){return find(p) == find(q);}
    public void union(int p, int q){
        int pID = find(p);
        int qID = find(q);
        if (pID == qID) return;
        for (int i = 0; i < id.length; i++){
            if (id[i] == pID) id[i] = qID;
        }
        count--;
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        Ex1_5_11 WQF = new Ex1_5_11(N);
        while (!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (WQF.connected(p, q)) continue;
            WQF.union(p, q);
            StdOut.println(p + "  " + q );
        }
    }
}
