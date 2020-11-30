package Chapter1_5_Union_Find;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Random connections. Develop a UF client ErdosRenyi that takes an integer value N from the command line, generates
*   random pairs of integers between 0 and N-1, calling connected() to determine if they are connected and then union()
*   if not(as in our development client), looping until all sites are connected, and printing the number of connections
*   generated. Packages your program as a static method count() that takes N as argument and returns the number of
*   connections and a main() that takes N from the command line, calls count(), and prints the returned value.
*
* */
public class Ex1_5_17 {
    static class compressedWeightedQuickUnion{
        private int[] id;
        private int[] size;
        private int count;
        compressedWeightedQuickUnion(int N){
            count = N;
            id = new int[N];
            size = new int[N];
            for (int i = 0; i < N; i++){
                id[i] = i;
                size[i] = 1;
            }
        }
        int count(){return count;}
        int find(int p){
            int root = p;
            while (id[root] != root)
                root = id[root];
            while (id[p] != p){
                int parent = id[p];
                id[p] = root;
                size[parent] -= size[p];
                p = parent;
            }
            return root;
        }
        boolean connected(int p, int q){return find(p) == find(q);}
        boolean allConnected(){
            int count = 0;
            for (int i = 0; i < id.length; i++)
                if (id[i] == i) count++;
            return count == 1;
        }
        void union(int p, int q){
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) return;
            if (size[pRoot] < size[qRoot]){
                id[pRoot] = qRoot;
                size[qRoot] += size[pRoot];
            }else {
                id[pRoot] = qRoot;
                size[pRoot] += size[qRoot];
            }
        }
        static int count(int N){
            int count = 0;
            compressedWeightedQuickUnion cwqu = new compressedWeightedQuickUnion(N);
            while (!cwqu.allConnected()){
                count++;
                if (cwqu.connected(StdRandom.uniform(0, N-1), StdRandom.uniform(0, N-1)))continue;
                cwqu.union(StdRandom.uniform(0, N-1), StdRandom.uniform(0, N-1));
            }
            return count;
        }

        public static void main(String[] args) {
            int N = 1000;
            StdOut.printf("% connected, generated %d random connections\n", N, compressedWeightedQuickUnion.count(N));
        }
    }
}
