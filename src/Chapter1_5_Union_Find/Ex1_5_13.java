package Chapter1_5_Union_Find;

import AnalysisOfAlgorithmsTest.Stopwatch;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Weighted quick-union with path compression. Modify weighted quick-union(Algorithm 1.5) to implement path compression,
*   as described in exercise 1.5.12. Give a sequence of input input pairs that causes this method to produce a tree of
*   height 4. Note: The amortized cost per operation for this algorithm is known to be bounded by a function known as
*   the inverse Ackermann function and is less_exch than 5 for any conceivable practical value of N.
*
* */
public class Ex1_5_13 {
    static class compressedWeightedQuickUnion{
        private int[] id;
        private int[] size;
        compressedWeightedQuickUnion(int N){
            id = new int[N];
            size = new int[N];
            for (int i = 0; i < id.length; i++){
                id[i] = i;
                size[i] = 1;
            }
        }
        public int find(int p){
            int root = p;
            while (root != id[root]) root = id[root];
            while (root != id[p]){
                int parent = id[p];
                id[p] = root;
                p = parent;
            }
            return root;
        }
        public void union(int p, int q){
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) return;
            if (size[pRoot] < size[qRoot]){
                id[pRoot] = qRoot;
                size[qRoot] += size[pRoot];
            }else {
                id[qRoot] = pRoot;
                size[pRoot] += size[qRoot];
            }
        }
        public int maxTreeDepth(){
            int depth = 0;
            for (int i = 0; i < id.length; i++){
                int tmp = 0;
                int p = i;
                while (p != id[p]){
                    tmp++;
                    p = id[p];
                }
                if (tmp > depth) depth = tmp;
            }
            return depth;
        }
        public String toString(){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("-----------------------------\n");
            stringBuilder.append("Index:");
            int i = 0;
            while (i < id.length) stringBuilder.append(" " + i ++);
            stringBuilder.append("\n\t");
            i = 0;
            while (i < id.length) stringBuilder.append(" " + id[i++]);
            stringBuilder.append("\nSize:");
            i = 0;
            while (i < id.length) stringBuilder.append(" " + size[i++]);
            stringBuilder.append("\nTree eight: " + maxTreeDepth());
            stringBuilder.append("\n---------------------------\n");
            return stringBuilder.toString();
        }
    }

    public static void main(String[] args) {
        int N = 1000000, pairCount = 1000000;
        compressedWeightedQuickUnion cwqu = new compressedWeightedQuickUnion(N);
        Stopwatch timer1 = new Stopwatch();
        for (int i = 0; i < pairCount; i++){
            cwqu.union(StdRandom.uniform(0, N), StdRandom.uniform(0, N));
        }
        StdOut.printf("Execute completion: %.3f\n", timer1.elapsedTime());
        StdOut.printf("Tree height: %d\n", cwqu.maxTreeDepth());
    }
}
