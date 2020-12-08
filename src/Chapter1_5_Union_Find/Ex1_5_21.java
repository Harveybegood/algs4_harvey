package Chapter1_5_Union_Find;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Erdos-Renyi model. Use your client from exercise 1.5.17 to Test the hypothesis that the number of pairs generated
*   to get one component is ~1/2NlnN
*
* */
public class Ex1_5_21 {
    static class weightedQuickUnion{
        private int[] id;
        private int[] size;
        private int count;
        weightedQuickUnion(int N){
            id = new int[N];
            size = new int[N];
            for (int i = 0; i < N; i++){
                id[i] = i;
                size[i] = 1;
            }
            count = N;
        }
        public int find(int p){
            while (p != id[p]) p = id[p];
            return p;
        }
        public void reset(){
            for (int i = 0; i < id.length; i++){
                id[i] = i;
                size[i] = 1;
            }
            count = id.length;
        }
        public boolean connected(int p, int q){
            return find(p) == find(q);
        }
        public boolean allConnected(){return count == 1;}
        public void union(int p, int q){
            int pROot = find(p);
            int qRoot = find(q);
            if (pROot == qRoot)return;
            if (size[pROot] < size[qRoot]){
                id[pROot] = qRoot;
                size[qRoot] += size[pROot];
            }else {
                id[qRoot] = pROot;
                size[pROot] += size[qRoot];
            }
            count--;
        }
        public static int count(int N, int M){
            int count = 0;
            weightedQuickUnion wqu = new weightedQuickUnion(N);
            for (int i = 0; i < M; i++){
                while (!wqu.allConnected()){
                    wqu.union(StdRandom.uniform(0, N), StdRandom.uniform(0, N));
                    count++;
                }
                wqu.reset();
            }
            return count / M;
        }
    }

    public static void main(String[] args) {
        int N = 1000, M = 1000;
        StdOut.printf("N = %d, numberOfTotalValues: %d, Theorem values: %d\n", N, weightedQuickUnion.count(N, M),
                (int)(0.5 * N * Math.log(N)));
    }
}
