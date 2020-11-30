package Chapter1_5_Union_Find;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
*   Do Exercise1.5.1, but use quick-union(page 224). In addition, draw the forest of trees represented by the id[]
*   after each input pair is processed.
*   Show the contents of the id[] array and the number of times the array is accessed for each input pair.
*
* */
public class Ex5_2 {
    private int id[];
    private int count;
    public Ex5_2(int N){
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++){
            id[i] = i;
        }
    }

    public int count(){return count;}
    public int find(int p){
        if (p != id[p]){
            p = id[p];
        }
        return p;
    }
    public boolean connected(int p, int q){return find(p) == find(q);}
    public void union(int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;
        id[pRoot] = qRoot;
        count--;
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        Ex5_2 unionFind = new Ex5_2(N);
        while (!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if(unionFind.connected(p, q)) continue;
            unionFind.union(p, q);
            StdOut.printf("%d  %d | ", p, q);
        }
        StdOut.println(unionFind.count() + " Connected");
        /*unionFind.union(9, 0);
        unionFind.union(3, 4);
        unionFind.union(5, 8);
        unionFind.union(7, 2);
        unionFind.union(2, 1);
        unionFind.union(5, 7);
        unionFind.union(0, 3);
        unionFind.union(4, 2);*/
    }
}
