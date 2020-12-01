package Chapter1_5_Union_Find;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


/*
*   Show the contents of the id[] array and the number of times the array is accessed for each input pair when you use
*   quick-find the sequence 9-0 3-4 5-8 7-2 2-1 5-7 0-3 4-2
*
* */
public class Ex5_1 {
    private int count;
    //private int counter;
    private int[] id;
    public Ex5_1(int N){
        //counter = 0;
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++){
            id[i] = i;
        }
    }
    public int count(){return count;}
    public int find(int p){return id[p];}
    public boolean connected(int p, int q){return find(p) == find(q);}
    public void union(int p, int q){
        //int p = StdIn.readInt();
        //int q = StdIn.readInt();
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
        Ex5_1 UF = new Ex5_1(N);
        while (!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (UF.connected(p, q)) continue;
            UF.union(p, q);
            StdOut.println(p + "  " + q);
        }
        // how many components
        StdOut.println(UF.count() + " components");
        //
    }
}
