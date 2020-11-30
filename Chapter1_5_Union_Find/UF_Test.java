package Chapter1_5_Union_Find;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class UF_Test {
    private int count;
    private int[] id;
    private int counter;
    public UF_Test(int N){
        count = N;
        counter = 0;
        id = new int[N];
        for (int i = 0; i < N; i++){
            id[i] = i;
        }
    }
    public int count(){return count;}
    public int find(int p){return id[p];}
    public void union(int p, int q){
        counter++;
        int pID = find(p);
        int qID = find(q);
        if (pID == qID) return;
        //Stopwatch timer1 = new Stopwatch();
        for (int i = 0; i < id.length; i++){
            if (id[i] == pID) id[i] = qID;
        }
        count--;
        //return timer1.elapsedTime();
    }
    public boolean connected(int p, int q){return find(p) == find(q);}

    public static void main(String[] args) {
        //FileReader fr = new FileReader("./algs4-data/tinyUF.txt");
        Stopwatch timer1 = new Stopwatch();
        int N = StdIn.readInt();
        UF_Test uf = new UF_Test(N);

        while (!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q + " " + uf.counter);
            //StdOut.printf("%5d\n", timer1);
        }

        StdOut.println(uf.count() + " components");
        StdOut.println(uf.counter + " be union.");
        double time = timer1.elapsedTime();
        StdOut.printf("%2.1s seconds\n", time);
    }
}
