package Chapter1_5_Union_Find;

import edu.princeton.cs.algs4.StdOut;

/*
*   Quick-union with path compression. Modify quick-union(page 224) to include path compression, by adding a loop to
*   union() that linkedlists every site on the paths from p and q to the roots of their trees to the root of the new tree.
*   Give a sequence of input pairs that causes this method to produce a path of length 4. Note: The amortized cost per
*   operation for this algorithm is known to be logarithmic.
*
* */
public class Ex1_5_12 {
    private int[] id;
    private int count;
    public Ex1_5_12(int N){
        count = N;
        id = new int[N];
        for (int i = 0; i < id.length; i++){
            id[i] = i;
        }
    }
    public int count(){return count;}
    public int find(int p){
        if (p == id[p]) return p;
        return id[p] = find(id[p]);
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
        Ex1_5_12 ex1_5_12 = new Ex1_5_12(10);
        ex1_5_12.union(0,1);
        ex1_5_12.union(2,3);
        ex1_5_12.union(3,4);
        ex1_5_12.union(2,4);
        //StdOut.println();
        StdOut.println("Components: " + ex1_5_12.count());
        Ex1_5_12 ex1_5_12_1 = new Ex1_5_12(10);
        ex1_5_12_1.union(0, 1);
        ex1_5_12_1.union(2, 3);
        ex1_5_12_1.union(4, 5);
        ex1_5_12_1.union(6, 7);
        ex1_5_12_1.union(6, 4);
        ex1_5_12_1.union(4, 2);
        ex1_5_12_1.union(4, 0);
        StdOut.println("components: " + ex1_5_12_1.count());
    }
}
