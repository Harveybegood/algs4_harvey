package Chapter1_5_Union_Find;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/*
*   Weighted quick-union by height. Develop a UF implementation that uses the same basic strategy as weighted quick-union
*   but keeps track of tree height and always linkedlists the shorter tree to the taller one. Prove a logarithmic upper bound
*   on the height of the trees for N sites with your algorithm.
*
* */
public class Ex1_5_14 {
    static class depthWeightedQuickUnion{
        private int[] id;
        private int[] depth;
        depthWeightedQuickUnion(int N){
            id = new int[N];
            depth = new int[N];
            for (int i = 0; i < id.length; i++){
                id[i] = i;
            }
        }
        public int find(int p){
            while (p != id[p]) p = id[p];
            return p;
        }
        public int maxTreeDepth(){
            int dep = 0;
            for (int i = 0; i < depth.length; i++){
                if (depth[i] > dep)
                    dep = depth[i];
            }
            return dep;
        }
        public void update(int p){
            while (id[p] != p){
                depth[id[p]]++;
            }
        }
        public void union(int p, int q){
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot){
                StdOut.printf("%d %d connected\n", p, q);
                return;
            }
            if (depth[pRoot] < depth[qRoot]) id[pRoot] = qRoot;
            else if (depth[pRoot] > depth[qRoot]) id[qRoot] = pRoot;
            else {
                update(pRoot);
            }
            StdOut.printf("connect %d %d\n", p,  q);
            StdOut.println(this);
        }
        public String toString(){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("-----------------------------------------------------\n");
            stringBuilder.append("Index:");
            int i = 0;
            while (i < id.length)stringBuilder.append(" " + i++);
            stringBuilder.append("\n\t");
            i = 0;
            while (i < id.length)stringBuilder.append(" " + id[i++]);
            stringBuilder.append("\nTree height: ");
            i = 0;
            while (i < id.length)stringBuilder.append(" " + depth[i++]);
            stringBuilder.append("\nMax Tree Height: " + maxTreeDepth());
            stringBuilder.append("\n----------------------------------------------------\n");
            return stringBuilder.toString();
        }
    }

    static void theWorstCOndition(){
        int N = 20;
        depthWeightedQuickUnion dwqu = new depthWeightedQuickUnion(N);
        dwqu.union(1, 2);
        dwqu.union(3, 4);
        dwqu.union(2, 4);
        dwqu.union(5, 6);
        dwqu.union(7, 8);
        dwqu.union(6, 8);
        dwqu.union(4, 8);
        dwqu.union(10, 11);
        dwqu.union(12, 13);
        dwqu.union(11, 13);
        dwqu.union(14, 15);
        dwqu.union(16, 17);
        dwqu.union(15, 17);
        dwqu.union(13, 17);
        dwqu.union(18, 17);
        StdOut.printf("Tree height: %d\n", dwqu.maxTreeDepth());
    }

    public static void main(String[] args) {
        int N = 20;
        Stopwatch timer1 = new Stopwatch();
        depthWeightedQuickUnion dwqu = new depthWeightedQuickUnion(N);
        for (int i = 0; i < N; i++){
            dwqu.union(StdRandom.uniform(0, 20), StdRandom.uniform(0, 20));
        }
        StdOut.printf("Execute completion, time cost: %.3f\n", timer1.elapsedTime());
        StdOut.printf("Tree height: %d\n", dwqu.maxTreeDepth());
    }
}
